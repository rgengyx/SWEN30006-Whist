
public class GameStrategyFactory {
	private static GameStrategyFactory instance;
	private IGameStrategy randomStrategy;

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
