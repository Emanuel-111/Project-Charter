package platform.maingame;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class PlatformGameTutorial {

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setSize(700, 700);
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((int)(screenSize.getWidth()/2 - screenSize.getSize().getWidth()/2), 
						 (int) (screenSize.getHeight()/2 - screenSize.getSize().getHeight()/2));
		
		frame.setResizable(false);
		frame.setTitle("Platform");
		frame.setVisible(true);
		
		
		
	}

}
