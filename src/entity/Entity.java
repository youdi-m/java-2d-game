package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	
	//position and speed variables
	public int worldX, worldY;
	public int speed;
	
	//images and direction variable
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	
	//variables for animation
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	//rectangle for collision
	public Rectangle solidArea;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
}
