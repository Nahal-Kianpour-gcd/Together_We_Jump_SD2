package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import griffith.GamePanel;

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
				gamePanel.setDirection(1, true); // UP for Player 1
				break;
			case KeyEvent.VK_A:
				aPressed = true;
				gamePanel.setDirection(0, true); // LEFT for Player 1
				break;
			case KeyEvent.VK_S:
				sPressed = true;
				gamePanel.setDirection(3, true); // DOWN for Player 1
				break;
			case KeyEvent.VK_D:
				dPressed = true;
				gamePanel.setDirection(2, true); // RIGHT for Player 1
				break;
				
			// Player 2 controls (Arrow keys)
			case KeyEvent.VK_UP:
				upPressed = true;
				gamePanel.setDirection(1, false); // UP for Player 2
				break;
			case KeyEvent.VK_LEFT:
				leftPressed = true;
				gamePanel.setDirection(0, false); // LEFT for Player 2
				break;
			case KeyEvent.VK_DOWN:
				downPressed = true;
				gamePanel.setDirection(3, false); // DOWN for Player 2
				break;
			case KeyEvent.VK_RIGHT:
				rightPressed = true;
				gamePanel.setDirection(2, false); // RIGHT for Player 2
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			// Player 1 controls (WASD)
			case KeyEvent.VK_W:
				wPressed = false;
				break;
			case KeyEvent.VK_A:
				aPressed = false;
				break;
			case KeyEvent.VK_S:
				sPressed = false;
				break;
			case KeyEvent.VK_D:
				dPressed = false;
				break;
				
			// Player 2 controls (Arrow keys)
			case KeyEvent.VK_UP:
				upPressed = false;
				break;
			case KeyEvent.VK_LEFT:
				leftPressed = false;
				break;
			case KeyEvent.VK_DOWN:
				downPressed = false;
				break;
			case KeyEvent.VK_RIGHT:
				rightPressed = false;
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
		if (wPressed) gamePanel.setDirection(1, true); // UP
		if (sPressed) gamePanel.setDirection(3, true); // DOWN
		if (aPressed) gamePanel.setDirection(0, true); // LEFT
		if (dPressed) gamePanel.setDirection(2, true); // RIGHT

		// Second player movement (Arrow keys)
		if (upPressed) gamePanel.setDirection(1, false); // UP
		if (downPressed) gamePanel.setDirection(3, false); // DOWN
		if (leftPressed) gamePanel.setDirection(0, false); // LEFT
		if (rightPressed) gamePanel.setDirection(2, false); // RIGHT
	}
}
