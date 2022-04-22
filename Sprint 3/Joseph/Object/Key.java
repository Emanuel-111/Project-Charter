package Object;

import Entity.Entity;
import Main.GamePanel;

public class Key extends Entity {
		
	public Key(GamePanel gp)
	{
		super(gp);
				
		name = "Key";
		down1 = setup("/Objects/key", gp.tileSize, gp.tileSize);
	}

}
