package TileMap;

import java.awt.image.BufferedImage;

public class Tile {

	private BufferedImage image;
	private int type;
	
	// tile types
	public static final int NORMAL = 0;
	public static final int BLOCKED = 1;
	
	// A Tile contains an image and a type
	public Tile(BufferedImage image, int type)
	{
		this.image = image;
		this.type = type;
	}
	
	// Grabs an image
	public BufferedImage getImage()
	{
		return image;
	}
	
	// Grabs the type
	public int getType()
	{
		return type;
	}
	
}
