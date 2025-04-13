package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * The KeyHandler class handles keyboard input for the game.
 */
public class KeyHandler implements KeyListener {
	Game_Controller mGame_C_Ref;

	public KeyACTION keyDoes = KeyACTION.NON;

	/**
	 * Enumeration representing possible actions for keyboard input.
	 */
	public enum KeyACTION {
		goUP, goDOWN, golEFT, goRIGHT, NON
	}

	public boolean upPressed, downPressed, leftPressed, rightPressed;

	/**
     * Constructs a KeyHandler with the specified Game_Controller.
     *
     * @param mGame The Game_Controller instance.
     */
	public KeyHandler(Game_Controller mGame) {
		this.mGame_C_Ref = mGame;
	}

	/**
	 * not used
	 */
	@Override
	public void keyTyped(KeyEvent e) {

	}
	
	/**
     * Invoked when a key is pressed.
     *
     * @param e The KeyEvent.
     */
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			mGame_C_Ref.fireEvent(Game_Controller.ACTION.PAUSE_TOGGLE);
			break;
		case KeyEvent.VK_UP:
			keyDoes = KeyACTION.goUP;
			break;
		case KeyEvent.VK_DOWN:
			keyDoes = KeyACTION.goDOWN;
			break;
		case KeyEvent.VK_RIGHT:
			keyDoes = KeyACTION.goRIGHT;
			break;
		case KeyEvent.VK_LEFT:
			keyDoes = KeyACTION.golEFT;
			break;
		}
	}

	 /**
     * Invoked when a key is released.
     *
     * @param e The KeyEvent.
     */
	@Override
	public void keyReleased(KeyEvent e) {
		keyDoes = KeyACTION.NON;
	}

}
