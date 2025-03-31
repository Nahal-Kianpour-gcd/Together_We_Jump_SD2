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
	}
}
