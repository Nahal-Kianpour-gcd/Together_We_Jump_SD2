package griffith;

import java.awt.Graphics; // Import Graphics class for drawing capabilities
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel; // Import JPanel to create a panel

public class GamePanel extends JPanel { // Extends JPanel to create custom panel

	public GamePanel() {
		// Constructor for GamePanel, no initialization needed
		addKeyListener(new KeyListener() { 
			// Adding a KeyListener to handle keyboard input events
			// Methods will be implemented to manage keyTyped, keyPressed, and keyReleased actions

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
				// TODO Auto-generated method stub
				
			}
			
		});
		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // Call superclass method to handle standard painting
		g.fillRect(100, 100, 200, 50); // Draw a rectangle on the panel at position (100, 100) with width 200 and
										// height 50
	}
}
