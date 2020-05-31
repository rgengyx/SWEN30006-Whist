package WhistGame;

import ch.aplu.jcardgame.Card;

/* This is a composite class that contains all smart strategies. This class also serves as means to determine
 * which strategy is going to be picked (not really sure about this yet) */
public class CompositeSmartStartStrategy implements IStartStrategy {
	
	private IStartStrategy noTrumpStrat;
	
	public CompositeSmartStartStrategy() {
		noTrumpStrat = (IStartStrategy) new UseNotTrumpStrategy();
	}
	
	@Override
	/* Very simplified (most probably won't look like this) */
	public Card playLeadingCard(Player player) {
		return noTrumpStrat.playLeadingCard(player);
	}

}
