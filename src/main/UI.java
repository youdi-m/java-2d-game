package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.Key_Object;

public class UI {

	//variables
	GamePanel gp;
	Font arial_40;
	BufferedImage keyImage;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	
	//UI construct
	public UI(GamePanel gp) {
		
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		Key_Object key = new Key_Object();	
	    keyImage = key.image;
	}
	
	//function to show temporary messages
	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}
	
	//function to draw the text/images
	public void draw(Graphics2D g2) {
		
		g2.setFont(arial_40);
		
		//checks if game is complete
		if(gameFinished == true) {
			
			g2.setColor(Color.yellow);
			
			String text;
			int textLength;
			int x;
			int y;
			
			text = "Congratulations!";
			textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			
			x = gp.screenWidth/2 - textLength/2;
			y = gp.screenHeight/2 - (gp.tileSize * 2);
			g2.drawString(text, x, y);
			
		}else {
			
			g2.setColor(Color.white);
			g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
			g2.drawString("x " + gp.player.numOfKeys, 74, 5);
			
			//Display Message
			if(messageOn == true) {
				
				g2.setFont(g2.getFont().deriveFont(25f));
				g2.drawString(message, gp.tileSize, gp.tileSize * 11);
				
				messageCounter++;
				
				//120 frames so the text disappears after 2 seconds
				if(messageCounter > 120) {
					
					messageCounter = 0;
					messageOn = false;
				}
			}	
		}
	}
}
