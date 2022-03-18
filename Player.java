package platform.maingame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class Player {

	GamePanel panel;
	int x; // X coordinates
	int y; // Y coordinates
	int width; // Width of player
	int height; // Height of player

	double Xspeed; // Speed of the player
	double Yspeed; // Speed of the player

	Rectangle hitbox; // Collision of the player

	boolean keyLeft; // Left key
	boolean keyRight; // Right key
	boolean keyUp; // Up key
	boolean keyDown; // Down key

	boolean mouseClick; // Mouse click

	public Player(int x, int y, GamePanel panel) {

		this.panel = panel;
		this.x = x;
		this.y = y;

		width = 50;
		height = 100;
		hitbox = new Rectangle(x, y, width, height);

	}

	// Set is the speed the character is walking
	// The if statements are there to set speeds in certain scenarios
	public void set() {
		if (keyLeft && keyRight || !keyLeft && !keyRight)
			Xspeed *= 0.8;

		else if (keyLeft && !keyRight)
			Xspeed--;

		else if (keyRight && !keyLeft)
			Xspeed++;

		if (Xspeed > 0 && Xspeed < 0.75)
			Xspeed = 0;

		if (Xspeed < 0 && Xspeed > -0.75)
			Xspeed = 0;

		if (Xspeed > 7)
			Xspeed = 7;

		if (Xspeed < -7)
			Xspeed = -7;

		if (keyUp) {
			hitbox.y++;
			for (Wall wall : panel.walls) {
				if (wall.hitBox.intersects(hitbox))
					;
				Yspeed = -6;
			}
			hitbox.y--;
		}

		Yspeed += 0.3;

		// Horizontal Collision
		hitbox.x += Xspeed;
		for (Wall wall : panel.walls) {
			if (hitbox.intersects(wall.hitBox)) {
				hitbox.x -= Xspeed;
				while (!wall.hitBox.intersects(hitbox)) {
					hitbox.x += Math.signum(Xspeed);
				}
				panel.cameraX += x - hitbox.x; // Camera stays with the player in the x coordinate
				hitbox.x -= Math.signum(Xspeed);
				Xspeed = 0;
				hitbox.x = x;

			}
		}

		// Vertical Collision
		hitbox.y += Yspeed;
		for (Wall wall : panel.walls) {
			if (hitbox.intersects(wall.hitBox)) {
				hitbox.y -= Yspeed;
				while (!wall.hitBox.intersects(hitbox)) {
					hitbox.y += Math.signum(Yspeed);
				}

				hitbox.y -= Math.signum(Yspeed);
				Yspeed = 0;
				y = hitbox.y;
			}
		}

		panel.cameraX += Xspeed;
		y += Yspeed;

		hitbox.x = x;
		hitbox.y = y;

		// Death code
		if (x < 0 ||y > 800)
			panel.reset();
 
	}

	// Draws player in game
	public void draw(Graphics2D gtd) {

		gtd.setColor(Color.BLACK);
		gtd.fillRect(x, y, width, height);
		gtd.setColor(Color.RED);

	}
}
