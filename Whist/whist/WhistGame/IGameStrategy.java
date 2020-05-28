package WhistGame;
import WhistGame.Whist.Suit;
import ch.aplu.jcardgame.Card;

public interface IGameStrategy {
	Card playTrick(Player player, Suit lead);
	Card playLeadingCard(Player player);
}
