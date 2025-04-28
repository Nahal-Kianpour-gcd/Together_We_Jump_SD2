package timer;

public class Timer {
	// time remaining, in seconds
	private float timeRemaining;

	/**
	 * seconds initial countdown duration
	 */
	public Timer(float seconds) {
		this.timeRemaining = seconds;
	}

	/**
	 * Call once per frame with the elapsed time since last frame. deltaSeconds time
	 * in seconds since last update()
	 */
	public void update(float deltaSeconds) {
		// subtract elapsed time, but never go below zero
		timeRemaining = Math.max(0, timeRemaining - deltaSeconds);
	}

	// true once the timer has reached zero //
	public boolean isFinished() {
		return timeRemaining <= 0;
	}

	// total whole minutes left //
	public int getMinutes() {
		return (int) (timeRemaining / 60);
	}

	// remaining whole seconds (0–59) //
	public int getSeconds() {
		return (int) (timeRemaining % 60);
	}

	/**
	 * formatted mm:ss, e.g. “01:05” or “00:09”
	 */
	public String getTimeString() {
		int m = getMinutes();
		int s = getSeconds();
		// pad seconds with leading zero if needed
		return String.format("%02d:%02d", m, s);
	}
}