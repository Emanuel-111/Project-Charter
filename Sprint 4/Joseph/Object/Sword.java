package Object;

import Entity.Entity;
import Main.GamePanel;

public class Sword extends Entity {

	public Sword(GamePanel gp) 
	{
		super(gp);
		
		name = "Normal Sword";
		down1 = setup("/Objects/sword_normal", gp.tileSize, gp.tileSize);
		attackValue = 1;		
	}

}
