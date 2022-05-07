package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Music.Music;
import TileMap.Background;

public class FirstStoryBook extends GameState {

	private Background bg;
	private int dialogueCounter = 0;
	
	private int currentChoice = 0; // Choices
	
	private String[] options = {  //The options the user has to choose
			"Mom is taking me to the store to ",
			"get a new toy, I'm so excited!"
	}; 
	
	private String[] toysDialogue = {
			"So many cool choices! I don’t know",
			"which one to pick!",
			"A red fast race car.",
			"A green alien robot.",
			"A wet blue water gun.",
			"But wait, what is that?"
			
	};
	
	private String[] dollCutDialogue = {
			"There was something stuck behind ",
			"one of the red cars",
		    "probably another kid had put ",
		    "it back there incorrectly.",
			"The tag said “Aika the Doll”",
			"She had the following pink dress ",
			"and smooth black hair.",
			"I was so captivated it was nothing ",
			"like any of the toys",
			"I had at home.”",
			"“It was pretty and cute ",
			"and just felt right."
	};
	
	private String[] bffStartDialogue = {
			"Hey, do you like dolls too?",
			"I turned to see a girl around my", 
			"age with a doll of her own in her hand.",
			"My name is Ava. What is yours?",
			"I told her my name was Lucas.",
			"We chatted for a while and she told",
			"me all about her collection of dolls and figures."
	};
	
	private String[] showingToysDialogue = {
			"I was so excited that the next day I ",
			"went to see my friends to show them Aika.",
			"I told them all about the cute doll I had ",
			"found and that maybe we could play with them",
			"instead of cars and robots somtimes.",
			"Instead they laughed at me for what",
			"felt like an eternity.",
			"I was called names, horrible names."
	};
	
	private String[] wantToLeaveDialogue = {
			
			"I wanted to disappear",
			"I wanted to disappear",
			"Why",
			"I want to go home"
	};
	
	private Color titleColor; // The color of the title
	private Font titleFont; // The font of the title
	
	private Font font; // Font for the options
	
	//private Music firstStoryBookTheme = new Music(); // Music for the menu state
	
	public FirstStoryBook(GameStateManager gsm)
	{
		this.gsm = gsm;
		
		
		
		try
		{
			//firstStoryBookTheme.stopSong();
			init();
			//firstStoryBookTheme.stopSong();
			
			// This will be how you set a background and which state it will be in
			bg = new Background("/Backgrounds/HandHolding.gif", 1);
			
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
		//firstStoryBookTheme.playSong("Resource/Music/storybook.wav");
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
			for (int i = 0; i < options.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.YELLOW);

				else
					g.setColor(Color.BLUE);

				g.drawString(options[i], 0, 130 + i * 15);
			}
			break;
			
		case 1:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < toysDialogue.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.YELLOW);

				else
					g.setColor(Color.BLUE);

				g.drawString(toysDialogue[i], 0, 110 + i * 15);
			}
			break;
			
		case 2:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < dollCutDialogue.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.YELLOW);

				else
					g.setColor(Color.BLUE);

				g.drawString(dollCutDialogue[i], 0, 110 + i * 15);
			}
			break;
			
		case 3:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < bffStartDialogue.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.YELLOW);

				else
					g.setColor(Color.BLUE);

				g.drawString(bffStartDialogue[i], 0, 100 + i * 15);
			}
			break;
			
		case 4:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < showingToysDialogue.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.YELLOW);

				else
					g.setColor(Color.BLUE);

				g.drawString(showingToysDialogue[i], 0, 50 + i * 15);
			}
			break;
			
		case 5:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < wantToLeaveDialogue.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.YELLOW);

				else
					g.setColor(Color.BLUE);

				g.drawString(wantToLeaveDialogue[i], 0, 100 + i * 15);
			}
			break;
			
		case 6:
			//firstStoryBookTheme.stopSong();
			gsm.setState(GameStateManager.LEVEL3STATE);
		}
		
	}

	private void select()
	{
		if (currentChoice == 0)
		{
			dialogueCounter++;
			//firstStoryBookTheme.stopSong();
			
			if (dialogueCounter == 0 || dialogueCounter == 1)
				bg = new Background("/Backgrounds/toys.gif", 1);
			
			if (dialogueCounter == 2)
				bg = new Background("/Backgrounds/dollCut.gif", 1);
			
			if(dialogueCounter == 3)
				bg = new Background("/Backgrounds/bffStart.gif", 1);
			
			if(dialogueCounter == 4)
				bg = new Background("/Backgrounds/ShowingToys.png", 1);
			
			if(dialogueCounter == 5)
				bg = new Background("/Backgrounds/wantToLeave.gif", 1);
			
			if(dialogueCounter == 6)
				//firstStoryBookTheme.stopSong();
				gsm.setState(GameStateManager.LEVEL3STATE);
		}
		
		if (currentChoice == 1)
		{
			dialogueCounter++;
			//firstStoryBookTheme.stopSong();
			
			if (dialogueCounter == 1)
				bg = new Background("/Backgrounds/toys.gif", 1);
			
			if (dialogueCounter == 2)
				bg = new Background("/Backgrounds/dollCut.gif", 1);
			
			if(dialogueCounter == 3)
				bg = new Background("/Backgrounds/bffStart.gif", 1);
			
			if(dialogueCounter == 4)
				bg = new Background("/Backgrounds/ShowingToys.png", 1);
			
			if(dialogueCounter == 5)
				bg = new Background("/Backgrounds/wantToLeave.gif", 1);
			
			if(dialogueCounter == 6)
				//firstStoryBookTheme.stopSong();
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
				currentChoice = options.length - 1;
		}
		
		if (k == KeyEvent.VK_DOWN || k == KeyEvent.VK_S)
		{
			currentChoice++;
			if (currentChoice == options.length)
				currentChoice = 0;
		}
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

	
}
