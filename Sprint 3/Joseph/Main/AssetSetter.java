package Main;

import Entity.NPC_OldMan;
import Monster.NPC_Monster;
import Object.Door;
import Object.Key;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp)
	{
		this.gp = gp;
	}
	
	public void setObject()
	{
		
	}
	
	public void setNPC()
	{
		gp.npc[0] = new NPC_OldMan(gp);
		gp.npc[0].worldX = gp.tileSize * 21;
		gp.npc[0].worldY = gp.tileSize * 21;
	}
	
	public void setMonster()
	{
		gp.monster[0] = new NPC_Monster(gp);
		gp.monster[0].worldX = gp.tileSize * 23;
		gp.monster[0].worldY = gp.tileSize * 36;
		
		gp.monster[1] = new NPC_Monster(gp);
		gp.monster[1].worldX = gp.tileSize * 23;
		gp.monster[1].worldY = gp.tileSize * 37;
	}

}
