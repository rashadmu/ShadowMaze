package main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import object.Key;

/**
 * The UI class handles user interface components and displays in the game.
 */
public class UI {
	
    /** The Game Controller associated with the UI. */
	Game_Controller gc;
	
    /** The first custom Font used in the UI. */
	public Font font1; 
	
    /** The Arial Font used in the UI. */
	public Font arial;
	
    /** The image of a key used in the UI. */
	BufferedImage keyImage;

    /** Flag indicating whether a message is currently displayed. */
	public boolean messageOn = false;
	
    /** The message to be displayed in the UI. */
	public String message = "";
	
    /** Counter to control the duration of displayed messages. */
	int messageCounter = 0;

	/**
     * Constructs a UI object with the specified Game Controller.
     *
     * @param gc The Game Controller associated with the UI.
     */
	public UI(Game_Controller gc) {
		this.gc = gc;

		Key key = new Key();
		keyImage = key.image;

		arial = new Font("Arial", 30, 30);
		try {
			InputStream is = getClass().getResourceAsStream("/fonts/Horrorshow.ttf");
			font1 = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
     * Displays the specified message on the UI.
     *
     * @param text The message to be displayed.
     */
	public void showMessage(String text) {
		message = text;
		messageOn = true;
	}
	
	 /**
     * Draws UI components on the graphics context.
     *
     * @param g2 The Graphics2D object for drawing.
     */
	public void draw(Graphics2D g2) {
		
	}
}
