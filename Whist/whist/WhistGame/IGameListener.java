package WhistGame;
/* Made by - Project Team 24 */
import WhistGame.Whist.Suit;
import ch.aplu.jcardgame.Card;

public interface IGameListener {
	public void updateCard(Card card, boolean winningCard);
	public void updateScore(int player);
	public void updateTrump(Suit trump);
	public void removeHistory();
}
