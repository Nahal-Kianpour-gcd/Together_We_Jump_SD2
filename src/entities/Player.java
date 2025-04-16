package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import utilz.Constants;
import utilz.LoadSave;
import griffith.Game;
import java.awt.Rectangle;


public class Player extends Entity {
	// Array of frames for idle animation
	private BufferedImage[] idleAnimation;

	// Array of frames for running animation
	private BufferedImage[] runAnimation;

	// Controls animation frame timing
	private int aniTick, aniIndex, aniSpeed = 15;

	// Current player action: 0 = idle, 1 = run
	private int playerAction = Constants.PlayerConstants.IDLE;

	// Indicates whether the player is currently moving
	private boolean moving = false;

	// Direction of player movement: 0 = left, 1 = up, 2 = right, 3 = down
	private int playerDir = -1;
	private float playerSpeed = 3.0f;
	private boolean left, right, up, down;

	// File path to the character's sprite sheet (used for loading animations)- TPH
	private String characterPath;
	// Loads level data into the class by assigning the provided 2D array to lvlData.

	private int[][] lvlData;

	// Player constructor that sets position and character sprite path
	public Player(float x, float y, int width, int height, String characterPath) {
	    super(x, y, width, height); 
	    this.characterPath = characterPath;
	    loadAnimations();
	}

	// Loads the idle and run animations for this player
	private void loadAnimations() {
		// Create empty arrays to hold animation frames
		idleAnimation = new BufferedImage[Constants.IDLE_FRAMES];
		runAnimation = new BufferedImage[Constants.RUN_FRAMES];

		/* Old code - will be removed
		loadAnimation(characterPath + "/Idle (32x32).png", idleAnimation);
		loadAnimation(characterPath + "/Run (32x32).png", runAnimation);
		*/

		// Get the character name from the path (e.g., "Ninja_Frog" or "Virtual_Guy")
		String characterName = characterPath.substring(characterPath.lastIndexOf("Main_Characters/") + 15);
		
		// Load idle animation
		BufferedImage img = LoadSave.getCharacterSprite(characterName, "Idle (32x32).png");
		for (int i = 0; i < idleAnimation.length; i++) {
			idleAnimation[i] = img.getSubimage(i * 32, 0, 32, 32);
		}
		
		// Load run animation
		img = LoadSave.getCharacterSprite(characterName, "Run (32x32).png");
		for (int i = 0; i < runAnimation.length; i++) {
			runAnimation[i] = img.getSubimage(i * 32, 0, 32, 32);
		}
	}

	public void loadLvlData(int[][] lvlData) {
		if (lvlData == null) {
			this.lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
		} else {
			this.lvlData = lvlData;
		}
	}

	//TPH
	public void update() {
	// Main update method called every frame or tick
	updateAnimationTick(); // Updates animation frame based on timing
	setAnimation();        // Sets the current animation state (idle or running)
	updatePos();           // Updates the player's position (not shown here)
	updateHitbox();
}

	private void updateAnimationTick() {
	aniTick++; // Increment the animation tick counter
	if (aniTick >= aniSpeed) { // If enough ticks have passed to update animation frame
		aniTick = 0; // Reset tick counter
		aniIndex++;  // Move to next animation frame

		// If the animation frame index exceeds the number of frames available
		if (aniIndex >= Constants.getSpriteAmount(playerAction)) {
			aniIndex = 0; // Loop back to the first frame
		}
	}
}
	//TPH
	private void setAnimation() {
		// Determines the player's current action (animation state) based on whether the player is moving or not

		if (moving) {
			// If the player is currently moving, switch to the RUNNING animation
			playerAction = Constants.PlayerConstants.RUNNING;
		} else {
			// If the player is not moving, switch to the IDLE animation
			playerAction = Constants.PlayerConstants.IDLE;
		}
	}



	// Updates the player's position based on movement direction
	private void updatePos() {
		moving = false;
		if(!left && !right && !up && !down)
			return;
		
		if (left && !right) {
			x -= playerSpeed;
			moving = true;
		} else if (right && !left) {
			x += playerSpeed;
			moving = true;
		}
		
		if (up && !down) {
			y -= playerSpeed;
			moving = true;
		} else if (down && !up) {
			y += playerSpeed;
			moving = true;
		}
		
			
			
		/*if (moving) { // Only update position if the player is currently moving
			switch (playerDir) {
				case Constants.Directions.LEFT:
					x -= 3; // Move left by 3 units
					break;
				case Constants.Directions.UP:
					y -= 3; // Move up by 3 units
					break;
				case Constants.Directions.RIGHT:
					x += 3; // Move right by 3 units
					break;
				case Constants.Directions.DOWN:
					y += 3; // Move down by 3 units
					break;
			}
		}*/
	}

	// Renders the current frame of the player's animation on screen |NK
	public void render(Graphics g) {
	   // Determine which animation to use based on player action (idle or run)
	    BufferedImage[] currentAnimation = (playerAction == Constants.PlayerConstants.IDLE) ? idleAnimation : runAnimation;

	    // Scale the character to be 2 tiles tall and 2 tiles wide
	    int drawSize = Game.TILES_SIZE * 2;

	    // Draw the current frame at the player's current position
	    g.drawImage(currentAnimation[aniIndex], (int) x, (int) y, width, height, null); 
		drawHitbox(g);
		
	}


	// Sets the player's movement direction and marks the player as moving
	public void setDirection(int direction, boolean isPressed) {
		switch(direction) {
			case Constants.Directions.LEFT:
				left = isPressed;
				break;
			case Constants.Directions.RIGHT:
				right = isPressed;
				break;
			case Constants.Directions.UP:
				up = isPressed;
				break;
			case Constants.Directions.DOWN:
				down = isPressed;
				break;
		}
	}

	// Sets the moving status of the player
	public void setMoving(boolean moving) {
		this.moving = moving;
		if (!moving) {
			aniIndex = 0; // Reset animation frame to the beginning when movement stops
		}
	}

	// Returns the player's current X position
	public float getX() {
		return x;
	}

	// Returns the player's current Y position -TPH
	public float getY() {
		return y;
	}

	@Override
	protected void initHitBox() {
		hitbox = new Rectangle((int) x, (int) y, width, height);
	}

	protected void updateHitbox() {
		hitbox.x = (int) x;
		hitbox.y = (int) y;
	}
}
