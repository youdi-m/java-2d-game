package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	
	//array to store tile info, size of array is amount of tiles, this can be changed to introduce more tiles
	public Tile[] tile;
	
	//2D array to store map data
	public int mapTileNum[][];
	
	//tileManager construct
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/map01.txt");
	}
	
	//function to create new tile object, add them to tile array and give them an image
	public void getTileImage() {
		
		try {
			
			//grass tile
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
			
			//wall tile
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[1].collision = true;
			
			//water tile
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			tile[2].collision = true;
			
			//earth tile
			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));
			
			//sand tile
			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
			
			//tree tile
			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			tile[5].collision = true;
			
		}catch(IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) {
		
		try {
			
			//importing map data from text file
			InputStream is = getClass().getResourceAsStream(filePath);
			
			//reading map01.txt
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				//read 1 line from text file
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					
					//splitting numbers using a space to get data (0 = grass, 1 = wall, etc...)
					String numbers[] = line.split(" ");
					
					//parsing from string to int to use it
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				
				//once we have drawn an entire horizontal line
				//we reset and draw the next one under it
				if(col == gp.maxWorldCol) {
					
					col = 0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e) {
			
		}
	}
	
	//function to draw the tiles onto the screen, gets called in the GamePanel
	//g2.drawImage(tile, x location, y location, image width, image height, image observer(null))
	public void draw(Graphics2D g2) {
		
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			//getting tile world positions
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			
			//using player location relative to world x.y locations to know where to draw each tile on screen then we offset player difference
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			//this huge if statement checks if the tile is within our screen's view.
			//if it is, we draw the tile on the screen.
			//basically, we are asking our computer to only draw what WE can actually see.
			//this helps with performance
			if(worldX + gp.tileSize> gp.player.worldX - gp.player.screenX &&
			   worldX - gp.tileSize< gp.player.worldX + gp.player.screenX &&
			   worldY + gp.tileSize> gp.player.worldY - gp.player.screenY &&
			   worldY - gp.tileSize< gp.player.worldY + gp.player.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
			worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				
				worldCol = 0;
				worldRow++;
			}
		}
	}
}








