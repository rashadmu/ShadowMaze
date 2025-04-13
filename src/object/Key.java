package object;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Key class represents a key object in the game.
 */
public class Key extends SuperObject{

    /**
     * Constructor for the Key class.
     * Initializes the name, image, and collision properties of the key.
     */
	public Key() {
		name = "Key";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
