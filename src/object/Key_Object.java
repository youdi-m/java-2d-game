package object;

import java.io.IOException;
import javax.imageio.ImageIO;

public class Key_Object extends SuperObject{
	
	public Key_Object() {
		
		name = "Key";
		
		try {
			
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
