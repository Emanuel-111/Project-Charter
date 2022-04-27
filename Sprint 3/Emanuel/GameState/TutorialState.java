package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Music.Music;
import TileMap.Background;

public class TutorialState extends GameState {
	
	private Background tutorialBg; // Background
	
	private int currentChoice = 0; // Choice
	
	private String[] backOption = {"Back"}; // Show-able option
	
	private Font font;// Font of the back Button
	
	private Music tutorialTheme = new Music(); // Music for the state

	public TutorialState(GameStateManager gsm)
	{
		this.gsm = gsm;
		
		try
		{
			init();
			
			tutorialBg = new Background("/Backgrounds/Movement Keys-2.png", 1);
			tutorialBg.setVector(-0, 0);
			
			font = new Font("Arail", Font.PLAIN, 12);
			
			tutorialTheme.stopSong();
			
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

	@Override
	public void init() 
	{
		tutorialTheme.playSong("Resource/Music/How to Play - SSBM.wav");
	}

	@Override
	public void update() 
	{
		tutorialBg.update();
	}

	@Override
	public void draw(Graphics2D g) 
	{
		// Draw background
		tutorialBg.draw(g);
		
		
		g.setFont(font);
		for (int i = 0; i < backOption.length; i++)
		{
			if(i == currentChoice)
				g.setColor(Color.BLACK);
			
			else
				g.setColor(Color.RED);
			
			g.drawString(backOption[i], 50, 50);
		}
		
	}
	
	private void select()
	{
		if (currentChoice == 0)
		{
			tutorialTheme.stopSong();
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}

	@Override
	public void keyPressed(int k) 
	{
		if (k == KeyEvent.VK_ENTER)
			select();
	}

	@Override
	public void keyReleased(int k) 
	{
		
	}

}
