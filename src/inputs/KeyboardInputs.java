package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import griffith.GamePanel;

public class KeyboardInputs implements KeyListener {
	// Class to handle keyboard input events using the KeyListener interface
	private GamePanel gamePanel;
	
	

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Switch statement to handle key press events for movement keys (WASD)
		// Each case prints a corresponding message for the detected key
		switch(e.getKeyCode()) {
		 
		case KeyEvent.VK_W:
			gamePanel.changeYDelta(-5);   //Forward movement	
			break;
		case KeyEvent.VK_A:
			gamePanel.changeXDelta(-5);  // Left movement		
			break;
		case KeyEvent.VK_S:
			gamePanel.changeYDelta(5);  // Backward movement
			break;
		case KeyEvent.VK_D:
			gamePanel.changeXDelta(5);	// Right movement
			break;
		
		}
		
	}


}
