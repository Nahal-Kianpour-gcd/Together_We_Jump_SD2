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
}
