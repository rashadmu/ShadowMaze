package main;

import entity.Entity;

/**
 * The CollisionChecker class is responsible for checking collisions between entities and game objects.
 */
public class CollisionChecker {
	
	/** The Game_Controller instance used for collision checking. */
	Game_Controller gc;

	/**
     * Constructs a CollisionChecker with the specified Game_Controller instance.
     *
     * @param gc The Game_Controller instance for collision checking.
     */
	public CollisionChecker(Game_Controller gc) {
		this.gc = gc;
	}

	/**
     * Checks for collisions with tiles based on the entity's direction.
     *
     * @param entity The Entity for which collision with tiles is checked.
     */
	public void checkTile(Entity entity) {
		int tileNumber;
		switch (entity.direction) {
		case UP:
			tileNumber = gc.tileM.mapTileNum[entity.x][entity.y - 1];

			if (gc.tileM.tile[tileNumber].collinsion == true) {
				entity.collisionOn = true;
			}
			break;
		case DOWN:
			tileNumber = gc.tileM.mapTileNum[entity.x][entity.y + 1];

			if (gc.tileM.tile[tileNumber].collinsion == true) {
				entity.collisionOn = true;
			}
			break;
		case RIGHT:
			tileNumber = gc.tileM.mapTileNum[entity.x + 1][entity.y];

			if (gc.tileM.tile[tileNumber].collinsion == true) {
				entity.collisionOn = true;
			}
			break;
		case LEFT:
			tileNumber = gc.tileM.mapTileNum[entity.x - 1][entity.y];

			if (gc.tileM.tile[tileNumber].collinsion == true) {
				entity.collisionOn = true;
			}
			break;
		}

	}
	
	/**
     * Checks for collisions with game objects and returns the index of the collided object.
     *
     * @param entity The Entity for which collision with game objects is checked.
     * @return The index of the collided object, or 999 if no collision occurs.
     */
	public int checkObject(Entity entity) {
		int index = 999;
		for(int i = 0; i < gc.obj.length; i++ ) {
			if(gc.obj[i] != null) {
				switch (entity.direction) {
				case UP:
					if (entity.x == gc.obj[i].x && entity.y -1 == gc.obj[i].y) {
						if(gc.obj[i].collision == true) {
							index = i;
						}
					}
					break;
				case DOWN:
					if (entity.x == gc.obj[i].x && entity.y +1 == gc.obj[i].y) {
						if(gc.obj[i].collision == true) {
							index = i;
						}
					}
					break;
				case RIGHT:
					if (entity.x + 1 == gc.obj[i].x && entity.y == gc.obj[i].y) {
						if(gc.obj[i].collision == true) {
							index = i;
						}
					}
					break;
				case LEFT:
					if (entity.x - 1== gc.obj[i].x && entity.y == gc.obj[i].y) {
						if(gc.obj[i].collision == true) {
							index = i;
						}
					}
					break;
				}
				
			}
		}
		
		return index;
		
	}
}
