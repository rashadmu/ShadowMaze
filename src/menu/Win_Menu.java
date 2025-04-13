package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import main.Game_Controller;
import main.Tools;

/**
 * Win_Menu class represents the win menu panel in the game.
 */
public class Win_Menu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Buttons for menu options
    private JButton menu = new JButton();
    private JButton newxtLevel = new JButton();

    // Coordinates and dimensions for button layout
    private int screenCenterX;
    private int screenCenterY;

    private int bWidth = 350;
    private int bHeight = 75;

    private int abstand = 20;

    private int count = 2;

    // Font and image for styling
    private Font font1;
    private BufferedImage congraits;

    /**
     * Constructor for Win_Menu class.
     */
	public Win_Menu() {
		Tools ts = new Tools();
		font1 = ts.getFont1();

		try {
			congraits = ImageIO.read(getClass().getResourceAsStream("/menuStuff/congs.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		setBackground(Color.black);

		setLayout(null);

		setFocusable(false);

		add(menu);
		add(newxtLevel);

		menu.setText("Main Menu");
		menu.setBounds(200, 100, 150, 50);
		menu.setVisible(true);
		menu.addActionListener(e -> Game_Controller.getGame_C_Ref().fireEvent(Game_Controller.ACTION.MENU));
	}
	
	/**
     * Initializes the win menu with buttons and their actions.
     */
	public void initMain() {

		screenCenterX = this.getWidth() / 2;
		screenCenterY = this.getHeight() / 2 + 100;

		initButton(menu, "Main Menu");
		menu.addActionListener(e -> Game_Controller.getGame_C_Ref().fireEvent(Game_Controller.ACTION.MENU));

		initButton(newxtLevel, "Next Level");
		newxtLevel.addActionListener(e -> Game_Controller.getGame_C_Ref().startLevel(Game_Controller.level + 1));
	}
	
	/**
     * Initializes a button with specified text and styling.
     *
     * @param b     The button to be initialized.
     * @param text  The text to be displayed on the button.
     */
	public void initButton(JButton b, String text) {
		b.setBounds(screenCenterX - (bWidth / 2), screenCenterY - (count * (abstand + bHeight)), bWidth, bHeight);
		b.setBackground(Color.white);
		b.setText(text);
		b.setFont(font1.deriveFont(Font.PLAIN, 25));
		b.setVisible(true);
		count--;
	}

	/**
     * Overrides the paintComponent method to draw the congratulations image.
     *
     * @param g The Graphics object used for painting.
     */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draw the image
		if (congraits != null) {
			g.drawImage(congraits, screenCenterX - (congraits.getWidth() / 2), screenCenterY - 400, this); // Draw the
																											// image at
																											// coordinates
																											// (0, 0)
		}
	}
}
