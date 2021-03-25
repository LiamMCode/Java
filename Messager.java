package fBookMessager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Messager extends Application
{
	public static Pane canvas;
	
	@Override
	public void start(final Stage primaryStage) //TODO: make this loop so that multiple messages can be sent and check if it can communicate with other PC's
	{
		canvas = new Pane(); //setting up canvas 
		final Scene scene = new Scene(canvas,800,600);
		
		primaryStage.setTitle("Messenger");
		primaryStage.setScene(scene);
		primaryStage.show();

		TextField textField = new TextField(); //text box 
		HBox hb = new HBox();
		hb.getChildren().addAll(textField);//adding the text box to hb
		hb.setSpacing(10);
		hb.relocate(0, 550);
		Button submit = new Button("Submit");//creating a button
		GridPane.setConstraints(submit, 1, 0);
		hb.getChildren().add(submit);//adding button to the hb
		canvas.getChildren().addAll(hb); //adding all hb elements to the canvas 
		
		submit.setOnAction((event) -> { //button click event
				System.out.println("Message sent");
				
				try //sending message from text box
				{
					Socket socket;
					socket = new Socket("10.4.193.80",1124);
					PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
					out.println(textField.getText());
					socket.close();
				} 
				catch (UnknownHostException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				new Thread(() -> //at the same time checking if theres a message to recieve
				 {
						try 
						{
							ServerSocket serverSocket;
							serverSocket = new ServerSocket (1124);
							Socket clientSocket = serverSocket.accept();
							BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
							System.out.println("Recieved: " + in.readLine());
							clientSocket.close();
							serverSocket.close();
						} 
						catch (IOException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}).start();
		}
		);
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}