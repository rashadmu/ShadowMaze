package lighting;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.Shape;
import java.awt.RadialGradientPaint;

import main.Game_Controller;

/**
 * Light class represents the lighting effects in the game.
 */
public class Light {

	/** Reference to the Game_Controller. */
	Game_Controller gc;

	/** Graphics2D object for rendering. */
	Graphics2D g2;

	/** BufferedImage representing the darkness filter. */
	public BufferedImage darknessFilter;

	/** Diameter of the light circle. */
	int circleSize;

	/** Radius of the light circle. */
	int radius;

	/** Size of the filter area. */
	int filterSize;

	/** Radius of the filter area. */
	int filterRadius;

	/** Area representing the darkened portion of the filter. */
	Area darkArea;

	/** Area representing the light circle. */
	Area lightArea;

	/** Shape representing the light circle. */
	Shape circleShape;

	/** X-coordinate represents the center of the light circle. */
	int centerX;

	/** Y-coordinate represents the center of the light circle. */
	int centerY;

	/** X-coordinate the center of the circle for rendering. */
	public double circleX = centerX;

	/** Y-coordinate the center of the circle for rendering. */
	public double circleY = centerY;

	/** Array of colors for creating a gradient effect. */
	Color[] color = new Color[5];

	/** Array of fractions for creating a gradient effect. */
	float[] fraction = new float[5];

	/**
	 * Constructor for the Light class.
	 *
	 * @param gc The Game_Controller instance.
	 */
	public Light(Game_Controller gc) {
		this.gc = gc;
		this.circleSize = gc.lightSize;
		setUpFilter();
	}

	/** Sets up the gradient effect for the darkness filter. */
	public void setUpFilter() {
		color[0] = new Color(0, 0, 0, 0f);
		color[1] = new Color(0, 0, 0, 0.25f);
		color[2] = new Color(0, 0, 0, 0.5f);
		color[3] = new Color(0, 0, 0, 0.75f);
		color[4] = new Color(0, 0, 0, 0.99f);

		fraction[0] = 0.25f;
		fraction[1] = 0.60f;
		fraction[2] = 0.75f;
		fraction[3] = 0.90f;
		fraction[4] = 1f;

		resizeFilter();
	}

	/** Resizes the darkness filter based on the given light size. */
	public void resizeFilter() {
		this.circleSize = gc.tileSize * gc.givenLight;
		this.radius = (circleSize / 2);
		this.filterSize = circleSize + (5 * gc.tileSize);
		this.filterRadius = filterSize / 2;

		darknessFilter = new BufferedImage(filterSize, filterSize, BufferedImage.TYPE_INT_ARGB);
		this.g2 = (Graphics2D) darknessFilter.getGraphics();

		darkArea = new Area(new Rectangle2D.Double(0, 0, filterSize, filterSize));
		circleShape = new Ellipse2D.Double(0, 0, circleSize, circleSize);
		lightArea = new Area(circleShape);

		darkArea.subtract(lightArea);

		RadialGradientPaint gPaint = new RadialGradientPaint(filterRadius, filterRadius, radius, fraction, color);

		g2.setPaint(gPaint);
		g2.setBackground(Color.black);
		g2.fill(lightArea);
		g2.fill(darkArea);
		g2.dispose();
	}

	/** Updates the position of the light circle based on the player's position. */
	public void updateFilter() {
		circleX = gc.player.x * gc.tileSize + (gc.tileSize / 2) - filterRadius;
		circleY = gc.player.y * gc.tileSize + (gc.tileSize / 2) - filterRadius;
	}
}