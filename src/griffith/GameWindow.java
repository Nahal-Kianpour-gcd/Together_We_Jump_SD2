package griffith;

import javax.swing.JFrame; // Import the JFrame class for GUI creation

public class GameWindow {
	private JFrame jframe; // Declare a JFrame object

	public GameWindow() {
		jframe = new JFrame(); // Initialize the JFrame object
		jframe.setSize(400, 400); // Set the size of the JFrame
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set the default close operation
		jframe.setVisible(true); // Make the JFrame visible
	}
}
