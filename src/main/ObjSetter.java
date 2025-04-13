package main;

import object.*;

/**
 * The ObjSetter class is responsible for setting up game objects based on the current game level.
 */
public class ObjSetter {
	
    /** The Game_Controller instance to which game objects are associated. */
	Game_Controller gc;
	
	/**
     * Constructs an ObjSetter with the specified Game_Controller instance.
     *
     * @param gc The Game_Controller instance to associate with this ObjSetter.
     */
	public ObjSetter(Game_Controller gc) {
		this.gc = gc;
	}
	
	 /**
     * Sets up game objects based on the current game level.
     */
	public void setObject() {
		switch(Game_Controller.level) {
		case 1:
			gc.obj[0] = new Key();
			gc.obj[0].x = 12;
			gc.obj[0].y = 8;
			
			gc.obj[1] = new Door();
			gc.obj[1].x = 3;
			gc.obj[1].y = 8;
			
			gc.obj[2] = new Flame();
			gc.obj[2].x = 14;
			gc.obj[2].y	= 1;
			break;
		case 2:
			gc.obj[0] = new Key();
			gc.obj[0].x = 12;
			gc.obj[0].y = 10;
			
			gc.obj[1] = new Door();
			gc.obj[1].x = 1;
			gc.obj[1].y = 3;
			
			gc.obj[2] = new Flame();
			gc.obj[2].x = 12;
			gc.obj[2].y	= 7;
			break;
		case 3:
			gc.obj[0] = new Key();
			gc.obj[0].x = 5;
			gc.obj[0].y = 17;
			
			gc.obj[1] = new Door();
			gc.obj[1].x = 23;
			gc.obj[1].y = 9;
			
			gc.obj[2] = new Flame();
			gc.obj[2].x = 7;
			gc.obj[2].y	= 9;
			
			gc.obj[3] = new Flame();
			gc.obj[3].x = 3;
			gc.obj[3].y	= 1;
			break;
		}
	
	}
	
}
