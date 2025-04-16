package utilz;

import griffith.Game;

public class HelpMethods {
	// Checks if an entity can move to a specific location based on its hitbox and the level data
	public static boolean CanMoveHere(float x, float y, int width, int height, int[][] lvlData) {
		if(!IsSolid(x,y,lvlData))
			if(!IsSolid(x+width, y+height,lvlData))
				if(!IsSolid(x+width, y,lvlData))
					if(!IsSolid(x, y+height,lvlData))
						return true;
		return false;
	}
	// Determines if the given position is solid (non-walkable) based on the level data
	private static boolean IsSolid(float x, float y, int [][] lvlData) {
		 // Check if coordinates are out of bounds
		if(x < 0 || x > Game.GAME_WIDTH)
			return true;
		if(y < 0 || y > Game.GAME_HEIGHT)
			return true;
		
		// Convert pixel position to tile index
		float xIndex = x / Game.TILES_SIZE;
		float yIndex = y / Game.TILES_SIZE;
		
		int value = lvlData[(int) xIndex][(int) yIndex];
		
		// Check if the tile is considered solid
		if(value >= 48 || value <0 || value == 11)
			return true;
		return false;
	}
}
