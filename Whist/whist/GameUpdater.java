
import java.util.ArrayList;

import ch.aplu.jcardgame.Card;

/* This is the "subject" of the observer pattern, it notifies other players when there are specific changes
 * during and before the game
 * Made by - Project Team 24 */
public class GameUpdater {
	private ArrayList<IGameListener> gameListeners;

	public GameUpdater() {
		gameListeners = new ArrayList<IGameListener>();
	}

	public void addGameListeners(IGameListener player) {
		gameListeners.add(player);
	}

	// notifies players that a card has been played and whether or not this card is
	// the best card in the trick
	public void updateCard(Card card, boolean winningCard) {
		for (IGameListener player : gameListeners) {
			player.updateCard(card, winningCard);
		}
	}

	public void updateScore(int nPlayer) {
		for (IGameListener player : gameListeners) {
			player.updateScore(nPlayer);
		}
	}

	// trump suits do not remain consistent, there is a chance that no winners have
	// been set even if hands are depleted
	public void updateTrump(Whist.Suit trump) {
		for (IGameListener player : gameListeners) {
			player.updateTrump(trump);
		}
	}

	/*
	 * all history is removed because a deck of cards can be redistributed again if
	 * no winners are settled, that is, we don't want players to assume that
	 * previous cards are still present
	 */
	public void removeHistory() {
		for (IGameListener player : gameListeners) {
			player.removeHistory();
		}
	}
}
