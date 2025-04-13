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
 * The Level_Menu class represents the level selection menu in the game.
 */
public class Level_Menu extends JPanel {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The custom Font used in the level selection menu. */
    Font font1;

    /** The background image of the level selection menu. */
    private BufferedImage bgImage;

    /** The center X-coordinate of the screen. */
    private int screenCenterX;

    /** The center Y-coordinate of the screen. */
    private int screenCenterY;

    /** The width of buttons in the level selection menu. */
    private int bWidth = 350;

    /** The height of buttons in the level selection menu. */
    private int bHeight = 75;

    /** The vertical distance between buttons in the level selection menu. */
    private int abstand = 20;

    /** The count of buttons in the level selection menu. */
    private int count = 2;

    /** The "Level 1" button in the level selection menu. */
    private JButton level1 = new JButton();

    /** The "Level 2" button in the level selection menu. */
    private JButton level2 = new JButton();

    /** The "Level 3" button in the level selection menu. */
    private JButton level3 = new JButton();

    /** The "Back" button in the level selection menu. */
    private JButton back = new JButton();

    /**
     * Constructs a Level_Menu object.
     */
	public Level_Menu() {
		back.setBounds(50, 110, 150, 50);
		back.setText("Back");
		back.setVisible(true);
		back.addActionListener(e -> Game_Controller.getGame_C_Ref().fireEvent(Game_Controller.ACTION.MENU));
	
		Tools t = new Tools();
		font1 = t.getFont1();
		
		try {
			bgImage = ImageIO.read(getClass().getResourceAsStream("/menuStuff/bg.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		setBackground(Color.black);
		setLayout(null);
		setFocusable(false);
		
		add(level1);
		add(level2);
		add(level3);
		add(back);
	}
	
	 /**
     * Initializes the main settings of the level selection menu.
     */
	public void initMain() {
		bgImage = Tools.scaleImage(bgImage, getWidth(), getHeight());
		
		screenCenterX = this.getWidth() / 2;
		screenCenterY = this.getHeight() / 2;
		
		initButton(level1, "Level 1");
		level1.addActionListener(e -> Game_Controller.getGame_C_Ref().startLevel(1));


		initButton(level2, "Level 2");
		level2.addActionListener(e -> Game_Controller.getGame_C_Ref().startLevel(2));

		
		initButton(level3, "Level 3");
		level3.addActionListener(e -> Game_Controller.getGame_C_Ref().startLevel(3));

		
		initButton(back, "Back");
		back.setBounds(screenCenterX - (bWidth/2), screenCenterY + 200, bWidth, bHeight);
		back.addActionListener(e -> Game_Controller.getGame_C_Ref().fireEvent(Game_Controller.ACTION.MENU));

		
	
	}
	
	/**
     * Initializes a button in the level selection menu with the specified text.
     *
     * @param b    The button to be initialized.
     * @param text The text to be displayed on the button.
     */
	public void initButton(JButton b, String text) {
		
		b.setBounds(screenCenterX - (bWidth/2), screenCenterY - (count*(abstand + bHeight)), bWidth, bHeight);
		b.setBackground( Color.white );
		b.setText(text);
		b.setFont(font1.deriveFont(Font.PLAIN, 25));
		b.setVisible(true);
		count--;
	}
	
	 /**
     * Paints the components of the level selection menu.
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
