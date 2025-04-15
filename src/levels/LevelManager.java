// Nahal Kianpour
// Manages the current level, loading, switching, and level-related logic
package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import griffith.Game;
import utilz.LoadSave;

public class LevelManager {
    
    private Game game;                // Reference to the main game instance
    private BufferedImage levelSprite; // Image containing all level tiles/sprites
    
    public LevelManager(Game game) {
        this.game = game;
        System.out.println("Initializing LevelManager...");
        System.out.println("Attempting to load level sprites from: " + LoadSave.LEVEL_SPRITES);
        importLevelSprites();
    }
    
    /**
     * Loads the level sprite sheet from resources
     */
    private void importLevelSprites() {
        levelSprite = LoadSave.getLevelSprite();
        if (levelSprite == null) {
            System.out.println("ERROR: Could not load level sprite sheet!");
        } else {
            System.out.println("Successfully loaded level sprite sheet!");
            System.out.println("Sprite dimensions: " + levelSprite.getWidth() + "x" + levelSprite.getHeight());
        }
    }
    
    /**
     * Draws the level elements on screen
     * @param g Graphics context to draw on
     */
    public void draw(Graphics g) {
        if (levelSprite != null) {
            g.drawImage(levelSprite, 0, 0, null);
        } else {
            System.out.println("WARNING: Attempted to draw null level sprite!");
        }
    }
    
    /**
     * Updates the level state
     * Will be used later for animated tiles or level mechanics
     */
    public void update() {
        // Will be implemented later for level mechanics
    }
}
