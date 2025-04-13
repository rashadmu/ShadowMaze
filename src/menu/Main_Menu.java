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
 * Main_Menu class represents the main menu panel in the game.
 */
public class Main_Menu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Buttons for menu options
	private JButton start = new JButton();
	private JButton anleitung = new JButton();
	private JButton quit = new JButton();

	// Coordinates and dimensions for button layout
	private int screenCenterX;
	private int screenCenterY;

	private int bWidth = 350;
	private int bHeight = 75;

	private int abstand = 20;

	private int count = 2;

	// Font and images for styling
	private Font font1;
	private BufferedImage bgImage;
	private BufferedImage titleImage;

	/**
	 * Constructor for Main_Menu class.
	 */
	public Main_Menu() {
		Tools ts = new Tools();
		font1 = ts.getFont1();

		try {
			bgImage = ImageIO.read(getClass().getResourceAsStream("/menuStuff/bg.png"));
			titleImage = ImageIO.read(getClass().getResourceAsStream("/menuStuff/gameTitle.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		setBackground(Color.black);

		setLayout(null);

		setFocusable(false);

		add(start);
		add(anleitung);
		add(quit);

	}

	/**
	 * Initializes the main menu with buttons and their actions.
	 */
	public void initMain() {
		bgImage = Tools.scaleImage(bgImage, getWidth(), getHeight());

		screenCenterX = this.getWidth() / 2;
		screenCenterY = this.getHeight() / 2 + 100;

		initButton(start, "Start");
		start.addActionListener(e -> Game_Controller.getGame_C_Ref().fireEvent(Game_Controller.ACTION.CHOOSE_LEVEL));

		initButton(anleitung, "How to play");
		anleitung.addActionListener(e -> Game_Controller.getGame_C_Ref().fireEvent(Game_Controller.ACTION.INIST));


		initButton(quit, "Quit");
		quit.addActionListener(e -> Game_Controller.getGame_C_Ref().fireEvent(Game_Controller.ACTION.QUIT));
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
     * Overrides the paintComponent method to draw background and title images.
     */
    @Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draw the image
		if (bgImage != null) {
			g.drawImage(bgImage, 0, 0, this); // Draw the image at coordinates (0, 0)
		}
		if (titleImage != null) {
			g.drawImage(titleImage, screenCenterX - (titleImage.getWidth() / 2), screenCenterY - 400, this); // Draw the
		}
	}

}
