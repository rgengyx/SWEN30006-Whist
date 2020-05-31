package WhistGame;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import WhistGame.Whist.Suit;
import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class RandomStrategy implements IGameStrategy {

	static final Random random = ThreadLocalRandom.current();

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
