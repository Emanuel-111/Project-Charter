package Monster;

import java.util.Random;

import Entity.Entity;
import Main.GamePanel;

public class NPC_Monster extends Entity {
	
	GamePanel gp;

	public NPC_Monster(GamePanel gp) 
	{
		super(gp);
		
		this.gp = gp;
		
		type = 2;
		name = "Green Monster";
		speed = 1;
		maxLife = 5;
		life = maxLife;
		
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 42;
		solidArea.height = 30;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	
	public void getImage()
	{
		up1 = setup("/Monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/Monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		down1 = setup("/Monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/Monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		left1 = setup("/Monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/Monster/greenslime_down_2", gp.tileSize, gp.tileSize);
		right1 = setup("/Monster/greenslime_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/Monster/greenslime_down_2", gp.tileSize, gp.tileSize);
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
	
	public void damageReaction()
	{
		actionLockCounter = 0;
		direction = gp.player.direction;
	}

}
