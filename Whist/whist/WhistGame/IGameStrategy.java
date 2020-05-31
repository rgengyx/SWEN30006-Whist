package WhistGame;
import WhistGame.Whist.Suit;
import ch.aplu.jcardgame.Card;

/* Strategies when a leading card has been placed */
public interface IGameStrategy {
	Card playTrick(Player player, Suit lead);
}
