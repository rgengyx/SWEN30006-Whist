
import ch.aplu.jcardgame.Card;

/* Strategies when trying to place a card
 * Made by - Project Team 24 */
public interface IGameStrategy {
	Card playTrick(Player player, Whist.Suit lead);
}
