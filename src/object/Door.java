package object;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Door class represents a door object in the game.
 */
public class Door extends SuperObject {
	
	/**
     * Constructor for the Door class.
     * Initializes the name, image, and collision properties of the door.
     */
	public Door() {
		name = "Door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}
