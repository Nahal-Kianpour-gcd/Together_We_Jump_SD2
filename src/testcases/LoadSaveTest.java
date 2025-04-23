//Nahal Kianpour Lirharani
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
		// This tests whether the level sprite loads correctly
		BufferedImage result = LoadSave.getLevelSprite();
		assertNotNull(result, "Level sprite should not be null.");
	}

	@Test
	void testGetLevelData() {
	    // This test checks if level data is properly extracted from the level image
	    int[][] levelData = LoadSave.getLevelData(); 
	    assertNotNull(levelData, "Level data should not be null.");
	    assertTrue(levelData.length > 0, "Level data should have rows.");
	    assertTrue(levelData[0].length > 0, "Level data should have columns.");
	}


	@Test
	void testGetLevelSpriteString() {
		// This tests loading a specific named sprite by path (likely used internally)
		BufferedImage result = LoadSave.getLevelSprite("/image-resources/World/world_set/outside_sprites.png");
		assertNotNull(result, "Named level sprite should not be null.");
	}

}
