//PS
package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Coin {

	private BufferedImage image;
	private float x, y;
	private int width, height;
    private Rectangle hitbox;
    private boolean active = true;

    private int currentFrame = 0;
    private final int frameCount = 17;
    private final int frameWidth = 32;
    private final int frameHeight = 32;
    private int frameDelay = 10;
    private int frameTick = 0;
    
    public Coin(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initHitbox();
        loadImage();
    }

    private void initHitbox() {
        hitbox = new Rectangle((int) x, (int) y, width, height);
    }
    
    private void loadImage() {
        try {
            // Load the image from a file (make sure to use the correct file path)
            image = ImageIO.read(new File("src/image-resources/World/Strawberry.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void update() {
        hitbox.x = (int) x;
        hitbox.y = (int) y;
        
        frameTick++;
        if (frameTick >= frameDelay) {
            frameTick = 0;
            currentFrame = (currentFrame + 1) % frameCount;
        }
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
    
    public void render(Graphics g) {
       /* if (active) {
        	
            g.setColor(Color.YELLOW);
            g.fillRect((int) x, (int) y, width, height);
        	
        	 int subImageX = 0;  // X position for the subimage within the image
             int subImageY = 0;  // Y position for the subimage within the image
             int subImageWidth = 32;  // Width of the subimage
             int subImageHeight = 32;  // Height of the subimage
             
          // Get the subimage from the full image
             BufferedImage subImage = image.getSubimage(subImageX, subImageY, subImageWidth, subImageHeight);

             // Draw the subimage at the location of the object (x, y)
             g.drawImage(subImage, (int) x, (int) y, null);
        } */
    	
    	 if (active && image != null) {
             int subImageX = currentFrame * frameWidth;
             int subImageY = 0;

             BufferedImage subImage = image.getSubimage(subImageX, subImageY, frameWidth, frameHeight);
             g.drawImage(subImage, (int) x, (int) y, width, height, null);
         }
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
