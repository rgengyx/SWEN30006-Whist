package WhistGame;

import WhistGame.Whist.Suit;
import ch.aplu.jcardgame.Card;
import java.lang.Math;
import java.util.ArrayList;

public class SmartStrategy implements IGameStrategy {

	@Override
	public Card playTrick(Player player, Suit lead) {
		if (lead == null) {
			return determineLeadingCard(player);
		} else {
			return determineFollowCard(player, lead);
		}
	}
	
	// will find the best leading card to place
	private Card determineLeadingCard(Player player) {
		// represents the player (not the one invoking this method) with the highest score
		int otherPlayerScore = player.getHighestPlayerScore();
		// represents when a player should start placing more aggressive cards;
		int breakingPoint = (int) Math.ceil(Whist.returnWinningScore() / (float) 2);
		
		if (otherPlayerScore >= breakingPoint) {
			return returnHighestCard(player);
		} else {
			return findLowCard(player);
		}
	}
	
	// attempt to find the lowest rank card that isn't the trump suit if possible; if not, return lowest trump suit card
	private Card findLowCard(Player player) {
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
	
	// return the highest leading card
	private Card returnHighestCard(Player player) {
		ArrayList<Card> currentHand = player.getHand().getCardList();
		Card bestCard = null;
		
		for (Card card : currentHand) {
			if (bestCard == null) {
				bestCard = card;
			}
			
			if (card.getRankId() > bestCard.getRankId()) {
				bestCard = card;
			}
		} 
		return bestCard;
	}
	
	// will find the best follow up card to place
	private Card determineFollowCard(Player player, Suit lead) {
		if (player.getHand().getNumberOfCardsWithSuit(lead) != 0) {
			return counterFind(player, lead);
		} else {
			return returnOtherCards(player, lead);
		}
	}
	
	/* Will attempt to return a card that ranks higher than the leading card (that trumps it, but not by a lot)
	 * and is of the same suit. If can't find, return lowest ranking card with the lead suit */
	private Card counterFind(Player player, Suit lead) {
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
	
	// will attempt to return lowest trump suit card. If not, will return lowest ranked card of any suit
	private Card returnOtherCards(Player player, Suit lead) {
		ArrayList<Card> currentHand = player.getHand().getCardList();
		Suit trump = player.getTrump();
		Card trumpCard = null, otherCard = null;
		
		for (Card card : currentHand) {
			if (card.getSuit() == trump) {
				if (trumpCard == null) {
					trumpCard = card;
				} else if (trumpCard.getRankId() > card.getRankId()) {
					trumpCard = card;
				}
			} else {
				if (otherCard == null) {
					otherCard = card;
				} else if (otherCard.getRankId() > card.getRankId()) {
					otherCard = card;
				}
			}
		}
	
		if (trumpCard != null) {
			return trumpCard;
		} else {
			return otherCard;
		}
	}
}
