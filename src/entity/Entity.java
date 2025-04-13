package entity;

import java.awt.image.BufferedImage;

/**
 * parent class for entities
 * @author rashad mustafa
 *
 */
public class Entity {
	/**
	 * the current position of the Entity
	 */
	public int x, y;
	
	int targetTileX, targetTileY;
	
	/**
	 * Entity moving speed 
	 */
	public int speed = 2;
	
	/**
	 * Image for each direction 
	 */
	public BufferedImage upImage, downImage, rightImage, leftImage;

	public enum DIRECTIONS {
		UP,DOWN,LEFT,RIGHT
	}
	
	/**
	 * describes the current direction of the Entity 
	 */
	public DIRECTIONS direction;
	
	/**
	 * will be changed to true if the collision on the targeted tile is true
	 */
	public boolean collisionOn = false;
	
	
	
	public void move(int targetTileX, int targetTileY) {
		this.targetTileX = targetTileX;
		this.targetTileY = targetTileY;
	}
}
