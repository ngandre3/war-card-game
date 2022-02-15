package ca.sheridancollege.project;

/**
 * The CardPile class models the pile where players place and compare 
 * their drawn cards. It adds all its Cards to the winner's Stack.
 * <p>
 * This class acts as a Model in the MVC pattern. It follows the singleton pattern.
 * 
 * @author (Andrew) Yin Tung Ng , Aug 2020
 */
public class CardPile extends GroupOfCards {

	private static CardPile onlyOneCardPile = null;
	
	private CardPile(int size) {
		super(size);
	}

	/**
	 * Returns a singleton instance of a CardPile.
	 * 
	 * @return the single instance of a CardPile
	 */
	public static CardPile getInstance() {
		
		if (onlyOneCardPile == null) {
			onlyOneCardPile = new CardPile(0);
		}
		
		return onlyOneCardPile;
	}
	
	/**
	 * Adds all the Cards in the CardPile to the turn winner's Stack.
	 * 
	 * @param winner the winning WarPlayer of the turn
	 */
	public void addCardsToWinner(WarPlayer winner) {
		
		for (Card card : this.getCards()) {		// add all the cards in the Pile to the Winner's stack
			winner.getStack().addCard(card);
		}
		
		clear();					// empty the CardPile of any Card objects
	}
}
