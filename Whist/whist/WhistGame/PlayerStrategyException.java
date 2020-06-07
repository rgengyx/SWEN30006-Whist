package WhistGame;
/**
 * Exception that is thrown when player strategy declared is unknown
 */
@SuppressWarnings("serial")
public class PlayerStrategyException extends Exception {
	public PlayerStrategyException() {
		super("Player strategy declared is unknown (must be either smart, random, legal or interactive).");
	}
}
