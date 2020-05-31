package WhistGame;

import java.util.ArrayList;

import WhistGame.Whist.Suit;
import ch.aplu.jcardgame.Card;

/* The counter strategy involves finding a card that ranks a bit higher than the winning card and is of the same suit
 * as the winning card. If such a card can't be found, return card of lowest rank.
 * VERY IMPORTANT: CURRENT HAND MUST HAVE CARD(S) WITH LEADING SUIT */
public class CounterStrategy implements IGameStrategy {

	@Override
	// beware of UGLY ASS implementation, will need to find a way better one
	public Card playTrick(Player player, Suit lead) {
		ArrayList<Card> currentHand = player.getHand().getCardsWithSuit(lead);
		Card leadingCard = player.getWinningCard();
		Card higherCard = null, lowestCard = null;
		
		for (Card card : currentHand) {
			if (leadingCard.getRankId() < card.getRankId()) {
				if (higherCard == null) {
					higherCard = card;
				} else if (card.getRankId() < higherCard.getRankId()) {
					higherCard = card;
				}
			}
			
			if (leadingCard.getRankId() > card.getRankId()) {
				if (lowestCard == null) {
					lowestCard = card;
				} else if (card.getRankId() < lowestCard.getRankId()) {
					lowestCard = card;
				}
			}
		}
	
		if (higherCard != null) {
			return higherCard;
		} else {
			return lowestCard;
		}
	}

}
