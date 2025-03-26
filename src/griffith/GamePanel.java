package griffith;

import java.awt.Graphics; // Import Graphics class for drawing capabilities
import javax.swing.JPanel; // Import JPanel to create a panel

public class GamePanel extends JPanel { // Extends JPanel to create custom panel

	public GamePanel() {
		// Constructor for GamePanel
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // Call superclass method to handle standard painting
		g.drawRect(100, 100, 200, 50); // Draw a rectangle on the panel
	}
}
