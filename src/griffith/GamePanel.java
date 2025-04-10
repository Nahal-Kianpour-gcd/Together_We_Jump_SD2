package griffith;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

import javax.swing.Timer;


import inputs.KeyboardInputs;
import inputs.MouseInputs;
import utilz.Constants;

public class GamePanel extends JPanel { // Extends JPanel to allow custom drawing and event handling

	private MouseInputs mouseInputs; // Handles mouse input events (clicks, movement)

	private KeyboardInputs keyboardInputs;
	
	// First player position (WASD)
	private float xDelta = 100, yDelta = 100;
	// Second player position (Arrow keys)
	private float xDelta2 = 200, yDelta2 = 100;
	
	private BufferedImage player1img; // Holds the loaded sprite image
	private BufferedImage player2Img; // Image for second player
	
	private static final int IDLE_FRAMES = Constants.IDLE_FRAMES; //Use constant from Constants class |PS
	private static final int RUN_FRAMES = Constants.RUN_FRAMES;   //Use constant from Constants class |PS
	
	private BufferedImage[] idleAnimation1; //Player 1 idle animation frames	|PS
	private BufferedImage[] runAnimation1;  //Player 1 run animation frames		|PS
	private BufferedImage[] idleAnimation2; //Player 2 idle animation frames	|PS
	private BufferedImage[] runAnimation2;  //Player 2 run animation frames		|PS
	
	// Player states
	private int playerDir1 = -1, playerDir2 = -1; // Separate directions for each player
	private boolean moving1 = false, moving2 = false; // Separate movement states for each player

	public GamePanel() {
	
		mouseInputs = new MouseInputs(this);
		
		importImg();
		loadAnimations();

		// Add input listeners for keyboard and mouse
		keyboardInputs = new KeyboardInputs(this);
		
		setPanelSize();
		addKeyListener(keyboardInputs);
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
		
		// Create and start game loop
		Timer gameLoop = new Timer(16, new ActionListener() { // 60 FPS
			@Override
			public void actionPerformed(ActionEvent e) {
				update();
				repaint();
			}
		});
		gameLoop.start();
	}
	
	//Method to load animation assets.|PS
	private void loadAnimations() {	
		idleAnimation1 = new BufferedImage[IDLE_FRAMES];
		runAnimation1 = new BufferedImage[RUN_FRAMES];
	    idleAnimation2 = new BufferedImage[IDLE_FRAMES];
	    runAnimation2 = new BufferedImage[RUN_FRAMES];

	    loadAnimation("/image-resources/Main_Characters/Ninja_Frog/Idle (32x32).png", idleAnimation1);
	    loadAnimation("/image-resources/Main_Characters/Ninja_Frog/Run (32x32).png", runAnimation1);
	    loadAnimation("/image-resources/Main_Characters/Virtual_Guy/Idle (32x32).png", idleAnimation2);
	    loadAnimation("/image-resources/Main_Characters/Virtual_Guy/Run (32x32).png", runAnimation2);
	}

	//Method to read the sprite sheet and extract individual frames. |PS
	private void loadAnimation(String path, BufferedImage[] animation) {
		 try {
		        InputStream is = getClass().getResourceAsStream(path);
		        if (is == null) {
		            throw new RuntimeException("Resource not found: " + path);
		        }
		        BufferedImage img = ImageIO.read(is);
		        int frameWidth = img.getWidth() / animation.length; //Calculate frame width
		        int frameHeight = img.getHeight(); //Height remains the same

		        for (int i = 0; i < animation.length; i++) {
		            animation[i] = img.getSubimage(i * frameWidth, 0, frameWidth, frameHeight); //Extract each frame
		        }
		        is.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	}

	// Loads a sprite image from the resources folder
	private void importImg() {
		InputStream is = getClass().getResourceAsStream("/image-resources/Main_Characters/Ninja_Frog/Idle (32x32).png"); // Load the image as a stream from the
																				// classpath
		try {
			// Read the image from the input stream and assign it to img
			player1img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace(); // Print error details if the image fails to load
		}
		
		InputStream is2 = getClass().getResourceAsStream("/image-resources/Main_Characters/Virtual_Guy/Idle (32x32).png"); // or whatever sprite you use
		try {

			player2Img = ImageIO.read(is2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setPanelSize() {
		Dimension size = new Dimension(1280, 800);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}

	public void setDirection(int direction, boolean isPlayer1) {
		if (isPlayer1) {
			this.playerDir1 = direction;
			moving1 = true;
		} else {
			this.playerDir2 = direction;
			moving2 = true;
		}
	}
	
	public void setMoving(boolean moving, boolean isPlayer1) {
		if (isPlayer1) {
			moving1 = moving;
		} else {
			moving2 = moving;
		}
	}


	public void update() {
		keyboardInputs.update();
		updatePos();
	}
	
	private void updatePos() {
		// Update first player position
		if(moving1) {
			switch(playerDir1) {
				case 0: // LEFT
					xDelta -= 3; // Movement speed
					break;
				case 1: // UP
					yDelta -= 3; // Movement speed
					break;
				case 2: // RIGHT
					xDelta += 3; // Movement speed
					break;
				case 3: // DOWN
					yDelta += 3; // Movement speed
					break;
			}
		}
		
		// Update second player position
		if(moving2) {
			switch(playerDir2) {
				case 0: // LEFT
					xDelta2 -= 3; // Movement speed
					break;
				case 1: // UP
					yDelta2 -= 3; // Movement speed
					break;
				case 2: // RIGHT
					xDelta2 += 3; // Movement speed
					break;
				case 3: // DOWN
					yDelta2 += 3; // Movement speed
					break;
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(player1img.getSubimage(0, 0, 32, 32), (int)xDelta, (int)yDelta, 64, 64, null); // Draws 32x32 sprite at player position (WASD-controlled)
		g.drawImage(player2Img.getSubimage(0, 0, 32, 32), (int)xDelta2, (int)yDelta2, 64, 64, null); // Draws second player (arrow keys)
	}
	
	// Returns Player 2's Y position as an Integer (rounded from float)
	public Integer getYDelta2() {
	    return (int) yDelta2;
	}

	// Returns Player 1's current X position
	public float getXDelta1() {
	    return xDelta;
	}

	// Returns Player 2's current X position
	public float getXDelta2() {
	    return xDelta2;
	}

}
