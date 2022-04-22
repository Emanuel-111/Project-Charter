package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;
import Entity.Entity;
import Entity.Player;
import Tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	
	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 48x48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	// WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	// FPS
	int FPS = 60;
	
	// SYSTEM
	TileManager tileManager = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound music = new Sound();
	Sound soundEffect = new Sound();
	public CollisionChecker collisionChecker = new CollisionChecker(this);
	public AssetSetter assetSetter = new AssetSetter(this);
	public UserInterface userInterface = new UserInterface(this);
	public EventHandler eHandler = new EventHandler(this);
	Thread gameThread;
	
	// ENTITY AND OBJECT
	public Player player = new Player(this, keyH);
	public Entity obj[] = new Entity[10];
	public Entity npc[] = new Entity[10];
	public Entity monster[] = new Entity[20];
	ArrayList<Entity> entityList = new ArrayList<>();
	
	// GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int characterState = 4;
	
	public GamePanel()
	{
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setUpGame()
	{
		assetSetter.setObject();
		assetSetter.setNPC();
		assetSetter.setMonster();
		//playMusic(0);
		gameState = titleState;
	}
	
	public void startGameThread()
	{
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() 
	{
		double drawInterval = 1000000000 / FPS; // 0.01666 seconds
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null)
		{
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += currentTime - lastTime;
			lastTime = currentTime;
			
			if(delta >= 1)
			{
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000)
			{
				//System.out.println("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
			
		}
		
	}
	
	public void update()
	{
		if(gameState == playState)
		{
			// PLAYER
			player.update();
			
			// NPC
			for(int i = 0; i < npc.length; i++)
			{
				if(npc[i] != null)
				{
					npc[i].update();
				}
			}
			
			for(int i = 0; i < monster.length; i++)
			{
				if(monster[i] != null)
				{
					if(monster[i].alive == true && monster[i].dying == false)
					{
						monster[i].update();
					}
					
					if(monster[i].alive == false)
					{
						monster[i] = null;

					}
					
				}
				
			}
			
		}
		if(gameState == pauseState)
		{
			// NOTHING
		}
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		// DEBUG
		long drawStart = 0;
		if(keyH.checkDrawTime == true)
		{
			drawStart = System.nanoTime();
		}
		
		// TITLE SCREEN
		if(gameState == titleState)
		{
			userInterface.draw(g2d);
		}
		
		// OTHERS
		else
		{
			// TILE
			tileManager.draw(g2d);
			
			// ADD ENTITIES TO THE LIST
			entityList.add(player);
			
			for(int i = 0; i < npc.length; i++)
			{
				if(npc[i] != null)
				{
					entityList.add(npc[i]);
				}
			}
			
			for(int i = 0; i < obj.length; i++) 
			{
				if(obj[i] != null)
				{
					entityList.add(obj[i]);
				}
			}
			
			for(int i = 0; i < monster.length; i++) 
			{
				if(monster[i] != null)
				{
					entityList.add(monster[i]);
				}
			}
			
			// SORT
			Collections.sort(entityList, new Comparator<Entity>()
			{

				@Override
				public int compare(Entity e1, Entity e2) 
				{
					int result = Integer.compare(e1.worldY, e2.worldY);
					return 0;
				}
				
			});
			
			// DRAW ENTITIES
			for(int i = 0; i < entityList.size(); i++)
			{
				entityList.get(i).draw(g2d);
			}
			
			// EMPTY ENTITY LIST
			entityList.clear();
			
			// USER INTERFACE
			userInterface.draw(g2d);
		}
		
		// DEBUG
		if(keyH.checkDrawTime == true)
		{
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			g2d.setColor(Color.WHITE);
			g2d.drawString("Draw Time: " + passed, 10, 400);
			System.out.println("Draw Time: " + passed);
		}
		
		g2d.dispose();
	}
	
	public void playMusic(int i)
	{
		//music.setFile(i);
		//music.play();
		//music.loop();
	}
	
	public void stopMusic()
	{
		music.stop();
	}
	
	public void playSoundEffect(int i)
	{
		soundEffect.setFile(i);
		soundEffect.play();
	}

}
