package ca.sheridancollege.project;

/**
 * This enumeration contains the Rank constants for a PlayingCard. A rank is any value of numbers 2-10, Jack, Queen,
 * King and Ace.
 *
 * @author (Andrew) Yin Tung Ng , Aug 2020
 */
public enum Rank {

	TWO("Two", 2),
	THREE("Three", 3),
	FOUR("Four", 4),
	FIVE("Five", 5),
	SIX("Six", 6),
	SEVEN("Seven", 7),
	EIGHT("Eight", 8),
	NINE("Nine", 9),
	TEN("Ten", 10),
	JACK("Jack", 11),
	QUEEN("Queen", 12),
	KING("King", 13),
	ACE("Ace", 14);

	private String name;
	private int value;

	private Rank(String name, int value) {
		this.name = name;
		this.value = value;
	}

	/**
	 * Returns the name of the playing card's rank.
	 *
	 * @return the name of the PlayingCard's rank as a String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the numeric value of the playing card's rank.
	 * 
	 * @return the integer value of the PlayingCard rank
	 */
	public int getValue() {
		return value;
	}
}
