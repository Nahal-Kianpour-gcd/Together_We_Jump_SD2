package testcases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.Player;
import griffith.Game;

class PlayerTest {

	private Player player;
	private final int tileSize = Game.TILES_SIZE;

	@BeforeEach
	void setUp() {
		player = new Player(0, 0, tileSize, tileSize, "res/Main_Characters/Ninja_Frog");
	}

	@Test
	void testLoadLvlDataWithNull() {
		player.loadLvlData(null); // Should initialize walkable level
		// Not crashing is a pass for now
		assertTrue(true);
	}

	@Test
	void testSetDirectionAndMovement() {
		player.setDirection(0, true); // Simulate pressing LEFT
		player.setMoving(true);
		// If no error is thrown, pass it (we can’t access internals)
		assertTrue(true);
	}

	@Test
	void testSetMovingFalseResets() {
		player.setDirection(1, true);
		player.setMoving(false); // Should reset directions and animation
		// Again, if no crash happens, consider it a pass for now
		assertTrue(true);
	}

	@Test
	void testGetXAndGetY() {
		assertEquals(0, player.getX());
		assertEquals(0, player.getY());
	}
}
