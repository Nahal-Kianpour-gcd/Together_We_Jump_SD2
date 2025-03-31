package griffith;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel { // Extends JPanel to allow custom drawing and event handling

	private MouseInputs mouseInputs; // Handles mouse input events (clicks, movement)
	private KeyboardInputs keyboardInputs;
	
	// First player position (WASD)
	private float xDelta = 100, yDelta = 100;
	// Second player position (Arrow keys)
	private float xDelta2 = 200, yDelta2 = 100;

	public GamePanel() {
		mouseInputs = new MouseInputs(this);

		// Add input listeners for keyboard and mouse
		keyboardInputs = new KeyboardInputs(this);
		
		setPanelSize();
		addKeyListener(keyboardInputs);
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
	}

	private void setPanelSize() {
		Dimension size = new Dimension(1280, 800);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
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

		updateRectangle(); // Update rectangle position and color if needed

		g.setColor(color); // Set drawing color
		g.fillRect((int) xDelta, (int) yDelta, 200, 50); // Draw the rectangle
	}

	// Update rectangle's position and direction based on boundaries
	private void updateRectangle() {
		xDelta += xDir;
		if (xDelta > 400 || xDelta < 0) {
			xDir *= -1; // Reverse direction when hitting left/right edges
			color = getRndColor(); // Change to a random color
		}

		yDelta += yDir;
		if (yDelta > 400 || yDelta < 0) {
			yDir *= -1; // Reverse direction when hitting top/bottom edges
			color = getRndColor(); // Change to a random color
		}
	}

	// Generate and return a random color
	private Color getRndColor() {
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		return new Color(r, g, b);
	}
}
