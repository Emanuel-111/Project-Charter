package Entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import Main.GamePanel;
import Main.KeyHandler;
import Main.UtilityTool;
import Object.Shield;
import Object.Sword;

public class Player extends Entity {
	
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public boolean attackCanceled = false;
		
	public Player(GamePanel gp, KeyHandler keyH)
	{
		super(gp);
		
		this.keyH = keyH;
		
		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		attackArea.width = 36;
		attackArea.height = 36;
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
	}
	
	public void setDefaultValues()
	{
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
		
		// PLAYER STATUS
		level = 1;
		maxLife = 6;
		life = maxLife;
		strength = 1; // The more strength he has, the more damage he deals.
		dexterity = 1; // The more dexterity he has, the less damage he receives.
		experience = 0;
		nextLevelExperience = 5;
		coin = 0;
		currentWeapon = new Sword(gp);
		currentShield = new Shield(gp);
		attack = getAttack(); // The total attack value is decided by strength and weapon.
		defense = getDefense(); // The total defense value is decided by dexterity and shield.
	}
	
	public int getAttack()
	{
		return attack = strength * currentWeapon.attackValue;
	}
	
	public int getDefense()
	{
		return defense = dexterity * currentWeapon.defenseValue;
	}
	
	public void getPlayerImage()
	{
		up1 = setup("/Player/boy_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/Player/boy_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/Player/boy_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/Player/boy_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/Player/boy_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/Player/boy_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/Player/boy_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/Player/boy_right_2", gp.tileSize, gp.tileSize);
	}
	
	public void getPlayerAttackImage()
	{
		attackUp1 = setup("/Player/boy_attack_up_1", gp.tileSize, gp.tileSize * 2);
		attackUp2 = setup("/Player/boy_attack_up_2", gp.tileSize, gp.tileSize * 2);
		attackDown1 = setup("/Player/boy_attack_down_1", gp.tileSize, gp.tileSize * 2);
		attackDown2 = setup("/Player/boy_attack_down_2", gp.tileSize, gp.tileSize * 2);
		attackLeft1 = setup("/Player/boy_attack_left_1", gp.tileSize * 2, gp.tileSize);
		attackLeft2 = setup("/Player/boy_attack_left_2", gp.tileSize * 2, gp.tileSize);
		attackRight1 = setup("/Player/boy_attack_right_1", gp.tileSize * 2, gp.tileSize);
		attackRight2 = setup("/Player/boy_attack_right_2", gp.tileSize * 2, gp.tileSize);
	}
	
	public void update()
	{
		if(attacking == true)
		{
			attacking();
		}
		
		else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true)
		{
			if(keyH.upPressed == true)
			{
				direction = "up";
			}
			else if(keyH.downPressed == true)
			{
				direction = "down";
			}
			else if(keyH.leftPressed == true)
			{
				direction = "left";
			}
			else if(keyH.rightPressed == true)
			{
				direction = "right";
			}
			
			// CHECK TILE COLLISION
			collisionOn = false;
			gp.collisionChecker.checkTile(this);
			
			// CHECK OBJECT COLLISION
			int objectIndex = gp.collisionChecker.checkObject(this, true);
			pickUpObject(objectIndex);
			
			// CHECK NPC COLLISION
			int npcIndex = gp.collisionChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			// CHECK MONSTER COLLISION
			int monsterIndex = gp.collisionChecker.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			
			// CHECK EVENT
			gp.eHandler.checkEvent();
			
			// IF COLLISION IS FALSE, PLAYER CAN MOVE
			if(collisionOn == false && keyH.enterPressed == false)
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
			
			if(keyH.enterPressed == true && attackCanceled == false)
			{
				gp.playSoundEffect(7);
				attacking = true;
				spriteCounter = 0;
			}
			
			attackCanceled = false;
			gp.keyH.enterPressed = false;
			
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
			
		}
		
		// Outside of key if statement.
		if(invincible == true)
		{
			invincibleCounter++;
			
			if(invincibleCounter > 60)
			{
				invincible = false;
				invincibleCounter = 0;
			}
			
		}
		
	}
	
	public void attacking()
	{
		spriteCounter++;
		
		if(spriteCounter <= 5)
		{
			spriteNum = 1;
		}
		
		if(spriteCounter > 5 && spriteCounter <= 25)
		{
			spriteNum = 2;
			
			// Save the current worldX, worldY, solidArea
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			// Adjust player's worldX and worldY for the attackArea
			switch(direction)
			{
				case"up":
					worldY -= attackArea.height;
					break;
				case"down":
					worldY += attackArea.height;
					break;
				case"left":
					worldX -= attackArea.width;
					break;
				case"right":
					worldX += attackArea.width;
					break;
			}
			
			// attackArea becomes solidArea
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			
			// Check monster collision with the updated worldX, worldY, and solidArea
			int monsterIndex = gp.collisionChecker.checkEntity(this, gp.monster);
			damageMonster(monsterIndex);
			
			// After checking collision, restore the original data
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		
		if(spriteCounter > 25)
		{
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}
	}
	
	public void pickUpObject(int i)
	{
		if(i != 999)
		{
			
		}
	}
	
	public void interactNPC(int i)
	{
		if(gp.keyH.enterPressed == true)
		{
			if(i != 999)
			{
				attackCanceled = true;
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
			}
			
		}
		
	}
	
	public void contactMonster(int i)
	{
		if(i != 999)
		{
			if(invincible == false)
			{
				gp.playSoundEffect(6);
				life -= 1;
				invincible = true;
			}
			
		}
	}
	
	public void damageMonster(int i)
	{
		if(i != 999)
		{
			if(gp.monster[i].invincible == false)
			{
				gp.playSoundEffect(5);
				gp.monster[i].life -= 1;
				gp.monster[i].invincible = true;
				gp.monster[i].damageReaction();
				
				if(gp.monster[i].life <= 0)
				{
					gp.monster[i].dying = true;
				}
			}
		}
	
	}
	
	public void draw(Graphics2D g2d)
	{	
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		
		switch(direction) 
		{
			case "up":
				if(attacking == false)
				{
					if(spriteNum == 1)
					{
						image = up1;
					}
					if(spriteNum == 2)
					{
						image = up2;
					}
				}
				if(attacking == true)
				{
					tempScreenY = screenY - gp.tileSize;
					if(spriteNum == 1)
					{
						image = attackUp1;
					}
					if(spriteNum == 2)
					{
						image = attackUp2;
					}
				}
				break;
			case "down":
				if(attacking == false)
				{
					if(spriteNum == 1)
					{
						image = down1;
					}
					if(spriteNum == 2)
					{
						image = down2;
					}
				}
				if(attacking == true)
				{
					if(spriteNum == 1)
					{
						image = attackDown1;
					}
					if(spriteNum == 2)
					{
						image = attackDown2;
					}
				}
				break;
			case "left":
				if(attacking == false)
				{
					if(spriteNum == 1)
					{
						image = left1;
					}
					if(spriteNum == 2)
					{
						image = left2;
					}
				}
				if(attacking == true)
				{
					tempScreenX = screenX - gp.tileSize;
					if(spriteNum == 1)
					{
						image = attackLeft1;
					}
					if(spriteNum == 2)
					{
						image = attackLeft2;
					}
				}
				break;
			case "right":
				if(attacking == false)
				{
					if(spriteNum == 1)
					{
						image = right1;
					}
					if(spriteNum == 2)
					{
						image = right2;
					}
				}
				if(attacking == true)
				{
					if(spriteNum == 1)
					{
						image = attackRight1;
					}
					if(spriteNum == 2)
					{
						image = attackRight2;
					}
				}
				break;
		}
		
		if(invincible == true)
		{
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f));
		}
		
		g2d.drawImage(image, tempScreenX, tempScreenY, null);
		
		// Reset alpha
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

		/* DEBUG
		g2d.setFont(new Font("Arial", Font.PLAIN, 26));
		g2d.setColor(Color.white);
		g2d.drawString("Invincible: " + invincibleCounter, 10, 400);*/
		
	}

}
