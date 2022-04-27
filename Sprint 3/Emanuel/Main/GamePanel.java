package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import GameState.GameStateManager;

/*
 * GamePanel 
 * 
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener {

	// Dimensions of the JFrame (GUI)
	public static final int WIDTH = 220; // Might expand it: Orginal 300
	public static final int HEIGHT = 200; // Might expand it: Original 200
	public static final int SCALE = 4;
	
	
	// Game Thread
	private Thread thread;
	private boolean running; // Checking if the game is running
	private int FPS = 60; // Frames per second (Changing this will only make the game feel faster)
	private long targetTime = 1000/FPS;
	
	// Image
	BufferedImage image; // An image
	private Graphics2D g; // Graphics
	
	// Game State Manager
	private GameStateManager gsm; // Manages the game in many ways detailed later
	
	// Sets parameters for the screen 
	public GamePanel()
	{
		super();
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify()
	{
		super.addNotify();
		if (thread == null)
		{
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
			
		}
	}
	
	// Initializes GamePanel 
	private void init()
	{
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		g = (Graphics2D) image.getGraphics();
		
		running = true;
		
		gsm = new GameStateManager();
	}

	// Runs the time
	public void run()
	{
		init();
		
		long start;
		long elapsed;
		long wait;
		
		
		//game loop
		while(running)
		{
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed / 1000000;
			
			try 
			{
				Thread.sleep(wait);
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}
	}	
		
	private void update()
	{
		gsm.update();
	}
	
	private void draw()
	{
		gsm.draw(g);
	}
	
	private void drawToScreen()
	{
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0,0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g2.dispose();
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

	// Checks for when keys are pressed
	// (No need to dwell on this)
	@Override
	public void keyPressed(KeyEvent e) 
	{
		gsm.keyPressed(e.getKeyCode());
	}

	// Checks for when keys are released
	// (No need to dwell on this)
	@Override
	public void keyReleased(KeyEvent e) 
	{
		gsm.keyReleased(e.getKeyCode());
	}

	// When done looking at GamePanel, head to the package "GameState"

}

