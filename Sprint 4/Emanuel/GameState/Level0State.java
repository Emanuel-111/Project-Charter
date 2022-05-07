package GameState;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Entity.NonPlayableCharacter;
import Entity.Player;
import Main.GamePanel;
import Music.Music;
import SFX.SFX;
import TileMap.Background;
import TileMap.TileMap;


/* Level 0 State begins begins with the player waking up in 
 * the middle of the night walking around trying to figure out
 * what to do. He keeps walking forward until he sees a glitched
 * object. Once he passes through, the game state changes
 */
public class Level0State extends GameState {
	
	private TileMap tileMap; // The tilemap
	
	private Background bg; // The background
	
	private Player player; // The player
	
	private NonPlayableCharacter portalNPC; // A portal that will warp the player to a next level
	
	public Level0State(GameStateManager gsm)
	{
		this.gsm = gsm;
		
		try
		{
			init();
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
		tileMap.loadMap("/Maps/FlatMap2.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
				
		// Initializes background
		bg = new Background("/Backgrounds/daylevel.gif", 0.1);

		// Adds the player
		player = new Player(tileMap, 16, 29, "/Sprites/shadowMainCharacter.png");
		player.setPosition(100,286);

		//Adds the NPC
		portalNPC = new NonPlayableCharacter(tileMap, 16, 30, "/Sprites/portal.png");
		portalNPC.setPosition(300, 286);
		
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
		
		portalNPC.update();
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
		portalNPC.draw(g);
		
		if((int)player.getPositionX() >= (int)portalNPC.getPositionX() - 20 &&
				(int)player.getPositionX() <= (int)portalNPC.getPositionX() + 20 )
		{
			gsm.setState(GameStateManager.LEVEL1STATE);
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
