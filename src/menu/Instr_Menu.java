package menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.Game_Controller;
import main.Tools;

/**
 * The Instr_Menu class represents the instruction menu in the game.
 */
public class Instr_Menu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The custom Font used in the instruction menu. */
	Font font1;

	/** The background image of the instruction menu. */
	private BufferedImage bgImage;

	/** The center X-coordinate of the screen. */
	private int screenCenterX;

	/** The center Y-coordinate of the screen. */
	private int screenCenterY;

	/** The width of buttons in the instruction menu. */
	private int bWidth = 350;

	/** The height of buttons in the instruction menu. */
	private int bHeight = 75;

	/** The vertical distance between buttons in the instruction menu. */
	private int abstand = 20;

	/** The count of buttons in the instruction menu. */
	private int count = 4;

	/** The "Back" button in the instruction menu. */
	private JButton back = new JButton();

	/**
	 * Constructs an Instr_Menu object.
	 */
	public Instr_Menu() {
		back.setBounds(50, 110, 150, 50);
		back.setText("Back");
		back.setVisible(true);
		back.addActionListener(e -> Game_Controller.getGame_C_Ref().fireEvent(Game_Controller.ACTION.MENU));

		Tools t = new Tools();
		font1 = t.getFont1();

		try {
			bgImage = ImageIO.read(getClass().getResourceAsStream("/menuStuff/A_img.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		setBackground(Color.black);
		setLayout(null);
		setFocusable(false);

		add(back);
	}

	/**
	 * Initializes the main settings of the instruction menu.
	 */
	public void initMain() {
		bgImage = Tools.scaleImage(bgImage, getWidth(), getHeight());

		screenCenterX = this.getWidth() / 2;
		screenCenterY = this.getHeight() / 2;

		initButton(back, "Back");
		back.setBounds(screenCenterX - (bWidth / 2), screenCenterY + 200, bWidth, bHeight);
		back.addActionListener(e -> Game_Controller.getGame_C_Ref().fireEvent(Game_Controller.ACTION.MENU));

	}

	/**
	 * Initializes a button in the instruction menu with the specified text.
	 *
	 * @param b    The button to be initialized.
	 * @param text The text to be displayed on the button.
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
	 * Paints the components of the instruction menu.
	 *
	 * @param g The Graphics object for painting.
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draw the image
		if (bgImage != null) {
			g.drawImage(bgImage, 0, 0, this); // Draw the image at coordinates (0, 0)
		}
	}
}
