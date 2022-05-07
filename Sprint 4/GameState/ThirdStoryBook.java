package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Music.Music;
import TileMap.Background;

public class ThirdStoryBook extends GameState {

	private Background bg;
	private int dialogueCounter  = 0;
	
	private int currentChoice = 0; // Choices
	
	private String[] bffHappyDialogue = {
			"I didn't want to go to Ava’s house ",
			"anymore so Ava started coming to mine",
			"and bringing along with her, her toys.",
			"I wasn’t sleeping that well after I last ",
			"went to her house but I was surprised to ",
			"see Ava with bright purple hair."
	}; 
	
	private String[] bffSadDialogue = {
			
			"She had recently bought a hair dye and ",
			"wanted us to dye our hair together.",
			"I was so happy and excited.",
			"We went to the bathroom and she helped ",
			"me to get the color just right."
			
	};
	
private String[] inkDialogue = {
			
		"She didn’t just come to dye our hair together.",
		"She had brought the dress and had an idea.",
		"Maybe with long purple hair people would think ",
		"I'm a girl.”",
		"That I could go outside wearing the dress.",
		"I was nervous of course but I trusted Ava so much.",
		"I didn’t want to let her down, so I agreed."
			
	};
	
	private String[] mallDialogue = {
			
			"We ended up going to the local mall.",
			"We walked around and talked all day."
			
	};
	
	private String[] peopleDialogue = {
		
			"People passed us by and didn’t say a thing.",
			"An older man tipped his hat saying ",
			"good morning ladies.” as he walked bye",
			"I could finally be me."
			
	};
	
	private String[] people2Dialogue = {
			
			"However faces in the crowd started to form.",
			"Familiar faces, faces that I wished I had forgotten.",
			"Ava’s Brother and his friends were also",
			"in the mall when they saw us.",
			"They immediately recognized me.",
			"I tried to hide but it was too late.",
			"They yelled “Hey everybody look its a !@#!@!””"
			
	};
	
	private String[] people3Dialogue = {
			
			"However faces in the crowd started to form.",
			"Familiar faces, faces that I wished I had forgotten.",
			"Ava’s Brother and his friends were also in the mall when",
			"they saw us.” “They immediately recognized me.",
			"I tried to hide but it was too late.",
			"They yelled “Hey everybody look its a !@#!@!”"
			
	};
	
	private String[] people3Dialogue2 = {
			
			"I ran as fast as I could.",
			"I didn’t want to see anybody anymore.",
			"I...I.....",
			"Why do I exist?",
			"I am.....",
			"worthless."
	};
	
	private Color titleColor; // The color of the title
	private Font titleFont; // The font of the title
	
	private Font font; // Font for the options
	
	private Music thirdStoryBookTheme = new Music(); // Music for the menu state
	
	public ThirdStoryBook(GameStateManager gsm)
	{
		this.gsm = gsm;
		
		try
		{
			init();
			
			// This will be how you set a background and which state it will be in
			bg = new Background("/Backgrounds/bbfSad.png", 1);
			
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
		//thirdStoryBookTheme.playSong("Resource/Music/storybook.wav");
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
			for (int i = 0; i < bffHappyDialogue.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.YELLOW);

				else
					g.setColor(Color.BLUE);

				g.drawString(bffHappyDialogue[i], 0, 80 + i * 15);
			}
			break;
			
		case 1:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < bffSadDialogue.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.YELLOW);

				else
					g.setColor(Color.BLUE);

				g.drawString(bffSadDialogue[i], 0, 110 + i * 15);
			}
			break;
			
		case 2:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < inkDialogue.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.YELLOW);

				else
					g.setColor(Color.BLUE);

				g.drawString(inkDialogue[i], 0, 60 + i * 15);
			}
			break;
			
		case 3:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < mallDialogue.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.YELLOW);

				else
					g.setColor(Color.BLUE);

				g.drawString(mallDialogue[i], 0, 100 + i * 15);
			}
			break;
			
		case 4:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < peopleDialogue.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.YELLOW);

				else
					g.setColor(Color.BLUE);

				g.drawString(peopleDialogue[i], 0, 100 + i * 15);
			}
			break;
			
		case 5:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < people2Dialogue.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.YELLOW);

				else
					g.setColor(Color.BLUE);

				g.drawString(people2Dialogue[i], 0, 100 + i * 15);
			}
			
		case 6:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < people3Dialogue.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.YELLOW);

				else
					g.setColor(Color.BLUE);

				g.drawString(people3Dialogue[i], 0, 100 + i * 15);
			}
			break;
			
		case 7:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < people3Dialogue2.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.YELLOW);

				else
					g.setColor(Color.BLUE);

				g.drawString(people3Dialogue2[i], 0, 100 + i * 15);
			}
			break;
			
		case 8:
			gsm.setState(GameStateManager.LEVEL3STATE);
		}
		
	}

	private void select()
	{
		if (currentChoice == 0)
		{
			dialogueCounter++;
			
			
			
			if (dialogueCounter == 0 || dialogueCounter == 1)
				bg = new Background("/Backgrounds/ink.png", 1);
			
			if (dialogueCounter == 2)
				bg = new Background("/Backgrounds/bffHappy.png", 1);
			
			if(dialogueCounter == 3)
				bg = new Background("/Backgrounds/mall.png", 1);
			
			if(dialogueCounter == 4)
				bg = new Background("/Backgrounds/people.png", 1);
			
			if(dialogueCounter == 5)
				bg = new Background("/Backgrounds/people2.png", 1);
			
			if(dialogueCounter == 6)
				bg = new Background("/Backgrounds/people3.png", 1);
			
			if(dialogueCounter == 7)
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
				currentChoice = bffHappyDialogue.length - 1;
		}
		
		if (k == KeyEvent.VK_DOWN || k == KeyEvent.VK_S)
		{
			currentChoice++;
			if (currentChoice == bffHappyDialogue.length)
				currentChoice = 0;
		}
	}

	@Override
	public void keyReleased(int k) {
		
	}

	
}
