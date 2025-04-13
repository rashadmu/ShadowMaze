package main;

import entity.Player;
import lighting.Light;
import object.SuperObject;
import tile.TileManager;

/**
 * The Game_Controller class is the central control unit for the game logics, responsible for managing game elements,
 * handling user input, and controlling the game loop.
 */
public class Game_Controller implements Runnable {

	private static GUI_Controller mGUI_C_Ref;
	private static Game_Controller mGame_C_Ref;
	private Thread gameThread;
	private boolean mRunning = true;
	public static final int mTickrate = 1000 / 11; // Tickrate in 11 pro Sekunde

	// Game_Panel SITTINGS
	public int userScreenHight;
	public int userScreenWidth;
	public final int originalTileSize = 16; // 16x16 tile
	public int maxCol;
	public int maxRow;
	public double scale;
	public int tileSize; // 48x48 tile
	public int panelWidth; // 768
	public int panelHeight; // 576
	public int panelOffset;

	// GAME SETUP
	public static int level;
	public int givenLight = 4;
	public int lightSize;

	// SYSTEM
	public KeyHandler keyH;
	TileManager tileM;
	ObjSetter oSetter;
	public CollisionChecker cCheck;
	public UI ui;
	public Light light;

	// SOUND
	Sound music = new Sound();
	Sound soundEffect = new Sound();

	// ENTITY AND OBJECTS
	public Player player;
	public SuperObject obj[] = new SuperObject[10];

	/**
     * Constructs a Game_Controller instance and initializes the game components.
     */
	public Game_Controller() {

		mGame_C_Ref = this;
		mGUI_C_Ref = new GUI_Controller(this);

        // switch to the menu
		changeState(GAMESTATE.MENU);

		playMusic(0); // play main music

        // start the game loop
		gameThread = new Thread(this);
		gameThread.setName("GameThread");
		gameThread.start();
	}

	private void setupGame() {
		tileM = new TileManager(this);
		oSetter = new ObjSetter(this);
		cCheck = new CollisionChecker(this);
		ui = new UI(this);
		light = new Light(this);

		player = new Player(this, keyH);

		oSetter.setObject();
	}

	/**
     * setup a new game with the specified level number.
     *
     * @param level The level number to start.
     */
	private void setupLevel(int maxRow, int maxCol) {
		this.maxRow = maxRow;
		this.maxCol = maxCol;
		this.scale = userScreenHight / (double) (originalTileSize * maxRow);
		this.tileSize = (int) (originalTileSize * scale);
		this.panelWidth = tileSize * maxCol;
		this.panelHeight = tileSize * maxRow;
		this.panelOffset = userScreenWidth / 2 - panelWidth / 2;
		this.lightSize = givenLight * tileSize;

		mGUI_C_Ref.mGame_Panel.setBounds(panelOffset, 0, panelWidth, panelHeight);
	}

	/**
     * Starts a new game level with the specified level number.
     *
     * @param level The level number to start.
     */
	public void startLevel(int level) {

		Game_Controller.level = level;

		switch (level) {
		case 1:
			setupLevel(12, 20);
			break;
		case 2:
			setupLevel(16, 23);
			break;
		case 3:
			setupLevel(19, 27);
			break;
		case 4:
			fireEvent(ACTION.MENU);
			break;

		}
		setupGame();

		fireEvent(ACTION.START);
	}

	/**
     * Game loop for game logic.
     */
	@Override
	public void run() {

		long startTime, endTime, deltaTime;
		while (mRunning) {
			startTime = System.currentTimeMillis();
			// Game Logic
			if (state != GAMESTATE.PAUSED) {

				if (state == GAMESTATE.RUNNING) {

					light.updateFilter();
					player.update();
				}

			}
			
			endTime = System.currentTimeMillis();
			deltaTime = endTime - startTime;
			if (mTickrate - deltaTime > 0) {
				try {
					Thread.sleep(mTickrate - deltaTime);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}
		stopMusic();
		mGUI_C_Ref.quit();
		/*
         * Code for saving, etc.
         */
		System.out.println("Game Threed ended");

	}

    // possible states of the program
	public enum GAMESTATE {
		MENU, LEVEL_MENUE, RUNNING, PAUSED, GAMEOVER, WINED, INIST_M
	}

	public GAMESTATE state; // current state

    // possible actions
	public enum ACTION {
		QUIT, START, PAUSE_TOGGLE, MENU, CHOOSE_LEVEL, WIN, INIST
	}
	/**
     * Changes the current state of the game and calls changeWindowConfig().
     *
     * @param newState The new state to set.
     */
	public void changeState(GAMESTATE newState) {
		state = newState;
		mGUI_C_Ref.changeWindowConfig(newState);
	}

	/**
     * Gets the reference to the GUI_Controller instance.
     *
     * @return The GUI_Controller instance.
     */
	public static GUI_Controller getGUI_C_Ref() {
		return mGUI_C_Ref;
	}

	/**
     * Gets the reference to the Game_Controller instance.
     *
     * @return The Game_Controller instance.
     */
	public static Game_Controller getGame_C_Ref() {
		return mGame_C_Ref;
	}

	/**
     * Triggers an event.
     *
     * @param a The action to trigger.
     */
	public void fireEvent(ACTION a) {
		switch (a) {
		case START -> changeState(GAMESTATE.RUNNING);
		case QUIT -> mRunning = false;
		case PAUSE_TOGGLE -> {
			if (state == GAMESTATE.PAUSED) {
				changeState(GAMESTATE.RUNNING);
			} else if (state == GAMESTATE.RUNNING) {
				changeState(GAMESTATE.PAUSED);
			}
		}
		case MENU -> changeState(GAMESTATE.MENU);
		case CHOOSE_LEVEL -> changeState(GAMESTATE.LEVEL_MENUE);
		case WIN -> changeState(GAMESTATE.WINED);
		case INIST -> changeState(GAMESTATE.INIST_M);
		}
	}

	/**
     * Plays the specified music track in loop.
     *
     * @param i The index of the music track to play.
     */
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}

	/**
     * Stops the currently playing music.
     */
	public void stopMusic() {
		music.stop();
	}

	/**
     * Plays the specified sound effect.
     *
     * @param i The index of the sound effect to play.
     */
	public void playSE(int i) {
		soundEffect.setFile(i);
		soundEffect.play();
	}

}
