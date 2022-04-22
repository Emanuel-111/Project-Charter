package Object;

import Entity.Entity;
import Main.GamePanel;

public class Heart extends Entity {
		
	public Heart(GamePanel gp)
	{
		super(gp);
		
		name = "Heart";
		image = setup("/Objects/heart_full", gp.tileSize, gp.tileSize);
		image2 = setup("/Objects/heart_half", gp.tileSize, gp.tileSize);
		image3 = setup("/Objects/heart_blank", gp.tileSize, gp.tileSize);
	}

}
