package WhistGame;
/**
 * Exception that is thrown when the winning score value is not above 1 inclusive.
 */
@SuppressWarnings("serial")
public class WinningScoreException extends Exception {
	public WinningScoreException() {
		super("winningScore value must be over 1 inclusive.");
	}
}
