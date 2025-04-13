package menu;

import javax.swing.*;

import main.Game_Controller;
import main.Tools;

import java.awt.*;

/**
 * Pause_Menu class represents the pause menu panel in the game.
 */
public class Pause_Menu extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Font for styling
    Font font1;

    // Coordinates and dimensions for button layout
    private int screenCenterX;
    private int screenCenterY;

    private int bWidth = 350;
    private int bHeight = 75;

    private int abstand = 20;

    private int count = 2;

    // Buttons for menu options
    private JButton menu = new JButton();
    private JButton cont = new JButton();

    /**
     * Constructor for Pause_Menu class.
     */
	public Pause_Menu() {
		Tools t = new Tools();
		font1 = t.getFont1();

		setLayout(null);
		setBackground(Color.black);

		add(menu);
		add(cont);
	}

	/**
     * Initializes the pause menu with buttons and their actions.
     */
	public void initMain() {
		screenCenterX = this.getWidth() / 2;
		screenCenterY = this.getHeight() / 2 + 100;

		initButton(cont, "continue");
		cont.addActionListener(e -> Game_Controller.getGame_C_Ref().fireEvent(Game_Controller.ACTION.PAUSE_TOGGLE));

		initButton(menu, "Quit to Main Menu");
		menu.addActionListener(e -> Game_Controller.getGame_C_Ref().fireEvent(Game_Controller.ACTION.MENU));

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
     * Overrides the paintComponent method to draw the "Paused" message.
     *
     * @param g The Graphics object used for painting.
     */
    @Override
	protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        
        g.setFont(new Font("Arial", Font.BOLD, 100));
        g.setColor(Color.white);
        g.drawString("Paused", screenCenterX - 175, screenCenterY - 300);
    }
	
	
}
