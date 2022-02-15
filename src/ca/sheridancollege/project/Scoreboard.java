package ca.sheridancollege.project;

/**
 * This models the Scoreboard for the War Card Game. It tracks the scores of players 1 and 2, and can show the
 * scoreboard on demand.
 * <p>
 * This class acts as a Model in the MVC pattern.
 *
 * @author (Andrew) Yin Tung Ng , Aug 2020
 * @author Marjorie Teu , Aug 2020
 */
public class Scoreboard {

	private static int player1Score = 0;
	private static int player2Score = 0;

	/**
	 * Returns a WarPlayer's score.
	 *
	 * @param player the WarPlayer in the game
	 * @return the player's current score
	 */
	public static int getScore(WarPlayer player) {

		if (player.getPlayerCode() == 1) {
			return player1Score;
		} else {
			return player2Score;
		}
	}

	/**
	 * Overloaded method that returns a WarPlayer's score, by playerCode (1 or 2).
	 *
	 * @param playerCode the WarPlayer's playerCode (1 or 2)
	 * @return the integer score of the WarPlayer
	 */
	public static int getScore(int playerCode) {

		if (playerCode == 1) {
			return player1Score;
		} else {
			return player2Score;
		}
	}

	/**
	 * Returns the player scores in a formatted string with their names.
	 *
	 * @return a String formatted with the 2 player scores.
	 */
	public static String getScores() {

		String name1 = WarGame.getInstance().getPlayer1().getName();		// get names of players
		String name2 = WarGame.getInstance().getPlayer2().getName();

		String str = "Scores:   %s = %d   |   %s = %d";	// format the scoreboard String
		return String.format(str, name1, player1Score, name2, player2Score);
	}

	/**
	 * Increases the score of the round's winning player by 1.
	 *
	 * @param winner the integer to indicate which player (1 or 2) won the round
	 */
	public static void updateScore(int winner) {

		if (winner == 1) {			// if player 1 won, increase that score by 1
			player1Score += 1;
		} else if (winner == 2) {	// if player 2 won, increase that score by 1
			player2Score += 1;
		}
	}

}
