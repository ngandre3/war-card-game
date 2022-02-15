package ca.sheridancollege.project;

/**
 * This class models a regular playing card with a unique suit and rank combination.
 * <p>
 * This class acts as a Model in the MVC pattern.
 *
 * @author (Andrew) Yin Tung Ng , Aug 2020
 */
public class PlayingCard extends Card {

	private Rank rank;
	private Suit suit;

	/**
	 * Parameterized constructor accepts a Suit and Rank.
	 * 
	 * @param rank Rank enum to be set
	 * @param suit Suit enum to be set
	 */
	public PlayingCard(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	/**
	 * Returns the rank of the PlayingCard, as per the Rank enum.
	 *
	 * @return the Rank enum constant of the PlayingCard
	 */
	public Rank getRank() {
		return rank;
	}

	/**
	 * Returns the suit of the PlayingCard, as per the Suit enum.
	 *
	 * @return the Suit enum constant of the PlayingCard
	 */
	public Suit getSuit() {
		return suit;
	}
	
	/**
	 * Returns the value of the PlayingCard's rank, for comparison.
	 * 
	 * @return the integer value of the Card
	 */
	public int getValue() {
		return rank.getValue();
	}

	/**
	 * Returns a formatted string with the rank and suit of the PlayingCard.
	 *
	 * @return the rank and suit of the card, as a String
	 */
	@Override
	public String toString() {
		return rank.getName() + " of " + suit.getSuit();
	}

}
