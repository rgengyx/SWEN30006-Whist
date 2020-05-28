package WhistGame;

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
}
