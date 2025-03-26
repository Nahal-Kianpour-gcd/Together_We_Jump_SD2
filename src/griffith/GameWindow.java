package griffith;

import javax.swing.JFrame; // Import JFrame class for creating windows

public class GameWindow {
	private JFrame jframe; // Declare a JFrame object

	public GameWindow(GamePanel gamePanel) { // Constructor now takes a GamePanel object as parameter
		jframe = new JFrame(); // Create a new JFrame object
		jframe.setSize(400, 400); // Set the size of the JFrame
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation to exit the application
		jframe.add(gamePanel); // Add the GamePanel to the JFrame
		jframe.setVisible(true); // Make the JFrame visible
	}
}
