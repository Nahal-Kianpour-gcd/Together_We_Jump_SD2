package entities;

import java.awt.Rectangle;

/**
 * Abstract base class representing a generic entity with x and y coordinates.
 *-TPH
 */
public abstract class Entity {
    
    // The x and y coordinates of the entity
    protected float x, y;
    protected int width, height;
    protected Rectangle hitbox;

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
 // Initializes the hitbox for the object using its x, y, width, and height properties.
	protected abstract void initHitBox(); {
		hitbox = new Rectangle((int) x, (int) y, width, height);
	}
}
