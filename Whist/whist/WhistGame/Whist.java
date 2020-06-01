package WhistGame;
// Whist.java

import java.awt.Color;
import java.awt.Font;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;

import ch.aplu.jcardgame.Card;
import ch.aplu.jcardgame.CardAdapter;
import ch.aplu.jcardgame.CardGame;
import ch.aplu.jcardgame.CardListener;
import ch.aplu.jcardgame.Deck;
import ch.aplu.jcardgame.Hand;
import ch.aplu.jcardgame.RowLayout;
import ch.aplu.jcardgame.TargetArea;
import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.Location;
import ch.aplu.jgamegrid.TextActor;

@SuppressWarnings("serial")
public class Whist extends CardGame {

	public enum Suit {
		SPADES, HEARTS, DIAMONDS, CLUBS
	}

	public enum Rank {
		// Reverse order of rank importance (see rankGreater() below)
		// Order of cards is tied to card images
		ACE, KING, QUEEN, JACK, TEN, NINE, EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO
	}

	final String trumpImage[] = { "bigspade.gif", "bigheart.gif", "bigdiamond.gif", "bigclub.gif" };

	private final Random random;

	// return random Enum value
	public <T extends Enum<?>> T randomEnum(Class<T> clazz) {
		int x = random.nextInt(clazz.getEnumConstants().length);
		return clazz.getEnumConstants()[x];
	}

	public boolean rankGreater(Card card1, Card card2) {
		return card1.getRankId() < card2.getRankId(); // Warning: Reverse rank order of cards (see comment on enum)
	}
	
	public static int returnNBStartCards() {
		return nbStartCards;
	}
	
	public static int returnWinningScore() {
		return winningScore;
	}
	
	public static int returnNBPlayers() {
		return nbPlayers;
	}

	private final String version = "1.0";
	private static final int nbPlayers = 4;
	private static int nbStartCards = 13;
	private static int winningScore = 11;
	private final int handWidth = 400;
	private final int trickWidth = 40;
	private final Deck deck = new Deck(Suit.values(), Rank.values(), "cover");
	private final Location[] handLocations = { new Location(350, 625), new Location(75, 350), new Location(350, 75),
			new Location(625, 350) };
	private final Location[] scoreLocations = { new Location(575, 675), new Location(25, 575), new Location(575, 25),
			new Location(650, 575) };
	private Actor[] scoreActors = { null, null, null, null };
	private final Location trickLocation = new Location(350, 350);
	private final Location textLocation = new Location(350, 450);
	private final int thinkingTime = 2000;
	private Hand[] hands;
	private Location hideLocation = new Location(-500, -500);
	private Location trumpsActorLocation = new Location(50, 50);
	private boolean enforceRules = false;

	// New code
	private Player[] players = new Player[4];
	private GameUpdater gameUpdater = new GameUpdater();

	public void setStatus(String string) {
		setStatusText(string);
	}

	private int[] scores = new int[nbPlayers];

	Font bigFont = new Font("Serif", Font.BOLD, 36);

	private void initScore() {
		for (int i = 0; i < nbPlayers; i++) {
			scores[i] = 0;
			scoreActors[i] = new TextActor("0", Color.WHITE, bgColor, bigFont);
			addActor(scoreActors[i], scoreLocations[i]);
		}
	}

	private void updateScore(int player) {
		removeActor(scoreActors[player]);
		scoreActors[player] = new TextActor(String.valueOf(scores[player]), Color.WHITE, bgColor, bigFont);
		addActor(scoreActors[player], scoreLocations[player]);
	}

	private Card selected;

	private void initRound() {
		hands = deck.dealingOut(nbPlayers, nbStartCards); // Last element of hands is leftover cards; these are ignored
		for (int i = 0; i < nbPlayers; i++) {
			hands[i].sort(Hand.SortType.SUITPRIORITY, true);
			players[i].setHand(hands[i]);
		}
		
		// new code 31/05/2020
		// erasing history here
		gameUpdater.removeHistory();

		// Set up human player for interaction
		CardListener cardListener = new CardAdapter() // Human Player plays card
		{
			public void leftDoubleClicked(Card card) {
				selected = card;
				players[0].getHand().setTouchEnabled(false);
			}
		};
		players[0].getHand().addCardListener(cardListener);

		// graphics
		RowLayout[] layouts = new RowLayout[nbPlayers];
		for (int i = 0; i < nbPlayers; i++) {
			layouts[i] = new RowLayout(handLocations[i], handWidth);
			layouts[i].setRotationAngle(90 * i);
			// layouts[i].setStepDelay(10);
			hands[i].setView(this, layouts[i]);
			hands[i].setTargetArea(new TargetArea(trickLocation));
			hands[i].draw();
		}
//		for (int i = 1; i < nbPlayers; i++) // This code can be used to visually hide the cards in a hand (make them
		// face down)
//			hands[i].setVerso(true);
		// End graphics
	}

	private Optional<Integer> playRound() { // Returns winner, if any
		// Select and display trump suit
		final Suit trumps = randomEnum(Suit.class);
		
		// new code - 31/05/2020
		gameUpdater.updateTrump(trumps);
		
		final Actor trumpsActor = new Actor("sprites/" + trumpImage[trumps.ordinal()]);
		addActor(trumpsActor, trumpsActorLocation);
		// End trump suit
		Hand trick;
		int winner;
		Card winningCard;
		Suit lead;
		int nextPlayer = random.nextInt(nbPlayers); // randomly select player to lead for this round
		for (int i = 0; i < nbStartCards; i++) {
			trick = new Hand(deck);
			selected = null;
			if (0 == nextPlayer) { // Select lead depending on player type
				players[0].getHand().setTouchEnabled(true);
				setStatus("Player 0 double-click on card to lead.");
				while (null == selected)
					delay(100);
			} else {
				setStatusText("Player " + nextPlayer + " thinking...");
				delay(thinkingTime);
//				selected = randomCard(hands[nextPlayer]);
				selected = players[nextPlayer].playTrick(null);
			}
			// Lead with selected card
			trick.setView(this, new RowLayout(trickLocation, (trick.getNumberOfCards() + 2) * trickWidth));
			trick.draw();
			selected.setVerso(false);
			// No restrictions on the card being lead
			lead = (Suit) selected.getSuit();
			selected.transfer(trick, true); // transfer to trick (includes graphic effect)
			winner = nextPlayer;
			winningCard = selected;
			
			// new code - 31/05/2020
			// all lead cards are winning cards (at the beginning at least0
			gameUpdater.updateCard(selected, true);
			
			// End Lead
			for (int j = 1; j < nbPlayers; j++) {
				if (++nextPlayer >= nbPlayers)
					nextPlayer = 0; // From last back to first
				selected = null;
				if (0 == nextPlayer) {
					players[0].getHand().setTouchEnabled(true);
					setStatus("Player 0 double-click on card to follow.");
					while (null == selected)
						delay(100);
				} else {
					setStatusText("Player " + nextPlayer + " thinking...");
					delay(thinkingTime);
					selected = players[nextPlayer].playTrick(lead);
				}
				// Follow with selected card
				trick.setView(this, new RowLayout(trickLocation, (trick.getNumberOfCards() + 2) * trickWidth));
				trick.draw();
				selected.setVerso(false); // In case it is upside down
				// Check: Following card must follow suit if possible
				if (selected.getSuit() != lead && hands[nextPlayer].getNumberOfCardsWithSuit(lead) > 0) {
					// Rule violation
					String violation = "Follow rule broken by player " + nextPlayer + " attempting to play " + selected;
					System.out.println(violation);
					if (enforceRules)
						try {
							throw (new BrokeRuleException(violation));
						} catch (BrokeRuleException e) {
							e.printStackTrace();
							System.out.println("A cheating player spoiled the game!");
							System.exit(0);
						}
				}
				// End Check
				selected.transfer(trick, true); // transfer to trick (includes graphic effect)
				System.out.println("winning: suit = " + winningCard.getSuit() + ", rank = " + winningCard.getRankId());
				System.out.println(" played: suit = " + selected.getSuit() + ", rank = " + selected.getRankId());
				if ( // beat current winner with higher card
				(selected.getSuit() == winningCard.getSuit() && rankGreater(selected, winningCard)) ||
				// trumped when non-trump was winning
						(selected.getSuit() == trumps && winningCard.getSuit() != trumps)) {
					System.out.println("NEW WINNER");
					winner = nextPlayer;
					winningCard = selected;
				}
				
				// new code - 31/05/2020
				if (selected == winningCard) {
					gameUpdater.updateCard(selected, true);
				} else {
					gameUpdater.updateCard(selected, false);
				}
				
				// End Follow
			}
			delay(600);
			trick.setView(this, new RowLayout(hideLocation, 0));
			trick.draw();
			nextPlayer = winner;
			setStatusText("Player " + nextPlayer + " wins trick.");
			scores[nextPlayer]++;
			updateScore(nextPlayer);
			
			// new code - 30/05/2020
			gameUpdater.updateScore(nextPlayer);
			
			if (winningScore == scores[nextPlayer])
				return Optional.of(nextPlayer);
		}
		removeActor(trumpsActor);
		return Optional.empty();
	}

	public Whist(Properties properties) {

		super(700, 700, 30);

		// New code
		long seedProp = Long.parseLong(properties.getProperty("Seed"));
		random = new Random(seedProp);
		nbStartCards = Integer.parseInt(properties.getProperty("nbStartCards"));
		winningScore = Integer.parseInt(properties.getProperty("winningScore"));
		enforceRules = Boolean.parseBoolean(properties.getProperty("enforceRules"));

		if (properties.getProperty("name").equals("original")) {

			IGameStrategy randomStrategy = GameStrategyFactory.getInstance().getRandomStrategy();
			players[0] = new Player(randomStrategy, 0, nbPlayers, deck);
			players[1] = new Player(randomStrategy, 1, nbPlayers, deck);
			players[2] = new Player(randomStrategy, 2, nbPlayers, deck);
			players[3] = new Player(randomStrategy, 3, nbPlayers, deck);

		} else if (properties.getProperty("name").equals("legal")) {

			IGameStrategy legalStrategy = GameStrategyFactory.getInstance().getLegalStrategy();
			players[0] = new Player(legalStrategy, 0, nbPlayers, deck);
			players[1] = new Player(legalStrategy, 1, nbPlayers, deck);
			players[2] = new Player(legalStrategy, 2, nbPlayers, deck);
			players[3] = new Player(legalStrategy, 3, nbPlayers, deck);
		}
		
		// new code - 31/05/2020
		for (int i = 0; i < nbPlayers; i++) {
			gameUpdater.addGameListeners(players[i]);
		}

		setTitle("Whist (V" + version + ") Constructed for UofM SWEN30006 with JGameGrid (www.aplu.ch)");
		setStatusText("Initializing...");
		initScore();
		Optional<Integer> winner;
		do {
			initRound();
			winner = playRound();
		} while (!winner.isPresent());
		addActor(new Actor("sprites/gameover.gif"), textLocation);
		setStatusText("Game over. Winner is player: " + winner.get());
		refresh();
	}

	public static void main(String[] args) throws IOException {

		Properties whistProperties = new Properties();

		// Read properties
		FileReader inStream = null;
		try {
			inStream = new FileReader("legal.properties");
			whistProperties.load(inStream);
		} finally {
			if (inStream != null) {
				inStream.close();
			}
		}

		// System.out.println("Working Directory = " + System.getProperty("user.dir"));
		new Whist(whistProperties);
	}

}
