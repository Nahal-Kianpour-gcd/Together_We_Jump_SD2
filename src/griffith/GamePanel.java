package griffith;

import java.awt.Graphics; // Import Graphics class for drawing capabilities


import javax.swing.JPanel; // Import JPanel to create a panel

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel { // Extends JPanel to create custom panel

	private MouseInputs mouseInputs; // Declares a private variable 'mouseInputs' of type MouseInputs, which will be used to handle mouse-related input events.
	private int xDelta = 0, yDelta = 0; // Declares and initializes two integer variables 'xDelta' and 'yDelta' to track the change in position (delta) for X and Y coordinates.
	
	public GamePanel() {
		// Constructor for GamePanel, no initialization needed
		
		mouseInputs = new  MouseInputs();
		addKeyListener(new KeyboardInputs()); 
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
		
		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // Call superclass method to handle standard painting
		g.fillRect(100 + xDelta, 100 + yDelta, 200, 50); // Draw a rectangle on the panel at position (100, 100) with width 200 and
														// height 50
	}
}
