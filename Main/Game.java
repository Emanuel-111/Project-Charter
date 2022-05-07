package Main;

import javax.swing.JFrame;

public class Game {
	
	/*
	 * This is the main file that runs the game
	 */
	public static void main(String[] args)
	{
		//GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		
		
		//Creates the JFrame (GUI) for the game to run on
		JFrame window = new JFrame("Project Normic");
		
		//GraphicsDevice device = ge.getDefaultScreenDevice();
		
		// No need to worry about this.
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
		//device.setFullScreenWindow(window);
		
	}

	
}
