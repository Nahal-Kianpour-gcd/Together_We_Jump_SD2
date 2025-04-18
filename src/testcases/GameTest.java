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
}