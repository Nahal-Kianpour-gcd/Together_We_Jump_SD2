// Nahal Kianpour
// Manages the current level, loading, switching, and level-related logic
package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import griffith.Game;
import utilz.LoadSave;

public class LevelManager {
    
    private Game game;                // Reference to the main game instance
    private BufferedImage[] levelSprite; // Holds individual tile images
    
    public LevelManager(Game game) {
        this.game = game;
        System.out.println("Initializing LevelManager...");
        //System.out.println("Attempting to load level sprites from: " + LoadSave.LEVEL_SPRITES);
        importLevelSprites();
    }
    
    /**
     * Loads the level sprite sheet from resources
     */
   

    private void importLevelSprites() {
        BufferedImage img = LoadSave.getLevelSprite(); // Load the full tileset image
        levelSprite = new BufferedImage[64]; // 8x8 = 64 tiles

        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                int index = j * 8 + i;
                levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
        }

        System.out.println("Successfully sliced and loaded level sprites!");
    }

    
    /**
     * Draws the level elements on screen
     * @param g Graphics context to draw on
     */
    public void draw(Graphics g) {
        if (levelSprite != null) {
            g.drawImage(levelSprite[2], 0, 0, null);
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