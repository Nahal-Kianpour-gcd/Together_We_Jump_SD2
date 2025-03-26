package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
	// Class to handle keyboard input events using the KeyListener interface
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
			System.out.println("Its W"); //Forward movement	
			break;
		case KeyEvent.VK_A:
			System.out.println("Its A");  // Left movement		
			break;
		case KeyEvent.VK_S:
			System.out.println("Its S");  // Backward movement
			break;
		case KeyEvent.VK_D:
			System.out.println("Its D");	// Right movement
			break;
		
		}
		
	}


}
