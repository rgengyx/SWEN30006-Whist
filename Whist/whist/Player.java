import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class Player {
	private Hand hand;
	private boolean isInteractive = false;

	private IGameStrategy gameStrategy;

	public Player(IGameStrategy gameStrategy) {
		this.gameStrategy = gameStrategy;
	}

	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public boolean isInteractive() {
		return isInteractive;
	}

	public void setInteractive(boolean isInteractive) {
		this.isInteractive = isInteractive;
	}

	public Card playTrick() {
		return gameStrategy.playTrick(this);
	}

}
