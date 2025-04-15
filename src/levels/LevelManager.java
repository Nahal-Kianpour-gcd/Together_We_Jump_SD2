// Nahal Kianpour
// Manages the current level, loading, switching, and level-related logic
package levels;

import java.awt.image.BufferedImage;
import griffith.Game;
import utilz.LoadSave;

public class LevelManager {
    
    private Game game;
    private BufferedImage levelSprite;
    
    public LevelManager(Game game) {
        this.game = game;
        importLevelSprites();
    }
    
    private void importLevelSprites() {
        levelSprite = LoadSave.getLevelSprite();
    }
    
    public void draw() {
        // Will be implemented later for drawing the level
    }
    
    public void update() {
        // Will be implemented later for updating level state
    }
}
