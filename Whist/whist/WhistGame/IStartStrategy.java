package WhistGame;

import ch.aplu.jcardgame.Card;

/* Strategies for placing a leading card */
public interface IStartStrategy {
	Card playLeadingCard(Player player);
}
