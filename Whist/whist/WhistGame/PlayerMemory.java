package WhistGame;

import WhistGame.Whist.Suit;
import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Deck;
import ch.aplu.jcardgame.Hand;

/* Represents the info a player can store during the game - this needed to formulate strategies */
public class PlayerMemory {
	private int[] scores;
	private Suit trump;
	private Hand cardHistory;
	
	// is the winning card of the current turn (or trick)
	private Card currentWinningCard;
	
	public PlayerMemory(int nbPlayers, Deck deck) {
		scores = new int[nbPlayers];
		initScores(nbPlayers);
		cardHistory = new Hand(deck);
		trump = null;
		currentWinningCard = null;
	}
	
	private void initScores(int nbPlayers) {
		for (int i = 0; i < nbPlayers; i++) {
			scores[i] = 0;
		}
	}
	
	public Suit getTrump() {
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
	
	public void updateTrump(Suit trump) {
		this.trump = trump;
	}
	
	public void removeHistory() {
		// very very very uncertain what this parameter really does
		cardHistory.removeAll(false);
	}
}