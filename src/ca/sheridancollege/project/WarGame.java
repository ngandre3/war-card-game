package ca.sheridancollege.project;

import java.util.Scanner;

/**
 * The WarGame class models an instance of the card game. A WarGame is composed of 2 WarPlayers, a CardPile and a
 * CardDeck.
 * <p>
 * This class acts as the View in the MVC pattern. The singleton pattern is followed to only create 1 instance of the
 * class.
 *
 * @author (Andrew) Yin Tung Ng , Aug 2020
 * @author Marjorie Teu , Aug 2020 
 */
public class WarGame extends Game {

	// used to create the COMPOSITION relationship: 1 WarGame has 2 WarPlayers
	private WarPlayer player1 = new WarPlayer("Player 1", 1);		// create a new player object with playerCode
	private WarPlayer player2 = new WarPlayer("Player 2", 2);

	private CardPile pile = CardPile.getInstance();	// COMPOSITION: a WarGame exclusively owns 1 CardPile
	private CardDeck deck = new CardDeck();			// COMPOSITION: a WarGame exclusively owns 1 CardDeck
	private int turnNum = 1;						// the counter for the turn being played

	private static WarGame onlyoneWarGame = null;

	/**
	 * Returns a singleton instance of a WarGame.
	 *
	 * @return the single instance of a WarGame.
	 */
	public static WarGame getInstance() {

		if (onlyoneWarGame == null) {
			onlyoneWarGame = new WarGame();
		}

		return onlyoneWarGame;
	}

	private WarGame() {
		super("War");				// create a Game with name = "War"
		this.initializePlayers();	// method create the COMPOSITION relationship: 1 Game exclusively has 2 Players
	}

	private void initializePlayers() {

		this.setPlayer(player1);		// set the 2 players into the WarGame's ArrayList
		this.setPlayer(player2);
	}

	/**
	 * Returns the CardPile object of the WarGame
	 *
	 * @return the CardPile object
	 */
	public CardPile getPile() {
		return pile;
	}

	/**
	 * Returns the CardDeck object of the WarGame
	 *
	 * @return the CardDeck object
	 */
	public CardDeck getDeck() {
		return deck;
	}

	/**
	 * Returns the WarPlayer object for Player 1 in the WarGame.
	 *
	 * @return the WarPlayer object for Player 1
	 */
	public WarPlayer getPlayer1() {
		return ((WarPlayer) this.getPlayers().get(0));	// cast Player obj to WarPlayer
	}

	/**
	 * Returns the WarPlayer object for Player 2 in the WarGame.
	 *
	 * @return the WarPlayer object for Player 2
	 */
	public WarPlayer getPlayer2() {
		return ((WarPlayer) this.getPlayers().get(1));	// cast Player obj to WarPlayer
	}

	/**
	 * Returns the WarPlayer object by its PlayerCode (1 or 2).
	 *
	 * @param playerCode the int 1 or 2, depending on which player it is
	 * @return the WarPlayer with the matching code
	 */
	public WarPlayer getPlayerByCode(int playerCode) {
		if (playerCode == 1) {
			return getPlayer1();
		} else if (playerCode == 2) {
			return getPlayer2();
		} else {
			return null;
		}
	}

	/**
	 * Prints out the War game rules.
	 */
	public void showRules() {

		String rules = "\nThe rules are simple:\n";
		rules += "2 players draw cards from their card stacks.\n";
		rules += "The cards are compared by their rank.\n";
		rules += "The player with the higher ranked card wins all the cards.\n";
		rules += "Those cards are added to the winner's card stack.\n";
		rules += "\n";
		rules += "If both cards have the same rank, then a WAR is played.\n";
		rules += "Both players place down 3 cards, then draw and compare their fourth cards.\n";
		rules += "The winner of a WAR adds all the cards at stack to their stack.\n";
		rules += "\n";
		rules += "A Game is a best-of-3 Round contest.\n";
		rules += "A player wins a Round when the other player has no more cards remaining,\n"
			+ "    or when the other player surrenders the Round.\n";
		rules += "The first player to win 2 Rounds wins the Game,\n"
			+ "    or when the other player surrenders the Game.\n";

		System.out.println(rules);
	}

	/**
	 * Asks players to input their names, and returns the name.
	 *
	 * @return a trimmed String of the Scanner name input
	 */
	public String askPlayerName() {

		Scanner input = new Scanner(System.in);		// start the input scanner
		String name = "";

		do {
			System.out.print("Enter your player name: ");
			name = input.nextLine();
		} while (name.isBlank());					// exit loop if name is not blank

		return name.trim();					// trim off any spaces, then return string
	}

	/**
	 * Prints out the game options menu to the Player.
	 *
	 * @param player the WarPlayer to be prompted with turn options
	 */
	public void showGameOptions(WarPlayer player) {

		String[] playerOptions = new String[5];		// array to hold option label Strings
		playerOptions[0] = "Draw Next Card";
		playerOptions[1] = "Check Stack Size";
		playerOptions[2] = "Check Scoreboard";
		playerOptions[3] = "Surrender the Round";
		playerOptions[4] = "Exit the Game";

		System.out.printf("\n%s's options:\n", player.getName());	// print out player's name
		for (int i = 0; i < playerOptions.length; i++) {			// print option labels
			System.out.println(i + " : " + playerOptions[i]);
		}
		System.out.println("");
	}

	/**
	 * Asks the Player for their turn option, a number 0-4 from the menu.
	 *
	 * @return the integer option code: 0, 1, 2, 3 or 4
	 */
	public int askOption() {

		Scanner input = new Scanner(System.in);		// start the input scanner
		boolean isValid = false;						// used for while loop
		int validOption = 0;

		while (!isValid) {		// keep asking for input until valid
			try {
				System.out.print("Enter an option (0-4) from the menu: ");
				String option = input.next();

				if (!option.matches("[0-4]")) {				// use regex to match the input requirement
					throw new IllegalArgumentException("Error: input must be a digit from 0 to 4.");
				} else {
					validOption = Integer.parseInt(option);	// if OK, parse to integer
					isValid = true;							// change to true to exit while loop
				}
			} catch (Exception ex) {
				System.out.println(ex.getMessage() + " Try again.");	// show error message if invalid input
			}
		}
		return validOption;
	}

	/**
	 * Prints out the winner of the round.
	 *
	 * @param winner the winning WarPlayer object
	 */
	public void declareRoundWinner(WarPlayer winner) {

		int roundLoserCode = (winner.getPlayerCode() == 1) ? 2 : 1;	// ternary determines the loser's playerCode

		System.out.println("OH NO! " + getPlayerByCode(roundLoserCode).getName() + " ran out of cards!");
		System.out.printf("Well done! The winner of this round is %s!\n", winner.getName());
	}

	/**
	 * Overloaded method that prints out the winner for the game round, using the winner's playerCode.
	 *
	 * @param winnerCode the winner's playerCode (1 or 2), for player 1 or player 2
	 */
	public void declareRoundWinner(int winnerCode) {

		WarPlayer winner = getPlayerByCode(winnerCode);
		declareRoundWinner(winner);
	}

	/**
	 * Prints out the winner of the entire card game.
	 *
	 * @param winner the winning WarPlayer object
	 */
	public void declareGameWinner(WarPlayer winner) {
		System.out.printf("Congrats! The winner of this game of War is %s!\n", winner.getName());
	}

	/**
	 * Overloaded method that prints out the winner of the entire card game, using the winner's playerCode.
	 *
	 * @param winnerCode the winner's playerCode (1 or 2), for player 1 or player 2
	 */
	public void declareGameWinner(int winnerCode) {
		WarPlayer winner = getPlayerByCode(winnerCode);
		declareGameWinner(winner);
	}

	/**
	 * Starts the WarGame by registering player names and dealing cards to their Stacks. Player names must be unique.
	 */
	public void start() {

		Scanner input = new Scanner(System.in);				// start the input scanner
		String player1_Name = "", player2_Name = "";
		char answer = 'n';

		System.out.printf("============= %s Card Game =============\n\n", getName());		// game title

		System.out.println("Welcome to the Card Game of War!\n");

		System.out.println("Show the rules? (y/n)");
		if (input.next().charAt(0) == 'y') {
			showRules();								// print the game rules
		}

		System.out.println("Player 1:");
		player1_Name = askPlayerName();				// ask player 1 for name

		do {
			System.out.println("Player 2:");
			player2_Name = askPlayerName();			// ask player 2 for name

			if (player1_Name.equalsIgnoreCase(player2_Name)) {		// check if names are the same
				System.out.println("Player 1 and Player 2 cannot have the same name. Player 2, try again.");
			}

		} while (player1_Name.equalsIgnoreCase(player2_Name));		// keep asking if both names are the same

		GameController.registerPlayers(player1, player2, player1_Name, player2_Name);	// add names to the WarGame
		System.out.println("Player name registration is DONE!\n");

		do {		// ask players if they want to start the game?

			System.out.println("Start playing the game? (y/n)");
			answer = input.next().charAt(0);

			if (!(answer == 'y' || answer == 'Y')) {
				System.out.println("Come on, let's play! It will be fun!");
			}
		} while (!(answer == 'y' || answer == 'Y'));
	}

	/**
	 * Plays a Round of War. Menu options are presented to players: play turn, check stack size, check scores, surrender
	 * round, or exit game.
	 */
	@Override
	public void play() {

		Stack stack1 = getPlayer1().getStack();	// for convenience, get references for their stacks
		Stack stack2 = getPlayer2().getStack();
		int option1 = 99, option2 = 99, roundWinnerCode = 0; 	// initialize to invalid option int (99 is not 0-4)

		System.out.println("\n=============== New Round ===============\n");

		turnNum = 1;			// RESET the turn # counter at start of each ROUND

		pile.clear();			// remove any Cards in the pile from the last round
		stack1.clear();			// clear the player Stacks of any cards from last round
		stack2.clear();
		System.out.println("Clearing the pile and the player stacks .... DONE!\n");

		// FLEXIBILITY: ask players how many cards to play
		// allows players to choose less cards, for shorter rounds
		int numCards = GameController.askNumberOfCards();

		deck = new CardDeck();						// start each round with a new, full CardDeck
		GameController.dealCards(numCards);			// deal the desired # of cards to both stacks
		System.out.println("Shuffling the deck and dealing the cards ... DONE!\n");

		do {		// *** PLAY A ROUND function: loop through # of turns until a player runs out of Cards

			System.out.printf("=============== Turn %03d ================\n", turnNum);	// banner of each turn

			showGameOptions(player1);			// show menu options to player 1
			do {									// loop through options until player chooses Draw Card				
				option1 = askOption();			// get player 1's option selection
				player1.doOption(option1);
			} while (option1 != 0);

			showGameOptions(player2);			// show menu options to player 2
			do {									// loop through options until player chooses Draw Card				
				option2 = askOption();			// get player 2's option selection
				player2.doOption(option2);
			} while (option2 != 0);

			// NOW both players choose to draw cards and play the turn
			int exitCode = GameController.playTurn();

			// if turn's exitCode is 0 (same ranks), then play a WAR turn.
			// play until there is a winner from the WAR (exitCode = 1 or 2)
			while (exitCode == 0) {
				exitCode = GameController.playWarTurn();
			}

			// check stacks if there is a round winner, which 
			roundWinnerCode = GameController.checkStacks();

			turnNum += 1;			// increment turn counter by 1 at end of a turn

			// SHUFFLES THE STACKS after some number of turns. You can adjust the frequency with the step variable.
			// MAY help to break out of unresolvable gameplay loops -> round never ends
			int step = 5;
			if (turnNum % step == 0) {
				GameController.shuffleStacks();
			}

		} while (roundWinnerCode == 0);		// exit the Round loop if there is a Round winner

		// HERE: some player ran out of cards -> LOSER,    ROUND IS OVER
		// player with cards leftover -> WINNER, update winner's score
		//
		// show the end-of-round info, and decide if another round needs to be played
		showRoundOver(roundWinnerCode);
	}

	/**
	 * Displays the end-of-round info, and checks if a new round needs to be played. Only shows up if some player ran
	 * out of cards, such that the roundWinnerCode is not 0 (so = 1 or 2).
	 *
	 * @param roundWinnerCode the playerCode (1 or 2) of the round's winning WarPlayer
	 */
	public void showRoundOver(int roundWinnerCode) {

		if (roundWinnerCode == 1 || roundWinnerCode == 2) {

			System.out.println("\n=============== Round Over ==============\n");
			declareRoundWinner(roundWinnerCode);			// print which player won

			Scoreboard.updateScore(roundWinnerCode);		// increase winner's score
			GameController.showScoreboard();

			// VERY IMPORTANT: controller method checks the scores if anyone has score=2 yet (won the game).
			// if no game winner yet, then method starts a new round by calling play() from WarGame class.
			GameController.checkPlayAnotherRound();
		}
	}

	// UNUSED, need to remove from Game class, to minimize public interface
//	@Override
//	public void declareWinner() {
//	}
}
