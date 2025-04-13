package main;

import javax.swing.*;

import entity.Entity;
import main.Game_Controller.GAMESTATE;
import object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 *  extends JPanel and represents the main panel for rendering the game.

 * @author Rashad Mustafa
 *
 */
public class Game_Panel extends JPanel {

	/** Serial version UID for serialization. */
	private static final long serialVersionUID = 1L;
	
	/** Reference to the Game_Controller. */
	Game_Controller gc;
	
	/** JLabel displaying game information. */
	private JLabel label = new JLabel("Game (ESC: Pause Menü)");

	/**
	 * Constructor for Game_Panel.
	 *
	 * @param gc The Game_Controller instance.
	 */
	Game_Panel(Game_Controller gc) {

		this.gc = gc;

		setDoubleBuffered(true);
		setBackground(Color.black);
		add(label);
		label.setBounds(100, 100, 100, 50);
		label.setVisible(true);
		label.setForeground(Color.orange);
		setFocusable(false);

	}
	
	/**
	 * Overrides the paintComponent method to render the game elements.
	 *
	 * @param g The Graphics object for rendering.
	 */
	@Override
	public void paintComponent(Graphics g) {
		if (gc.state == GAMESTATE.RUNNING) {
			super.paintComponent(g);

			Graphics2D g2 = (Graphics2D) g;

			// TILES DRAW
			drawMap(g2);
			//
			// OBJECTS DRAW
			for (int i = 0; i < gc.obj.length; i++) {
				if (gc.obj[i] != null) {
					drawObject(g2, gc.obj[i]);
				}
			}

			// PALYER DRAW
			drawEntity(g2, gc.player);

			
			// LIIGHT DRAW
			drawLight(g2);

			// UI
			drawUI(g2);

			g2.dispose();
		}
	}

	/**
	 * Draws the specified entity on the panel.
	 *
	 * @param g2     The Graphics2D object for rendering.
	 * @param entity The entity to be drawn.
	 */
	private void drawEntity(Graphics2D g2, Entity entity) {
		g2.setColor(Color.white);
		BufferedImage image = null;

		switch (entity.direction) {
		case UP:
			image = entity.upImage;
			break;
		case DOWN:
			image = entity.downImage;
			break;
		case RIGHT:
			image = entity.rightImage;
			break;
		case LEFT:
			image = entity.leftImage;
			break;
		}
		g2.drawImage(image, entity.x * gc.tileSize, entity.y * gc.tileSize, null);
	}

	/**
	 * Draws the specified object on the panel based on player distance.
	 *
	 * @param g2     The Graphics2D object for rendering.
	 * @param object The object to be drawn.
	 */
	private void drawObject(Graphics2D g2, SuperObject object) {
		int x = object.x;
		int y = object.y;
		
		int playerX = gc.player.x;
		int playerY = gc.player.y;
		
		Point player = new Point(playerX, playerY);
		Point obj = new Point(x, y);
		
		// Draw the object only if it is within a certain distance from the player
		if ( player.distance(obj) <  4) {
			g2.drawImage(object.image, object.x * gc.tileSize, object.y * gc.tileSize, gc.tileSize, gc.tileSize, null);
		}
	}

	/**
	 * Draws the needed part of the map on the panel only.
	 *
	 * @param g2 The Graphics2D object for rendering.
	 */
	private void drawMap(Graphics2D g2) {
		int givenlight = gc.givenLight + 3 ;
		int givenlightHalf = gc.givenLight / 2;

		int startX = gc.player.x - givenlightHalf - 1;
		int startY = gc.player.y - givenlightHalf - 1;

		int col = 0;
		int row = 0;

		int cX = startX;
		int cY = startY;

		int imageX = startX * gc.tileSize;
		int imageY = startY * gc.tileSize;

		while (col < givenlight && row < givenlight) {
			int tileNum;
			if(cX >= 0 && cY >= 0 && cX < gc.maxCol && cY < gc.maxRow) {
				tileNum = gc.tileM.mapTileNum[cX][cY];
				g2.drawImage(gc.tileM.tile[tileNum].image, imageX, imageY, null);
			}
			
			col++;
			cX++;
			imageX += gc.tileSize;

			if (col == givenlight) {
				col = 0;
				cX = startX;
				imageX = startX * gc.tileSize;
				row++;
				cY++;
				imageY += gc.tileSize;
			}

		}
	}

	/**
	 * Draws the darkness filter over the map, representing light on the panel.
	 *
	 * @param g2 The Graphics2D object for rendering.
	 */
	public void drawLight(Graphics2D g2) {
		g2.drawImage(gc.light.darknessFilter, (int)gc.light.circleX, (int)gc.light.circleY, null);
	}
	
	/**
	 * Draws the UI elements on the panel.
	 *
	 * @param g2 The Graphics2D object for rendering.
	 */
	public void drawUI(Graphics2D g2) {
		g2.setFont(gc.ui.arial);
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30));
		g2.drawImage(gc.ui.keyImage, 10, 10, 32, 32, null);
		g2.drawString("x " + gc.player.hasKey, 40, 40);

		if (gc.ui.messageOn == true) {
			g2.setFont(gc.ui.font1);
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 30));
			g2.drawString(gc.ui.message, gc.panelWidth / 4, gc.panelWidth / 4);
			gc.ui.messageCounter++;
			if (gc.ui.messageCounter == 11 * 2) {
				gc.ui.messageCounter = 0;
				gc.ui.messageOn = false;
			}
		}
	}

}
