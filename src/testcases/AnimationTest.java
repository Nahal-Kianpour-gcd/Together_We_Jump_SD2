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
}
