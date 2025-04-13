package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Game_Controller;
import main.Game_Controller.ACTION;
import main.KeyHandler;
import main.KeyHandler.KeyACTION;
import main.Tools;

/**
 * Player class represents the player entity in the game.
 */
public class Player extends Entity {

	/** Reference to the Game_Controller. */
	Game_Controller gc;

	/** Reference to the KeyHandler. */
	KeyHandler keyH;

	/** Number of keys collected so far by the player. */
	public int hasKey;

	/**
	 * Constructor for the Player class.
	 *
	 * @param gc   The Game_Controller instance.
	 * @param keyH The KeyHandler instance.
	 */
	public Player(Game_Controller gc, KeyHandler keyH) {
		this.gc = gc;
		this.keyH = keyH;
		setDefaultValues();
		getPlayerImage();
	}

	/** Sets the default values for the player's initial position and direction. */
	public void setDefaultValues() {
		x = 1;
		y = 1;
		direction = DIRECTIONS.DOWN;
	}

	/** sets up player images for each of the directions. */
	public void getPlayerImage() {
		upImage = setupImage("player_up");
		downImage = setupImage("player_down");
		rightImage = setupImage("player_right");
		leftImage = setupImage("player_left");
	}

	/**
	 * loads the image for the player.
	 *
	 * @param imageName The name of the image file.
	 * @return The loaded BufferedImage.
	 */
	public BufferedImage setupImage(String imageName) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
			image = Tools.scaleImage(image, gc.tileSize, gc.tileSize);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	/**
	 * Updates the player's position and performs collision checks based on user
	 * input.
	 */
	public void update() {
		if (keyH.keyDoes != KeyACTION.NON) {

			switch (keyH.keyDoes) {
			case goUP:
				direction = DIRECTIONS.UP;
				break;
			case goDOWN:
				direction = DIRECTIONS.DOWN;
				break;
			case golEFT:
				direction = DIRECTIONS.LEFT;
				break;
			case goRIGHT:
				direction = DIRECTIONS.RIGHT;
				break;
			default:
				break;
			}

			// CHECK TILE COLLISION
			collisionOn = false;
			gc.cCheck.checkTile(this);

			// CHECK OBJECT COLLISION
			int objIndex = gc.cCheck.checkObject(this);
			pickUpObject(objIndex);

			// IF COLLISION IS FALSE, PLAYER CAN MOVE
			if (!collisionOn) {
				switch (direction) {
				case UP:
					y -= 1;
					break;
				case DOWN:
					y += 1;
					break;
				case RIGHT:
					x += 1;
					break;
				case LEFT:
					x -= 1;
					break;
				default:
					break;
				}

			} else {
				gc.playSE(2);
				System.out.println("Collision!!!!");
			}
		}
	}

	/**
	 * Picks up an object based and performs corresponding actions.
	 *
	 * @param i The index of the object type in the objects array in Game_Controller.
	 */
	public void pickUpObject(int i) {
		if (i != 999) {
			String objectName = gc.obj[i].name;

			switch (objectName) {
			case "Key":
				gc.playSE(1);
				gc.obj[i] = null;
				hasKey++;
				gc.ui.showMessage("Cool, you got the Key!");
				break;
			case "Door":
				// TODO Won the game
				if (hasKey > 0) {
					System.out.println("YOU WON THE GAME!!!");
					gc.fireEvent(ACTION.WIN);

				} else {
					gc.ui.showMessage("YOU NEED AT LEAST ONE KEY");
				}
				break;
			case "Flame":
				gc.playSE(1);
				gc.givenLight += 2;
				gc.light.resizeFilter();
				gc.obj[i] = null;
				gc.ui.showMessage("now you can see more");
				break;
			}
		}
	}
}