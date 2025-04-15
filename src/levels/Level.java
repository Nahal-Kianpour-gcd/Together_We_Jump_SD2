//Nahal Kianpour
package levels;

/**
 * Represents a game level by holding a 2D array of tile data. Each tile is
 * represented by an integer that refers to a sprite index.
 */
public class Level {

	private int[][] lvlData; // 2D array storing tile sprite indices for this level

	/**
	 * Constructor that initializes the level with tile data.
	 * 
	 * @param lvlData 2D array of integers representing tile indices.
	 */
	public Level(int[][] lvlData) {
		this.lvlData = lvlData;
	}

	/**
	 * Retrieves the sprite index (tile ID) at a specific position in the level.
	 * 
	 * @param x The horizontal (column) coordinate.
	 * @param y The vertical (row) coordinate.
	 * @return The sprite index stored at the given position.
	 */
	public int getSpriteIndex(int x, int y) {
		return lvlData[y][x];
	}
}
