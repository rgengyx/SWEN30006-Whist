package WhistGame;

import java.util.Random;

/* This is a factory class that produces strategies when a leading card has been set */
public class GameStrategyFactory {
	private static GameStrategyFactory instance;

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
