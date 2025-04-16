package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import griffith.GamePanel;
import utilz.Constants;

public class KeyboardInputs implements KeyListener {
	// Class to handle keyboard input events using the KeyListener interface
	private GamePanel gamePanel;
	
	// Player 1: controlled by WASD
	private boolean wPressed = false;
	private boolean aPressed = false;
	private boolean sPressed = false;
	private boolean dPressed = false;
	
	// Player 2: controlled by arrow keys
	
	private boolean upPressed = false;
	private boolean leftPressed = false;
	private boolean downPressed = false;
	private boolean rightPressed = false;

	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Not used
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			// Player 1 controls (WASD)
			case KeyEvent.VK_W:
				wPressed = true;
				gamePanel.setDirection(Constants.Directions.UP, true, true);
				break;
			case KeyEvent.VK_A:
				aPressed = true;
				gamePanel.setDirection(Constants.Directions.LEFT, true, true);
				break;
			case KeyEvent.VK_S:
				sPressed = true;
				gamePanel.setDirection(Constants.Directions.DOWN, true, true);
				break;
			case KeyEvent.VK_D:
				dPressed = true;
				gamePanel.setDirection(Constants.Directions.RIGHT, true, true);
				break;
				
			// Player 2 controls (Arrow keys)
			case KeyEvent.VK_UP:
				upPressed = true;
				gamePanel.setDirection(Constants.Directions.UP, false, true);
				break;
			case KeyEvent.VK_LEFT:
				leftPressed = true;
				gamePanel.setDirection(Constants.Directions.LEFT, false, true);
				break;
			case KeyEvent.VK_DOWN:
				downPressed = true;
				gamePanel.setDirection(Constants.Directions.DOWN, false, true);
				break;
			case KeyEvent.VK_RIGHT:
				rightPressed = true;
				gamePanel.setDirection(Constants.Directions.RIGHT, false, true);
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			// Player 1 controls (WASD)
			case KeyEvent.VK_W:
				wPressed = false;
				gamePanel.setDirection(Constants.Directions.UP, true, false);
				break;
			case KeyEvent.VK_A:
				aPressed = false;
				gamePanel.setDirection(Constants.Directions.LEFT, true, false);
				break;
			case KeyEvent.VK_S:
				sPressed = false;
				gamePanel.setDirection(Constants.Directions.DOWN, true, false);
				break;
			case KeyEvent.VK_D:
				dPressed = false;
				gamePanel.setDirection(Constants.Directions.RIGHT, true, false);
				break;
				
			// Player 2 controls (Arrow keys)
			case KeyEvent.VK_UP:
				upPressed = false;
				gamePanel.setDirection(Constants.Directions.UP, false, false);
				break;
			case KeyEvent.VK_LEFT:
				leftPressed = false;
				gamePanel.setDirection(Constants.Directions.LEFT, false, false);
				break;
			case KeyEvent.VK_DOWN:
				downPressed = false;
				gamePanel.setDirection(Constants.Directions.DOWN, false, false);
				break;
			case KeyEvent.VK_RIGHT:
				rightPressed = false;
				gamePanel.setDirection(Constants.Directions.RIGHT, false, false);
				break;
		}
		
		// Check if any movement keys are still pressed for each player
		if(!wPressed && !aPressed && !sPressed && !dPressed) {
			gamePanel.setMoving(false, true); // Player 1 (WASD)
		}
		if(!upPressed && !leftPressed && !downPressed && !rightPressed) {
			gamePanel.setMoving(false, false); // Player 2 (Arrow keys)
		}
	}

	public void update() {
		// First player movement (WASD)
		if (wPressed) gamePanel.setDirection(Constants.Directions.UP, true, true);
		if (sPressed) gamePanel.setDirection(Constants.Directions.DOWN, true, true);
		if (aPressed) gamePanel.setDirection(Constants.Directions.LEFT, true, true);
		if (dPressed) gamePanel.setDirection(Constants.Directions.RIGHT, true, true);

		// Second player movement (Arrow keys)
		if (upPressed) gamePanel.setDirection(Constants.Directions.UP, false, true);
		if (downPressed) gamePanel.setDirection(Constants.Directions.DOWN, false, true);
		if (leftPressed) gamePanel.setDirection(Constants.Directions.LEFT, false, true);
		if (rightPressed) gamePanel.setDirection(Constants.Directions.RIGHT, false, true);
	}

}