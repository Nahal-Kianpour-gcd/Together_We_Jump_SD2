//Polina Shtefan
package testcases;

import org.junit.jupiter.api.Test;

import utilz.Constants;

import static org.junit.jupiter.api.Assertions.*;

public class AnimationTest {

	 @Test
	    void testGetSpriteAmount_idle() {
	        int expected = Constants.IDLE_FRAMES;
	        int actual = Constants.getSpriteAmount(Constants.IDLE_FRAMES);
	        assertEquals(expected, actual);
	    }
	 
	 @Test
	    void testGetSpriteAmount_running() {
	        int expected = Constants.RUN_FRAMES;
	        int actual = Constants.getSpriteAmount(Constants.PlayerConstants.RUNNING);
	        assertEquals(expected, actual);
	    }
	 
	 @Test
	    void testGetSpriteAmount_unknown() {
	        int expected = 0;
	        int actual = Constants.getSpriteAmount(999);
	        assertEquals(expected, actual);
	    }
}
