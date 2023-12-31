package main;

import object.OBJ_Boot;
import object.OBJ_Chest;
import object.OBJ_Door;
import object.OBJ_Key;

public class AssetSetter {
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp =gp;
	}
public void setObject() {
		
		gp.obj[0] = new OBJ_Key(gp);

		gp.obj[0].worldX = 23 * gp.tileSize;
		gp.obj[0].worldY = 7 * gp.tileSize;
		
		gp.obj[1] = new OBJ_Key(gp);
		gp.obj[1].worldX = 23 * gp.tileSize;
		gp.obj[1].worldY = 40 * gp.tileSize;
		
		gp.obj[2] = new OBJ_Key(gp);
		gp.obj[2].worldX = 38 * gp.tileSize;
		gp.obj[2].worldY = 9 * gp.tileSize;
		
		gp.obj[3] = new OBJ_Key(gp);
		gp.obj[3].worldX = 14 * gp.tileSize;
		gp.obj[3].worldY = 34 * gp.tileSize;
		
		//
		gp.obj[4] = new OBJ_Door(gp);
		gp.obj[4].worldX = 30 * gp.tileSize;
		gp.obj[4].worldY = 40 * gp.tileSize;
		
		gp.obj[5] = new OBJ_Door(gp);
		gp.obj[5].worldX = 10 * gp.tileSize;
		gp.obj[5].worldY = 12 * gp.tileSize;
		
		gp.obj[6] = new OBJ_Door(gp);
		gp.obj[6].worldX = 8 * gp.tileSize;
		gp.obj[6].worldY = 28 * gp.tileSize;
	
		gp.obj[6] = new OBJ_Door(gp);
		gp.obj[6].worldX = 12 * gp.tileSize;
		gp.obj[6].worldY = 23 * gp.tileSize;
	 	
		gp.obj[7] = new OBJ_Chest(gp);
		gp.obj[7].worldX = 10 * gp.tileSize;
		gp.obj[7].worldY = 7 * gp.tileSize;
		
		gp.obj[8] = new OBJ_Boot(gp);
		gp.obj[8].worldX = 37 * gp.tileSize;
		gp.obj[8].worldY = 42 * gp.tileSize;
		
		gp.obj[9] = new OBJ_Boot(gp);
		gp.obj[9].worldX = 9 * gp.tileSize;
		gp.obj[9].worldY = 34 * gp.tileSize;
	
}
}
