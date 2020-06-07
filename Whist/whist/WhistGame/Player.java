package WhistGame;
/* Made by - Project Team 24 */
import WhistGame.Whist.Suit;
import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Deck;
import ch.aplu.jcardgame.Hand;

public class Player implements IGameListener {
	private Hand hand;
	private int id;

	private IGameStrategy gameStrategy;
	private PlayerMemory memory;

	public Player(IGameStrategy gameStrategy, int id, int nbPlayers, Deck deck) {
		this.gameStrategy = gameStrategy;
		this.id = id;
		this.memory = new PlayerMemory(nbPlayers, deck);
	}
	
	// attempts to retrieve player (not limited to current player) with highest score
	public int getHighestPlayerScore() {
		return memory.getHighestPlayerScore(id);
	}

	public Hand getHand() {
		return hand;
	}

	public Suit getTrump() {
		return memory.getTrump();
	}

	public Card getWinningCard() {
		return memory.getWinningCard();
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public IGameStrategy getGameStrategy() {
		return gameStrategy;
	}

	public Card playTrick(Suit lead) {
		return gameStrategy.playTrick(this, lead);
	}

	// Not sure about this type of code implementation

	@Override
	public void updateCard(Card card, boolean winningCard) {
		memory.updateCard(card, winningCard);
	}

	@Override
	public void updateScore(int player) {
		memory.updateScore(player);
	}

	@Override
	public void updateTrump(Suit trump) {
		memory.updateTrump(trump);
	}

	@Override
	public void removeHistory() {
		memory.removeHistory();
	}

}
