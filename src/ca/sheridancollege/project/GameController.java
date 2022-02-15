package ca.sheridancollege.project;

import java.util.Scanner;

/**
 * This is the controller for the War card game application. It has the utilities (methods) that handle the game logic.
 * <p>
 * It acts as a Controller in the MVC pattern.
 *
 * @author (Andrew) Yin Tung Ng, Aug 2020
 * @author Marjorie Teu, Aug 2020 
 */
public class GameController {

	/**
	 * Evenly deals a desired number of a CardDeck's Cards to the Stacks of the 2 game players.
	 * 
	 * @param numCards number of cards to be played in the round, must be even integer from 2 to 52
	 */
	public static void dealCards(int numCards) {

		for (int i = 0; i < numCards; i++) {			// deal only a certain number of cards

			Card drawnCard = WarGame.getInstance().getDeck().drawCard();		// draw a card from the deck

			// alternate between giving cards to player 1 and 2, based on even/odd loop index
			if (i % 2 == 0) {
				WarGame.getInstance().getPlayer1().getStack().addCard(drawnCard);
			} else {
				WarGame.getInstance().getPlayer2().getStack().addCard(drawnCard);
			}
		}
	}

	/**
	 * Returns the winner's playerCode integer (1 or 2) after comparing the rank values of 2 PlayingCards. If 0, the
	 * values are the same and the game-state of War is triggered.
	 *
	 * @param card1 player 1's drawn PlayingCard
	 * @param card2 player 2's drawn PlayingCard
	 * @return 1 if player 1 won the turn; 2 if player 2 won the turn; 0 if the Card rank values are equal.
	 */
	public static int compareCards(PlayingCard card1, PlayingCard card2) {

		int card1_Value = card1.getValue();		// get the Card rank values
		int card2_Value = card2.getValue();

		if (card1_Value > card2_Value) {	// compare which card is higher by their rank value (2-14).
			return 1;
		} else if (card1_Value < card2_Value) {
			return 2;
		} else {
			return 0;					// if both cards have equal rank, then nobody won the turn.
		}
	}

	/**
	 * Returns the playerCode (1 or 2) of the player with cards remaining. Checks if any of the 2 players ran out of
	 * cards in their Stacks. The player who still has cards left after a turn wins the round.
	 *
	 * @return 2 if player 1 is out of cards; 1 if player 2 is out of cards; 0 if no one has run out of cards yet.
	 */
	public static int checkStacks() {

		if (WarGame.getInstance().getPlayer1().getStack().getSize() == 0) {
			return 2;						// if player 1's stack is empty/size=0, then player 2 wins
		} else if (WarGame.getInstance().getPlayer2().getStack().getSize() == 0) {
			return 1;						// if player 2's stack is empty/size=0, then player 1 wins
		} else {
			return 0;	// no player is out of cards yet
		}
	}

	/**
	 * Set the names of the Players.
	 *
	 * @param player1 Player instance for player 1
	 * @param player2 Player instance for player 2
	 * @param name1 player 1's name String
	 * @param name2 player 2's name String
	 */
	public static void registerPlayers(Player player1, Player player2, String name1, String name2) {
		player1.setName(name1);		// set the players' names into their WarPlayer instances
		player2.setName(name2);
	}

	/**
	 * Prints out a string with the player's stack size.
	 *
	 * @param player the WarPlayer asking to check the size
	 */
	public static void showStackSize(WarPlayer player) {
		System.out.printf("Your card stack's size is %s.\n", player.getStack().getSize());
	}

	/**
	 * Asks and returns the number of cards to be played in the round. Players can choose from 2 to 52 cards to be dealt
	 * between Players. Input value must be even, to evenly deal cards to Stacks.
	 *
	 * @return an even integer number of cards to be dealt
	 */
	public static int askNumberOfCards() {

		Scanner input = new Scanner(System.in);
		int numCards = 1;
		boolean isValid = false;		// swtiches to true when input is valid

		System.out.println("The game can be played with any even number of cards, from 2 to 52.");

		do {			// use loop to prompt user until input is correct
			try {
				System.out.print("Enter the number of cards you want to play with: ");
				String str = input.next();
				numCards = Integer.parseInt(str);		// parse the input into an integer

				if (numCards >= 2 && numCards <= 52 && numCards % 2 == 0) {	// check input is even and in range.
					isValid = true;
				} else {
					throw new IllegalArgumentException();		// if bad input, throw exception, trigger error message
				}

			} catch (Exception ex) {
				System.out.println("Error: input must be an even number from 2 to 52.");
			}
		} while (!isValid);		// exit the loop when input is valid

		return numCards;
	}

	/**
	 * Executes the player actions for playing a turn. The method draws the cards, compares them, and adds all the cards
	 * in the CardPile to winner's Stack.
	 *
	 * @return the exit code: 1 if player 1 won, 2 if player 2 won, 0 if nobody won the turn (equal ranks)
	 */
	public static int playTurn() {

		WarPlayer player1 = WarGame.getInstance().getPlayer1();	// for convenience, get references for the WarPlayers
		WarPlayer player2 = WarGame.getInstance().getPlayer2();
		Stack stack1 = player1.getStack();						// for convenience, get references for their stacks
		Stack stack2 = player2.getStack();
		CardPile pile = WarGame.getInstance().getPile();			// reference for the game's pile

		PlayingCard drawnCard1 = (PlayingCard) stack1.drawCard();		// draw and store the top Card from each Stack
		PlayingCard drawnCard2 = (PlayingCard) stack2.drawCard();

		// print out the PlayingCard string with the player's name
		System.out.println("\n*****************************************");
		System.out.printf("%12s drew the %s\n", player1.getName(), drawnCard1.toString());
		System.out.printf("%12s drew the %s\n", player2.getName(), drawnCard2.toString());
		System.out.println("*****************************************\n");

		pile.addCard(drawnCard1);	// add the two cards to the Pile
		pile.addCard(drawnCard2);

		int exitCode = compareCards(drawnCard1, drawnCard2);	// compare the 2 cards to determine the winning player

		if (exitCode == 1) {		// if player 1 is the turn winner,

			System.out.printf("****** %s won the turn! *******\n\n", player1.getName());
			pile.addCardsToWinner(player1);		// add cards in pile to Player 1's Stack

		} else if (exitCode == 2) {	// if player 2 is the turn winner,

			System.out.printf("****** %s won the turn! *******\n\n", player2.getName());
			pile.addCardsToWinner(player2);		// add cards in pile to Player 2's Stack

		} else {			// show message if ranks are equal -> PLAY WAR
			System.out.println("**** The cards have the same rank! ****");
			System.out.println("********** Time to play WAR! **********");
		}

		return exitCode;
	}

	/**
	 * Play a special turn of WAR. 3 more cards are drawn from each Stack into the Pile. Then a regular turn is played
	 * to find out who won the WAR.
	 *
	 * @return the exit code: 1 if player 1 won, 2 if player 2 won, 0 if nobody won the turn (equal ranks)
	 */
	public static int playWarTurn() {

		WarPlayer player1 = WarGame.getInstance().getPlayer1();	// for convenience, get references for the WarPlayers
		WarPlayer player2 = WarGame.getInstance().getPlayer2();
		Stack stack1 = player1.getStack();						// for convenience, get references for their stacks
		Stack stack2 = player2.getStack();
		CardPile pile = WarGame.getInstance().getPile();			// reference for the game's pile
		int roundWinnerCode = 0;

		for (int i = 0; i < 3; i++) {							// PLAYER 1
			pile.addCard(stack1.drawCard());							// ADD 3 cards from each Stack to the Pile
			roundWinnerCode = checkStacks();							// check after each draw if cards ran out
			WarGame.getInstance().showRoundOver(roundWinnerCode);		// end the round if player 1 is out of cards
		}

		for (int i = 0; i < 3; i++) {							// PLAYER 2
			pile.addCard(stack2.drawCard());							// ADD 3 cards from each Stack to the Pile
			roundWinnerCode = checkStacks();							// check after each draw if cards ran out
			WarGame.getInstance().showRoundOver(roundWinnerCode);		// end the round if player 2 is out of cards		
		}

		System.out.printf("\n%s draws 3 cards into the card pile ... DONE!\n", player1.getName());
		System.out.printf("%s draws 3 cards into the card pile ... DONE!\n", player2.getName());
		
		return playTurn();			// play a regular turn to determine the winner
	}

	/**
	 * Shuffles the Cards in the 2 Stacks when called. This helps to break up any loops that stop a round from ending.
	 */
	public static void shuffleStacks() {
		WarGame.getInstance().getPlayer1().getStack().shuffle();
		WarGame.getInstance().getPlayer2().getStack().shuffle();
	}

	/**
	 * Prints the scoreboard.
	 */
	public static void showScoreboard() {
		System.out.println(Scoreboard.getScores());
	}

	/**
	 * Surrenders the round for the requesting (loser) player. The other player wins and adds 1 to their score. A new
	 * round is played.
	 * 
	 * @param loser the WarPlayer object of the loser
	 */
	public static void surrenderRound(WarPlayer loser) {

		WarPlayer winner;		// declare for use

		// determine and get the winning player's WarPlayer object.
		// if player 1 surrendered, then player 2 is the round winner
		if (loser.getPlayerCode() == 1) {
			winner = WarGame.getInstance().getPlayer2();
		} else {
			winner = WarGame.getInstance().getPlayer1();
		}

		System.out.println("\n=============== Round Over ==============\n");

		// print which player surrendered
		System.out.printf("%s has surrendered this round.\n", loser.getName());
		// print which player won
		WarGame.getInstance().declareRoundWinner(winner);

		Scoreboard.updateScore(winner.getPlayerCode());	// increase winner's score
		showScoreboard();

		checkPlayAnotherRound();		// checks if a new round needs to be played/ any game winner yet?
	}

	/**
	 * Checks if any player has the game by reaching a score of 2 rounds won. If no one has won 2 rounds yet, a new
	 * round is played.
	 */
	public static void checkPlayAnotherRound() {

		int winnerCode = 0;

		if (Scoreboard.getScore(1) == 2) {	// check if player 1 won the game (score=2)
			winnerCode = 1;
		}

		if (Scoreboard.getScore(2) == 2) {	// check if player 2 won the game (score=2)
			winnerCode = 2;
		}

		// play a new round if neither player has won 2 rounds yet (i.e. won the game)
		if (winnerCode == 0) {
			WarGame.getInstance().play();		// start a new round when nobody has a score of 2 yet
		} else {
			GameController.declareWinner(winnerCode);		// else, there is a winner. So we declare the win.
		}
	}

	/**
	 * Prints out the Game Over section when there is a Game winner, by the winner's playerCode.
	 *
	 * @param winnerCode the playerCode int (1 or 2) of the winning Player
	 */
	public static void declareWinner(int winnerCode) {

		System.out.println("\n=============== Game Over ===============\n");

		// print which player won
		WarGame.getInstance().declareGameWinner(winnerCode);

		showScoreboard();

		System.out.println("\n========== Thanks for Playing! ==========\n");

		System.exit(0);		// exits the game program
	}

	/**
	 * Exits the game after asking the player for confirmation.
	 * 
	 * @param loser the losing WarPlayer
	 */
	public static void exitGame(WarPlayer loser) {

		WarPlayer winner;		// declare for use

		// determine and get the winning player's WarPlayer object.
		// if player 1 surrendered, then player 2 is the round winner
		if (loser.getPlayerCode() == 1) {
			winner = WarGame.getInstance().getPlayer2();
		} else {
			winner = WarGame.getInstance().getPlayer1();
		}

		Scanner input = new Scanner(System.in);		// start the input scanner

		// ask players to confirm if they want to quit the game
		System.out.println("Do you want to quit the game? (y/n)");
		char answer = input.next().charAt(0);

		if (answer == 'y' || answer == 'Y') {		// if YES, then do:

			// print which player quit the Game
			System.out.printf("%s has quit the game.\n", loser.getName());
			Scoreboard.updateScore(winner.getPlayerCode());	// increase winner's score

			declareWinner(winner.getPlayerCode());	// show the Game winner message
		}

	}

}
