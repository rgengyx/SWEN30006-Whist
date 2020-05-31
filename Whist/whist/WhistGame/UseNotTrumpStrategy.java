package WhistGame;

import java.util.ArrayList;

import WhistGame.Whist.Suit;
import ch.aplu.jcardgame.Card;

/* This strat will try to return a low ranking card that isn't a trump suit. However, in the case that there are
 * no cards except the trump suit, return a low rank trump suit card */
public class UseNotTrumpStrategy implements IStartStrategy {

	@Override
	public Card playLeadingCard(Player player) {
		ArrayList<Card> currentHand = player.getHand().getCardList();
		Suit currentTrump = player.getTrump();
		Card trumpCard = null, leastRankCard = null;
			
		// a very bad implementation of finding the least rank cards 
		for (Card card : currentHand) {
			if (trumpCard == null && currentTrump == card.getSuit()) {
				trumpCard = card;
			} else if (currentTrump == card.getSuit() && trumpCard.getRankId() > card.getRankId()) {
				trumpCard = card;
			}
			
			if (leastRankCard == null && card.getSuit() != currentTrump) {
				leastRankCard = card;
			} else if (currentTrump != card.getSuit() && leastRankCard.getRankId() > card.getRankId()) {
				leastRankCard = card;
			}
		}
		
		if (leastRankCard != null) {
			return leastRankCard;
		} else {
			return trumpCard;
		}
	}

}
