package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Chest_Object extends SuperObject{
	
	public Chest_Object() {
		
		
		name = "Chest";
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
