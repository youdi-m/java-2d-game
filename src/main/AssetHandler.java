package main;

import object.Boots_Object;
import object.Chest_Object;
import object.Door_Object;
import object.Key_Object;

public class AssetHandler {

	GamePanel gp;
	
	public AssetHandler(GamePanel gp) {
		
		this.gp = gp;
	}
	
	//function used to place the objects in the world
	public void setObject() {
		
		gp.obj[0] = new Key_Object();
		gp.obj[0].worldX = 12 * gp.tileSize;
		gp.obj[0].worldY = 32 * gp.tileSize;
		
		gp.obj[1] = new Key_Object();
		gp.obj[1].worldX = 36 * gp.tileSize;
		gp.obj[1].worldY = 13 * gp.tileSize;
		
		gp.obj[2] = new Door_Object();
		gp.obj[2].worldX = 2 * gp.tileSize;
		gp.obj[2].worldY = 4 * gp.tileSize;
		
		gp.obj[3] = new Chest_Object();
		gp.obj[3].worldX = 33 * gp.tileSize;
		gp.obj[3].worldY = 44 * gp.tileSize;
		
		gp.obj[4] = new Boots_Object();
		gp.obj[4].worldX = 27 * gp.tileSize;
		gp.obj[4].worldY = 27 * gp.tileSize;
	}
}
