package ca.sheridancollege.project;

/**
 * The WarPlayer class models a game player in the War card game.
 * <p>
 * An instance exclusively owns a card Stack and a unique playerCode integer (1 or 2).
 * <p>
 * This class acts as a Model in the MVC pattern.
 *
 * @author (Andrew) Yin Tung Ng, Aug 2020
 * @author Marjorie Teu, Aug 2020
 */
public class WarPlayer extends Player {

	private Stack stack = new Stack(0);	// COMPOSITION: a WarPlayer exclusively owns a Stack object
	private int playerCode;				// playerCode can only be 1 or 2; indicates if they are Player 1 or 2

	/**
	 * Single parameter constructor that takes in the player's name.
	 *
	 * @param name the name of the WarPlayer
	 * @param playerCode the integer code (1 or 2) for the player
	 */
	public WarPlayer(String name, int playerCode) {
		super(name);
		this.playerCode = playerCode;		// public setter was not needed for only this one use
	}

	/**
	 * Returns the player's code (1 or 2), which indicates if they are Player 1 or 2.
	 *
	 * @return 1 or 2 to show which Player they are
	 */
	public int getPlayerCode() {
		return playerCode;
	}

	/**
	 * Returns the player's Stack instance.
	 *
	 * @return the player's Stack
	 */
	public Stack getStack() {
		return stack;
	}

	// UNUSED, need to remove from Player class, to minimize public interface
//	@Override
//	public void play() {		
//
//	}

	/**
	 * Calls the GameController to execute the desired option, by integer value.
	 * 
	 * @param option the integer value of the selected option (0,1,2,3,4)
	 */
	public void doOption(int option) {

		// use switch-case to execute the player's option
		switch (option) {

			case 1:		// 1. check this player's stack size 
				GameController.showStackSize(this);
				break;

			case 2:		// 2. check scoreboard
				GameController.showScoreboard();
				break;

			case 3:		// 3. surrender the round
				GameController.surrenderRound(this);
				break;

			case 4:		// 4. exit the game
				GameController.exitGame(this);
				break;
		}
	}
}
