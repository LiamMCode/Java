package Pong;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Paddle
{
  private static final int WIDTH = 10, HEIGHT = 100;
  private Pong game;
  private int up, down, x, y, y2;

  public Paddle(Pong game, int up, int down, int x)
  {
    this.game = game;
    this.x = x;
    y = game.getHeight() / 2;
    this.up = up;
    this.down = down;
  }
  public void update()
  {
    if (y > 0 && y < game.getHeight() - HEIGHT -29)
    {
      y += y2;
    }
    else if (y == 0)
    {
      y++;
    }
    else if (y == game.getHeight() - HEIGHT - 29)
    {
      y--;
    }
  }
  public void press(int keyCode)
  {
    if (keyCode == up)
    {
      y2 = -1;
    }
    else if (keyCode == down)
    {
      y2 = 1;
    }
  }
  public void release(int keyCode)
  {
    if (keyCode == up || keyCode == down)
    {
      y2 = 0;
    }
  }
  public Rectangle getBounds()
  {
    return new Rectangle(x, y, WIDTH, HEIGHT);
  }
  public void paint(Graphics g)
  {
    g.fillRect(x, y, WIDTH, HEIGHT);
  }
}
