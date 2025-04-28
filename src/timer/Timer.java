package timer;

public class Timer {
	// time remaining, in seconds
	private float timeRemaining;

	/**
	 * @param seconds initial countdown duration
	 */
	public Timer(float seconds) {
		this.timeRemaining = seconds;
	}
	
    /**
     * Call once per frame with the elapsed time since last frame.
     * @param deltaSeconds time in seconds since last update()
     */
    public void update(float deltaSeconds) {
        // subtract elapsed time, but never go below zero
        timeRemaining = Math.max(0, timeRemaining - deltaSeconds);
    }
}