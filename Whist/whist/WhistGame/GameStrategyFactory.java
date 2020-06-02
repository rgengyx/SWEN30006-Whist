package WhistGame;

/* This is a factory class that produces strategies when a leading card has been set */
public class GameStrategyFactory {
	private static GameStrategyFactory instance;

	public static GameStrategyFactory getInstance() {
		if (instance == null) {
			instance = new GameStrategyFactory();
		}
		return instance;
	}

	public IGameStrategy getRandomStrategy() {
		return (IGameStrategy) new RandomStrategy();
	}

	public IGameStrategy getLegalStrategy() {
		return (IGameStrategy) new LegalStrategy();
	}

	public IGameStrategy getInteractiveStrategy() {
		return (IGameStrategy) new InteractiveStrategy();
	}

	public IGameStrategy getSmartStrategy() {
		return (IGameStrategy) new CompositeSmartStrategy();
	}
}
