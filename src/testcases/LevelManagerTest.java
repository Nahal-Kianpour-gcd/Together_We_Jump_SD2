// Nahal Kianpour Lirharani
package testcases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import levels.LevelManager;
import griffith.Game;
import levels.Level;

class LevelManagerTest {

	@Test
	void testLevelManager() {
		// Test that the LevelManager can be instantiated with a valid Game object
		Game dummyGame = new Game(); // I used a dummy Game instance here; if the constructor launches the UI, I
										// might need to mock it later
		LevelManager manager = new LevelManager(dummyGame);
		assertNotNull(manager, "LevelManager should be created and not null.");
	}

	@Test
	void testUpdate() {
		// Ensure that the update method doesn't throw exceptions (currently empty
		// method)
		Game dummyGame = new Game();
		LevelManager manager = new LevelManager(dummyGame);
		assertDoesNotThrow(() -> manager.update(), "Update method should not throw exceptions.");
	}

	@Test
	void testGetCurrentLevel() {
		// Test that getCurrentLevel returns a valid level object
		Game dummyGame = new Game();
		LevelManager manager = new LevelManager(dummyGame);
		Level currentLevel = manager.getCurrentLevel();
		assertNotNull(currentLevel, "Current level should not be null.");
	}

}
