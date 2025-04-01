package griffith;

import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel { // Extends JPanel to allow custom drawing and event handling

	private MouseInputs mouseInputs; // Handles mouse input events (clicks, movement)
	private KeyboardInputs keyboardInputs;
	
	// First player position (WASD)
	private float xDelta = 100, yDelta = 100;
	// Second player position (Arrow keys)
	private float xDelta2 = 200, yDelta2 = 100;
	
	// Player states
	private int playerDir1 = -1, playerDir2 = -1; // Separate directions for each player
	private boolean moving1 = false, moving2 = false; // Separate movement states for each player

	public GamePanel() {
		mouseInputs = new MouseInputs(this);

		// Add input listeners for keyboard and mouse
		keyboardInputs = new KeyboardInputs(this);
		
		setPanelSize();
		addKeyListener(keyboardInputs);
		addMouseListener(mouseInputs);
		addMouseMotionListener(mouseInputs);
		
		// Create and start game loop
		Timer gameLoop = new Timer(16, new ActionListener() { // 60 FPS
			@Override
			public void actionPerformed(ActionEvent e) {
				update();
				repaint();
			}
		});
		gameLoop.start();
	}

	private void setPanelSize() {
		Dimension size = new Dimension(1280, 800);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}

	public void setDirection(int direction, boolean isPlayer1) {
		if (isPlayer1) {
			this.playerDir1 = direction;
			moving1 = true;
		} else {
			this.playerDir2 = direction;
			moving2 = true;
		}
	}
	
	public void setMoving(boolean moving, boolean isPlayer1) {
		if (isPlayer1) {
			moving1 = moving;
		} else {
			moving2 = moving;
		}
	}

	public void update() {
		keyboardInputs.update();
		updatePos();
	}
	
	private void updatePos() {
		// Update first player position
		if(moving1) {
			switch(playerDir1) {
				case 0: // LEFT
					xDelta -= 3; // Movement speed
					break;
				case 1: // UP
					yDelta -= 3; // Movement speed
					break;
				case 2: // RIGHT
					xDelta += 3; // Movement speed
					break;
				case 3: // DOWN
					yDelta += 3; // Movement speed
					break;
			}
		}
		
		// Update second player position
		if(moving2) {
			switch(playerDir2) {
				case 0: // LEFT
					xDelta2 -= 3; // Movement speed
					break;
				case 1: // UP
					yDelta2 -= 3; // Movement speed
					break;
				case 2: // RIGHT
					xDelta2 += 3; // Movement speed
					break;
				case 3: // DOWN
					yDelta2 += 3; // Movement speed
					break;
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Draw first player (WASD)
		g.setColor(Color.BLUE);
		g.fillRect((int)xDelta, (int)yDelta, 50, 50);
		
		// Draw second player (Arrow keys)
		g.setColor(Color.RED);
		g.fillRect((int)xDelta2, (int)yDelta2, 50, 50);
	}
}
