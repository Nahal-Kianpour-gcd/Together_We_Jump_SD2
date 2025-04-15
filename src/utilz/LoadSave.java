//Nahal Kianpour Lirharani
package utilz;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class LoadSave {
    
    /**
     * Loads a sprite sheet for either Ninja Frog or Virtual Guy character animations
     * @param path The path to the character sprite (e.g., "/Main_Characters/Ninja_Frog/Idle (32x32).png")
     * @return The loaded BufferedImage, or null if loading fails
     */
	//|NK
    public static BufferedImage getCharacterSprite(String path) {
        // Declare the BufferedImage variable that will store our sprite
        BufferedImage img = null;
        
        // Get the input stream for the sprite image
        InputStream is = LoadSave.class.getResourceAsStream(path);
        
        try {
            // Try to read the image from the input stream
            if (is != null) {
                img = ImageIO.read(is);
            } else {
                throw new RuntimeException("Resource not found: " + path);
            }
            
        } catch (Exception e) {
            // Print stack trace if there's an error loading the image
            e.printStackTrace();
        } finally {
            // Close the input stream in a finally block to ensure it always happens
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        // Return the loaded image
        return img;
    }
}
