/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 * @author (Andrew) Yin Tung Ng , Aug 2020
 * @author Marjorie Teu , Aug 2020
 */
public abstract class Player {

	private String name; //the unique name for this player

	/**
	 * A constructor that allows you to set the player's unique ID
	 *
	 * @param name the unique ID to assign to this player.
	 */
	public Player(String name) {
		this.name = name;
	}

	/**
	 * @return the player name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Ensure that the playerID is unique
	 *
	 * @param name the player name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	// play() was removed from this Player class. It was never implemented by subclasses (other methods did the job).
	// So it is better to remove it (here) from the superclass to MINIMIZE THE PUBLIC CONTRACT.
//    /**
//     * The method to be overridden when you subclass the Player class with your specific type of Player and filled in
//     * with logic to play your game.
//     */
//    public abstract void play();
}
