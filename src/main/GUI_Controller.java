package main;
import javax.swing.*;

import menu.Instr_Menu;
import menu.Level_Menu;
import menu.Main_Menu;
import menu.Pause_Menu;
import menu.Win_Menu;

import java.awt.*;

/**
 * The GUI_Controller class manages the graphical user interface for the game.
 */
public class GUI_Controller implements Runnable {

	private JFrame window; // The window in which everything happen

	
    // Individual windows/components of the game
	private Main_Menu mMainMenu;
	public Game_Panel mGame_Panel;
	private Pause_Menu mPauseMenu;
	private Level_Menu mLevelMenu;
	private Win_Menu mWinMenu;
	private Instr_Menu mInstr_Menu;
	
	public KeyHandler keyH; //KeyListner
	

	private Game_Controller mGame_C_Ref; // Reference to the Game_Controlle
		
	private boolean mGraphicsRunning = true; // Is the game loop running for repaint
	private long mFramerate = Game_Controller.mTickrate;
	
	public Thread guiThread;

	/**
     * Constructs a GUI_Controller instance with the specified Game_Controller.
     *
     * @param gameController The Game_Controller instance.
     */
	GUI_Controller(Game_Controller gameController) {
		mGame_C_Ref = gameController;
		
		window = new JFrame("Mein tolles Spiel");
        // Application exits when the window is closed
		window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		window.setLayout(null);
		window.setExtendedState(Frame.MAXIMIZED_BOTH); // Window fills the screen

		window.setUndecorated(true);

        // The window becomes visible
		window.setVisible(true);
		
		
		mGame_C_Ref.userScreenHight = window.getHeight();
		mGame_C_Ref.userScreenWidth = window.getWidth();

        // Objects of the panels are created
		mMainMenu = new Main_Menu();
		mGame_Panel = new Game_Panel(mGame_C_Ref);
		mPauseMenu = new Pause_Menu();
		mLevelMenu = new Level_Menu();
		mWinMenu = new Win_Menu();
		mInstr_Menu = new Instr_Menu();

        // Panels are added and scaled
		Container cp = window.getContentPane();
		cp.setBackground(Color.black);
		cp.add(mMainMenu);
		mMainMenu.setBounds(0, 0, window.getWidth(), window.getHeight());
		cp.add(mGame_Panel);
		//mGame_Panel.setBounds(0, 0, window.getWidth(), window.getHeight());
		cp.add(mPauseMenu, 0);
		mPauseMenu.setBounds(0, 0, window.getWidth(), window.getHeight());
		cp.add(mLevelMenu);
		mLevelMenu.setBounds(0, 0, window.getWidth(), window.getHeight());
		cp.add(mWinMenu);
		mWinMenu.setBounds(0, 0, window.getWidth(), window.getHeight());
		cp.add(mInstr_Menu);
		mInstr_Menu.setBounds(0, 0, window.getWidth(), window.getHeight());
		
		
		mMainMenu.initMain();
		mLevelMenu.initMain();
		mPauseMenu.initMain();
		mWinMenu.initMain();
		mInstr_Menu.initMain();
		
		
		
        // Keyboard inputs
		keyH = new KeyHandler(mGame_C_Ref);
		mGame_C_Ref.keyH = this.keyH;
		window.addKeyListener(keyH);
		
		window.setFocusable(true);
		window.setAutoRequestFocus(true);
		
		guiThread = new Thread(this);
		guiThread.start();
	}

	/**
     * Changes the configuration of the displayed window, e.g., Main_Menu -> Game for a switch, using changeState() of
     * Game_Controller.
     *
     * @param state The new state to set.
     */
	public void changeWindowConfig(Game_Controller.GAMESTATE state) {
		if (state != Game_Controller.GAMESTATE.PAUSED) {
			for (Component element : window.getContentPane().getComponents()) {
				element.setVisible(false);
			}
		}

		switch (state) {
		case MENU -> mMainMenu.setVisible(true);
		case RUNNING -> mGame_Panel.setVisible(true);
		case PAUSED -> mPauseMenu.setVisible(true);
		case LEVEL_MENUE -> mLevelMenu.setVisible(true);
		case WINED -> mWinMenu.setVisible(true);
		case INIST_M -> mInstr_Menu.setVisible(true);
		default -> throw new IllegalArgumentException("Unexpected value: " + state);
		}
		window.repaint();
	}

	 /**
     * Game loop for graphics).
     */
	@Override
	public void run() {
		

		// GameLoop für Grafik
		long startTime, endTime, deltaTime;
		while (mGraphicsRunning) {
			startTime = System.currentTimeMillis();
			
			window.repaint();
			endTime = System.currentTimeMillis();
			deltaTime = endTime - startTime;
			if (mFramerate - deltaTime > 0) {
				try {
					Thread.sleep(mFramerate - deltaTime);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	/**
     * Closes the window.
     */
	public void quit() {
		mGraphicsRunning = false;
		window.dispose();
	}
}
