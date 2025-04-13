package main;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * The Tools class provides utility methods for handling images and fonts.
 */
public class Tools {
	
	/**
     * Scales the given image to the specified width and height.
     *
     * @param original The original image to be scaled.
     * @param width    The desired width of the scaled image.
     * @param height   The desired height of the scaled image.
     * @return A new BufferedImage representing the scaled image.
     */
	public static BufferedImage scaleImage(BufferedImage original, int width, int hight) {
		
		BufferedImage scaledImage = new BufferedImage(width, hight, original.getType());
		Graphics2D g2 = scaledImage.createGraphics();
		g2.drawImage(original, 0, 0, width, hight, null);
		g2.dispose();
		return scaledImage;
	}
	
	/**
     * Retrieves and returns a custom Font object from a TrueType Font file.
     *
     * @return The custom Font object.
     */
	public Font getFont1() {
		Font font1 = null;
		try {
			InputStream is = getClass().getResourceAsStream("/fonts/menuFont.ttf");
			font1 = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return font1;
	}
}
