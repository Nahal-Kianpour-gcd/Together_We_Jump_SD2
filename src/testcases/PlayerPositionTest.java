package testcases;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Player;
import griffith.Game;
import java.awt.geom.Rectangle2D;

class PlayerPositionTest {
    private Player player;
    private int[][] levelData;
    private final int TILES_IN_HEIGHT = Game.TILES_IN_HEIGHT;
    private final int TILES_IN_WIDTH = Game.TILES_IN_WIDTH;
    private final int TILES_SIZE = Game.TILES_SIZE;
    private static final float DELTA = 0.15f; // Increased tolerance for floating point comparisons

    @BeforeEach
    void setUp() {
        // Create a test level with solid ground at the bottom
        levelData = new int[TILES_IN_HEIGHT][TILES_IN_WIDTH];
        for (int i = 0; i < TILES_IN_HEIGHT; i++) {
            for (int j = 0; j < TILES_IN_WIDTH; j++) {
                if (i == TILES_IN_HEIGHT - 1 || j == 0 || j == TILES_IN_WIDTH - 1) {
                    levelData[i][j] = 0; // Solid tile
                } else {
                    levelData[i][j] = 11; // Empty tile
                }
            }
        }
        
        // Create a test player starting higher up to ensure they're in the air
        player = new Player(200, 100, (int)(32 * Game.SCALE), (int)(32 * Game.SCALE), 
                          "/image-resources/Main_Characters/Ninja_Frog");
        player.loadLvlData(levelData);
    }

    @Test
    void testPlayerStartsAboveGround() {
        // Get initial Y position
        float initialY = player.getY();
        
        // Update player position multiple times to ensure gravity takes effect
        for (int i = 0; i < 3; i++) {
            player.update();
        }
        
        // Player should move down due to gravity
        assertTrue(player.getY() > initialY, 
                  "Player should start falling if spawned above ground");
    }

    @Test
    void testPlayerStopsAtGround() {
        // Let the player fall until they hit the ground
        float lastY = -1;
        float currentY = player.getY();
        
        // Keep updating until the player stops moving (hits ground)
        int maxIterations = 100; // Prevent infinite loop
        int iterations = 0;
        int stableCount = 0;
        float groundPosition = -1;
        
        while (iterations < maxIterations && stableCount < 5) {
            lastY = currentY;
            player.update();
            currentY = player.getY();
            
            if (Math.abs(lastY - currentY) < DELTA) {
                if (groundPosition == -1) {
                    groundPosition = currentY;
                }
                stableCount++;
            } else {
                stableCount = 0;
                groundPosition = -1;
            }
            
            iterations++;
        }
        
        assertTrue(groundPosition != -1, "Player should have reached stable ground position");
        
        // Update a few more times to verify player stays at ground level
        for (int i = 0; i < 5; i++) {
            player.update();
            assertTrue(Math.abs(groundPosition - player.getY()) < DELTA,
                      "Player should remain near ground level after stopping");
        }
    }

    @Test
    void testPlayerSpawnPosition() {
        // Initial spawn coordinates
        assertEquals(200, player.getX(), 
                    "Player should spawn at x=200");
        assertTrue(player.getY() <= (TILES_IN_HEIGHT - 1) * TILES_SIZE, 
                  "Player should not spawn below the ground level");
    }

    @Test
    void testLevelDataInitialization() {
        // Test bottom row is solid
        for (int j = 0; j < TILES_IN_WIDTH; j++) {
            assertEquals(0, levelData[TILES_IN_HEIGHT - 1][j], 
                        "Bottom row should be solid ground");
        }
        
        // Test sides are solid
        for (int i = 0; i < TILES_IN_HEIGHT; i++) {
            assertEquals(0, levelData[i][0], 
                        "Left wall should be solid");
            assertEquals(0, levelData[i][TILES_IN_WIDTH - 1], 
                        "Right wall should be solid");
        }
    }
}