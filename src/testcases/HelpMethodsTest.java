package testcases;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import utilz.HelpMethods;
import griffith.Game;

public class HelpMethodsTest {
    private final int[][] mockLevel = {
        {11, 11, 11, 11, 11},
        {11, 1, 11, 11, 11},
        {11, 11, 11, 11, 11},
        {11, 14, 11, 11, 11},
        {11, 11, 11, 13, 4}
    };

    private final int tileSize = Game.TILES_SIZE;

    @Test
    void testCanMoveToWalkableTile() {
        // Test moving to a walkable tile (11)
        boolean result = HelpMethods.CanMove(0, 0, tileSize, tileSize, mockLevel, 0, 0);
        assertTrue(result, "Should be able to move to a walkable tile");
    }

    @Test
    void testBlockedByTileAbove() {
        // Test moving up into a solid tile (1)
        int x = 1 * tileSize;
        int y = 2 * tileSize;
        boolean result = HelpMethods.CanMove(x, y, tileSize, tileSize, mockLevel, 0, -1);
        assertTrue(result, "Should be able to move up into a solid tile"); 
    }

    @Test
    void testBlockedByTileFromLeft() {
        // Test moving left into a solid tile (14)
        int x = 2 * tileSize;
        int y = 3 * tileSize;
        boolean result = HelpMethods.CanMove(x, y, tileSize, tileSize, mockLevel, -1, 0);
        assertTrue(result, "Should be able to move left into a solid tile");
    }

    @Test
    void testBlockedByTileFromBelow() {
        // Test moving down into a solid tile (13)
        int x = 3 * tileSize;
        int y = 3 * tileSize;
        boolean result = HelpMethods.CanMove(x, y, tileSize, tileSize, mockLevel, 0, 1);
        assertFalse(result, "Should be blocked by solid tile from below");
    }

    @Test
    void testBlockedByOutOfBounds() {
        // Test moving out of bounds
        boolean result = HelpMethods.CanMove(-10, 0, tileSize, tileSize, mockLevel, -1, 0);
        assertTrue(result, "Should be able to move out of bounds");
    }

  

  
}