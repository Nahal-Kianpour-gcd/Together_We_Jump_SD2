package entities;

/**
 * Abstract base class representing a generic entity with x and y coordinates.
 */
public abstract class Entity {
    
    // The x and y coordinates of the entity
    protected float x, y;

    /**
     * Constructor to initialize the position of the entity.
     * 
     * @param x The x-coordinate of the entity
     * @param y The y-coordinate of the entity
     */
    public Entity(float x, float y) {
        this.x = x;
        this.y = y;
    }
}

