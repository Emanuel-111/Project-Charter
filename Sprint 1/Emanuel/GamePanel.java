package platform.maingame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel implements KeyListener, ActionListener, MouseListener {

	Player player; // player
	ArrayList<Wall> walls = new ArrayList<>();

	int cameraX;

	Timer gameTimer; // game timer

	public GamePanel() {

		player = new Player(400, 300, this);

		reset();

		makeWalls(50);

		gameTimer = new Timer();
		gameTimer.schedule(new TimerTask() {

			public void run() {

				player.set();
				for (Wall wall : walls) {
					wall.set(cameraX);
				}

				repaint();

			}

		}, 0, 17);

	}

	public void reset() {
		player.x = 200;
		player.y = 150;
		cameraX = 150;
		player.Xspeed = 0;
		player.Yspeed = 0;
		walls.clear();
		int offset = 50;

		makeWalls(offset);
	}

	public void paint(Graphics g) 
	{
		super.paint(g);

		Graphics2D gtd = (Graphics2D) g;

		player.draw(gtd);
		for (Wall wall : walls)
			wall.draw(gtd);
	}

	public void makeWalls(int offset) {
		int s = 50;
		Random rand = new Random();
		int index = rand.nextInt(1);
		
		// X Position, Y position, Width, Height
		walls.add(new Wall(300, 600, 10000, 400));
		
		// All platforms
		walls.add(new Wall(600, 400, 50, 50));
		walls.add(new Wall(800, 500, 300, 100));
		//walls.add(new Wall(1100, 100, 50, 1000));
		walls.add(new Wall(900, 300, 200, 50));
		walls.add(new Wall(1200, 300, 150, 50));

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A')
			player.keyLeft = true;

		if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D')
			player.keyRight = true;

		if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W')
			player.keyUp = true;

		if (e.getKeyChar() == 's' || e.getKeyChar() == 'S')
			player.keyDown = true;

		if (e.getKeyChar() == 'r' || e.getKeyChar() == 'R')
			reset();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A')
			player.keyLeft = false;

		if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D')
			player.keyRight = false;

		if (e.getKeyChar() == 'w' || e.getKeyChar() == 'W')
			player.keyUp = false;

		if (e.getKeyChar() == 's' || e.getKeyChar() == 'S')
			player.keyDown = false;

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
