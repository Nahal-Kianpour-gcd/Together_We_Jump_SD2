package griffith;

public class Game {
	private GameWindow gameWindow; // Declare a GameWindow object
	private GamePanel gamePanel; // Declare a GamePanel object

	public Game() {
		gamePanel = new GamePanel(); // Initialize the GamePanel object
		gameWindow = new GameWindow(gamePanel); // Initialize the GameWindow object, passing the gamePanel to it
	}
}
