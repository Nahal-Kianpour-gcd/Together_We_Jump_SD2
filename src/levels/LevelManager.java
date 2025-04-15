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
    private Level levelOne;
    
    public LevelManager(Game game) {
        this.game = game;
        System.out.println("Initializing LevelManager...");
        //System.out.println("Attempting to load level sprites from: " + LoadSave.LEVEL_SPRITES);
        importLevelSprites();
        levelOne = new Level(LoadSave.GetLevelData());
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
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++) {
            for (int i = 0; i < Game.TILES_IN_WIDTH; i++) {
                int index = levelOne.getSpriteIndex(i, j); // Get tile index at grid (i, j)
                g.drawImage(levelSprite[index], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
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