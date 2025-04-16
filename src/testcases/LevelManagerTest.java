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
		Game dummyGame = new Game(); // If your Game constructor launches UI, you may need to mock it
		LevelManager manager = new LevelManager(dummyGame);
		assertNotNull(manager, "LevelManager should be created and not null.");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCurrentLevel() {
		fail("Not yet implemented");
	}

}
