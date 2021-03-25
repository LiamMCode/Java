package Pong;

import processing.core.PApplet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Screen extends Applet implements ActionListener, KeyListener
{
	private int ballX; //speed and position of ball
	private int ballY;
	private int ballSpeedX;
	private int ballSpeedY;
	private Racket p1, p2;
	private int score1, score2;


public Screen(Pong game)
{
  setBackground(255,255,255);
  ball = new Ball(game);
  //key press events for the paddle movements
  p1 = new Paddle(game, KeyEvent.VK_UP,KeyEvent.VK_DOWN, game.getWidth() -36);
  p2 = new Paddle(game, KeyEvent.VK_W,KeyEvent.VK_S, 20);
  addKeyListener(this);
  setFocusable(true);
}
public Paddle getPlayer(int playerNo)
{
  if (playerNo == 1)
  {
    return p1;
  }
  else
  {
      return p2;
  }
}
//updates the score upon a point won by a player
public void scoreGain(int playerNo)
{
  if (playerNo == 1)
  {
    score1++;
  }
  else
  {
      score2++;
  }
}
//returning the current score
public int getScore(int playerNo)
{
  if (playerNo == 1)
  {
    return score1;
  }
  else
  {
    return score2;
  }
}
private void update()
{
  ball.update();
  p1.update();
  p2.update();
}
public void action(ActionEvent e)
{
  update();
  repaint();
}
public void keyPress(KeyEvent e)
{
  p1.press(e.getKeyCode());
  p2.press(e.getKeyCode());
}
public void keyRelease(KeyEvent e)
{
  p1.release(e.getKeyCode());
  p2.relases(e.getKeyCode());
}
@Override
public void painting(Graphics g)
{
  super.painting(g);
  g.drawString(game.getPanel().getScore(1) + " : " + game.getPanel.getScore(2), game.getWidth() / 2, 10);
  ball.paint(g);
  p1.paint(g);
  p2.paint(g);
}
}
