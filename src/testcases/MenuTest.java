//Thanh Phuong Hoang
package testcases;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Component;

import javax.swing.JButton;

import org.junit.jupiter.api.Test;

import griffith.Menu;

class MenuTest {

	@Test

    void testMenuWindowProperties() {
        Menu menu = new Menu();
        
        // Check that the Menu window is created
        assertNotNull(menu, "Menu window should not be null");
        
        // Check the window title
        assertEquals("Together We Jump - Menu", menu.getTitle(), "Menu title should match");
        
        // Check that the window is not visible yet (since setVisible(true) happens in main)
        assertFalse(menu.isVisible(), "Menu should not be visible immediately after constructor");

    }
	/*
    @Test
    void testStartAndQuitButtonsExist() {
        Menu menu = new Menu();

        // Get all components added to the menu's content pane
        Component[] components = menu.getContentPane().getComponents();

        int buttonCount = 0;
        boolean foundStart = false;
        boolean foundQuit = false;

        for (Component comp : components) {
            if (comp instanceof JButton button) {
                buttonCount++;
                if ("Start Game".equals(button.getText())) {
                    foundStart = true;
                }
                if ("Quit".equals(button.getText())) {
                    foundQuit = true;
                }
            }
        }

        assertEquals(2, buttonCount, "There should be exactly 2 buttons (Start and Quit)");
        assertTrue(foundStart, "Start Game button should exist");
        assertTrue(foundQuit, "Quit button should exist");
    } 
    // Commented out because it was a duplicate method definition, which would cause a compilation error |NK
*/


	@Test
	void testStartAndQuitButtonsExist() {
	    Menu menu = new Menu();

	    // Get all UI components added to the Menu window
	    Component[] components = menu.getContentPane().getComponents();

	    int buttonCount = 0;
	    boolean foundStart = false;
	    boolean foundQuit = false;

	    // Loop through components and check if they are buttons with expected labels
	    for (Component comp : components) {
	        if (comp instanceof JButton button) {
	            buttonCount++;
	            if ("Start".equals(button.getText())) {
	                foundStart = true;
	            }
	            if ("Quit".equals(button.getText())) {
	                foundQuit = true;
	            }
	        }
	    }

	    // Ensure there are exactly two buttons
	    assertEquals(2, buttonCount, "There should be exactly 2 buttons (Start and Quit)");

	    // Ensure both Start and Quit buttons are present
	    assertTrue(foundStart, "Start Game button should exist");
	    assertTrue(foundQuit, "Quit button should exist");
	}
	
	@Test
	void testStartButtonClickDoesNotThrow() {
	    Menu menu = new Menu();

	    // Simulate clicking the Start Game button
	    assertDoesNotThrow(() -> {
	        menu.getStartButton().doClick();
	    }, "Clicking Start Game should not throw an exception");
	}

	@Test
	void testQuitButtonClickDoesNotExit() {
	    Menu menu = new Menu();

	    // Temporarily override System.exit to prevent it from terminating the test
	    SecurityManager originalSecurityManager = System.getSecurityManager();
	    System.setSecurityManager(new SecurityManager() {
	        @Override
	        public void checkExit(int status) {
	            throw new SecurityException("System.exit() was called");
	        }

	        @Override
	        public void checkPermission(java.security.Permission perm) {
	            // Allow all other permissions
	        }
	    });

	    try {
	        menu.getQuitButton().doClick();
	        fail("Expected System.exit() to be called");
	    } catch (SecurityException e) {
	        // Expected behavior
	    } finally {
	        System.setSecurityManager(originalSecurityManager); // Restore normal behavior
	    }
	}



}
