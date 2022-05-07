package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Music.Music;
import TileMap.Background;

public class FinalCutscene extends GameState {

	private Background bg;
	private int dialogueCounter = -1;
	
	private int currentChoice = 0; // Choices
	
	private String[] finalCutsceneDialogue1 = {
			
			"Hey Lucas it's me Ava"
		
	};
	
	private String[] finalCutsceneDialogue2 = {
			
			"I'm worried about you"
		
	};

	private String[] finalCutsceneDialogue3 = {
		
		"You stopped coming to school and you",
		"haven't picked up my calls"
	
};
	
	private String[] finalCutsceneDialogue4 = {
			
			"Go away"
		
	};
	
	private String[] finalCutsceneDialogue5 = {
			
			"I'm a mistake"
		
	};
	
	private String[] finalCutsceneDialogue6 = {
			
			"You should be playing with other",
			"girls, not me"
		
	};
	
	private String[] finalCutsceneDialogue7 = {
			
			"People like me dont deserve to live"
		
	};
	
	private String[] finalCutsceneDialogue8 = {
			
			"What?"
		
	};
	
	private String[] finalCutsceneDialogue9 = {
			
			"I like this place"
		
	};
	
	private String[] finalCutsceneDialogue10 = {
			
			"There is nothing here, it's perfect"
		
	};
	
	private String[] finalCutsceneDialogue11 = {
			
			"I don't have to feel hurt ever again"
		
	};
	
	private String[] finalCutsceneDialogue12 = {
			
			"That's not healthy Lucas"
		
	};
	
	private String[] finalCutsceneDialogue13 = {
			
			"What about love and happiness?"
		
	};
	
	private String[] finalCutsceneDialogue14 = {
			
			"I don't deserve that"
		
	};
	
	private String[] finalCutsceneDialogue15 = {
			
			"Go away"
		
	};
	
	private String[] finalCutsceneDialogue16 = {
			
			"Turn around"
		
	};
	
	private String[] finalCutsceneDialogue17 = {
			
			"Go away"
		
	};
	
	private String[] finalCutsceneDialogue18 = {
			
			"SAY THAT TO MY FACE!"
		
	};
	
	private String[] finalCutsceneDialogue19 = {
			
			"Are you crying?"
		
	};
	
	private String[] finalCutsceneDialogue20 = {
			
			"I know you're hurting inside but",
			"but you don't have to suffer alone."
		
	};
	
	private String[] finalCutsceneDialogue21 = {
			
			"You're my best friend and I worry",
			"so much about you"
		
	};
	
	private String[] finalCutsceneDialogue22 = {
			
			"Even if the whole world turns against you",
			"I will always be there for you"
		
	};
	
	private String[] finalCutsceneDialogue23 = {
			
			"........"
		
	};
	
	private String[] finalCutsceneDialogue24 = {
			
			"I......"
		
	};
	
	private String[] finalCutsceneDialogue25 = {
			
			"I don't know what to do"
		
	};
	
	private String[] finalCutsceneDialogue26 = {
			
			"There's new desires"
		
	};
	
	private String[] finalCutsceneDialogue27 = {
			
			"There's new feelings"
		
	};
	
	private String[] finalCutsceneDialogue28 = {
			
			"I'm scared"
		
	};
	
	private String[] finalCutsceneDialogue29 = {
			
			"That's ok"
		
	};
	
	private String[] finalCutsceneDialogue30 = {
			
			"Even if the fear never goes away"
		
	};
	
	private String[] finalCutsceneDialogue31 = {
			
			"Even if the self-hate never goes away"
		
	};
	
	private String[] finalCutsceneDialogue32 = {
			
			"We can work on it together"
		
	};
	
	private String[] finalCutsceneDialogue33 = {
			
			"........"
		
	};
	
	private String[] finalCutsceneDialogue34 = {
			
			"........"
		
	};
	
	private String[] finalCutsceneDialogue35 = {
			
			"I love you Ava, let's be friends",
			"forever and ever"
		
	};
	
	private String[] finalCutsceneDialogue36 = {
			
			"I love you, Lucas, forever and ever"
		
	};
	
	private String[] finalCutsceneDialogue37 = {
			
			"From now on, call me Aika"
		
	};
	
	private String[] finalCutsceneDialogue38 = {
			
			"The End"
			
	};
	
	private Color titleColor; // The color of the title
	private Font titleFont; // The font of the title
	
	private Font font; // Font for the options
	
	//private Music firstStoryBookTheme = new Music(); // Music for the menu state
	
	public FinalCutscene(GameStateManager gsm)
	{
		this.gsm = gsm;
		
		
		
		try
		{
			//firstStoryBookTheme.stopSong();
			init();
			//firstStoryBookTheme.stopSong();
			
			// This will be how you set a background and which state it will be in
			bg = new Background("/Backgrounds/face1", 1);
			
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
		case -1:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue1.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue1[i], 0, 130 + i * 15);
			}
			break;
			
		case 0:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue2.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue2[i], 0, 110 + i * 15);
			}
			break;
			
		case 1:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue3.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue3[i], 0, 110 + i * 15);
			}
			break;
			
		case 2:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue4.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue4[i], 0, 100 + i * 15);
			}
			break;
			
		case 3:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue5.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue5[i], 0, 100 + i * 15);
			}
			break;
			
		case 4:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue6.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue6[i], 0, 100 + i * 15);
			}
			break;
			
		case 5:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue7.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue7[i], 0, 100 + i * 15);
			}
			break;
			
		case 6:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue8.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue8[i], 0, 100 + i * 15);
			}
			break;
			
		case 7:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue9.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue9[i], 0, 100 + i * 15);
			}
			break;
			
		case 8:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue10.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue10[i], 0, 100 + i * 15);
			}
			break;
			
		case 9:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue11.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue11[i], 0, 100 + i * 15);
			}
			break;
			
		case 10:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue12.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue12[i], 0, 100 + i * 15);
			}
			break;
			
		case 11:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue13.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue13[i], 0, 100 + i * 15);
			}
			break;
			
		case 12:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue14.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue14[i], 0, 100 + i * 15);
			}
			break;
			
		case 13:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue15.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue15[i], 0, 100 + i * 15);
			}
			break;
			
		case 14:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue16.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue16[i], 0, 100 + i * 15);
			}
			break;
			
		case 15:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue17.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue17[i], 0, 100 + i * 15);
			}
			break;
			
		case 16:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue18.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue18[i], 0, 100 + i * 15);
			}
			break;
			
		case 17:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue19.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue19[i], 0, 100 + i * 15);
			}
			break;
			
		case 18:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue20.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue20[i], 0, 100 + i * 15);
			}
			break;
			
		case 19:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue21.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue21[i], 0, 100 + i * 15);
			}
			break;
			
		case 20:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue22.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue22[i], 0, 100 + i * 15);
			}
			break;
			
		case 21:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue23.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue23[i], 0, 100 + i * 15);
			}
			break;
			
		case 22:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue24.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue24[i], 0, 100 + i * 15);
			}
			break;
			
			
		case 23:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue25.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue25[i], 0, 100 + i * 15);
			}
			break;
			
		case 24:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue26.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue26[i], 0, 100 + i * 15);
			}
			break;
			
		case 25:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue27.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue27[i], 0, 100 + i * 15);
			}
			break;
			
		case 26:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue26.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue26[i], 0, 100 + i * 15);
			}
			break;
			
		case 27:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue29.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue29[i], 0, 100 + i * 15);
			}
			break;
			
		case 28:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue30.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue30[i], 0, 100 + i * 15);
			}
			break;
			
		case 29:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue31.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue31[i], 0, 100 + i * 15);
			}
			break;
			
		case 30:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue32.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue32[i], 0, 100 + i * 15);
			}
			break;
			
		case 31:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue33.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue33[i], 0, 100 + i * 15);
			}
			break;
			
		case 32:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue34.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue34[i], 0, 100 + i * 15);
			}
			break;
			
		case 33:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue35.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue35[i], 0, 100 + i * 15);
			}
			break;
			
		case 34:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue36.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue36[i], 0, 100 + i * 15);
			}
			break;
			
		case 35:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue37.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue37[i], 0, 100 + i * 15);
			}
			break;
			
		case 36:
			bg.draw(g);
			g.setFont(font);
			for (int i = 0; i < finalCutsceneDialogue38.length; i++)
			{
				if(i == currentChoice)
					g.setColor(Color.GREEN);

				else
					g.setColor(Color.BLUE);

				g.drawString(finalCutsceneDialogue38[i], 100, 100 + i * 15);
			}
			break;
		}
		
	}

	private void select()
	{
		if (currentChoice == 0)
		{
			dialogueCounter++;
			
			if(dialogueCounter == 0 || dialogueCounter == -1)
				bg = new Background("/Backgrounds/face1.png", 1);
			
			if(dialogueCounter == 1)
				bg = new Background("/Backgrounds/face1.png", 1);
			
			if(dialogueCounter == 2)
				bg = new Background("/Backgrounds/Depressed.png", 1);
			
			if(dialogueCounter == 3)
				bg = new Background("/Backgrounds/Depressed.png", 1);
			
			if(dialogueCounter == 4)
				bg = new Background("/Backgrounds/Depressed.png", 1);
			
			if(dialogueCounter == 5)
				bg = new Background("/Backgrounds/Depressed.png", 1);
			
			if(dialogueCounter == 6)
				//What?
				bg = new Background("/Backgrounds/face1.png",1);
			
			if(dialogueCounter == 7)
				bg = new Background("/Backgrounds/Depressed.png", 1);
			
			if(dialogueCounter == 8)
				bg = new Background("/Backgrounds/Depressed.png", 1);
			
			if(dialogueCounter == 9)
				bg = new Background("/Backgrounds/Depressed.png", 1);
			
			if(dialogueCounter == 10)
				//That's not healthy Lucas
				bg = new Background("/Backgrounds/face1.png", 1);
			
			if(dialogueCounter == 11)
				bg = new Background("/Backgrounds/face1.png", 1);
			
			if(dialogueCounter == 12)
				bg = new Background("/Backgrounds/Depressed.png", 1);
			
			if(dialogueCounter == 13)
				bg = new Background("/Backgrounds/Depressed.png", 1);
			
			if(dialogueCounter == 14)
				//Turn around
				bg = new Background("/Backgrounds/face2.png", 1);
			
			if(dialogueCounter == 15)
				bg = new Background("/Backgrounds/Depressed.png", 1);
			
			if(dialogueCounter == 16)
				//SAY THAT TO MY FACE
				bg = new Background("/Backgrounds/SadCrying.png", 1);
			
			if(dialogueCounter == 17)
				bg = new Background("/Backgrounds/DepressedEyesOpen.png", 1);
			
			if(dialogueCounter == 18)
				bg = new Background("/Backgrounds/SadCrying.png", 1);
			
			if(dialogueCounter == 19)
				bg = new Background("/Backgrounds/SadCrying.png", 1);
			
			if(dialogueCounter == 20)
				// Even if world
				bg = new Background("/Backgrounds/SadCrying.png", 1);
			
			if(dialogueCounter == 21)
				bg = new Background("/Backgrounds/DepressedEyesOpen.png", 1);
			
			if(dialogueCounter == 22)
				bg = new Background("/Backgrounds/DepressedEyesOpen.png", 1);
			
			if(dialogueCounter == 23)
				bg = new Background("/Backgrounds/DepressedCrying.png", 1);
			
			if(dialogueCounter == 24)
				bg = new Background("/Backgrounds/DepressedCrying.png", 1);
			
			if(dialogueCounter == 25)
				bg = new Background("/Backgrounds/DepressedCrying.png", 1);
			
			if(dialogueCounter == 26)
				bg = new Background("/Backgrounds/DepressedCrying.png", 1);
			
			if(dialogueCounter == 27)
				bg = new Background("/Backgrounds/CryingHappy.png", 1);
			
			if(dialogueCounter == 28)
				bg = new Background("/Backgrounds/CryingHappy.png", 1);
			
			if(dialogueCounter == 29)
				bg = new Background("/Backgrounds/CryingHappy.png", 1);
			
			if(dialogueCounter == 30)
				bg = new Background("/Backgrounds/CryingHappy.png", 1);
			
			if(dialogueCounter == 31)
				bg = new Background("/Backgrounds/DepressedCryingHappy.png", 1);
			
			if(dialogueCounter == 32)
				bg = new Background("/Backgrounds/CryingHappy.png", 1);
			
			if(dialogueCounter == 33)
				bg = new Background("/Backgrounds/DepressedCryingHappy.png", 1);
			
			if(dialogueCounter == 34)
				bg = new Background("/Backgrounds/CryingHappy.png", 1);
			
			if(dialogueCounter == 35)
				bg = new Background("/Backgrounds/white.jpg", 1);
			
			if(dialogueCounter == 36)
				bg = new Background("/Backgrounds/white.jpg",1);
			
			if(dialogueCounter == 37)
				bg = new Background("/Backgrounds/white.jpg",1);
			
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
				currentChoice = finalCutsceneDialogue1.length - 1;
		}
		
		if (k == KeyEvent.VK_DOWN || k == KeyEvent.VK_S)
		{
			currentChoice++;
			if (currentChoice == finalCutsceneDialogue1.length)
				currentChoice = 0;
		}
	}

	@Override
	public void keyReleased(int k) {
		
	}

	
}
