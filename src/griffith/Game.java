package griffith;

import levels.LevelManager;
import java.awt.Graphics;
import entities.Player;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import entities.CoinManager;


//Timer imports
import timer.Timer;
import timer.HeadsUpDisplay;


public class Game implements Runnable {

	private GameWindow gameWindow; // Declare a GameWindow object to display the game
	private GamePanel gamePanel; // Declare a GamePanel object where game graphics will be rendered
	private Thread gameThread; // Thread that will run the game loop
	private Player player1; // First player (Ninja Frog)
	private Player player2; // Second player (Virtual Guy)
	private LevelManager levelManager; // Manages level loading and rendering |NK
	private CoinManager coinManager; //Manages coin status ||PS
	private final int FPS_SET = 120; // Desired frames per second (FPS)
	private final int UPS_SET = 200; // Target updates per second (UPS) for the game loop |Nk
	private Timer timer; // Timer object to manage countdown |NK
	private boolean showGameOver = false; // Flag to control display of "Game Over" screen |NK
	private HeadsUpDisplay hud; // Heads-up display object to render timer on screen |NK
	private long lastNanoTime; // Tracks the timestamp of the last frame in nanoseconds |NK

	public final static int TILES_DEFULAT_SIZE = 32; // Default pixel size of a single tile (before scaling) |NK
	public final static float SCALE = 1f; // Scale factor to enlarge or shrink tiles (1.0 = original size)
	public final static int TILES_IN_WIDTH = 26; // Number of tiles horizontally across the game screen
	public final static int TILES_IN_HEIGHT = 14; // Number of tiles vertically down the game screen
	public final static int TILES_SIZE = (int) (TILES_DEFULAT_SIZE * SCALE); // Final scaled tile size in pixels
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH; // Total game screen width in pixels
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT; // Total game screen height in pixels |NK
	private boolean timeUpHandled = false; // Flag to ensure onTimeUp() is called only once |NK
	private boolean showWinMessage = false; // Show "You Win!" when all coins collected|NK



	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	// Constructor for the Game class
	public Game() {
		initClasses(); // Initialize player and level manager
		timer = new Timer(60f); // Initialize Timer with a 60-second countdown |NK
		hud = new HeadsUpDisplay(timer); // Create HeadsUpDisplay to render the timer visually |NK
		lastNanoTime = System.nanoTime(); // Capture the current time to track elapsed frame time |NK
		gamePanel = new GamePanel(this); // Initialize the GamePanel object
		gameWindow = new GameWindow(gamePanel); // Create  the game window and add the panel to it
		gamePanel.requestFocus(); // Request focus so keyboard input is directed to gamePanel
		startGameLoop(); // Start the game loop in a new thread
	}

	// Initialize game objects
	private void initClasses() {
		levelManager = new LevelManager(this);
		player1 = new Player(200, 200, 64, 64, "/image-resources/Main_Characters/Ninja_Frog");
		player2 = new Player(300, 200, 64, 64, "/image-resources/Main_Characters/Virtual_Guy");

		coinManager = new CoinManager(player1, player2);
		
		// Initialize level data for both players
		int[][] levelData = levelManager.getCurrentLevel().getLevelData();
		if (levelData == null) {
			// Create a basic level with solid ground if level data is null
			levelData = new int[TILES_IN_HEIGHT][TILES_IN_WIDTH];
			for (int i = 0; i < TILES_IN_HEIGHT; i++) {
				for (int j = 0; j < TILES_IN_WIDTH; j++) {
					// Make bottom row and sides solid
					if (i == TILES_IN_HEIGHT - 1 || j == 0 || j == TILES_IN_WIDTH - 1)
						levelData[i][j] = 0; // Solid tile
					else
						levelData[i][j] = 11; // Empty tile
				}
			}
		}
		player1.loadLvlData(levelData);
		player2.loadLvlData(levelData);
		
		coinManager.addTestCoins();
	}

	// Starts the game loop in a new thread
	private void startGameLoop() {
		gameThread = new Thread(this); // Create a new thread that runs this class (implements Runnable)
		gameThread.start(); // Start the thread, which calls the run() method
	}
	/*
	 * // Calls the game panel's update logic during each game loop cycle |NK public
	 * void update() { player1.update(); player2.update(); levelManager.update(); }
	 */

	// Updated game loop update method with timer functionality |NK
	public void update() {
	    if (showWinMessage) return; // Stop updating if win message is triggered |NK

	    long now = System.nanoTime();
	    float deltaSeconds = (now - lastNanoTime) / 1_000_000_000.0f;
	    lastNanoTime = now;

	    player1.update();
	    player2.update();
	    levelManager.update();

	    coinManager.update(); // Move coinManager update earlier to detect collection first |NK

	    // Trigger win if all coins are collected and timer not finished yet
	    if (!timer.isFinished() && coinManager.allCoinsCollected() && !showWinMessage) {
	        showWinMessage = true;
	        timer.setFinished(); // Stop timer to freeze game state |NK
	        System.out.println("WIN TRIGGERED: All coins collected!"); // Debug log |NK
	    }

	    timer.update(deltaSeconds); // Keep updating countdown unless setFinished() was called

	    if (timer.isFinished() && !timeUpHandled) {
	        onTimeUp();
	        timeUpHandled = true;
	    }
	}

	/*Old update() (replaced)
	public void update() {
	    long now = System.nanoTime();
	    float deltaSeconds = (now - lastNanoTime) / 1_000_000_000.0f;
	    lastNanoTime = now;

	    player1.update();
	    player2.update();
	    levelManager.update();
	    coinManager.update();
	    timer.update(deltaSeconds);
	    
	    if (timer.isFinished() && !timeUpHandled) {
	        onTimeUp();
	        timeUpHandled = true;
	    }
	}
	*/


	/*
	 // Render game graphics |NK
	 public void render(Graphics g) {
	     levelManager.draw(g);
	 }
	*/

	/*
	// Updated render method to include HUD rendering |NK
	public void render(Graphics g) {
	    // Draw the level elements |NK
	    levelManager.draw(g);
	    
	    // Cast Graphics to Graphics2D for better control (HUD needs Graphics2D) |NK
	    Graphics2D g2d = (Graphics2D) g;
	    
	    // Render heads-up display (timer) on screen |NK
	    hud.render(g2d);
	}
	*/

	public void render(Graphics g) {
	    levelManager.draw(g);
	    coinManager.render(g); // Draw coins |NK
	    Graphics2D g2d = (Graphics2D) g;
	    hud.render(g2d); // Draw timer HUD |NK

	    // Win condition
	    if (showWinMessage) {
	        g2d.setFont(new Font("Arial", Font.BOLD, 50));
	        g2d.setColor(Color.GREEN);
	        String winMessage = "You Win!";
	        int stringWidth = g2d.getFontMetrics().stringWidth(winMessage);
	        int stringHeight = g2d.getFontMetrics().getHeight();
	        int x = (Game.GAME_WIDTH - stringWidth) / 2;
	        int y = (Game.GAME_HEIGHT - stringHeight) / 2;
	        g2d.drawString(winMessage, x, y); // Draw win message |NK
	    } else if (showGameOver) {
	        g2d.setFont(new Font("Arial", Font.BOLD, 50));
	        g2d.setColor(Color.RED);
	        String message = "Time's Up!";
	        int stringWidth = g2d.getFontMetrics().stringWidth(message);
	        int stringHeight = g2d.getFontMetrics().getHeight();
	        int x = (Game.GAME_WIDTH - stringWidth) / 2;
	        int y = (Game.GAME_HEIGHT - stringHeight) / 2;
	        g2d.drawString(message, x, y); // Draw time-up message |NK
	    }
	}

	/* Old render() (replaced)
	public void render(Graphics g) {
	    levelManager.draw(g);
	    coinManager.render(g);
	    Graphics2D g2d = (Graphics2D) g;
	    hud.render(g2d);
	    if (showGameOver) {
	        g2d.setFont(new Font("Arial", Font.BOLD, 50));
	        g2d.setColor(Color.RED);
	        String message = "Time's Up!";
	        int stringWidth = g2d.getFontMetrics().stringWidth(message);
	        int stringHeight = g2d.getFontMetrics().getHeight();
	        int x = (Game.GAME_WIDTH - stringWidth) / 2;
	        int y = (Game.GAME_HEIGHT - stringHeight) / 2;
	        g2d.drawString(message, x, y);
	    }
	}
	*/



	// The core game loop runs here
	@Override
	public void run() {
		// Calculate how much time each frame should take (in nanoseconds)
		double timePerFrame = 1000000000.0 / FPS_SET;
		// Time in nanoseconds allocated for each update (1 second divided by target
		// UPS)
		double timePerUpdate = 1000000000.0 / UPS_SET;
		/* long lastFrame = System.nanoTime(); // Time the last frame was rendered */
		/* long now = System.nanoTime(); // Current time */
		long previousTime = System.nanoTime(); // Stores the timestamp of the previous loop iteration in nanoseconds |NK

		int frames = 0; // Frame counter
		int updates = 0; // Update counter
		long lastCheck = System.currentTimeMillis(); // Time for tracking FPS output
		double deltaU = 0; // Accumulates time to determine when to perform the next game update
		double deltaF = 0; // Accumulates time to determine when to render the next frame

		while (true) {
			/* now = System.nanoTime(); // Get current time */
			long currentTime = System.nanoTime(); // Captures the current timestamp at the start of the loop iteration
			deltaU += (currentTime - previousTime) / timePerUpdate; // Adds the elapsed time (since last frame) to
																	// deltaU, scaled by timePerUpdate |NK
			deltaF += (currentTime - previousTime) / timePerFrame; // Adds the elapsed time (since last frame) to
																	// deltaF, scaled by timePerFrame
			previousTime = currentTime; // Update the previousTime to the currentTime for the next loop iteration
			// Check if enough time has accumulated to perform a game update
			if (deltaU >= 1) {
				update(); // Call the update method to update game state
				updates++; // Increment the update counter
				deltaU--; // Reduce deltaU by 1 after each update
			}

			if (deltaF >= 1) {
				/*
				 * gamePanel.update(); // Renamed to avoid conflict with AWT's update(Graphics
				 * g) method
				 */
				gamePanel.updateGame(); // Custom method to update game state before rendering
				gamePanel.repaint(); // Redraw the game screen
				frames++; // Increment the frame counter
				deltaF--; // Reduce deltaF after rendering
			}

			/*
			 * // Check if it's time to render the next frame if (now - lastFrame >=
			 * timePerFrame) { gamePanel.update(); // Update game state for rendering
			 * gamePanel.repaint(); // Redraw the game screen lastFrame = now; // Update
			 * lastFrame time frames++; // Increment the frame counter }
			 */

			// Every second, print the current FPS to the console
			if (System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis(); // Reset timer
				System.out.println("FPS: " + frames + "  | UPS: " + updates); // Print the current Frames Per Second
																				// (FPS) and Updates Per Second (UPS) to
																				// the console
				frames = 0; // Reset frame count for the next second
				updates = 0; // Reset the update counter after outputting
			}
		}
	}

	// Calculates updates per second given update count and elapsed time in ms
	public static int calculateUPS(int updates, long elapsedMillis) {
		if (elapsedMillis == 0)
			return 0; // Avoid division by zero
		return (int) (updates * 1000.0 / elapsedMillis);
	}
	
	/*
	// Handle event when timer reaches zero |NK
	private void onTimeUp() {
		System.out.println("Time's up!"); // Output message when countdown finishes |NK
	}*/
	
	// Handle event when timer reaches zero |NK
	private void onTimeUp() {
	    System.out.println("Time's up!"); // Output message when countdown finishes |NK
	    showGameOver = true; // Set flag to indicate game over state |NK
	}
	
}