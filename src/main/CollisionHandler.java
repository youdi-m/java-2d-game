package main;

import entity.Entity;

public class CollisionHandler {

	GamePanel gp;
	
	//collision handler construct
	public CollisionHandler (GamePanel gp) {
		
		this.gp = gp;
	}
	
	//function to set player collision box
	public void checkTile(Entity entity) {
		
		//these variables are used to check where the entity's collision box is
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		//checking which direction the player is colliding
		switch (entity.direction) {
		
		case "up":
			
			//predicting where player is going to be
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			
			//setting the tiles we are checking to his left and right shoulders
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				
				entity.collisionOn = true;
			}
			break;
		case "down":
			
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
			
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				
				entity.collisionOn = true;
			}
			break;
		case "left":
			
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;

			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				
				entity.collisionOn = true;
			}
			break;
		case "right":

			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;

			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				
				entity.collisionOn = true;
			}
			break;
		}		
	}
	
	//function to check what object were colliding with
	public int checkObject(Entity entity, boolean player) {
		
		int index = 999;
		
		for(int i=0; i < gp.obj.length; i++) {
			
			//checking if what were colliding with is actually an object
			if(gp.obj[i] != null) {
				
				//get entity's solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				//get the object's solid area a position
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				//checking where we are in relation to what were colliding with
				switch(entity.direction) {
				
				case "up":
					entity.solidArea.y -= entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						
						if(gp.obj[i].collision == true) {
							
							entity.collisionOn = true;
						}
						if(player == true) {
							
							index = i;
						}
					}
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						
						if(gp.obj[i].collision == true) {
							
							entity.collisionOn = true;
						}
						if(player == true) {
							
							index = i;
						}
					}
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						
						if(gp.obj[i].collision == true) {
							
							entity.collisionOn = true;
						}
						if(player == true) {
							
							index = i;
						}
					}
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						
						if(gp.obj[i].collision == true) {
							
							entity.collisionOn = true;
						}
						if(player == true) {
							
							index = i;
						}
					}
					break;
				}
				
				//resetting solid area positions
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
}







