//Nahal Kianpour Lirharani
package utilz;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class LoadSave {
    
    // Base paths for resources
    public static final String CHARACTERS_FOLDER = "/image-resources/Main_Characters";
    public static final String LEVEL_SPRITES = "/image-resources/World/world_set/world_tileset.png";
    
    /**
     * Loads a sprite sheet for a specific character and animation
     * @param characterName The character folder name (e.g., "Ninja_Frog" or "Virtual_Guy")
     * @param fileName The animation file name (e.g., "Idle (32x32).png" or "Run (32x32).png")
     * @return The loaded BufferedImage, or null if loading fails
     */
    public static BufferedImage getCharacterSprite(String characterName, String fileName) {
        BufferedImage img = null;
        String fullPath = CHARACTERS_FOLDER + "/" + characterName + "/" + fileName;
        
        try (InputStream is = LoadSave.class.getResourceAsStream(fullPath)) {
            if (is != null) {
                img = ImageIO.read(is);
            } else {
                throw new RuntimeException("Resource not found: " + fullPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return img;
    }

    /**
     * Loads the level sprite sheet containing tiles and level elements
     * @return The loaded BufferedImage containing level sprites
     */
    public static BufferedImage getLevelSprite() {
        BufferedImage img = null;
        System.out.println("Attempting to load level sprite from: " + LEVEL_SPRITES);
        try (InputStream is = LoadSave.class.getResourceAsStream(LEVEL_SPRITES)) {
            if (is != null) {
                img = ImageIO.read(is);
            } else {
                System.out.println("Could not find level sprites at: " + LEVEL_SPRITES);
                System.out.println("Please make sure you have created the following directory structure:");
                System.out.println("src/");
                System.out.println("  └── image-resources/");
                System.out.println("      └── world/");
                System.out.println("          └── world_tileset.png");
                throw new RuntimeException("Level sprites not found: " + LEVEL_SPRITES);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return img;
    }
}
