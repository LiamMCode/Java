package noughts;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Ellipse;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Platform;

public class Noughts extends Application 
{
  private char turn = 'X'; //starts the game on the X turn
  private Cell[][] cell =  new Cell[3][3];
  private Label gameStatus = new Label("X's turn to play"); //label displayed under the grid to tell user hows turn it is
  
  @Override 
  public void start(Stage primaryStage) 
  {    
     Button x = new Button("X"); //creating buttons for the 2 player game 
     Button o = new Button("O");
     Button exit = new Button("Exit");
     Button play = new Button("Play Again?");
     x.setStyle("-fx-font-size: 15pt;"); //sets the font size for the buttons at 15pt
     o.setStyle("-fx-font-size: 15pt;");
     exit.setStyle("-fx-font-size: 15pt;");
     play.setStyle("-fx-font-size: 15pt;");
     
     exit.setOnAction(new EventHandler<ActionEvent>() //when the exit button is pressed
     {
         @Override 
         public void handle(ActionEvent e) 
         {
             Platform.exit(); //exits the game 
         }
     });
     
    GridPane pane = new GridPane(); //creates a new GridPane
    for (int i = 0; i < 3; i++) // for loop to create the 3*3 grid //3 columns
    {
      for (int j = 0; j < 3; j++) //3 rows
      {
        pane.add(cell[i][j] = new Cell(), j, i); //creates a new cell in the GridPane 
      }
    }
    BorderPane borderPane = new BorderPane(); //creates a new boarder pane in which
    borderPane.setCenter(pane);//the centre is the GridPane
    borderPane.setBottom(gameStatus);//the bottom is the label of game status 
    
    HBox buttonBar = new HBox(); //creates a horizontal box at the top of the scene
    buttonBar.setSpacing(87.0);//sets the spacing in the hbox
    buttonBar.getChildren().addAll(x, o, play,exit); //adds the 4 buttons to the hbox

    borderPane.setTop(buttonBar);
  
    Scene scene = new Scene(borderPane, 550, 470); //sets the scene size
    primaryStage.setTitle("Noughts & Crosses"); //sets the title of the windoe
    primaryStage.setScene(scene); //adds the scene to the window
    primaryStage.show();//shows the window
  }

  //checkes if the board is full
  public boolean boardFull() 
  {
    for (int i = 0; i < 3; i++)
    {
      for (int j = 0; j < 3; j++)
      {
        if (cell[i][j].getToken() == ' ')
        {
          return false;
        }
      }
    }
    return true;
  }

  //checks if a player has won using tokens
  public boolean won(char token) 
  {
    for (int i = 0; i < 3; i++) //checks the vertical rows to see if 3 are the same
      if (cell[i][0].getToken() == token && cell[i][1].getToken() == token && cell[i][2].getToken() == token) 
      {
        return true; 
      }

    for (int j = 0; j < 3; j++) //checks the horizontal rows to see if 3 are the same
      if (cell[0][j].getToken() ==  token && cell[1][j].getToken() == token && cell[2][j].getToken() == token) 
      {
        return true;
      }

    if (cell[0][0].getToken() == token && cell[1][1].getToken() == token && cell[2][2].getToken() == token) //next few if statements check all possible diagonals 
    {
      return true;
    }

    if (cell[0][2].getToken() == token && cell[1][1].getToken() == token && cell[2][0].getToken() == token) 
    {
      return true;
    }

    return false; //if none of these match then no-one has won the game
  }
  
  public class Cell extends Pane 
  {  
    private char token = ' ';  
    public Cell() 
    {
      setStyle("-fx-border-color: black"); 
      this.setPrefSize(2000, 2000);
      this.setOnMouseClicked(e -> handleMouseClick());
    }
    public char getToken() //returns token 
    {
      return token;
    }
    public void setToken(char c) //sets new token 
    {
      token = c;
      if (token == 'X') //draws the X in the button that has been selected 
      {
        Line line1 = new Line(10, 10, this.getWidth() - 10, this.getHeight() - 10);
        line1.endXProperty().bind(this.widthProperty().subtract(10));
        line1.endYProperty().bind(this.heightProperty().subtract(10));
        Line line2 = new Line(10, this.getHeight() - 10, 
        this.getWidth() - 10, 10);
        line2.startYProperty().bind(this.heightProperty().subtract(10));
        line2.endXProperty().bind(this.widthProperty().subtract(10));
        this.getChildren().addAll(line1, line2); // Add the lines to the pane
      }
      
      else if (token == 'O') //draws the 0 in the button that has been selected 
      {
        Ellipse ellipse = new Ellipse(this.getWidth() / 2, 
        this.getHeight() / 2, this.getWidth() / 2 - 10, 
        this.getHeight() / 2 - 10);
        ellipse.centerXProperty().bind(this.widthProperty().divide(2));
        ellipse.centerYProperty().bind(this.heightProperty().divide(2));
        ellipse.radiusXProperty().bind(this.widthProperty().divide(2).subtract(10));        
        ellipse.radiusYProperty().bind(this.heightProperty().divide(2).subtract(10));   
        ellipse.setStroke(Color.BLACK);
        ellipse.setFill(Color.WHITE);  
        getChildren().add(ellipse); // Add the ellipse to the pane
      }
    }

    private void handleMouseClick() 
    {
      // If cell is empty and game is not over
      if (token == ' ' && turn != ' ') 
      {
        setToken(turn); // Set token in the cell
        if (won(turn)) //if someone has won set label to display who has won
        {
        	gameStatus.setText(turn + " won! The game is over. Would you like to play again?");
        	turn = ' '; // Game is over
        }
        else if (boardFull()) //ends game if boards full and no-one has won
        {
        	gameStatus.setText("Draw! The game is over. Would you like to play again?");
        	turn = ' '; // Game is over
        }
        else 
        {
        	// Change the turn
        	turn = (turn == 'X') ? 'O' : 'X';
        	// Display whose turn
        	gameStatus.setText(turn + "'s turn.");
        }
      }
    }
  }
  public static void main(String[] args) 
  {
    launch(args);
  }
}