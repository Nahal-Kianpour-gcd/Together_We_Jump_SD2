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
}
