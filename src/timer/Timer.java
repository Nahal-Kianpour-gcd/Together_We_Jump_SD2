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
}