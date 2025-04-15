//Nahal Kianpour Lirharani
package utilz;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.awt.Color;

import griffith.Game;

public class LoadSave {

	// Base paths for resources
	public static final String CHARACTERS_FOLDER = "/image-resources/Main_Characters";
	public static final String LEVEL_SPRITES = "/image-resources/World/world_set/outside_sprites.png";
	public static final String Level_One_Data = "/image-resources/World/world_set/level_one_data.png";

	/**
	 * Loads a sprite sheet for a specific character and animation
	 * 
	 * @param characterName The character folder name (e.g., "Ninja_Frog" or
	 *                      "Virtual_Guy")
	 * @param fileName      The animation file name (e.g., "Idle (32x32).png" or
	 *                      "Run (32x32).png")
	 * @return The loaded BufferedImage, or null if loading fails
	 */
	public static BufferedImage getCharacterSprite(String characterName, String fileName) {
		BufferedImage img = null;
		String fullPath = CHARACTERS_FOLDER + "/" + characterName + "/" + fileName;

		try (InputStream is = LoadSave.class.getResourceAsStream(fullPath)) {
			if (is != null) {
				img = ImageIO.read(is);
			} else {
				throw new RuntimeException("Resource not found: " + fullPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return img;
	}

	/**
	 * Loads the level sprite sheet containing tiles and level elements
	 * 
	 * @return The loaded BufferedImage containing level sprites
	 */
	public static BufferedImage getLevelSprite() {
		BufferedImage img = null;
		System.out.println("Attempting to load level sprite from: " + LEVEL_SPRITES);
		try (InputStream is = LoadSave.class.getResourceAsStream(LEVEL_SPRITES)) {
			if (is != null) {
				img = ImageIO.read(is);
			} else {
				System.out.println("Could not find level sprites at: " + LEVEL_SPRITES);
				System.out.println("Please make sure you have created the following directory structure:");
				System.out.println("src/");
				System.out.println("  └── image-resources/");
				System.out.println("      └── world/");
				System.out.println("          └── world_tileset.png");
				throw new RuntimeException("Level sprites not found: " + LEVEL_SPRITES);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return img;
	}

	/**
	 * Reads level tile data encoded in an image's red channel
	 * 
	 * @return A 2D array representing tile indices
	 */
	public static int[][] GetLevelData() {
		int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
		BufferedImage img = getLevelSprite(Level_One_Data);

		for (int j = 0; j < img.getHeight(); j++) {
			for (int i = 0; i < img.getWidth(); i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				if (value >= 48)
					value = 0;

				lvlData[j][i] = value;
			}
		}

		return lvlData;
	}

	/**
	 * Loads a sprite sheet by path
	 * 
	 * @param fileName File path to the sprite sheet
	 * @return BufferedImage or null
	 */
	public static BufferedImage getLevelSprite(String fileName) {
		BufferedImage img = null;
		try (InputStream is = LoadSave.class.getResourceAsStream(fileName)) {
			if (is != null) {
				img = ImageIO.read(is);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return img;
	}
}