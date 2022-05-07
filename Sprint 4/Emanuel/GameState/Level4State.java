package GameState;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Entity.NonPlayableCharacter;
import Entity.Player;
import Main.GamePanel;
import Music.Music;
import SFX.SFX;
import TileMap.Background;
import TileMap.TileMap;

public class Level4State extends GameState {

private TileMap tileMap; // The tilemap
	
	private Background bg; // The background
	
	private Player player; // The player
	
	private NonPlayableCharacter kid1NPC;
	
	private Music Level3Music = new Music(); // Music for level 1 state
	
	private SFX sfx; // Sound effects
	
	public Level4State(GameStateManager gsm)
	{
		this.gsm = gsm;
		
		try
		{
			init();
			Level3Music.stopSong(); // I need this since the music wont play without it
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
		tileMap.loadTiles("/Tilesets/line.gif");
		tileMap.loadMap("/Maps/FinalRoom.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		// Initializes Music
		Level3Music.playSong("Resource/Music/final.wav");
				
		// Initializes background
		bg = new Background("/Backgrounds/finalroom.png", 0.1);

		// Adds the player
		player = new Player(tileMap, 16, 29, "/Sprites/shadowMainCharacter.png");
		player.setPosition(100,284);
		

		//Adds the NPC
		kid1NPC = new NonPlayableCharacter(tileMap, 16, 30, "/Sprites/laughingkid1.png");
		kid1NPC.setPosition(300, 284);
	}

	@Override
	public void update() {
		
		player.update();
		
		System.out.println("X");
		System.out.println((int)player.getPositionX());
		
		System.out.println("\nY");
		System.out.println((int)player.getPositionY());
		
		tileMap.setPosition(
				GamePanel.WIDTH / 2 - player.getX(),
				GamePanel.HEIGHT / 2 - player.getY()
			);
		
		kid1NPC.update();
		
	}

	@Override
	public void draw(Graphics2D g) {
		
		// 1st -> Draw Background 
		bg.draw(g);
		
		// 2nd - > Draw TileMap
		tileMap.draw(g);
		
		// 3rd -> Draw player
		player.draw(g);
		
		//4th -> Draw npc's
		kid1NPC.draw(g);
		
		// Dialogue for Laughing Kid 1
		if((int)player.getPositionX() >= (int)kid1NPC.getPositionX() - 20 &&
				(int)player.getPositionX() <= (int)kid1NPC.getPositionX() + 20 )
		{
			if(player.getTalking())
			{
				gsm.setState(GameStateManager.FINALCUTSCENE);
			}
		}
	}

	@Override
	public void keyPressed(int k) 
	{
		
		if(k == KeyEvent.VK_A)
		{
			player.setLeft(true);
		}
		if(k == KeyEvent.VK_D) 
		{
			player.setRight(true);
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
