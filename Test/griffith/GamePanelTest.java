package griffith;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import inputs.KeyboardInputs;
import java.awt.event.KeyEvent;

class GamePanelTest {
	private GamePanel gamePanel;
	private KeyboardInputs keyboardInputs;

	@BeforeEach
	public void setUp() {
		gamePanel = new GamePanel();
		keyboardInputs = new KeyboardInputs(gamePanel);
	}

	@Test
	public void testPlayer1MovesLeft() {
		float initialX = gamePanel.getXDelta1();
		keyboardInputs.keyPressed(
				new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A'));

		// Simulate multiple updates
		for (int i = 0; i < 5; i++) {
			gamePanel.update();
		}

		keyboardInputs.keyReleased(
				new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A'));

		assertEquals(initialX - 15, gamePanel.getXDelta1(),
				"Player 1 should move left by 15 units after multiple updates.");
	}

	@Test
	public void testPlayer1MovesRight() {
		// Save the initial X position of player 1 before pressing any keys
		float initialX = gamePanel.getXDelta1();

		// Simulate pressing the 'D' key (which should move player 1 to the right)
		keyboardInputs.keyPressed(
				new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D'));

		// Simulate 5 game updates (each update should move the player by 3 units to the
		// right)
		for (int i = 0; i < 5; i++) {
			gamePanel.update();
		}

		// Simulate releasing the 'D' key (stop movement)
		keyboardInputs.keyReleased(
				new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_D, 'D'));

		// Assert that the player has moved 15 units to the right (5 updates × 3 units)
		assertEquals(initialX + 15, gamePanel.getXDelta1(),
				"Player 1 should move right by 15 units after multiple updates.");
	}
	
	@Test
	public void testPlayer2MovesUp() {
	    // Store the initial Y position of Player 2 before any key is pressed
	    float initialY = gamePanel.getYDelta2();

	    // Simulate pressing the UP arrow key (used to move Player 2 upward)
	    keyboardInputs.keyPressed(new KeyEvent(
	        gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED));

	    // Simulate 5 game updates to apply the movement effect
	    for (int i = 0; i < 5; i++) {
	        gamePanel.update();
	    }

	    // Simulate releasing the UP arrow key to stop Player 2's movement
	    keyboardInputs.keyReleased(new KeyEvent(
	        gamePanel, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED));

	 // Final check to verify movement happened
	 // 0.001f is the tolerance range, which handles tiny decimal differences caused by floating point math.
	    assertEquals(initialY - 15, gamePanel.getYDelta2(), 0.001f, "Player 2 should move up by 15 units after multiple updates.");

	}
	
    @Test
    public void testPlayer2StopsMoving() {
        float initialX = gamePanel.getXDelta2();
        keyboardInputs.keyPressed(new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));

        for (int i = 0; i < 5; i++) {
            gamePanel.update();
        }

        keyboardInputs.keyReleased(new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));

        float movedX = gamePanel.getXDelta2();

        // Call update a few more times after stopping
        for (int i = 0; i < 10; i++) { // Run more updates
            gamePanel.update();
        }

        assertEquals(movedX, gamePanel.getXDelta2(), "Player 2 should remain in place after stopping.");
    
  

    }
	

}
