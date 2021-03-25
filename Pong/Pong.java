package Pong;

import java.awt.Color;
import processing.core.PApplet;

public class Pong extends Applet
{
	private final static int WIDTH = 640, HEIGHT = 480;
	private Screen panel;

	public Pong()
	{
		setSize(WIDTH,HEIGHT);
		setTitle("Pong");
		setBackground(255,255,255);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel = new Screen(this);
		add(panel);
	}
	public Screen getPanel()
	{
		return panel;
	}
	public static void main(String[] args)
	{
		PApplet.main("Pong"); //sets up processing library
	}
}
