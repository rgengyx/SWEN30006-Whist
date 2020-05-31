package WhistGame;

public class StartStrategyFactory {
	private static StartStrategyFactory instance;

	public static StartStrategyFactory getInstance() {
		if (instance == null) {
			instance = new StartStrategyFactory();
		}
		return instance;
	}

	public IStartStrategy getRandomStrategy() {
		return (IStartStrategy) new RandomStartStrategy();
	}

	public IStartStrategy getLegalStrategy() {
		return (IStartStrategy) new LegalStartStrategy();
	}
}
