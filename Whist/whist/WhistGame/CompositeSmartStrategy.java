package WhistGame;

import WhistGame.Whist.Suit;
import ch.aplu.jcardgame.Card;

/* This is a composite class that contains all smart strategies. This class also serves as means to determine
 * which strategy is going to be picked (not really sure about this yet) */
public class CompositeSmartStrategy implements IGameStrategy {
	private IGameStrategy counterStrategy;
	
	public CompositeSmartStrategy() {
		counterStrategy = (IGameStrategy) new CounterStrategy();
	}
	
	
	@Override
	// very simplified decision. End result won't look like this
	public Card playTrick(Player player, Suit lead) {
		return counterStrategy.playTrick(player, lead);
	}

}
