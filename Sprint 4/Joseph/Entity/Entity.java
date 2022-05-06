package Entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import Main.GamePanel;
import Main.UtilityTool;

public class Entity {
	
	GamePanel gp;
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
	public BufferedImage image, image2, image3;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collision = false;
	String dialogues[] = new String[20];
	
	// STATE
	public int worldX, worldY;
	public String direction = "down";
	public int spriteNum = 1;
	int dialogueIndex = 0;
	public boolean collisionOn = false;
	public boolean invincible = false;
	boolean attacking = false;
	public boolean alive = true;
	public boolean dying = false;
	boolean healthBarOn = false;
	
	// COUNTER
	public int spriteCounter = 0;
	public int actionLockCounter = 0;
	public int invincibleCounter = 0;
	int dyingCounter = 0;
	int healthBarCounter = 0;
	
	// CHARACTER ATTRIBUTES
	public int type; // 0 - Player, 1 - NPC, 2 - Monster
	public String name;
	public int speed;
	public int maxLife;
	public int life;
	public int level;
	public int strength;
	public int dexterity;
	public int attack;
	public int defense;
	public int experience;
	public int nextLevelExperience;
	public int coin;
	public Entity currentWeapon;
	public Entity currentShield;
	
	// ITEM ATTRIBUTES
	public int attackValue;
	public int defenseValue;
	
	public Entity (GamePanel gp)
	{
		this.gp = gp;
	}
	
	public void setAction()
	{
		
	}
	
	public void damageReaction()
	{
		
	}
	
	public void speak()
	{
		if(dialogues[dialogueIndex] == null)
		{
			dialogueIndex = 0;
		}
		
		gp.userInterface.currentDialogue = dialogues[dialogueIndex];
		dialogueIndex++;
		
		switch(gp.player.direction)
		{
			case "up":
				direction = "down";
				break;
			case "down":
				direction = "up";
				break;
			case "left":
				direction = "right";
				break;
			case "right":
				direction = "left";
				break;
		}
	}
	
	public void update()
	{
		setAction();
		
		collisionOn = false;
		gp.collisionChecker.checkTile(this);
		gp.collisionChecker.checkObject(this, false);
		gp.collisionChecker.checkEntity(this, gp.npc);
		gp.collisionChecker.checkEntity(this, gp.monster);
		gp.collisionChecker.checkPlayer(this);
		boolean contactPlayer = gp.collisionChecker.checkPlayer(this);
		
		if(this.type == 2 && contactPlayer == true)
		{
			if(gp.player.invincible == false)
			{
				// We can give damage
				gp.playSoundEffect(6);
				gp.player.life -= 1;
				gp.player.invincible = true;
			}
		}
		
		// IF COLLISION IS FALSE, PLAYER CAN MOVE
		if(collisionOn == false)
		{
			switch(direction)
			{
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
			}
		}
		
		spriteCounter++;
		
		if(spriteCounter > 12)
		{
			if(spriteNum == 1)
			{
				spriteNum = 2;
			}
			else if(spriteNum == 2)
			{
				spriteNum = 1;
			}
			
			spriteCounter = 0;
		}
		
		if(invincible == true)
		{
			invincibleCounter++;
			
			if(invincibleCounter > 40)
			{
				invincible = false;
				invincibleCounter = 0;
			}
			
		}
		
	}
	
	public void draw(Graphics2D g2d)
	{
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
			
			switch(direction) 
			{
				case "up":
					if(spriteNum == 1)
					{
						image = up1;
					}
					if(spriteNum == 2)
					{
						image = up2;
					}
					break;
				case "down":
					if(spriteNum == 1)
					{
						image = down1;
					}
					if(spriteNum == 2)
					{
						image = down2;
					}
					break;
				case "left":
					if(spriteNum == 1)
					{
						image = left1;
					}
					if(spriteNum == 2)
					{
						image = left2;
					}
					break;
				case "right":
					if(spriteNum == 1)
					{
						image = right1;
					}
					if(spriteNum == 2)
					{
						image = right2;
					}
					break;
			}
		
		// Monster Health Bar
		if(type == 2 && healthBarOn == true)
		{
			double oneScale = (double)gp.tileSize / maxLife;
			double healthBarValue = oneScale * life;
			
			g2d.setColor(new Color(35, 35, 35));
			g2d.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);
			
			g2d.setColor(new Color(255, 0, 30));
			g2d.fillRect(screenX, screenY - 15, (int)healthBarValue, 10);
			
			healthBarCounter++;
			
			if(healthBarCounter > 600)
			{
				healthBarCounter = 0;
				healthBarOn = false;
			}
		}
		
		if(invincible == true)
		{
			healthBarOn = true;
			healthBarCounter = 0;
			changeAlpha(g2d, 0.4F);		
		}
		
		if(dying == true)
		{
			dyingAnimation(g2d);
		}
			
		g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
		// Reset alpha
		changeAlpha(g2d, 1F);		
	}
	
	public void dyingAnimation(Graphics2D g2d)
	{
		dyingCounter++;
		
		int i = 5;
		
		if(dyingCounter <= i)
		{
			changeAlpha(g2d, 0f);
		}
		
		if(dyingCounter > i && dyingCounter <= i * 2)
		{
			changeAlpha(g2d, 1f);
		}
		
		if(dyingCounter > i * 2 && dyingCounter <= i * 3)
		{
			changeAlpha(g2d, 0f);
		}
		
		if(dyingCounter > i * 3 && dyingCounter <= i * 4)
		{
			changeAlpha(g2d, 1f);
		}
		
		if(dyingCounter > i * 4 && dyingCounter <= i * 5)
		{
			changeAlpha(g2d, 0f);
		}
		
		if(dyingCounter > i * 5 && dyingCounter <= i * 6)
		{
			changeAlpha(g2d, 1f);
		}
		
		if(dyingCounter > i * 6 && dyingCounter <= i * 7)
		{
			changeAlpha(g2d, 0f);
		}
		
		if(dyingCounter > i * 7 && dyingCounter <= i * 8)
		{
			changeAlpha(g2d, 1f);
		}
		
		if(dyingCounter > i * 8)
		{
			dying = false;
			alive = false;
		}
	}
	
	public void changeAlpha(Graphics2D g2d, float alphaValue)
	{
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
	}
	
	public BufferedImage setup(String imagePath, int width, int height)
	{
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try
		{
			image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
			image = uTool.scaleImage(image, width, height);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return image;
	}
	
}
