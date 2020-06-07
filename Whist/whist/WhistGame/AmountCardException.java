package WhistGame;
/**
 * An exception that is thrown when nbStartCards is not between 1 and 13
 * Made by - Project Team 24
 */
@SuppressWarnings("serial")
public class AmountCardException extends Exception {
	public AmountCardException() {
		super("nbStartCards value must be between 1 and 13 inclusive.");
	}
}
