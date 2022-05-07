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

	// Number of Dialogue sequences
	private int numOfNoAppleTreeDialogue = 0;
	private int numOfRavenDialogue = 0;
	private int numOfSnailDialogue = 0;
	private int numOfTVDialogue = 0;
	private int numOfFlourDialogue = 0;
	private int numOfRedDialogue = 0;
	private int numOfFlowerDialogue = 0;
	private int numOfPotDialogue = 0;
	private int numOfGhostDialogue = 0;
	private int numOfDollDialogue = 0;

	// All NPC's
	private NonPlayableCharacter npc;
	private NonPlayableCharacter noAppleTreeNPC;
	private NonPlayableCharacter ravenNPC;
	private NonPlayableCharacter tvNPC;
	private NonPlayableCharacter snailNPC;
	private NonPlayableCharacter flourNPC;
	private NonPlayableCharacter flowerNPC;
	private NonPlayableCharacter potNPC;
	private NonPlayableCharacter ghostNPC;
	private NonPlayableCharacter redNPC;
	private NonPlayableCharacter dollNPC;


	private Music Level1Music = new Music(); // Music for level 1 state

	private SFX sfx;

	private String[] dialogue = {"Hello, welcome to the game!"};

	private String[] noAppleTreeDialogue = { "All the trees look so lovely, I wish ",  
			"I had apples so I could look like them.",
			"Hello little butterfly, you wish to ", 
			"know where E@&A&@ went?",
			"Sorry but I don’t know but maybe you", 
			"could get me some apples?",
			"Thank you! Please come back here",
			"when you are done.",
			"Talk to the other residents of the forest ",
			"maybe they have some apples",
	"Maybe get me 3 apples.",
	"Oh thank you, thank you",
	"Now I can be just like everyone else.",
	"Oh wait no no no.",
	"My apples are red, not blue.",
	"Now I stand out even more."};

	private String[] ravenDialogue = { "Caw Caw Caw(Hungry)",
			"Caw Caw Caw Caw(Me Kyu-chan",
			"will trade cool feather yummy fast food)",
			"Caw caw (YUMMU FOOD)",
	"You gave the chips"};

	private String[] snailDialogue = { "Hello I am Toro the slug",
			"My Fiance is a snail so I wear this shell and pretend to be a snail.",
			"Why did I tell you?",
			"You seem kind of trustworthy.",
			"You are looking for apples?",
			"You can have mine.",
			"You got ⅓ apple.",
			"Maybe you can get more from the other residents ",
	"but they won’t be as generous as me."};

	private String[] tvDialogue = { "I don't understand humans.",
			"Mine watches me everyday and never leaves his ",
			"apartment.",
			"He has never told me his name but every time",
			"his mother calls she calls him hikikomori",
			"Hikkiomori never brings over any friends.",
			"He thinks the people on TV are his friends.",
			"If only there was some way to get him to stop",
			"watching TV and get him outside.",
			"My throat is kinda parched right now,",
			"would you mind getting me a drink?" ,
			"You go to the back to turn on the power switch.",
			"It doesn’t work but you find an old bag of chips.",
			"(YOU GOT CHIPS)"
	};

	private String[] flourDialogue = { "My name is Flower but I am a Flour.",
			"I am dour that I am not a true Flower.",
			"Could you help me be empowered and become ",
			"a true Flower?",
			"Flour has been turned into compost for a flower seed",
			"You find 2.541 of an apple"
	};

	private String[] flowerDialogue = {"My name is Flour but I am a Flower. ",
			"I am dour that I am not a true flour. ",
			"Could you help me be empowered and ",
			"become a true Flour?",
			"The flower has been ground up and turned into a flour",
			"You find ⅛ an apple"};
	
	private String[] potDialogue = {"I love to sleep because then I don’t ",
			 "have to think about my problems.",
			 "No thoughts are better than sad ones",
			 "(You tell a sad story)",
			 "Why would you tell me this?",
			 "YOU GET: TEARS"};
	
	private String[] ghostDialogue = {"Do you want to hear a sad story?”\n"
			+ "“When I was alive I was bullied a lot in school.”\n"
			+ "“The teachers never helped me and my parents were too busy.”\n"
			+ "“One day a shooting star flew brilliantly over the night sky and I made a wish.”\n"
			+ "“That all my problems would go away.”\n"
			+ "“The next day my school blew up from a ruptured gas pipe and my parents both had simultaneous heart attacks.”\n"
			+ "“There are two different types of loneliness.”\n"
			+ "“Being alone in a sea of people”\n"
			+ "“And being truly alone.”\n"
			+ "“This type of loneliness didn't hurt as bad as before.”\n"
			+ "“Nobody could torment me for crying.”\n"
			+ "“Nobody could  chastise me for simply trying.”\n"
			+ "“But I soon realized that…”\n"
			+ "“I had died in that explosion and my classmates would now bully me forever and ever in the afterlife.",
			"Thank you for listening.",
			"YOU GOT: SAD STORY"};
	
	private String[] redDialogue = {"Hello my name is Mr.K",
			"I have magic powers, I can swap anybody’s ",
			"bodies",
			"I mostly have to use this for Bias tests",
			"in certain pig stations.",
			"Do you want to see it?",
			"Just tell me two people to swap and give ",
			"me something like a gem and you will be amazed.",
			"(YOU GIVE GEM AND TELL HIM ABOUT FLOUR AND FLOWER)",
			"Ok time for presto chango",
			"There go check it out"
};
	
	private String[] dollDialogue = {"I am beautiful",
			"I am perfect just the way I am",
			"I am beautiful",
			"I am perfect just the way I am",
			"I am...",
			"Disgusting.",
			"Maybe if I had a beautiful feather",
			"a hat then my mother would love me.",
			"(YOU GAVE FEATHER)",
			"Here you can gave this beautiful gem.",
			"(YOU GOT GEM)"
};

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
		tileMap.loadMap("/Maps/SuperFlatMap2.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);

		// Initializes Music
		Level1Music.playSong("Resource/Music/roomTheme.wav");

		// Initializes background
		bg = new Background("/Backgrounds/daylevel.gif", 0.1);
		bg.setVector(-0.1, 0);

		// Adds the player
		player = new Player(tileMap, 15, 30, "/Sprites/mainCharacterButterfly.png");
		player.setPosition(100,256);

		//Adds the NPC's
		npc = new NonPlayableCharacter(tileMap, 16, 30, "/Sprites/pot.png" );
		npc.setPosition(200, 256);

		noAppleTreeNPC = new NonPlayableCharacter(tileMap, 49, 60, "/Sprites/AppleTree.png" );
		noAppleTreeNPC.setPosition(300, 240);

		ravenNPC = new NonPlayableCharacter(tileMap, 60, 60, "/Sprites/raven.png");
		ravenNPC.setPosition(400, 240);

		snailNPC = new NonPlayableCharacter(tileMap, 58, 55, "/Sprites/snail.png");
		snailNPC.setPosition(500, 254);

		tvNPC = new NonPlayableCharacter(tileMap, 47, 58, "/Sprites/tvOn.png");
		tvNPC.setPosition(600, 242);

		flourNPC = new NonPlayableCharacter(tileMap, 53, 57, "/Sprites/flour.png");
		flourNPC.setPosition(700, 243);

		flowerNPC = new NonPlayableCharacter(tileMap, 55, 57, "/Sprites/flower.png");
		flowerNPC.setPosition(800, 243);

		potNPC = new NonPlayableCharacter(tileMap, 52, 59, "/Sprites/pot.png");
		potNPC.setPosition(900, 241);

		ghostNPC = new NonPlayableCharacter(tileMap, 52, 58, "/Sprites/ghost.png");
		ghostNPC.setPosition(1200, 242);

		redNPC = new NonPlayableCharacter(tileMap, 38, 57, "/Sprites/red.png");
		redNPC.setPosition(1400, 243);

		dollNPC = new NonPlayableCharacter(tileMap, 39, 60, "/Sprites/doll.png");
		dollNPC.setPosition(1600, 240);

		// Font
		font = new Font("Arial", 0, 10);
	}

	// Each time an action occurs the player must be updated
	// and the tileMap in relation to the player
	@Override
	public void update() 
	{
		// Update player
		player.update();

		System.out.println("X");
		System.out.println((int)player.getPositionX());

		System.out.println("\nY");
		System.out.println((int)player.getPositionY());

		tileMap.setPosition(
				GamePanel.WIDTH / 2 - player.getX(),
				GamePanel.HEIGHT / 2 - player.getY()
				);

		// Update all NPC's
		npc.update();
		noAppleTreeNPC.update();
		ravenNPC.update();
		snailNPC.update();
		tvNPC.update();
		flourNPC.update();
		flowerNPC.update();
		potNPC.update();
		ghostNPC.update();
		redNPC.update();
		dollNPC.update();

	}

	// Draws the player, map, and player
	@Override
	public void draw(Graphics2D g) 
	{	
		//Make sure the draw function is done in this specific sequence shown here

		// This is the characters during the day
		if((int)player.getPositionX() <= 620)
		{
			//Draw Background	
			bg = new Background("/Backgrounds/daylevel.gif", 0.1);
			bg.draw(g);

			// Draw TileMap
			tileMap.draw(g);

			//Draw player
			player.draw(g);

			//Draw NPC
			npc.draw(g);
			noAppleTreeNPC.draw(g);
			ravenNPC.draw(g);
			snailNPC.draw(g);
			tvNPC.draw(g);
		}

		// This is the charcaters during the afternoon/evening
		if((int)player.getPositionX() >= 620)
		{
			bg = new Background("/Backgrounds/afternoonlevel.gif", 0.1);
			bg.draw(g);
			tileMap.draw(g);
			player.draw(g);
			flourNPC.draw(g);
			flowerNPC.draw(g);
			potNPC.draw(g);

		}

		if((int)player.getPositionX() >= 1110)
		{
			bg = new Background("/Backgrounds/nightLevel.gif", 0.1);
			bg.draw(g);
			tileMap.draw(g);
			player.draw(g);
			ghostNPC.draw(g);
			redNPC.draw(g);
			dollNPC.draw(g);
		}


		//Draw dialogue when needed
		if((int)player.getPositionX() >= (int)npc.getPositionX() - 20 &&
				(int)player.getPositionX() <= (int)npc.getPositionX() + 20 )
		{
			if (player.getTalking())
			{
				// Draw title
				g.setColor(Color.WHITE);
				g.setFont(font);
				g.drawString(dialogue[0], 2, 50);
			}

		}

		// Dialogue for the no Apple Tree
		if((int)player.getPositionX() >= (int)noAppleTreeNPC.getPositionX() - 20 &&
				(int)player.getPositionX() <= (int)noAppleTreeNPC.getPositionX() + 20 )
		{
			if(player.getTalking())
			{
				if(numOfNoAppleTreeDialogue == 0)
					for (int i = 0; i < 11; i++)
					{
						g.setColor(Color.YELLOW);
						g.setFont(font);
						g.drawString(noAppleTreeDialogue[i], 5, 10 + i * 15);
					}

				if(numOfNoAppleTreeDialogue == 1)
					for (int i = 11; i < noAppleTreeDialogue.length; i++)
					{
						g.setColor(Color.YELLOW);
						g.setFont(font);
						g.drawString(noAppleTreeDialogue[i], 5, 10 + (i-10) * 15);
						Level1Music.stopSong();
						gsm.setState(GameStateManager.LEVEL2STATE);
					}
			}
		}

		//Dialogue for Raven
		if((int)player.getPositionX() >= (int)ravenNPC.getPositionX() - 20 &&
				(int)player.getPositionX() <= (int)ravenNPC.getPositionX() + 20 )
		{
			if(player.getTalking())
			{
				if(numOfRavenDialogue == 0)
					for (int i = 0; i < 3; i++)
					{
						{
							g.setColor(Color.YELLOW);
							g.setFont(font);
							g.drawString(ravenDialogue[i], 5, 10 + i * 15);
						}
					}

				if(numOfRavenDialogue == 1)
				{
					for (int i = 3; i < ravenDialogue.length; i++)
					{
						{
							g.setColor(Color.YELLOW);
							g.setFont(font);
							g.drawString(ravenDialogue[i], 5, 10 + (i - 2)* 15);
							numOfDollDialogue = 1;
						}
					}
				}
			}
		}

		// Dialogue for snail
		if((int)player.getPositionX() >= (int)snailNPC.getPositionX() - 20 &&
				(int)player.getPositionX() <= (int)snailNPC.getPositionX() + 20 )
		{
			if(player.getTalking())
			{
				if(numOfSnailDialogue == 0)
					for (int i = 0; i < snailDialogue.length; i++)
					{
						g.setColor(Color.YELLOW);
						g.setFont(font);
						g.drawString(snailDialogue[i], 5, 10 + i * 15);
					}
			}
		}

		
		// Dialogue for TV
		if((int)player.getPositionX() >= (int)tvNPC.getPositionX() - 20 &&
				(int)player.getPositionX() <= (int)tvNPC.getPositionX() + 20 )
		{
			if(player.getTalking())
			{
				if(numOfTVDialogue == 0)
				{
					for (int i = 0; i < 11; i++)
					{
						g.setColor(Color.YELLOW);
						g.setFont(font);
						g.drawString(tvDialogue[i], 5, 10 + i * 15);
					}
				}

				if(numOfTVDialogue == 1)
				{
					for (int i = 11; i < tvDialogue.length; i++)
					{
						g.setColor(Color.YELLOW);
						g.setFont(font);
						g.drawString(tvDialogue[i], 5, 10 + (i-3) * 15);
						numOfRavenDialogue = 1;
					}
				}
			}
		}

		// Dialogue for Flour
		if((int)player.getPositionX() >= (int)flourNPC.getPositionX() - 20 &&
				(int)player.getPositionX() <= (int)flourNPC.getPositionX() + 20 )
		{
			if(player.getTalking())
			{
				if(numOfFlourDialogue == 0)
				{
					for (int i=0; i < 4; i++)
					{
						g.setColor(Color.YELLOW);
						g.setFont(font);
						g.drawString(flourDialogue[i], 5, 10 + i * 15);
					}
				}

				if(numOfFlourDialogue == 1)
				{
					for (int i=4; i < flourDialogue.length; i++)
					{
						g.setColor(Color.YELLOW);
						g.setFont(font);
						g.drawString(flourDialogue[i], 5, 10 + i * 15);
						numOfNoAppleTreeDialogue = 1;
					}
				}
			}
		}

		//Dialogue of Flower
		if((int)player.getPositionX() >= (int)flowerNPC.getPositionX() - 20 &&
				(int)player.getPositionX() <= (int)flowerNPC.getPositionX() + 20 )
		{
			if(player.getTalking())
			{
				if(numOfFlowerDialogue == 0)
				{
					for (int i=0; i < 4; i++)
					{
						g.setColor(Color.YELLOW);
						g.setFont(font);
						g.drawString(flowerDialogue[i], 5, 10 + i * 15);
					}
				}
			}

			if(numOfFlowerDialogue == 1)
			{
				for (int i=4; i < flowerDialogue.length; i++)
				{
					g.setColor(Color.YELLOW);
					g.setFont(font);
					g.drawString(flowerDialogue[i], 5, 10 + i * 15);
					numOfNoAppleTreeDialogue = 1;
				}
			}
		}

		// Dialogue for Pot
		if((int)player.getPositionX() >= (int)potNPC.getPositionX() - 20 &&
				(int)player.getPositionX() <= (int)potNPC.getPositionX() + 20 )
		{
			if(player.getTalking())
			{
				if(numOfPotDialogue == 0)
				{
					for (int i=0; i < 3; i++)
					{
						g.setColor(Color.YELLOW);
						g.setFont(font);
						g.drawString(potDialogue[i], 5, 10 + i * 15);
					}
				}
				
				if(numOfPotDialogue == 1)
				{
					for (int i=3; i < potDialogue.length; i++)
					{
						g.setColor(Color.YELLOW);
						g.setFont(font);
						g.drawString(potDialogue[i], 5, 10 + i * 15);
						numOfTVDialogue = 1;
					}
				}
			}
		}

		// Dialogue for Ghost
		if((int)player.getPositionX() >= (int)ghostNPC.getPositionX() - 20 &&
				(int)player.getPositionX() <= (int)ghostNPC.getPositionX() + 20 )
		{
			if(player.getTalking())
			{
				if(numOfGhostDialogue == 0)
				{
					for (int i=0; i < 3; i++)
					{
						g.setColor(Color.YELLOW);
						g.setFont(font);
						g.drawString(ghostDialogue[i], 5, 10 + i * 15);
						numOfPotDialogue = 1;
					}
				}
				
				if(numOfGhostDialogue == 1)
				{
					for (int i=3; i < ghostDialogue.length; i++)
					{
						g.setColor(Color.YELLOW);
						g.setFont(font);
						g.drawString(ghostDialogue[i], 5, 10 + i * 15);
						numOfPotDialogue = 1;
					}
				}
			}
		}

		//Dialogue for Doll
		if((int)player.getPositionX() >= (int)dollNPC.getPositionX() - 20 &&
				(int)player.getPositionX() <= (int)dollNPC.getPositionX() + 20 )
		{
			if(player.getTalking())
			{
				if (numOfDollDialogue == 0)
				{
					for (int i=0; i < 8; i++)
					{
						g.setColor(Color.YELLOW);
						g.setFont(font);
						g.drawString(dollDialogue[i], 5, 10 + i * 15);
					}
				}

				if (numOfDollDialogue == 1)
				{
					for (int i=8; i < dollDialogue.length; i++)
					{
						g.setColor(Color.YELLOW);
						g.setFont(font);
						g.drawString(dollDialogue[i], 5, 10 + i * 15);
						numOfRedDialogue = 1;
					}
				}

			}
		}

		//Dialogue for Red
		if((int)player.getPositionX() >= (int)redNPC.getPositionX() - 20 &&
				(int)player.getPositionX() <= (int)redNPC.getPositionX() + 20 )
		{
			if(player.getTalking())
			{
				if (numOfRedDialogue == 0)
				{
					for (int i=0; i < 8; i++)
					{
					g.setColor(Color.YELLOW);
					g.setFont(font);
					g.drawString(redDialogue[i], 5, 10 + i * 15);
					}
				}
				
				if (numOfRedDialogue == 1)
				{
					for (int i=9; i < redDialogue.length; i++)
					{
					g.setColor(Color.YELLOW);
					g.setFont(font);
					g.drawString(redDialogue[i], 5, 10 + i * 15);
					numOfFlowerDialogue = 1;
					numOfFlourDialogue = 1;
					}
				}
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

		if(k == KeyEvent.VK_Q) player.setTalking(true);
	}

	@Override
	public void keyReleased(int k) 
	{
		if(k == KeyEvent.VK_A) player.setLeft(false);
		if(k == KeyEvent.VK_D) player.setRight(false);
		if(k == KeyEvent.VK_UP) player.setUp(false);
		if(k == KeyEvent.VK_DOWN) player.setDown(false);
		if(k == KeyEvent.VK_Q) player.setTalking(false);
	}

}
