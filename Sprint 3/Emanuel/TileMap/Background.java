package TileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class Background {
	
	private BufferedImage image;
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	
	private double moveScale;
	
	/*
	 * Background sets a background for a specific scene
	 * 
	 * Example: Sky.png for GameState 1 (Menu State)
	 */
	public Background(String s, double ms)
	{
		try 
		{
			// The only way to grab background file is through the Resource folder
			image = ImageIO.read(getClass().getResourceAsStream(s));
			
			moveScale = ms; // Move scale moves the background to give it an overlook affect
		}
		
		catch (Exception e)
		{
			e.printStackTrace(); // prints the error and where they occur
		}
	}
	
	public void setPosition(double x, double y)
	{
		// Keeps the screen where it needs to be (smooth scrolling)
		this.x = (x * moveScale) % GamePanel.WIDTH;
		this.y = (y * moveScale) % GamePanel.WIDTH;
	}
	
	public void setVector(double dx, double dy)
	{
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update()
	{
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics2D g)
	{
		// Keeps the background from ending if the position is far
		g.drawImage(image, (int)x, (int)y, null);
		
		if (x < 0)
			g.drawImage(image, (int)x + GamePanel.WIDTH, (int)y, null);
		
		
		if (x > 0)
			g.drawImage(image, (int)x - GamePanel.WIDTH, (int)y, null);
	}

}
