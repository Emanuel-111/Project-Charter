package Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {
	
	private double x;
	private double y;
	
	private double velX = 0;
	private double velY = 0;
	
	private BufferedImage player;
	
	public Player(double x, double y, Game game)
	{
		this.x = x;
		this.y = y;
		
		SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		
		player = ss.grabImage(1, 1, 32, 32);
	}
	
	public void tick()
	{
		x+=velX;
		y+=velY;
		
		if(x <= 0)
		{
			x = 0;
		}
		if(x >= 640 - 30)
		{
			x = 640 - 30;
		}
		if(y <= 0)
		{
			y = 0;
		}
		if(y >= 480 - 40)
		{
			y = 480 - 40;
		}
	}
	
	public void render(Graphics g)
	{
		g.drawImage(player, (int)x, (int)y, null);
	}

	public double getX() 
	{
		return x;
	}

	public double getY() 
	{
		return y;
	}

	public void setX(double x) 
	{
		this.x = x;
	}
	
	public void setY(double y) 
	{
		this.y = y;
	}

	public void setVelX(double velX) 
	{
		this.velX = velX;
	}

	public void setVelY(double velY) 
	{
		this.velY = velY;
	}
	
}
