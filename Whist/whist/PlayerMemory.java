
/* Made by - Project Team 24 */
import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Deck;
import ch.aplu.jcardgame.Hand;

/* Represents the info a player can store during the game - this needed to formulate strategies */
public class PlayerMemory {
	private int[] scores;
	private Whist.Suit trump;
	private Hand cardHistory;

	// is the winning card of the current turn (or trick)
	private Card currentWinningCard;

	public PlayerMemory(int nbPlayers, Deck deck) {
		scores = new int[nbPlayers];
		initScores(nbPlayers);

		// the reason we create a new hand instance is to keep track of all cards
		cardHistory = new Hand(deck);
		trump = null;
		currentWinningCard = null;
	}

	private void initScores(int nbPlayers) {
		for (int i = 0; i < nbPlayers; i++) {
			scores[i] = 0;
		}
	}

	// retrieves highest player score (does not involve current player)
	public int getHighestPlayerScore(int id) {
		int maxScore = 0;
		for (int i = 0; i < Whist.returnNBPlayers(); i++) {
			if (i != id) {
				if (maxScore < scores[i]) {
					maxScore = scores[i];
				}
			}
		}
		return maxScore;
	}

	public Whist.Suit getTrump() {
		return trump;
	}

	public Card getWinningCard() {
		return currentWinningCard;
	}

	public void updateCard(Card card, boolean winningCard) {
		Card newCard = card.clone();
		cardHistory.insert(newCard, false);

		if (winningCard) {
			currentWinningCard = newCard;
		}
	}

	public void updateScore(int player) {
		scores[player]++;

		// resetting winning card for current play
		currentWinningCard = null;
	}

	public void updateTrump(Whist.Suit trump) {
		this.trump = trump;
	}

	// we remove history when all cards have been depleted, but game still goes on
	public void removeHistory() {
		cardHistory.removeAll(false);
	}
}
