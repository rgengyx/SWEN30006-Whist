
/* An interface representing what the GameUpdater will update for each of its subscribers
 * 
 * Made by - Project Team 24 */
import ch.aplu.jcardgame.Card;

public interface IGameListener {
	public void updateCard(Card card, boolean winningCard);

	public void updateScore(int player);

	public void updateTrump(Whist.Suit trump);

	public void removeHistory();
}
