package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.Tile;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

	//SCREEN SETTINGS
	final int originalTileSize = 16; //16x16 tiles
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; //scaling the screen
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; //768 pixels
	public final int screenHeight = tileSize * maxScreenRow; //576 pixels
	
	//WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	int FPS = 60;
	
	//INSTANTIATING SYSTEM
	KeyHandler keyH = new KeyHandler();
	TileManager tileM = new TileManager(this);
	Sound music = new Sound();
	Sound soundEffects = new Sound();
	public CollisionHandler cHandler = new CollisionHandler(this);
	public AssetHandler aHandler = new AssetHandler(this);
	public UI ui = new UI(this);
	Thread gameThread;
	
	//INSTANTIATING ENTITY AND OBJECT
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[10];
	
	//GamePanel construct
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	//function to set objects in the world and starting music
	public void setupGame() {
		
		aHandler.setObject();
		playMusic(0);
	}

	//function to start game thread
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			
			lastTime = currentTime;
			
			if(delta > 1) {
				
				update();
				repaint();
				delta--;
			}	
		}
	}
	
	public void update() {
		
		player.update();
	}
	
	//calling draw functions here works like layers. draw the world first, then objects and players
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		
		//for objects, we scan the obj array, if somethings there we draw it
		for(int i=0; i < obj.length; i++) {
			if(obj[i] != null) {
				
				obj[i].draw(g2, this);
			}
			
		}
		
		player.draw(g2);
		
		//UI
		
		ui.draw(g2);
		
		g2.dispose();
	}
	
	//function to play background music
	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		//we are looping the music
		music.loop();
	}
	
	//function to stop background music
	public void stopMusic() {
		
		music.stop();
	}
	
	//function to play sound effects (different object than music object
	//so we can play more than one sound at once(pick up sound and bg music))
	public void playSE(int i) {
		
		soundEffects.setFile(i);
		soundEffects.play();
	}
}












