//Nahal Kianpour Lirharani
package testcases;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; // Import JUnit assertion methods
import org.junit.jupiter.api.Test; // Import the JUnit @Test annotation
import timer.Timer; // Import the class we're testing

class TimerTest {

	// Test if the timer starts with the correct formatted time |NK
	@Test
	public void testInitialTime() {
		Timer timer = new Timer(120f); // 120 seconds = 2 minutes
		assertEquals("02:00", timer.getTimeString()); // Expect formatted time to be 02:00
	}

	// Test if the timer decreases after updating |NK
	@Test
	public void testUpdateDecreasesTime() {
		Timer timer = new Timer(10f); // 10 seconds
		timer.update(1.5f); // Update by 1.5 seconds
		assertEquals("00:08", timer.getTimeString()); // Expect 8 seconds left
	}

}
