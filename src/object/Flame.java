package object;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Flame class represents a flame object in the game.
 */
public class Flame extends SuperObject {

	/**
	 * Constructor for the Flame class. Initializes the name, image, and collision
	 * properties of the flame.
	 */
	public Flame() {
		name = "Flame";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/flame.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}

}
