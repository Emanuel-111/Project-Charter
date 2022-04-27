package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import TileMap.Background;

import Music.Music;

// First state the game begins on
public class MenuState extends GameState {
	
	private Background bg; // Background image
	
	private int currentChoice = 0; // Choices
	private String[] options = {  //The options the user has to choose
		"Start", "Help", "Quit"	
	}; 
	
	private Color titleColor; // The color of the title
	private Font titleFont; // The font of the title
	
	private Font font; // Font for the options
	
	private Music menuTheme = new Music(); // Music for the menu state

	// The Menu State will
	public MenuState(GameStateManager gsm)
	{
		this.gsm = gsm;
		
		// Each state must be on with a try and catch expression
		
		// This is so that the code can catch any errors and 
		// report it to the user without crashing
		try
		{
			init();
			
			// This will be how you set a background and which state it will be in
			bg = new Background("/Backgrounds/Landscape.png", 1);
			bg.setVector(-0.1, 0);
			
			// This will set the color and font of the title
			// While displayed in RGB, it can also be done as (Color.BLACK)
			titleColor = new Color(128, 0, 128);  
			
			// A font needs a Font, a style of font, and the size in pts
			titleFont = new Font("Century Gothic", Font.PLAIN, 25);
			
			// This will set the font of the choices
			font = new Font("Arial", Font.PLAIN, 12);
		}
		
		catch(Exception e)
		{
			e.printStackTrace(); // Prints the errors where they occur in the menu state
		}
	}
	
	/*
	 * Initalize
	 */
	public void init()
	{
		// Music must be initialized (I don't know why but it functions as long as it is in this function)
		
		// This plays the menu theme music (Super Mario Bros is the placeholder)
		// To play music, you need to make sure you find the source
		menuTheme.playSong("Resource/Music/Super Mario Bros. Theme Song.wav");
		
		// For more information, head to the package "Music" and 
	}
	
	// Updates the background
	public void update()
	{
		bg.update();
	}
	
	// Places everything in the menu state for the user to see
	// You must always remember to draw otherwise they will not appear
	public void draw(Graphics2D g)
	{
		// Draw Backgrounds
		bg.draw(g);
		
		// Draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("A D.S Simulator", 10, 60);
		
		//Draw Menu Options
		g.setFont(font);
		for (int i = 0; i < options.length; i++)
		{
			if(i == currentChoice)
				g.setColor(Color.YELLOW);
			
			else
				g.setColor(Color.BLUE);
			
			g.drawString(options[i], 95, 130 + i * 15);
		}
	}
	
	// Select allows the player to make the choice they 
	// want by "hovering" over the choice with the up and down keys
	private void select()
	{
		if (currentChoice == 0)
		{
			menuTheme.stopSong();
			gsm.setState(GameStateManager.LEVEL1STATE);
		}
		
		if (currentChoice == 1)
		{
			//Help Screen
			menuTheme.stopSong();
			gsm.setState(GameStateManager.TUTORIALSTATE);
			// We'll stick with a simple control layout
		}
			
		if (currentChoice == 2)
		{
			System.exit(0);
		}
	}
	
	
	public void keyPressed(int k)
	{
		if (k == KeyEvent.VK_ENTER)
			select();
		
		if (k == KeyEvent.VK_UP || k == KeyEvent.VK_W)
		{
			currentChoice--;
			if (currentChoice == -1)
				currentChoice = options.length - 1;
		}
		
		if (k == KeyEvent.VK_DOWN || k == KeyEvent.VK_S)
		{
			currentChoice++;
			if (currentChoice == options.length)
				currentChoice = 0;
		}
	}

	// Since we don't have any booleans to stop
	// the action of pressing any key, we don't
	// need to worry about it in this state
	public void keyReleased(int k) 
	{
		
	}
	
	// When done looking, head to Level1State
}
