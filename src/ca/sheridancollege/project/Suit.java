package ca.sheridancollege.project;

/**
 * This enumeration contains the Suit constants for a PlayingCard. A suit can be Hearts, Clubs, Diamonds, or Spades.
 *
 * @author (Andrew) Yin Tung Ng , Aug 2020
 */
public enum Suit {

	HEARTS("Hearts"),
	DIAMONDS("Diamonds"),
	CLUBS("Clubs"),
	SPADES("Spades");

	private String suit;

	private Suit(String givenSuit) {
		suit = givenSuit;
	}

	/**
	 * Returns the suit of the playing card.
	 * 
	 * @return the PlayingCard suit as a String
	 */
	public String getSuit() {
		return suit;
	}

}
