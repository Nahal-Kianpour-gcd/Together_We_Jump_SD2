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
        BufferedImage img = LoadSave.getLevelSprite();
        System.out.println("Image loaded with size: " + img.getWidth() + "x" + img.getHeight());

        int tileSize = 32;
        int tilesPerRow = img.getWidth() / tileSize;  // should be 12
        int tilesPerCol = img.getHeight() / tileSize; // should be 4

        levelSprite = new BufferedImage[tilesPerRow * tilesPerCol];

        for (int j = 0; j < tilesPerCol; j++) {
            for (int i = 0; i < tilesPerRow; i++) {
                int index = j * tilesPerRow + i;
                levelSprite[index] = img.getSubimage(i * tileSize, j * tileSize, tileSize, tileSize);
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