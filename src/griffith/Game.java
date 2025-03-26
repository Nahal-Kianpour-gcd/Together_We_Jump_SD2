package griffith;

public class Game {
	private GameWindow gameWindow; // Declare a GameWindow object
	private GamePanel gamePanel; // Declare a GamePanel object

	public Game() {
		gameWindow = new GameWindow(); // Initialize the GameWindow object
		gamePanel = new GamePanel(); // Initialize the GamePanel object
	}
}
