package WhistGame;
import WhistGame.Whist.Suit;
import ch.aplu.jcardgame.Card;

/* Strategies when trying to place a card
 * Made by - Project Team 24 */
public interface IGameStrategy {
	Card playTrick(Player player, Suit lead);
}
