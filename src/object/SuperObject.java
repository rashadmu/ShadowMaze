package object;

import java.awt.image.BufferedImage;



/**
 * SuperObject class serves as the base class for various objects in the game.
 */
public class SuperObject {
	/** The image associated with the object. */
    public BufferedImage image;
    
    /** The name or identifier of the object. */
    public String name;
    
    /** Indicates whether the object has collision properties. */
    public boolean collision = false;
    
    /** The x-coordinate of the object's position. */
    public int x;
    
    /** The y-coordinate of the object's position. */
    public int y;
	
}
