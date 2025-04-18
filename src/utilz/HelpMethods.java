package utilz;

import griffith.Game;

public class HelpMethods {
	// Checks if an entity can move to a specific location based on its hitbox and the level data
	public static boolean CanMoveHere(float x, float y, int width, int height, int[][] lvlData, int directionX, int directionY) {
		if(!IsSolid(x + width, y + height,lvlData, directionX, directionY))
			return true;
		return false;
	}
	// Determines if the given position is solid (non-walkable) based on the level data
	private static boolean IsSolid(float x, float y, int [][] lvlData, int directionX, int directionY) {
		 // Check if coordinates are out of bounds
		if(x < 0 || x >= Game.GAME_WIDTH)
			return true;
		if(y < 0 || y >= Game.GAME_HEIGHT)
			return true;
		
		// Convert pixel position to t1ile index
		float xIndex = x / Game.TILES_SIZE;
		float yIndex = y / Game.TILES_SIZE;
		
		int value = lvlData[(int) yIndex][(int) xIndex];
		// Check if the tile is considered solid
		if (directionY == -1 && value == 1) return true;
		if (directionX == -1 && value == 14 || value == 4) return true;
		if (directionY == 1 && (value == 13 || value == 14 || value == 4)) return true;
		return false;
	}
}
