package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import Entity.Entity;
import Object.Heart;
import Object.Key;

public class UserInterface {
	
	GamePanel gp;
	Graphics2D g2d;
	Font cambria;
	Font arial;
	BufferedImage heart_full, heart_half, heart_blank;
		
	public boolean messageOn = false; 
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	public String currentDialogue = "";
	public int commandNum = 0;
	
	public UserInterface(GamePanel gamePanel)
	{
		this.gp = gamePanel;
		cambria = new Font("Cambria", Font.PLAIN, 40);
		arial = new Font("Arial", Font.BOLD, 80);
		
		// CREATE HUD OBJECT
		Entity heart = new Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
	}
	
	public void showMessage(String text)
	{
		message = text;
		messageOn = true;
	}
	
	public void draw (Graphics2D g2d)
	{
		this.g2d = g2d;
		
		g2d.setFont(arial);
		g2d.setColor(Color.white);
		
		// TITLE STATE
		if(gp.gameState == gp.titleState)
		{
			drawTitleScreen();
		}
		
		// PLAY STATE
		if(gp.gameState == gp.playState)
		{
			drawPlayerLife();
		}
		
		// PAUSE STATE
		if(gp.gameState == gp.pauseState)
		{
			drawPlayerLife();
			drawPauseScreen();
		}
		
		// DIALOGUE STATE
		if(gp.gameState == gp.dialogueState)
		{
			drawPlayerLife();
			drawDialogueScreen();
		}
		
		// CHARACTER STATE
		if(gp.gameState == gp.characterState)
		{
			drawCharacterScreen();
		}
	}
	
	public void drawPlayerLife()
	{		
		int x = gp.tileSize / 2;
		int y = gp.tileSize / 2;
		int i = 0;
		
		// DRAW MAX HEART
		while(i < gp.player.maxLife / 2)
		{
			g2d.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		
		// RESET
		x = gp.tileSize / 2;
		y = gp.tileSize / 2;
		i = 0;
		
		// DRAW CURRENT LIFE
		while(i < gp.player.life)
		{
			g2d.drawImage(heart_half, x, y, null);
			i++;
			
			if(i < gp.player.life)
			{
				g2d.drawImage(heart_full, x, y, null);
			}
			
			i++;
			x += gp.tileSize;
			
		}
		
	}
	
	public void drawTitleScreen()
	{
		g2d.setColor(new Color(0, 0, 0));
		g2d.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		// TITLE NAME
		g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 96F));
		String text = "2D Adventure";
		int x = getXforCenteredText(text);
		int y = gp.tileSize * 3;
		
		// SHADOW
		g2d.setColor(Color.gray);
		g2d.drawString(text, x + 5, y + 5);
		
		// MAIN COLOR
		g2d.setColor(Color.white);
		g2d.drawString(text, x, y);
		
		// BLUE BOY IMAGE
		x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
		y += gp.tileSize * 2;
		g2d.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
		
		// MENU
		g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 48F));
		
		text = "NEW GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize * 3.5;
		g2d.drawString(text, x, y);
		
		if(commandNum == 0)
		{
			g2d.drawString(">", x - gp.tileSize, y);
		}
		
		text = "LOAD GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2d.drawString(text, x, y);
		
		if(commandNum == 1)
		{
			g2d.drawString(">", x - gp.tileSize, y);
		}
		
		text = "QUIT";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2d.drawString(text, x, y);
		
		if(commandNum == 2)
		{
			g2d.drawString(">", x - gp.tileSize, y);
		}
	}
	
	public void drawPauseScreen()
	{
		g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 80F));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight / 2;
		
		g2d.drawString(text, x, y);
	}
	
	public void drawDialogueScreen()
	{
		// WINDOW
		int x = gp.tileSize * 2;
		int y = gp.tileSize / 2;
		int width = gp.screenWidth - (gp.tileSize * 4);
		int height = gp.tileSize * 4;
		drawSubWindow(x, y, width, height);
		
		g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 28F));
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentDialogue.split("\n"))
		{
			g2d.drawString(line, x, y);
			y += 40;
		}
	}
	
	public void drawCharacterScreen()
	{
		// CREATE A FRAME
		final int frameX = gp.tileSize;
		final int frameY = gp.tileSize;
		final int frameWidth = gp.tileSize * 5;
		final int frameHeight = gp.tileSize * 10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		// TEXT
		g2d.setColor(Color.white);
		g2d.setFont(g2d.getFont().deriveFont(26F));
		
		int textX = frameX + 20;
		int textY = frameY + gp.tileSize;
		final int lineHeight = 35;
		
		// NAMES
		g2d.drawString("Level", textX, textY);
		textY += lineHeight;
		g2d.drawString("Life", textX, textY);
		textY += lineHeight;
		g2d.drawString("Strength", textX, textY);
		textY += lineHeight;
		g2d.drawString("Dexterity", textX, textY);
		textY += lineHeight;
		g2d.drawString("Attack", textX, textY);
		textY += lineHeight;
		g2d.drawString("Defense", textX, textY);
		textY += lineHeight;
		g2d.drawString("Experience", textX, textY);
		textY += lineHeight;
		g2d.drawString("Next Level", textX, textY);
		textY += lineHeight;
		g2d.drawString("Coin", textX, textY);
		textY += lineHeight + 20;
		g2d.drawString("Weapon", textX, textY);
		textY += lineHeight + 15;
		g2d.drawString("Shield", textX, textY);
		textY += lineHeight;
		
		// VALUES
		int tailX = (frameX + frameWidth) - 30;
		
		// RESET textY
		textY = frameY + gp.tileSize;
		String value;
		
		value = String.valueOf(gp.player.level);
		textX = getXforAlignToRightText(value, tailX);
		g2d.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
		textX = getXforAlignToRightText(value, tailX);
		g2d.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.strength);
		textX = getXforAlignToRightText(value, tailX);
		g2d.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.dexterity);
		textX = getXforAlignToRightText(value, tailX);
		g2d.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.attack);
		textX = getXforAlignToRightText(value, tailX);
		g2d.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.defense);
		textX = getXforAlignToRightText(value, tailX);
		g2d.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.experience);
		textX = getXforAlignToRightText(value, tailX);
		g2d.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.nextLevelExperience);
		textX = getXforAlignToRightText(value, tailX);
		g2d.drawString(value, textX, textY);
		textY += lineHeight;

		value = String.valueOf(gp.player.coin);
		textX = getXforAlignToRightText(value, tailX);
		g2d.drawString(value, textX, textY);
		textY += lineHeight;
		
		g2d.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 14, null);
		textY += gp.tileSize;
		g2d.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 14, null);
	}
	
	public void drawSubWindow(int x, int y, int width, int height)
	{
		Color c = new Color(0, 0, 0, 210);
		g2d.setColor(c);
		g2d.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255, 255, 255);
		g2d.setColor(c);
		g2d.setStroke(new BasicStroke(5));
		g2d.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
	}
	
	public int getXforCenteredText(String text)
	{
		int length = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
		int x = gp.screenWidth / 2 - length / 2;
		
		return x;
	}
	
	public int getXforAlignToRightText(String text, int tailX)
	{
		int length = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
		int x = tailX - length;
		
		return x;
	}

}