package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
	
	// DEBUG
	boolean checkDrawTime = false;
	
	public KeyHandler(GamePanel gp)
	{
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int code = e.getKeyCode();
		
		// TITLE STATE
		if(gp.gameState == gp.titleState)
		{
			titleState(code);
		}
		
		// PLAY STATE
		else if(gp.gameState == gp.playState)
		{
			playState(code);
		}
		
		// PAUSE STATE
		else if(gp.gameState == gp.pauseState)
		{
			pauseState(code);
		}
		
		// DIALOGUE STATE
		else if(gp.gameState == gp.dialogueState)
		{
			dialogueState(code);
		}
		
		// CHARACTER STATE
		else if (gp.gameState == gp.characterState)
		{
			characterState(code);
		}

	}
	
	public void titleState(int code)
	{
		if(code == KeyEvent.VK_W)
		{
			gp.userInterface.commandNum--;
			
			if(gp.userInterface.commandNum < 0)
			{
				gp.userInterface.commandNum = 2;
			}
		}
		
		if(code == KeyEvent.VK_S)
		{
			gp.userInterface.commandNum++;
			
			if(gp.userInterface.commandNum > 2)
			{
				gp.userInterface.commandNum = 0;
			}
		}
		
		if(code == KeyEvent.VK_ENTER)
		{
			if(gp.userInterface.commandNum == 0)
			{
				gp.gameState = gp.playState;
				gp.playMusic(0);
			}
			
			if(gp.userInterface.commandNum == 1)
			{
				// add later
			}
			
			if(gp.userInterface.commandNum == 2)
			{
				System.exit(0);
			}
		}
	}
	
	public void playState(int code)
	{
		if(code == KeyEvent.VK_W)
		{
			upPressed = true;
		}
		if(code == KeyEvent.VK_S)
		{
			downPressed = true;
		}
		if(code == KeyEvent.VK_A)
		{
			leftPressed = true;
		}
		if(code == KeyEvent.VK_D)
		{
			rightPressed = true;
		}
		if(code == KeyEvent.VK_P)
		{
			gp.gameState = gp.pauseState;
		}
		if(code == KeyEvent.VK_C)
		{
			gp.gameState = gp.characterState;
		}
		if(code == KeyEvent.VK_ENTER)
		{
			enterPressed = true;
		}
		
		// DEBUG
		if(code == KeyEvent.VK_T)
		{
			if(checkDrawTime == false)
			{
				checkDrawTime = true;
			}
			else if(checkDrawTime == true)
			{
				checkDrawTime = false;
			}
		}
	}
	
	public void pauseState(int code)
	{
		if(code == KeyEvent.VK_P)
		{
			gp.gameState = gp.playState;
		}
	}
	
	public void dialogueState(int code)
	{
		if(code == KeyEvent.VK_ENTER)
		{
			gp.gameState = gp.playState;
		}
	}
	
	public void characterState(int code)
	{
		if(code == KeyEvent.VK_C)
		{
			gp.gameState = gp.playState;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{	
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W)
		{
			upPressed = false;
		}
		if(code == KeyEvent.VK_S)
		{
			downPressed = false;
		}
		if(code == KeyEvent.VK_A)
		{
			leftPressed = false;
		}
		if(code == KeyEvent.VK_D)
		{
			rightPressed = false;
		}
		
	}

}
