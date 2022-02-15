/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you might want to subclass this more than
 * once. The group of cards has a maximum size attribute which is flexible for reuse.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 * @author (Andrew) Yin Tung Ng , Aug 2020
 */
public class GroupOfCards {

	//The group of cards, stored in an ArrayList
	private ArrayList<Card> cards = new ArrayList();	// initialized the ArrayList
	private int size;//the size of the grouping

	/**
	 * The constructor for a GroupOfCards.
	 *
	 * @param size the size of the GroupOfCards object, not its arraylist size
	 */
	public GroupOfCards(int size) {
		this.size = size;
	}

	/**
	 * A method that will get the group of cards as an ArrayList
	 *
	 * @return the group of cards.
	 */
	public ArrayList<Card> getCards() {
		return cards;
	}

	/**
	 * Shuffles the Cards in the cards ArrayList
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}

	/**
	 * @return the size of the group of cards
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the max size for the group of cards
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Returns the single Card at the index in the ArrayList
	 *
	 * @param index the integer index the Card is at in the list
	 * @return the Card object at the index
	 */
	public Card getCard(int index) {
		return cards.get(index);
	}

	/**
	 * Adds a Card to the end of the GroupOfCards ArrayList. The size data field increases by 1 after adding the Card.
	 *
	 * @param card the Card object to be added to the GroupOfCards
	 */
	public void addCard(Card card) {
		cards.add(card);				// adds the Card to ArrayList
		size += 1;					// increment the GroupOfCards size
	}

	/**
	 * Returns the first card in the GroupOfCards ArrayList
	 *
	 * @return the first Card object in the ArrayList
	 */
	public Card getFirstCard() {
		return cards.get(0);
	}

	/**
	 * Returns the first Card in the GroupOfCards, that will be removed from the ArrayList. The size data field
	 * decreases by 1 after removing the Card.
	 *
	 * @return the first card object, that will be removed
	 */
	public Card drawCard() {

		if (size > 0) {					// check if any cards left
			Card temp = getFirstCard();	// temp variable
			cards.remove(0);				// remove the first Card from list
			this.size -= 1;				// decrease the size counter, since 1 card removed
			return temp;
		} else {
			return null;
		}
	}

	/**
	 * Removes all Cards from the GroupOfCards, and resets the size counter to 0.
	 */
	public void clear() {
		this.getCards().clear();
		setSize(0);
	}
}//end class
