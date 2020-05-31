package WhistGame;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomStartStrategy implements IStartStrategy {
	
	static final Random random = ThreadLocalRandom.current();

	@Override
	public Card playLeadingCard(Player player) {
		return randomCard(player.getHand());
	}

	// return random Card from Hand
	public static Card randomCard(Hand hand) {
		int x = random.nextInt(hand.getNumberOfCards());
		return hand.get(x);
	}
}
