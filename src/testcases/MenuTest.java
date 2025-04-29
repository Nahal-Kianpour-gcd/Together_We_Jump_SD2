package testcases;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Component;

import javax.swing.JButton;

import org.junit.jupiter.api.Test;

import griffith.Menu;

class MenuTest {


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

}

