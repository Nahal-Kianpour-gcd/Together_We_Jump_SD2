//Nahal Kianpour Lirharani
package utilz;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class LoadSave {
    
    // Base path for character resources
    public static final String CHARACTERS_FOLDER = "/image-resources/Main_Characters";
    
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
}
