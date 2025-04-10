package griffith;


public class Game implements Runnable {
	
	private GameWindow gameWindow; // Declare a GameWindow object to display the game
	private GamePanel gamePanel;   // Declare a GamePanel object where game graphics will be rendered
	private Thread gameThread;     // Thread that will run the game loop
	
	private final int FPS_SET = 120; // Desired frames per second (FPS)
	private final int UPS_SET =200; // Target updates per second (UPS) for the game loop |Nk

	// Constructor for the Game class
	public Game() {
		gamePanel = new GamePanel();                // Initialize the GamePanel object
		gameWindow = new GameWindow(gamePanel);     // Create the game window and add the panel to it
		gamePanel.requestFocus();                   // Request focus so keyboard input is directed to gamePanel
		startGameLoop();                            // Start the game loop in a new thread
	}
	
	// Starts the game loop in a new thread
	private void startGameLoop() {
		gameThread = new Thread(this); // Create a new thread that runs this class (implements Runnable)
		gameThread.start();            // Start the thread, which calls the run() method
	}

	// The core game loop runs here
	@Override
	public void run() {
		// Calculate how much time each frame should take (in nanoseconds)
		double timePerFrame = 1000000000.0 / FPS_SET;
		// Time in nanoseconds allocated for each update (1 second divided by target UPS)
		double timePerUpdate = 1000000000.0 / UPS_SET;
		long lastFrame = System.nanoTime(); // Time the last frame was rendered
		long now = System.nanoTime();       // Current time
		long previousTime = System.nanoTime(); // Stores the timestamp of the previous loop iteration in nanoseconds |NK

		int frames = 0;                     // Frame counter
		long lastCheck = System.currentTimeMillis(); // Time for tracking FPS output

		while (true) {
			now = System.nanoTime(); // Get current time
			
			// Check if it's time to render the next frame
			if (now - lastFrame >= timePerFrame) {
				gamePanel.update();
				gamePanel.repaint(); // Redraw the game screen
				lastFrame = now;     // Update lastFrame time
				frames++;            // Increment the frame counter
			}

			// Every second, print the current FPS to the console
			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis(); // Reset timer
				System.out.println("FPS: " + frames);   // Output FPS
				frames = 0;                             // Reset frame count for the next second
			}
		}
	}
}
