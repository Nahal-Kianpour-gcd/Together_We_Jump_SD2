package utilz;

//POLINA SHTEFAN
public class Constants {

	//Directions for player movement
    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }
    
    //Player states/actions
    public static class PlayerConstants {
        public static final int IDLE = 0; 
        public static final int RUNNING = 1;
    }
    
    //Animation frame counts
    public static final int IDLE_FRAMES = 11; //Number of frames for idle animation
    public static final int RUN_FRAMES = 12;  //Number of frames for run animation
    
    //Method to get the number of sprite frames based on action type
    public static int getSpriteAmount(int action) {
        switch (action) {
            case PlayerConstants.IDLE:
                return IDLE_FRAMES;
            case PlayerConstants.RUNNING:
                return RUN_FRAMES;
            default:
                return 0; //Return 0 for unknown action
        }
    }
}
