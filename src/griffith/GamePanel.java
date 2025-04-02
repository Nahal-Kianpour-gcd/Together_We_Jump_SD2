package griffith;

import java.awt.Graphics; // Import Graphics class for drawing capabilities

import javax.swing.JPanel; // Import JPanel to create a custom panel

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel { // Extends JPanel to allow custom drawing and event handling

	private MouseInputs mouseInputs; // Handles mouse input events (clicks, movement)
	private float xDelta = 100, yDelta = 100; // Position of the rectangle
	private int frames = 0; // Used to count frames (not currently used)
	private long lastCheck = 0; // Used to check time intervals (not currently used)
	
	
	public GamePanel() {

		mouseInputs = new MouseInputs(this);

		// Add input listeners for keyboard and mouse
		addKeyListener(new KeyboardInputs(this)); 
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}

	// Change rectangle's X position by given value
	public void changeXDelta(int value) {
		this.xDelta += value;
	}

	// Change rectangle's Y position by given value
	public void changeYDelta(int value) {
		this.yDelta += value;
	}

	// Set rectangle's position to specified coordinates
	public void setRectPos(int x, int y) {
		this.xDelta = x;
		this.yDelta = y;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // Clear the panel before drawing

		g.drawImage(null, frames, frames, getFocusCycleRootAncestor());
	}

}
