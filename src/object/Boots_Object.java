package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Boots_Object extends SuperObject{

	public Boots_Object() {
		
		name = "Boots";
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
