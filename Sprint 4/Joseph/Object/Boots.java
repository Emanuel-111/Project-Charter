package Object;

import Entity.Entity;
import Main.GamePanel;

public class Boots extends Entity {
		
	public Boots(GamePanel gp)
	{
		super(gp);
		
		name = "Boots";
		down1 = setup("/Objects/boots", gp.tileSize, gp.tileSize);
	}

}
