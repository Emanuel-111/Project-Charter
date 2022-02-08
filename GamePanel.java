package platform.maingame;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class GamePanel extends JPanel implements KeyListener, ActionListener {

	Player player; // player
	ArrayList<Wall> walls = new ArrayList<>();
	
	Timer gameTimer; // game timer
	
	public GamePanel() {
		
	player = new Player(400, 300, this);
	
	reset();
	
	makeWalls(50);
		
	gameTimer = new Timer();
	gameTimer.schedule(new TimerTask() {
		
		public void run()
		{
			
			player.set();
			repaint();
			
		}
	
	}, 0, 17);
		
	}
	
	public void reset()
	{
		player.x = 200;
		player.y = 150;
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
		for (Wall wall: walls) 
			wall.draw(gtd);
		
	}
	
	public void makeWalls(int offset)
	{
		for(int i = 50; i < 650; i+=50)
		{
			walls.add(new Wall(i, 600, 50, 50));
		}
		walls.add(new Wall(50, 550, 50, 50));
		walls.add(new Wall(50, 500, 50, 50));
		walls.add(new Wall(50, 450, 50, 50));
		walls.add(new Wall(50, 550, 50, 50));
		walls.add(new Wall(50, 550, 50, 50));
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'a')
			player.keyLeft = true;
		
		if (e.getKeyChar() == 'd')
			player.keyRight = true;
		
		if (e.getKeyChar() == 'w')
			player.keyUp = true;
		
		if (e.getKeyChar() == 's')
			player.keyDown = true;
 		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyChar() == 'a')
			player.keyLeft = false;
		
		if (e.getKeyChar() == 'd')
			player.keyRight = false;
		
		if (e.getKeyChar() == 'w')
			player.keyUp = false;
		
		if (e.getKeyChar() == 's')
			player.keyDown = false;
		
	}

	

}
