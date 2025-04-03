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

}
