package entities;

/**
 * Abstract base class representing a generic entity with x and y coordinates.
 *-TPH
 */
public abstract class Entity {
    
    // The x and y coordinates of the entity
    protected float x, y;
    protected int width, height;

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
    }
}
