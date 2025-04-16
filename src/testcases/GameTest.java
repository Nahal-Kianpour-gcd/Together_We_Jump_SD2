// Nahal Kianpour Lirharani
package testcases;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import griffith.Game;

class GameTest {

	@Test
	void testCalculateUPS() {
		// Simulate 200 updates over 1000 milliseconds (1 second)
		int ups = Game.calculateUPS(200, 1000);
		assertEquals(200, ups, "Expected 200 UPS when 200 updates happen in 1 second.");
	}
	
	@Test
	void testCalculateUPSFastTime() {
	    // Given: 100 updates in 500ms should mean 200 UPS
	    int updates = 100;
	    long timeMillis = 500;

	    int result = Game.calculateUPS(updates, timeMillis);

	    assertEquals(200, result, "UPS should scale correctly with faster update timing.");
	}

}