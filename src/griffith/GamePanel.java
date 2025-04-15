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
import entities.Player;

public class GamePanel extends JPanel {

	private MouseInputs mouseInputs;
	private KeyboardInputs keyboardInputs;
	
	private Player player1;
	private Player player2;

	/* Old code - Start */
	/*
	// First player position (WASD)
	private float xDelta = 100, yDelta = 100;
	// Second player position (Arrow keys)
	private float xDelta2 = 200, yDelta2 = 100;

	private static final int IDLE_FRAMES = Constants.IDLE_FRAMES;
	private static final int RUN_FRAMES = Constants.RUN_FRAMES;

	private BufferedImage[] idleAnimation1;
	private BufferedImage[] runAnimation1;
	private BufferedImage[] idleAnimation2;
	private BufferedImage[] runAnimation2;

	//Player states
	private int playerDir1 = -1, playerDir2 = -1;
	private boolean moving1 = false, moving2 = false;

	private int aniTick1, aniTick2, aniSpeed = 15;
	private int aniIndex1 = 0, aniIndex2 = 0;
	private int playerAction1 = 0, playerAction2 = 0;
	*/
	/* Old code - End */

	public GamePanel() {
		mouseInputs = new MouseInputs(this);
		keyboardInputs = new KeyboardInputs(this);

		/* Old initialization code - Start */
		/*
		loadAnimations();
		*/
		/* Old initialization code - End */

		initializePlayers();
		setPanelSize();
		
		addKeyListener(keyboardInputs);
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);

		Timer gameLoop = new Timer(16, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateGame();
				repaint();
			}
		});
		gameLoop.start();
	}

	private void initializePlayers() {
		player1 = new Player(100, 100, "/image-resources/Main_Characters/Ninja_Frog");
		player2 = new Player(200, 100, "/image-resources/Main_Characters/Virtual_Guy");
	}

	/* Old animation methods - Start */
	/*
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

	private void loadAnimation(String path, BufferedImage[] animation) {
		try {
			InputStream is = getClass().getResourceAsStream(path);
			if (is == null) {
				throw new RuntimeException("Resource not found: " + path);
			}
			BufferedImage img = ImageIO.read(is);
			int frameWidth = img.getWidth() / animation.length;
			int frameHeight = img.getHeight();

			for (int i = 0; i < animation.length; i++) {
				animation[i] = img.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
			}
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setAnimation() {
		//Set Player 1 animation state
		if (moving1) {
			playerAction1 = 1; //RUN
		} else {
			playerAction1 = 0; //IDLE
		}
		
		//Set Player 2 animation state
		if (moving2) {
			playerAction2 = 1; //RUN
		} else {
			playerAction2 = 0; //IDLE
		}
	}

	private void updatePos() {
		// Update first player position
		if (moving1) {
			switch (playerDir1) {
				case 0: // LEFT
					xDelta -= 3;
					break;
				case 1: // UP
					yDelta -= 3;
					break;
				case 2: // RIGHT
					xDelta += 3;
					break;
				case 3: // DOWN
					yDelta += 3;
					break;
			}
		}

		// Update second player position
		if (moving2) {
			switch (playerDir2) {
				case 0: // LEFT
					xDelta2 -= 3;
					break;
				case 1: // UP
					yDelta2 -= 3;
					break;
				case 2: // RIGHT
					xDelta2 += 3;
					break;
				case 3: // DOWN
					yDelta2 += 3;
					break;
			}
		}
	}
// Method to switch between animations. |PS
	private void updateAnimationTick() {
		// Update first player animation
		aniTick1++;
		if (aniTick1 >= aniSpeed) {
			aniTick1 = 0;
			int maxFrames;
			if (playerAction1 == 0) {
				maxFrames = IDLE_FRAMES;
			} else {
				maxFrames = RUN_FRAMES;
			}
			if (aniIndex1 < maxFrames - 1) {
				aniIndex1++;
			} else {
				aniIndex1 = 0;
			}
		}

		// Update second player animation
		aniTick2++;
		if (aniTick2 >= aniSpeed) {
			aniTick2 = 0;
			int maxFrames;
			if (playerAction2 == 0) {
				maxFrames = IDLE_FRAMES;
			} else {
				maxFrames = RUN_FRAMES;
			}
			if (aniIndex2 < maxFrames - 1) {
				aniIndex2++;
			} else {
				aniIndex2 = 0;
			}
		}
	}
	
	/* Old animation methods - End */

	private void setPanelSize() {
		Dimension size = new Dimension(1280, 800);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}

	public void setDirection(int direction, boolean isPlayer1) {
		if (isPlayer1) {
			/* Old direction code - Start */
			/*
			playerDir1 = direction;
			moving1 = true;
			*/
			/* Old direction code - End */
			player1.setDirection(direction);
		} else {
			/* Old direction code - Start */
			/*
			playerDir2 = direction;
			moving2 = true;
			*/
			/* Old direction code - End */
			player2.setDirection(direction);
		}
	}

	public void setMoving(boolean moving, boolean isPlayer1) {
		if (isPlayer1) {
			/* Old moving code - Start */
			/*
			moving1 = moving;
			if (!moving) {
				aniIndex1 = 0;
			}
			*/
			/* Old moving code - End */
			player1.setMoving(moving);
		} else {
			/* Old moving code - Start */
			/*
			moving2 = moving;
			if (!moving) {
				aniIndex2 = 0;
			}
			*/
			/* Old moving code - End */
			player2.setMoving(moving);
		}
	}

	public void updateGame() {
		keyboardInputs.update();
		/* Old update code - Start */
		/*
		updateAnimationTick();
		setAnimation();
		updatePos();
		*/
		/* Old update code - End */
		player1.update();
		player2.update();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		/* Old render code - Start */
		/*
		//Draw Player 1
		if (playerAction1 == 0) { //Idle
			g.drawImage(idleAnimation1[aniIndex1], (int) xDelta, (int) yDelta, 96, 96, null);
		} else { //Running
			g.drawImage(runAnimation1[aniIndex1], (int) xDelta, (int) yDelta, 96, 96, null);
		}

		//Draw Player 2
		if (playerAction2 == 0) { //Idle
			g.drawImage(idleAnimation2[aniIndex2], (int) xDelta2, (int) yDelta2, 96, 96, null);
		} else { // Running
			g.drawImage(runAnimation2[aniIndex2], (int) xDelta2, (int) yDelta2, 96, 96, null);
		}
		*/
		/* Old render code - End */
		player1.render(g);
		player2.render(g);
	}
	// Returns Player 1's current X position
	public float getXDelta1() {
		return player1.getX();
	}
	// Returns Player 2's current X position
	public float getXDelta2() {
		return player2.getX();
	}
	// Returns Player 2's Y position as an Integer (rounded from float)
	public int getYDelta2() {
		return (int) player2.getY();
	}
}