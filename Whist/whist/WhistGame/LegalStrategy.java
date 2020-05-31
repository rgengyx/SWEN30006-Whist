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
		ArrayList<Card> leadCards = player.getHand().getCardsWithSuit(lead);
		
		if (leadCards.isEmpty()) {
			return randomCard(player.getHand());
		} else {
			return randomCard(leadCards);
		}
	}
	
	// return random Card from Hand
	public static Card randomCard(Hand hand) {
		int x = random.nextInt(hand.getNumberOfCards());
		return hand.get(x);
	}
	
	// return random Card from ArrayList
	public static Card randomCard(ArrayList<Card> list) {
		int x = random.nextInt(list.size());
		return list.get(x);
	}
}
