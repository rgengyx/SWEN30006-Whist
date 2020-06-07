
/* Strategy for human players, but really, this entire class is used as means to differentiate
 * between an NPC and an actual human playing.
 *  
 * Made by - Project Team 24 */
import ch.aplu.jcardgame.Card;

public class InteractiveStrategy implements IGameStrategy {

	@Override
	/* empty because this method is never called in the first place */
	public Card playTrick(Player player, Whist.Suit lead) {
		return null;
	}

}
