package Object;

import Entity.Entity;
import Main.GamePanel;

public class Shield extends Entity {

	public Shield(GamePanel gp) 
	{
		super(gp);
		
		name = "Wooden Shield";
		down1 = setup("/Objects/shield_wood", gp.tileSize, gp.tileSize);
		defenseValue = 1;		
	}
	
}
