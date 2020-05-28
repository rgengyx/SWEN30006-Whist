package WhistGame;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import WhistGame.Whist.Suit;
import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.Hand;

public class LegalStrategy implements IGameStrategy {

	static final Random random = ThreadLocalRandom.current();

	@Override
	public Card playTrick(Player player, Suit lead) {
		ArrayList<Card> leadCards = player.getHand().getCardsWithRank(lead);
		
		if (leadCards.isEmpty()) {
			return randomCard(player.getHand());
		} else {
			return leadCards.get(0);
		}
	}

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
