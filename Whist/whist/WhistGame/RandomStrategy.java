package WhistGame;
/* Made by - Project Team 24 */
import java.util.Random;

import WhistGame.Whist.Suit;
import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class RandomStrategy implements IGameStrategy {

	static Random random;

	public RandomStrategy(Random random) {
		this.random = random;
	}

	@Override
	public Card playTrick(Player player, Suit lead) {
		return randomCard(player.getHand());
	}

	// return random Card from Hand
	public static Card randomCard(Hand hand) {
		int x = random.nextInt(hand.getNumberOfCards());
		return hand.get(x);
	}
}
