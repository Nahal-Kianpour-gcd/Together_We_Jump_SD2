package griffith; 

import javax.swing.*; // Import Swing library for UI components
import java.awt.*; // Import AWT for layout managers
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Menu class to display the main menu for "Together We Jump" game.
 * Provides options to Start the Game or Quit.
 */
public class Menu extends JFrame implements ActionListener {
    private JButton startButton; // Button to start the game
    private JButton quitButton;  // Button to quit the application

 // Constructor: sets up the menu window - TPH
    public Menu() {
        setTitle("Together We Jump - Menu"); // Window title
        setSize(400, 300); // Window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit application when window is closed
        setLocationRelativeTo(null); // Center the window on the screen
        setLayout(new FlowLayout()); // Simple flow layout for placing buttons

        // Initialize buttons
        startButton = new JButton("Start");
        quitButton = new JButton("Quit");

        // Add action listeners to respond to button clicks
        startButton.addActionListener(this);
        quitButton.addActionListener(this);

        // Add buttons to the window
        add(startButton);
        add(quitButton);
    }

    // this method is triggered when a button is clicked
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            dispose(); // Close the menu window
            MainClass.main(null); // Start the actual game by calling MainClass
        } else if (e.getSource() == quitButton) {
            System.exit(0); // Exit the application
        }
    }

 // Main method to launch the Menu screen
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Menu menu = new Menu(); // Create a new Menu window
            menu.setVisible(true); // Make it visible
        });
    }
}
