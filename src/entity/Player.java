package entity;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UI;

public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;
	
	//these indicate where we DRAW the player
	public final int screenX;
	public final int screenY;
	public int numOfKeys = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		//adding collision rectangle to player
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 25;
		worldY = gp.tileSize * 24;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
		}catch(IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		//checking if a button is being pressed then setting player direction
		if(keyH.upPressed == true || keyH.downPressed == true ||
				keyH.leftPressed == true || keyH.rightPressed == true) {
			
			if(keyH.upPressed == true) {
				direction = "up";
			}
			else if(keyH.downPressed == true) {
				direction = "down";
			}
			else if (keyH.leftPressed == true) {
				direction = "left";
			}
			else if (keyH.rightPressed == true) {
				direction = "right";
			}
			
			//checking tile collision
			collisionOn = false;
			gp.cHandler.checkTile(this);
			
			//check object collision
			int objIndex = gp.cHandler.checkObject(this, true);
			pickUpObject(objIndex);
			
			//if collision is false, player can move
			if(collisionOn == false) {
				
				switch(direction) {
				
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
			}
			
			spriteCounter++;
			
			//updating player sprite variables for animation
			if(spriteCounter > 12) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		
	}
	
	//setting interactions with each item
	public void pickUpObject(int i) {
		
		if(i != 999) {
			
			String objectNameString = gp.obj[i].name;
			
			switch(objectNameString) {
			
			case "Key":
				
				gp.playSE(1);
				numOfKeys++;
				gp.obj[i] = null;
				gp.ui.showMessage("Key +1");
				break;
			case "Door":
				
				if(numOfKeys > 0) {
					
					gp.playSE(4);
					numOfKeys--;
					gp.obj[i] = null;
					gp.ui.showMessage("Door Unlocked");
				}
				break;
			case "Chest":
				
				if(numOfKeys > 0) {
					
					gp.ui.gameFinished = true;
					gp.stopMusic();
					gp.playSE(2);
					numOfKeys--;
					gp.obj[i] = null;
					gp.ui.showMessage("Chest Unlocked");
				}
				break;
			case "Boots":

				gp.playSE(3);
				speed = 6;
				gp.obj[i] = null;
				gp.ui.showMessage("Boots Aquired");
				break;
			}
		}
	}
	
	//drawing player sprite on screen
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		
		case "up":
			if(spriteNum == 1) {
				image = up1;
			}
			if(spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if(spriteNum == 1) {
				image = down1;
			}
			if(spriteNum == 2) {
				image = down2;
			}
			break;
		case "left":
			if(spriteNum == 1) {
				image = left1;
			}
			if(spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if(spriteNum == 1) {
				image = right1;
			}
			if(spriteNum == 2) {
				image = right2;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
}















