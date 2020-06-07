package WhistGame;
import WhistGame.Whist.Suit;
import ch.aplu.jcardgame.Card;

/* Strategies when a leading card has been placed
 * Made by - Project Team 24 */
public interface IGameStrategy {
	Card playTrick(Player player, Suit lead);
}
