package testcases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entities.CoinManager;
import entities.Player;

class CoinManagerTest {

	private CoinManager coinManager;
	private Player player1;
	private Player player2;
	
	@BeforeEach
	void setUp() {
	    player1 = new Player(0, 0, 0, 0, null);
	    player2 = new Player(0, 0, 0, 0, null);
	    coinManager = new CoinManager(player1, player2);
	}
	
	@Test
	void testAddTestCoins() {
		coinManager.addTestCoins();
		assertFalse(coinManager.allCoinsCollected());
	}
	
	@Test
	void testAllCoinsCollected() {
		assertTrue(coinManager.allCoinsCollected());
		
		coinManager.addTestCoins();
		assertFalse(coinManager.allCoinsCollected());
	}
}
