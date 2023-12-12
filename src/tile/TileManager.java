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
	Tile[] tile;
	
	//2D array to store map data
	int mapTileNum[][];
	
	//tileManager construct
	public TileManager(GamePanel gp) {
		
		this.gp = gp;
		
		tile = new Tile[10];
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		
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
			
			//water tile
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			
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
			
			while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
				
				//read 1 line from text file
				String line = br.readLine();
				
				while(col < gp.maxScreenCol) {
					
					//splitting numbers using a space to get data (0 = grass, 1 = wall, etc...)
					String numbers[] = line.split(" ");
					
					//parsing from string to int to use it
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				
				//once we have drawn an entire horizontal line
				//we reset and draw the next one under it
				if(col == gp.maxScreenCol) {
					
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
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
			
			int tileNum = mapTileNum[col][row];
			
			g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
			col++;
			x += gp.tileSize;
			
			if(col == gp.maxScreenCol) {
				
				col = 0;
				x = 0;
				row++;
				y += gp.tileSize;
			}
		}
	}
}








