//PS
package entities;

import java.awt.Rectangle;

public class Coin {

	private float x, y;
	private int width, height;
    private Rectangle hitbox;
    private boolean active = true;

    public Coin(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initHitbox();
    }

    private void initHitbox() {
        hitbox = new Rectangle((int) x, (int) y, width, height);
    }
    
    public void update() {
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}
