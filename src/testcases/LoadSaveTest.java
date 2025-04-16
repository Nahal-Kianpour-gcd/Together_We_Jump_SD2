package testcases;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;

import org.junit.jupiter.api.Test;

import utilz.LoadSave;


class LoadSaveTest {

	@Test
	void testGetCharacterSprite() {
		// This tests if the method can successfully load a known character sprite
		BufferedImage result = LoadSave.getCharacterSprite("Ninja_Frog", "Idle (32x32).png");
		assertNotNull(result, "Character sprite should not be null for a valid path.");
	}

	@Test
	void testGetLevelSprite() {
		fail("Not yet implemented");
	}

	@Test
	void testGetLevelData() {
		fail("Not yet implemented");
	}

	@Test
	void testGetLevelSpriteString() {
		fail("Not yet implemented");
	}

}
