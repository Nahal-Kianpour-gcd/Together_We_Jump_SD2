//Nahal Kianpour Lirharani
package griffith;

import javax.swing.SwingUtilities;

public class MainClass {

    public static void main(String[] args) {
        // Launch the menu screen first |NK
        SwingUtilities.invokeLater(() -> {
            new Menu().setVisible(true);
        });
    }
}
