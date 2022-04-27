package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Entity.NonPlayableCharacter;
import Entity.Player;
import Main.GamePanel;
import Music.Music;
import SFX.SFX;
import TileMap.*;

public class Level1State extends GameState {
	
	private TileMap tileMap; // The tilemap
	
	private Background bg; // The background
	
	private Player player; // The player
	
	private NonPlayableCharacter npc; // NPC
	
	private Music Level1Music = new Music(); // Music for level 1 state
	
	private SFX sfx;
	
	private String[] dialogue = {"Hello, welcome to the game!"};
	
	private Font font;
	
	
	public Level1State(GameStateManager gsm)
	{
		this.gsm = gsm;
		// Tries to use the initialize function and "stop the music"
		try
		{
			init();
			Level1Music.stopSong(); // I need this or the music wont play
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void init() 
	{
		// Load in tiles and map
		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/grasstileset.gif");
		tileMap.loadMap("/Maps/testmap.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		// Initializes Music
		//Level1Music.playSong("Resource/Music/Athletic - SMW.wav");
		
		// Initializes background
		bg = new Background("/Backgrounds/mountain.gif", 0.1);
		
		// Adds the player
		player = new Player(tileMap);
		player.setPosition(100,100);
		
		//Adds the NPC
		npc = new NonPlayableCharacter(tileMap);
		npc.setPosition(150, 194);
		
		font = new Font("Arial", 0, 15);
	}

	// Each time an action occurs the player must be updated
	// and the tileMap in relation to the player
	@Override
	public void update() 
	{
		player.update();
		tileMap.setPosition(
				GamePanel.WIDTH / 2 - player.getX(),
				GamePanel.HEIGHT / 2 - player.getY()
			);
		
		npc.update();
		
	}

	// Draws the player, map, and player
	@Override
	public void draw(Graphics2D g) 
	{	
		//Draw Background
		bg.draw(g);
		
		// Draw TileMap
		tileMap.draw(g);
		
		//Draw player
		player.draw(g);
		
		//Draw NPC
		npc.draw(g);
		
		//Draw dialogue when needed
		if((int)player.getPositionX() >= (int)npc.getPositionX() - 20 &&
				(int)player.getPositionX() <= (int)npc.getPositionX() + 20 )
		{
			if (player.getTalking())
			{
				g.setFont(font);
				g.drawString(dialogue[0], 10, 60);
			}
		}
	}
	@Override
	public void keyPressed(int k) 
	{
		if(k == KeyEvent.VK_A)
		{
			player.setLeft(true);
			sfx.playSFX("Resource/Sound_Effects/Walking.wav");
		}
		if(k == KeyEvent.VK_D) 
		{
			player.setRight(true);
			sfx.playSFX("Resource/Sound_Effects/Walking.wav");
		}
			
		
		if(k == KeyEvent.VK_UP) 
		{
			player.setUp(true);
		}
		
		if(k == KeyEvent.VK_DOWN) player.setDown(true);
		
		if(k == KeyEvent.VK_W) player.setJumping(true);
		
		if(k == KeyEvent.VK_Q) player.setTalking(true);
	}
	
	@Override
	public void keyReleased(int k) 
	{
		if(k == KeyEvent.VK_A) player.setLeft(false);
		if(k == KeyEvent.VK_D) player.setRight(false);
		if(k == KeyEvent.VK_UP) player.setUp(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false);
		if(k == KeyEvent.VK_W) player.setJumping(false);
		if(k == KeyEvent.VK_Q) player.setTalking(false);
	}

}
