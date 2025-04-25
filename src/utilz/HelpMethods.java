package utilz;

import griffith.Game;

public class HelpMethods {	
	private static final float BUFFER = 10f;
	private static final int TILE_SIZE = Game.TILES_SIZE;
	public static boolean CanMove(float x, float y, float width, float height, int[][] lvlData, int directionX, int directionY) {
	    
	    if (directionY != 0) {
	        float yOffset = (directionY == -1) ? y + height - BUFFER : y + BUFFER;
	        int yIndex = (int)(yOffset / TILE_SIZE);

	        for (float i = x + BUFFER; i < x + width - BUFFER; i += TILE_SIZE) {
	            int xIndex = (int)(i / TILE_SIZE);

	            if (yIndex < 0 || yIndex >= lvlData.length || xIndex < 0 || xIndex >= lvlData[0].length)
	                return false;

	            if (lvlData[yIndex][xIndex] != 11)
	                return false;
	        }
	    }

	    if (directionX != 0) {
	        float xOffset = (directionX == -1) ? x + BUFFER : x + width - BUFFER;
	        int xIndex = (int)(xOffset / TILE_SIZE);

	        for (float i = y + BUFFER; i < y + height - BUFFER; i += TILE_SIZE) {
	            int yIndex = (int)(i / TILE_SIZE);

	            if (yIndex < 0 || yIndex >= lvlData.length || xIndex < 0 || xIndex >= lvlData[0].length)
	                return false;

	            if (lvlData[yIndex][xIndex] != 11)
	                return false;
	        }
	    }
	    return true;
	}
}
