package Pong;

import java.awt.Graphics;
import java.awt.Rectangle;
import processing.core.PApplet;

public class Ball
{
  private static final int WIDTH = 32, HEIGHT = 32;
  private Pong game;
  private int x, y, x2 = 2, y2 = 2;

  public Ball(Pong game)
  {
    this.game = game;
    x = game.getWidth() / 2;
    y = game.getHeight() / 2;
  }

  public void update()
  {
    x += x2;
    y += y2;
    if (x < 0)
    {
      game.getPanel().scoreGain(1);
      x = game.getWidth() / 2;
      x2 = -x2;
    }
    else if (x > game.getWidth() - WIDTH -7)
    {
      game.getPanel().scoreGain(2);
      x = game.getWidth() / 2;
      x2 = -x2;
    }
    else if (y < 0 || y > game.getHeight() - HEIGHT - 29)
    {
      if (game.getPanel().getScore(1) == 10)
      {
          JOptionPane.showMessageDialog(null, "Player 1 Wins", "Pong", JOptionPane.PLAIN_MESSAGE);
      }
      else if (game.getPanel().getScore(2) == 10)
      {
        JOptionPane.showMessageDialog(null, "Player 2 Wins", "Pong", JOptionPane.PLAIN_MESSAGE);
      }
      CollisionCheck();
    }
  }
  public void CollisionCheck()
  {
    if (game.getPanel().getPlayer(1).getBounds().intersects(getBounds()) || game.getPanel().getPlayer(2).getBounds().intersects(getBounds()))
    {
      x2 = -x2;
    }
  }
  public Rectangle getBounds()
  {
    return new Recatngle(x, y, WIDTH, HEIGHT);
  }
  public void paint(Graphics g)
  {
    g.fillRect(x, y, WIDTH, HEIGHT);
  }
}
