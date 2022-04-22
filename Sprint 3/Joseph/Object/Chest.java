package Object;

import Entity.Entity;
import Main.GamePanel;

public class Chest extends Entity {
		
	public Chest(GamePanel gp)
	{
		super(gp);
		
		name = "Chest";
		down1 = setup("/Objects/chest", gp.tileSize, gp.tileSize);
	}
		
}
