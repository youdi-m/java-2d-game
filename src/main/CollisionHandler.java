package main;

import entity.Entity;

public class CollisionHandler {

	GamePanel gp;
	
	public CollisionHandler (GamePanel gp) {
		
		this.gp = gp;
	}
	
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
}
