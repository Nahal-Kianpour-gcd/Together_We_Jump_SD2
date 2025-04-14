package entities;

import java.awt.image.BufferedImage;

public class Player extends Entity{

	// Array of frames for idle animation
	private BufferedImage[] idleAnimation;

	// Array of frames for running animation
	private BufferedImage[] runAnimation;

	// Controls animation frame timing
	private int aniTick, aniIndex, aniSpeed = 15;

	// Current player action: 0 = idle, 1 = run
	private int playerAction = 0;

	// Indicates whether the player is currently moving
	private boolean moving = false;

	// Direction of player movement: 0 = left, 1 = up, 2 = right, 3 = down
	private int playerDir = -1;

	// File path to the character's sprite sheet (used for loading animations)- TPH
	private String characterPath;

	// Player constructor that sets position and character sprite path
	public Player(float x, float y, String characterPath) {
	    super(x, y); // Call constructor of the base Entity class
	    this.characterPath = characterPath; // Store the path to character sprite assets
	    loadAnimations(); // Load idle and run animations based on the given path
	}

	// Loads the idle and run animations for this player
	private void loadAnimations() {
	    // Create empty arrays to hold animation frames
	    idleAnimation = new BufferedImage[Constants.IDLE_FRAMES];
	    runAnimation = new BufferedImage[Constants.RUN_FRAMES];

	    // Load actual frame images from file paths using helper method
	    loadAnimation(characterPath + "/Idle (32x32).png", idleAnimation);
	    loadAnimation(characterPath + "/Run (32x32).png", runAnimation);
	}

	// Reads a sprite sheet and splits it into individual frames
	private void loadAnimation(String path, BufferedImage[] animation) {
	    try {
	        // Load the sprite sheet as a resource stream
	        InputStream is = getClass().getResourceAsStream(path);
	        if (is == null) {
	            throw new RuntimeException("Resource not found: " + path);
	        }

	        // Read the full image from the stream
	        BufferedImage img = ImageIO.read(is);
	        int frameWidth = img.getWidth() / animation.length; // Calculate width of one frame
	        int frameHeight = img.getHeight(); // Height stays the same

	        // Loop through and extract each frame as a subimage
	        for (int i = 0; i < animation.length; i++) {
	            animation[i] = img.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
	        }

	        // Close the stream after loading
	        is.close();
	    } catch (IOException e) {
	        e.printStackTrace(); // Print error if loading fails - TPH
	    }
	}

	public void update() {
		
	}
	public void render() {
		
	}
	
}
