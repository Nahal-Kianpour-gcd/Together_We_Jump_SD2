package griffith;

import javax.swing.JFrame; // Import JFrame class for creating windows

public class GameWindow {
	private JFrame jframe; // Declare a JFrame object

	public GameWindow(GamePanel gamePanel) { // Constructor now takes a GamePanel object as parameter
		jframe = new JFrame(); // Create a new JFrame object
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation to exit the application
		jframe.add(gamePanel); // Add the GamePanel to the JFrame
		jframe.setLocationRelativeTo(null); // Centers the JFrame in the middle of the screen
		jframe.setResizable(false); // Prevents the window from being resized by the user
		jframe.pack(); // fit the size of the window to its size of its components
		jframe.setVisible(true); // Make the JFrame visible
	}
}
