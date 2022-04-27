package Entity;

import TileMap.*;
import SFX.*;

import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

// Player takes from the MapObject class but has its own 
public class Player extends MapObject {
	
	// player stuff
	private int health;
	private int maxHealth;
	private int fire;
	private int maxFire;
	private boolean dead;
	private boolean flinching;
	private long flinchTimer;
	
	private boolean scratching;
	private boolean firing;
	
	//dialogue
	private boolean talking;
	
	// gliding
	private boolean gliding;
	
	// animations
	// Depending on the animation action, a certain amount of frames will be played
	// For example IDLE -> 1; WALKING -> 3; FALLING -> 1, etc...
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = {
		1, 3, 1, 1, 1, 1, 1
	};
	
	// animation actions
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	private static final int GLIDING = 4;
	private static final int FIREBALL = 5;
	private static final int SCRATCHING = 6;
	
	public Player(TileMap tm) {
		
		super(tm);
		
		width = 16; // Width by pixel
		height = 29; // height by pixel
		cwidth = 15; // Collision width
		cheight = 31; // Collision height
		
		moveSpeed = 0.3; // Movement
		maxSpeed = 3.0; // Max speed of the player
		stopSpeed = 0.4; // Stop speed of the player
		fallSpeed = 0.15; // Fall speed of the player
		maxFallSpeed = 4.0; // Max Fall speed of the player
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;
		
		facingRight = true; // Player will face right when the Level1State is first opened
		
		//health = maxHealth = 5;
		//fire = maxFire = 2500;
		
		//fireCost = 200;
		//fireBallDamage = 5;
		//fireBalls = new ArrayList<FireBall>();
		
		//scratchDamage = 8;
		//scratchRange = 40;
		
		// Loads Sprites 
		try {
			
			// This will grab the spritesheet
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Sprites/Studio_Project-4.png"
				)
			);
			
			// This places sprites in an array 
			sprites = new ArrayList<BufferedImage[]>();
			
			// For specific animations, whichever animation action is requested
			// it will look through the spritesheet for where it is.
			
			// For example, JUMPING is the THIRD animation action, so it will look in the third row
			// and find the position it is in with the width and height of the frame 
			for(int i = 0; i < 7; i++) {
				
				BufferedImage[] bi =
					new BufferedImage[numFrames[i]];
				
				for(int j = 0; j < numFrames[i]; j++) {
					
					if(i != 6) {
						bi[j] = spritesheet.getSubimage(
								(j * width),
								(i * height),
								width,
								height
						);
					}
					else {
						bi[j] = spritesheet.getSubimage(
								j * width * 2,
								i * height,
								width,
								height
						);
					}
					
				}
				
				sprites.add(bi);
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);
		
	}
	
	public void setGliding(boolean b) { 
		gliding = b;
	}
	
	public boolean getTalking()
	{
		return talking;
	}
	
	public void setTalking(boolean t)
	{
		talking = t;
	}
	
	private void getNextPosition() {
		
		// movement
		if(left) 
		{
			dx -= moveSpeed;
			if(dx < -maxSpeed) 
			{
				dx = -maxSpeed;
			}
		}
		else if(right) {
			dx += moveSpeed;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		}
		else {
			if(dx > 0) {
				dx -= stopSpeed;
				if(dx < 0) {
					dx = 0;
				}
			}
			else if(dx < 0) {
				dx += stopSpeed;
				if(dx > 0) {
					dx = 0;
				}
			}
		}
		
		// cannot move while attacking, except in air
		if(
		(currentAction == SCRATCHING || currentAction == FIREBALL) &&
		!(jumping || falling)) {
			dx = 0;
		}
		
		// jumping
		if(jumping && !falling) {
			dy = jumpStart;
			falling = true;	
		}
		
		// falling
		if(falling) {
			
			if(dy > 0 && gliding) dy += fallSpeed * 0.1;
			else dy += fallSpeed;
			
			if(dy > 0) jumping = false;
			if(dy < 0 && !jumping) dy += stopJumpSpeed;
			
			if(dy > maxFallSpeed) dy = maxFallSpeed;
			
		}
		
	}
	
	public void update() {
		
		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		// set animation
		if(scratching) {
			if(currentAction != SCRATCHING) {
				currentAction = SCRATCHING;
				animation.setFrames(sprites.get(SCRATCHING));
				animation.setDelay(50);
				width = 60;
			}
		}
		else if(firing) {
			if(currentAction != FIREBALL) {
				currentAction = FIREBALL;
				animation.setFrames(sprites.get(FIREBALL));
				animation.setDelay(100);
				width = 16;
			}
		}
		else if(dy > 0) {
			if(gliding) {
				if(currentAction != GLIDING) {
					currentAction = GLIDING;
					animation.setFrames(sprites.get(GLIDING));
					animation.setDelay(100);
					width = 16;
				}
			}
			else if(currentAction != FALLING) {
				currentAction = FALLING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(100);
				width = 16;
			}
		}
		else if(dy < 0) {
			if(currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(-1);
				width = 16;
			}
		}
		else if(left || right) {
			if(currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(10);
				width = 16;
			}
		}
		else {
			if(currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(10);
				width = 16;
			}
		}
		
		animation.update();
		
		// set direction
		if(currentAction != SCRATCHING && currentAction != FIREBALL) {
			if(right) facingRight = true;
			if(left) facingRight = false;
		}
		
	}
	
	public void draw(Graphics2D g) {
		
		setMapPosition();
		
		// draw player
		if(flinching) {
			long elapsed =
				(System.nanoTime() - flinchTimer) / 1000000;
			if(elapsed / 100 % 2 == 0) {
				return;
			}
		}
		
		if(facingRight) {
			g.drawImage(
				animation.getImage(),
				(int)(x + xmap - width / 2),
				(int)(y + ymap - height / 2),
				null
			);
		}
		else {
			g.drawImage(
				animation.getImage(),
				(int)(x + xmap - width / 2 + width),
				(int)(y + ymap - height / 2),
				-width,
				height,
				null
			);
			
		}
		
	}
	
}