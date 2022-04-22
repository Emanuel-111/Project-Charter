package Entity;

import java.util.Random;
import Main.GamePanel;

public class NPC_OldMan extends Entity {
	
	public NPC_OldMan(GamePanel gp)
	{
		super(gp);
		
		direction = "down";
		speed = 1;
		
		getImage();
		setDialogue();
	}

	public void getImage()
	{
		up1 = setup("/NPC/oldman_up_1", gp.tileSize, gp.tileSize);
		up2 = setup("/NPC/oldman_up_2", gp.tileSize, gp.tileSize);
		down1 = setup("/NPC/oldman_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/NPC/oldman_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/NPC/oldman_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/NPC/oldman_left_2", gp.tileSize, gp.tileSize);
		right1 = setup("/NPC/oldman_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/NPC/oldman_right_2", gp.tileSize, gp.tileSize);
	}
	
	public void setDialogue()
	{
		dialogues[0] = "Hello, Friend.";
		dialogues[1] = "Have you come to this island to find \ntreasure?";
		dialogues[2] = "Many years ago, I was an extraordinary \nwizard. Now, I am too old to take an \nadventure.";
		dialogues[3] = "Well, good luck to you.";
	}
	
	public void setAction()
	{
		actionLockCounter++;
		
		if(actionLockCounter == 120)
		{
			Random random = new Random();
			int i = random.nextInt(100) + 1; // pick up a number from 1 to 100
			
			if(i <= 25)
			{
				direction = "up";
			}
			if(i > 25 && i <= 50)
			{
				direction = "down";
			}
			if(i > 50 && i <= 75)
			{
				direction = "left";
			}
			if(i > 75 && i <= 100)
			{
				direction = "right";
			}
			
			actionLockCounter = 0;
		}
		
	}
	
	public void speak()
	{
		// Do this character specific stuff
		
		super.speak();
	}
	
}
