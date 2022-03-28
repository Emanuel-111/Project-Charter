package platform.maingame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	Image img;

	public MainFrame() {
			
		img = new ImageIcon("Landscape.png").getImage();
		
		GamePanel panel = new GamePanel();
		panel.setLocation(0, 0);
		panel.setSize(this.getSize());
		panel.setVisible(true);
		this.add(panel);

		addKeyListener(new KeyChecker(panel));

	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		Graphics2D gtd = (Graphics2D) g;
		gtd.drawImage(img, 50, 50, null);
		gtd.setColor(Color.BLUE);
		gtd.drawImage(img, 0, 0, null);
	}
}
