package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

/**
 * Abstract base class representing a generic entity with x and y coordinates.
 *-TPH
 */
public abstract class Entity {
    
    // The x and y coordinates of the entity
    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;

    /**
     * Constructor to initialize the position of the entity.
     * 
     * @param x The x-coordinate of the entity
     * @param y The y-coordinate of the entity
     * @param width The width of the entity
     * @param height The height of the entity
     */
    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        initHitBox();
    }
    
    protected void drawHitbox(Graphics g) {
    	// For debugging hitBox
    	g.setColor(Color.BLUE);
    	g.drawRect((int)hitbox.x, (int)hitbox.y, (int)hitbox.width, (int)hitbox.height);
    }
    
 // Initializes the hitbox for the object using its x, y, width, and height properties.
	protected abstract void initHitBox(float x, float y, float width, float height); {
		hitbox = new Rectangle2D.Float( x, y, width, height);
	}
//	protected void updateHitbox() {
//		hitbox.x = (int) x;
//		hitbox.y = (int) y;
//	}
	
	public Rectangle2D.Float getHitbox() {
		return hitbox;
	}

	protected void initHitBox() {
		// TODO Auto-generated method stub
		
	}
}
