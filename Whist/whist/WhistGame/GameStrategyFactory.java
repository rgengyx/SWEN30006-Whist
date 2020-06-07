package WhistGame;

import java.util.Random;

/* This is a factory class that produces strategies for card choice when playing Whist
 * Made by - Project Team 24 */
public class GameStrategyFactory {
	private static GameStrategyFactory instance;
	
	// The factory itself is a singleton 
	public static GameStrategyFactory getInstance() {
		if (instance == null) {
			instance = new GameStrategyFactory();
		}
		return instance;
	}

	public IGameStrategy getRandomStrategy(Random random) {
		return (IGameStrategy) new RandomStrategy(random);
	}

	public IGameStrategy getLegalStrategy(Random random) {
		return (IGameStrategy) new LegalStrategy(random);
	}

	public IGameStrategy getInteractiveStrategy() {
		return (IGameStrategy) new InteractiveStrategy();
	}

	public IGameStrategy getSmartStrategy() {
		return (IGameStrategy) new SmartStrategy();
	}
}
