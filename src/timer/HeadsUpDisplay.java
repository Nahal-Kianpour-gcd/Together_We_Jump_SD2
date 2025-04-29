//Nahal Kianpour Lirharani
package timer;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Color;

public class HeadsUpDisplay {
	private Timer timer;

	public HeadsUpDisplay(Timer timer) {
		this.timer = timer;
	}

	/**
	 * Render the timer text at top‐left of the screen. Call from your main
	 * render/draw method.
	 */
	public void render(Graphics2D g) {
		g.setFont(new Font("Arial", Font.BOLD, 24)); // choose font & size
		g.setColor(Color.WHITE); // choose text color
		g.drawString(timer.getTimeString(), 10, 30); // draw at (10,30)
	}
}