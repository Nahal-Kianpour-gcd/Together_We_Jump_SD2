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

	public Player(float x, float y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	public void update() {
		
	}
	public void render() {
		
	}
	
}
