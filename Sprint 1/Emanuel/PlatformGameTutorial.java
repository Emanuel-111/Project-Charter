package platform.maingame;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

// PlatfromGameTutorial is the tutorial that the game operates through
// Running this class runs the game
public class PlatformGameTutorial {

	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setSize(1440, 800);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((int) (screenSize.getWidth() / 2 - screenSize.getSize().getWidth() / 2),
				(int) (screenSize.getHeight() / 2 - screenSize.getSize().getHeight() / 2));

		frame.setResizable(false);
		frame.setTitle("Platform");
		frame.setVisible(true);

	}
}
