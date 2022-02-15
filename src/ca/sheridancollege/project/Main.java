package ca.sheridancollege.project;

/**
 * The Main class is a driver class that start and runs the WarGame instance.
 *
 * @author (Andrew) Yin Tung Ng
 * @author Marjorie Teu, Aug 2020
 */
public class Main {
	
	public static void main(String[] args) {
		WarGame warGame = WarGame.getInstance();		// create a war card game
		warGame.start();								// register player names
		warGame.play();								// play rounds of the game until there is a winner
	}
}
