package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import utilz.Constants;
import utilz.LoadSave;
import griffith.Game;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import static utilz.HelpMethods.*;


public class Player extends Entity {
    // Array of frames for idle animation
    private BufferedImage[] idleAnimation;

    // Array of frames for running animation
    private BufferedImage[] runAnimation;

    // Controls animation frame timing
    private int aniTick, aniIndex, aniSpeed = 15;

    // Current player action: 0 = idle, 1 = run
    private int playerAction = Constants.PlayerConstants.IDLE;

    // Indicates whether the player is currently moving
    private boolean moving = false;

    // Direction of player movement: 0 = left, 1 = up, 2 = right, 3 = down
    private int playerDir = -1;
    private float playerSpeed = 1.0f;
    private boolean left, right, up, down, jump;

    // File path to the character's sprite sheet (used for loading animations)- TPH
    private String characterPath;
    // Loads level data into the class by assigning the provided 2D array to lvlData.

    //Jumping and gravity
    private int[][] lvlData;
    private float xDrawOffSet = 6 * Game.SCALE;  // Offset to center sprite in hitbox
    private float yDrawOffSet = 4 * Game.SCALE;  // Offset to center sprite in hitbox
    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir =false;

    // Player constructor that sets position and character sprite path
    public Player(float x, float y, int width, int height, String characterPath) {
        super(x, y, width, height); 
        this.characterPath = characterPath;
        loadAnimations();
        initHitBox(x, y, 20 * Game.SCALE, 28 * Game.SCALE); // Reduced from 32x35 to 20x28 for tighter collision
    }

    // Loads the idle and run animations for this player
    private void loadAnimations() {
        // Create empty arrays to hold animation frames
        idleAnimation = new BufferedImage[Constants.IDLE_FRAMES];
        runAnimation = new BufferedImage[Constants.RUN_FRAMES];

        /* Old code - will be removed
        loadAnimation(characterPath + "/Idle (32x32).png", idleAnimation);
        loadAnimation(characterPath + "/Run (32x32).png", runAnimation);
        */

        // Get the character name from the path (e.g., "Ninja_Frog" or "Virtual_Guy")
        String characterName = characterPath.substring(characterPath.lastIndexOf("Main_Characters/") + 15);
        
        // Load idle animation
        BufferedImage img = LoadSave.getCharacterSprite(characterName, "Idle (32x32).png");
        for (int i = 0; i < idleAnimation.length; i++) {
            idleAnimation[i] = img.getSubimage(i * 32, 0, 32, 32);
        }
        
        // Load run animation
        img = LoadSave.getCharacterSprite(characterName, "Run (32x32).png");
        for (int i = 0; i < runAnimation.length; i++) {
            runAnimation[i] = img.getSubimage(i * 32, 0, 32, 32);
        }
    }

    public void loadLvlData(int[][] lvlData) {
        if (lvlData == null) {
            // Create an empty level data array with all walkable tiles (11)
            this.lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
            for (int i = 0; i < Game.TILES_IN_HEIGHT; i++) {
                for (int j = 0; j < Game.TILES_IN_WIDTH; j++) {
                    this.lvlData[i][j] = 11; // 11 represents a walkable tile
                }
            }
        } else {
            this.lvlData = lvlData;
        }
    }

    //TPH
    public void update() {
    // Main update method called every frame or tick
    updateAnimationTick(); // Updates animation frame based on timing
    setAnimation();        // Sets the current animation state (idle or running)
    updatePos();           // Updates the player's position (not shown here)
//  updateHitbox();
}

    private void updateAnimationTick() {
    aniTick++; // Increment the animation tick counter
    if (aniTick >= aniSpeed) { // If enough ticks have passed to update animation frame
        aniTick = 0; // Reset tick counter
        aniIndex++;  // Move to next animation frame

        // If the animation frame index exceeds the number of frames available
        if (aniIndex >= Constants.getSpriteAmount(playerAction)) {
            aniIndex = 0; // Loop back to the first frame
        }
    }
}
    //TPH
    private void setAnimation() {
        // Determines the player's current action (animation state) based on whether the player is moving or not

        if (moving) {
            // If the player is currently moving, switch to the RUNNING animation
            playerAction = Constants.PlayerConstants.RUNNING;
        } else {
            // If the player is not moving, switch to the IDLE animation
            playerAction = Constants.PlayerConstants.IDLE;
        }
    }



    // Updates the player's position based on movement direction
    private void updatePos() {
        moving = false;
        
        if(jump)
            jump();

        if(!left && !right && !inAir)
            return;

        float xSpeed = 0;
        
        if (left) 
            xSpeed -= playerSpeed;
        else if (right) 
            xSpeed += playerSpeed;
        
        if(inAir) {
            if(lvlData == null) return;
            
            // Check if we would hit the ground/platform below us
            int currentTile = (int)(hitbox.y / Game.TILES_SIZE);
            int nextYTile = (int)((hitbox.y + airSpeed) / Game.TILES_SIZE);
            
            // Check the tiles at foot level
            int xTile1 = (int)hitbox.x / Game.TILES_SIZE;
            int xTile2 = (int)(hitbox.x + hitbox.width) / Game.TILES_SIZE;
            
            // Make sure we don't check out of bounds
            if(nextYTile < lvlData.length) {
                // Check if next position would be inside a ground tile
                if(lvlData[nextYTile][xTile1] != 11 || lvlData[nextYTile][xTile2] != 11) {
                    // We hit ground/platform
                    hitbox.y = currentTile * Game.TILES_SIZE;
                    resetInAir();
                } else {
                    // Still in air, apply gravity
                    hitbox.y += airSpeed;
                    airSpeed += gravity;
                    updateXPos(xSpeed);
                }
            }
        } else {
            // Check if there's no ground below us
            int nextYTile = (int)((hitbox.y + hitbox.height + 1) / Game.TILES_SIZE);
            int xTile1 = (int)hitbox.x / Game.TILES_SIZE;
            int xTile2 = (int)(hitbox.x + hitbox.width) / Game.TILES_SIZE;
            
            if(nextYTile < lvlData.length) {
                if(lvlData[nextYTile][xTile1] == 11 && lvlData[nextYTile][xTile2] == 11) {
                    inAir = true;
                    airSpeed = 0;
                }
            }
            updateXPos(xSpeed);
        }
        
        moving = true;
    }
        
            
            
        private void jump() {
            if(inAir)
                return;
            inAir = true;
            airSpeed = jumpSpeed;
            
        
    }

        private void resetInAir() {
        inAir = false;
        airSpeed = 0;
        
    }

        private void updateXPos(float xSpeed) {
            if (lvlData == null) return;
            
            // Check if moving right and would hit a wall
            if (xSpeed > 0) {
                int xIndex = (int)((hitbox.x + xSpeed + hitbox.width) / Game.TILES_SIZE);
                int y1 = (int)(hitbox.y / Game.TILES_SIZE);
                int y2 = (int)((hitbox.y + hitbox.height) / Game.TILES_SIZE);
                
                // Check tiles to the right
                if (xIndex < lvlData[0].length && (lvlData[y1][xIndex] != 11 || lvlData[y2][xIndex] != 11)) {
                    hitbox.x = xIndex * Game.TILES_SIZE - hitbox.width;
                    return;
                }
            } else if (xSpeed < 0) { // Moving left
                int xIndex = (int)((hitbox.x + xSpeed) / Game.TILES_SIZE);
                int y1 = (int)(hitbox.y / Game.TILES_SIZE);
                int y2 = (int)((hitbox.y + hitbox.height) / Game.TILES_SIZE);
                
                // Check tiles to the left
                if (xIndex >= 0 && (lvlData[y1][xIndex] != 11 || lvlData[y2][xIndex] != 11)) {
                    hitbox.x = (xIndex + 1) * Game.TILES_SIZE;
                    return;
                }
            }
            
            // If no collision, move normally
            hitbox.x += xSpeed;
        }

        /*if (moving) { // Only update position if the player is currently moving
            switch (playerDir) {
                case Constants.Directions.LEFT:
                    x -= 3; // Move left by 3 units
                    break;
                case Constants.Directions.UP:
                    y -= 3; // Move up by 3 units
                    break;
                case Constants.Directions.RIGHT:
                    x += 3; // Move right by 3 units
                    break;
                case Constants.Directions.DOWN:
                    y += 3; // Move down by 3 units
                    break;
            }
        }*/
    

    // Renders the current frame of the player's animation on screen |NK
    public void render(Graphics g) {
       // Determine which animation to use based on player action (idle or run)
        BufferedImage[] currentAnimation = (playerAction == Constants.PlayerConstants.IDLE) ? idleAnimation : runAnimation;

        // Ensure aniIndex is within bounds
        if (aniIndex >= currentAnimation.length) {
            aniIndex = 0;
        }
        
        // Draw the entity at the hitbox position with offsets to center the sprite
        g.drawImage(currentAnimation[aniIndex], 
            (int)(hitbox.x - xDrawOffSet), 
            (int)(hitbox.y - yDrawOffSet), 
            (int)(32 * Game.SCALE), 
            (int)(32 * Game.SCALE), 
            null); 
        drawHitbox(g);
        
    }


    // Sets the player's movement direction and marks the player as moving
    public void setDirection(int direction, boolean isPressed) {
        switch(direction) {
            case 0: // LEFT
                left = isPressed;
                break;
            case 1: // UP
                up = isPressed;
                break;
            case 2: // RIGHT
                right = isPressed;
                break;
            case 3: // DOWN
                down = isPressed;
                break;
        }
    }

    // Sets the moving status of the player
    public void setMoving(boolean moving) {
        this.moving = moving;
        if (!moving) {
            aniIndex = 0; // Reset animation frame to the beginning when movement stops
            up = down = left = right = false;
        }
    }

    public void setJump(boolean jumping) {
        this.jump = jumping;
    }

    // Returns the player's current X position
    public float getX() {
        return x;
    }

    // Returns the player's current Y position -TPH
    public float getY() {
        return y;
    }

    @Override
    protected void initHitBox() {
        hitbox = new Rectangle2D.Float((int) x, (int) y, width, height);
    }

    protected void updateHitbox() {
        hitbox.x = (int) x;
        hitbox.y = (int) y;
    }

    @Override
    protected void initHitBox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }
}
