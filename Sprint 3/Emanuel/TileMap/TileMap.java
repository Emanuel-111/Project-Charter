package TileMap;

import java.awt.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;

import Main.GamePanel;

public class TileMap {
	
	// Position
	private double x; // Position X on screen
	private double y; // Position Y on screen
	
	// bounds
	private int xmin; // X minimum bound
	private int ymin; // Y minimum bound
	private int xmax; // X maximum bound
	private int ymax; // Y maximum bound
	
	private double tween; // Smoothing effect
	
	//map
	private int[][] map; // A double array for the map
	private int tileSize; // Size of a tile
	private int numRows; // Number of rows
	private int numCols; // Number of columns
	private int width; // Width of the tile
	private int height; // Height of the tile
	
	//tile set
	private BufferedImage tileset; // A set of tiles
	private int numTilesAcross; // Number of tiles across 
	private Tile[][] tiles; // A double array of tiles
	
	//drawing
	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;
	
	public TileMap(int tileSize)
	{
		this.tileSize = tileSize;
		numRowsToDraw = GamePanel.HEIGHT / tileSize + 2;
		numColsToDraw = GamePanel.HEIGHT / tileSize + 2;
		tween = 1.0;
	}
	
	public void loadTiles(String s)
	{
		
		try 
		{
			tileset = ImageIO.read(getClass().getResourceAsStream(s));
			
			numTilesAcross = tileset.getWidth() / tileSize;
			tiles = new Tile[2][numTilesAcross];
			
			BufferedImage subimage;
			for(int col = 0; col < numTilesAcross; col++)
			{
				subimage = tileset.getSubimage(col * tileSize, 0, tileSize, tileSize);
				
				tiles[0][col] = new Tile(subimage, Tile.NORMAL);
				
				subimage = tileset.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
				
				tiles[1][col] = new Tile(subimage, Tile.BLOCKED);
			}
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	public void loadMap(String s)
	{
		try
		{
			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			
			map = new int [numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;
			
			xmin = GamePanel.WIDTH - width;
			xmax = 0;
			ymin = GamePanel.HEIGHT - height;
			ymax = 0;
			
			String delims = "\\s+";
			for(int row = 0; row < numRows; row++)
			{
				String line = br.readLine();
				String[] tokens = line.split(delims);
				
				for(int col = 0; col < numCols; col++)
				{
					map[row][col] = Integer.parseInt(tokens[col]);
				}
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public int getTileSize()
	{
		return tileSize;
	}
	
	public int getX()
	{
		return (int)x;
	}
	
	public int getY()
	{
		return (int)y;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	
	public void setTween(double d)
	{
		tween = d;
	}
	public int getType(int row, int col)
	{
		int rc = map[row][col];
		int r = rc / numTilesAcross;
		int c = rc % numTilesAcross;
		
		return tiles[r][c].getType();
		
	}
	
	public void setPosition(double x, double y)
	{
		this.x += (x - this.x) * tween;
		this.y += (y - this.y) * tween;
		
		fixBounds();
		
		colOffset = (int) - this.x / tileSize;
		rowOffset = (int) - this.y / tileSize;
	}
	
	public void fixBounds()
	{
		if(x < xmin) x = xmin;
		if(y < ymin) y = ymin;
		if(x > xmax) x = xmax;
		if(y > ymax) y = ymax;
	}
	
	public void draw(Graphics2D g)
	{
		for(int row = rowOffset; row < rowOffset + numRowsToDraw; row++)
		{
			if (row >= numRows)
				break;
			
			for(int col = colOffset; col < colOffset + numColsToDraw; col++)
			{
				if (col >= numCols)
					break;
				
				if (map[row][col] == 0)
					continue;
				
				int rc = map[row][col];
				int r = rc / numTilesAcross;
				int c = rc % numTilesAcross;
				
				g.drawImage(tiles[r][c].getImage(), (int)x + col * tileSize, (int)y + row * tileSize, null);
			
			}
		}
	}
}
