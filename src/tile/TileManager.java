package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.Game_Controller;
import main.Tools;

/**
 * TileManager class manages the tiles and the game map.
 */
public class TileManager {

	 /** The Game_Controller instance associated with the tile manager. */
    Game_Controller gc;

    /** Array of Tile objects representing different types of tiles. */
    public Tile[] tile;

    /** 2D array representing the map with tile indices. */
    public int mapTileNum[][];

    /**
     * Constructor for TileManager class.
     * Initializes the tile manager, loads tile images, and loads the game map.
     * 
     * @param gc The Game_Controller instance associated with the tile manager.
     */
	public TileManager(Game_Controller gc) {

		this.gc = gc;
		tile = new Tile[10];
		mapTileNum = new int[gc.maxCol][gc.maxRow];
		getTileImage();
		loadMap("/maps/map0" + Game_Controller.level + ".txt");
		
	}
	
	/**
     * Loads the tile images for different types of tiles.
     */
	public void getTileImage() {

		setupImage(0, "wood", false);
		setupImage(1, "stone", true);
	}
	
	 /**
     * Sets up the image for a specific type of tile.
     * 
     * @param index      The index of the tile.
     * @param imageName  The name of the image file for the tile.
     * @param collision  Indicates whether the tile has collision properties.
     */
	public void setupImage(int index, String imageName, boolean collision) {

		try {
			
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName +".png"));
			tile[index].image = Tools.scaleImage(tile[index].image, gc.tileSize, gc.tileSize);
			tile[index].collinsion = collision;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


    /**
     * Loads the game map from a file.
     * 
     * @param mapPath The path to the file containing the map data.
     */
	public void loadMap(String mapPath) {

		try {
			InputStream is = getClass().getResourceAsStream(mapPath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			int col = 0;
			int row = 0;

			while (col < gc.maxCol && row < gc.maxRow) {
				String line = br.readLine();
				char numbers[] = line.toCharArray();

				while (col < gc.maxCol) {

					int num = Character.getNumericValue(numbers[col]);

					mapTileNum[col][row] = num;
					col++;
				}

				if (col == gc.maxCol) {
					col = 0;
					row++;
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	 /**
     * Draws the tiles on the game map.
     * 
     * @param g2 The Graphics2D object used for drawing.
     */
	public void draw(Graphics2D g2) {

		g2.drawImage(tile[0].image, 0, 0, gc.tileSize, gc.tileSize, null);

		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;

		while (col < gc.maxCol && row < gc.maxRow) {

			int tileNum = mapTileNum[col][row];

			g2.drawImage(tile[tileNum].image, x, y, null);
			col++;
			x += gc.tileSize;

			if (col == gc.maxCol) {
				col = 0;
				x = 0;
				row++;
				y += gc.tileSize;
			}

		}
	}
}
