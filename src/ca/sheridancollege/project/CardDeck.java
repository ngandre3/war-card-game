package ca.sheridancollege.project;

/**
 * The CardDeck class models the full 52-card deck, from which cards are dealt to players.
 * <p>
 * This class acts as a Model in the MVC pattern. This class follows the Singleton Pattern.
 *
 * @author (Andrew) Yin Tung Ng , Aug 2020
 */
public class CardDeck extends GroupOfCards {

	private boolean hasCards = false;		// toggle variable to only fill the deck once

	/**
	 * No-arg constructor.
	 */
	public CardDeck() {
		super(0);
		init();
		shuffle();
	}

	/**
	 * Fills in the empty CardDeck ArrayList with all 52 PlayingCards.
	 */
	private void init() {
		
		if (!hasCards) {

			for (int i = 0; i < Suit.values().length; i++) {

				for (int j = 0; j < Rank.values().length; j++) {

					// get the Rank and Suit from the current loop iteration
					Rank newRank = Rank.values()[j];
					Suit newSuit = Suit.values()[i];

					// create the new PlayingCard from the new values
					PlayingCard card = new PlayingCard(newRank, newSuit);

					// add the new PlayingCard to the CardDeck arraylist
					this.addCard(card);
				}
			}
		}

		// now cannot fill the deck with new cards again, because of the while condition
		hasCards = true;
	}

}
