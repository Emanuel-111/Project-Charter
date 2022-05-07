package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Music.Music;
import TileMap.Background;

public class SecondStoryBook extends GameState {

	private Background bg;
	private int dialogueCounter = 0;
	
	private int currentChoice = 0; // Choices
	
	private String[] bffPlayingDialogue = {  //The options the user has to choose
			"Ava invited me to her house to play.",
			"We played parent with some of her dolls.",
			"I was the daddy and she was the mommy",
			"After a while I had jokingly said",
			"Why don’t I be the mommy this time.”",
			"Ava didn’t laugh but instead encouraged me to try.",
			"She put on her best gruff voice to sound like a man while I",
			"spoke softly.” “Something kind of clicked inside me, ",
			"I don’t know what but something just felt right."
	}; 
	
	private String[] dressDialogue = {
			"Ava had just gotten a new dress and....",
			"I don’t know what I was thinking but I asked her...",
			"If I could try it on.",
			"I don’t know why or how it was just after",
			"playing the mommy, I just wanted to try it on.",
			"Ava said yes and enthusiastically brought it out ",
			"of the closet and let me put it on."
			
	};
	
private String[] redDressDialogue = {
			
			"It was nothing like the T-Shirt ",
			" and cargo pants I usually wear.",
			"It felt “flowy” and smooth.",
			"I walked around for a bit and did poses.",
			"I didn’t have the words to describe it but ",
			"it was as if two puzzle pieces had connected",
			"together to form a picture in my brain.",
			"I was scared, very scared but it ",
			"was also exciting and new.",
			"Just then Ava’s brother and his ",
			"friends came into the room because Ava",
			"had borrowed one of his yo-yos"
	};
	
	private String[] eyeDialogue = {
			"At first they looked around the room for the",
			"toy but after a split second something clicked",
			"in their brains. What they thought was ",
		    "two girls playing together.",
			"Was Ava and me."
	};
	
	private String[] eyeTwoDialogue = {
		
			"Their eyes stared blankly at me as if",
			"trying to process some kind of complex ",
			"math equation. It was as if time had",
			"stopped and the room had gone silent."
			
	};
	
	private String[] eyeThreeDialogue = {
			
			"Just then a burst of laughter echoed ",
			"throughout the room.” “Their eyes burned ",
			"right through me as though I was naked.",
			"I...I.....Ava tried to stop them but even ",
			"as they left the room their eyes.",
			"And the laughter just stayed etched into my brain.",
			"I didn’t even change, I just ran.",
			"I ran home as fast as I could.",
			"I want to disapear.",
			"I want to disapear.",
			"I want to disapear.",
			"I want to disapear.",
			"I want to disapear.",
			"I want to &#@!."
			
	};
	
	private Color titleColor; // The color of the title
	private Font titleFont; // The font of the title
	
	private Font font; // Font for the options
	
	public SecondStoryBook(GameStateManager gsm)
	{
		this.gsm = gsm;
		
		try
		{
			init();
			
			// This will be how you set a background and which state it will be in
			bg = new Background("/Backgrounds/bbfPlaying.png", 1);
			
			// This will set the color and font of the title
			// While displayed in RGB, it can also be done as (Color.BLACK)
			titleColor = new Color(128, 0, 128);  
			
			// A font needs a Font, a style of font, and the size in pts
			titleFont = new Font("Century Gothic", Font.PLAIN, 21);
			
			// This will set the font of the choices
			font = new Font("Arial", Font.PLAIN, 10);
		}
		
		catch(Exception e)
		{
			e.printStackTrace(); // Prints the errors where they occur in the menu state
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		//secondStoryBookTheme.playSong("Resource/Music/storybook.wav");
	}

	@Override
	public void update() {
		bg.update();
	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);

		// Dialogue presented to the player
		switch(dialogueCounter)
		{
		case 0:
			g.setFont(font);
			for (int i = 0; i < bffPlayingDialogue.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.YELLOW);

				else
					g.setColor(Color.BLUE);

				g.drawString(bffPlayingDialogue[i], 0, 80 + i * 15);
			}
			break;
			
		case 1:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < dressDialogue.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.YELLOW);

				else
					g.setColor(Color.BLUE);

				g.drawString(dressDialogue[i], 0, 80 + i * 15);
			}
			break;
			
		case 2:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < redDressDialogue.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.YELLOW);

				else
					g.setColor(Color.BLUE);

				g.drawString(redDressDialogue[i], 0, 80 + i * 15);
			}
			break;
			
		case 3:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < eyeDialogue.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.YELLOW);

				else
					g.setColor(Color.BLUE);

				g.drawString(eyeDialogue[i], 0, 100 + i * 15);
			}
			break;
			
		case 4:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < eyeTwoDialogue.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.YELLOW);

				else
					g.setColor(Color.BLUE);

				g.drawString(eyeTwoDialogue[i], 0, 100 + i * 15);
			}
			break;
			
		case 5:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < eyeThreeDialogue.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.YELLOW);

				else
					g.setColor(Color.BLUE);

				g.drawString(eyeThreeDialogue[i], 0, 10 + i * 15);
			}
			break;
			
		case 6:
			gsm.setState(GameStateManager.LEVEL3STATE);
		}
		
	}

	private void select()
	{
		if (currentChoice == 0)
		{
			dialogueCounter++;
			
			if (dialogueCounter == 1)
				bg = new Background("/Backgrounds/dress.png", 1);
			
			if (dialogueCounter == 2)
				bg = new Background("/Backgrounds/eye.png", 1);
			
			if(dialogueCounter == 3)
				bg = new Background("/Backgrounds/eye2.png", 1);
			
			if(dialogueCounter == 4)
				bg = new Background("/Backgrounds/eye3.png", 1);
			
			if(dialogueCounter == 5)
				bg = new Background("/Backgrounds/red dress.png", 1);
			
			if(dialogueCounter == 6)
				gsm.setState(GameStateManager.LEVEL3STATE);
		}
		
		if (currentChoice == 1)
		{
			dialogueCounter++;
			
			if (dialogueCounter == 1)
				bg = new Background("/Backgrounds/dress.png", 1);
			
			if (dialogueCounter == 2)
				bg = new Background("/Backgrounds/eye.png", 1);
			
			if(dialogueCounter == 3)
				bg = new Background("/Backgrounds/eye2.png", 1);
			
			if(dialogueCounter == 4)
				bg = new Background("/Backgrounds/eye3.png", 1);
			
			if(dialogueCounter == 5)
				bg = new Background("/Backgrounds/red dress.png", 1);
			
			if(dialogueCounter == 6)
				gsm.setState(GameStateManager.LEVEL3STATE);
			
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
				currentChoice = bffPlayingDialogue.length - 1;
		}
		
		if (k == KeyEvent.VK_DOWN || k == KeyEvent.VK_S)
		{
			currentChoice++;
			if (currentChoice == bffPlayingDialogue.length)
				currentChoice = 0;
		}
	}

	@Override
	public void keyReleased(int k) {
		
	}

	
}
