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
        keyboardInputs.keyPressed(new KeyEvent(gamePanel, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A'));

        // Simulate multiple updates
        for (int i = 0; i < 5; i++) {
            gamePanel.update();
        }

        keyboardInputs.keyReleased(new KeyEvent(gamePanel, KeyEvent.KEY_RELEASED, System.currentTimeMillis(), 0, KeyEvent.VK_A, 'A'));

        assertEquals(initialX - 15, gamePanel.getXDelta1(), "Player 1 should move left by 15 units after multiple updates.");
    }

}
