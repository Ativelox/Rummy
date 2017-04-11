/**
 * 
 */
package de.ativelox.rummy.server.model.properties;

import java.util.LinkedList;

import de.ativelox.rummy.server.model.Card;

/**
 * An interface containing the information needed for every hand object.
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public interface IHand {
	
	/**
	 * Gets all the cards currently held.
	 * 
	 * @return
	 * 		the cards.
	 */
	public LinkedList<Card> getCards();

	/**
	 * Adds a card to your own hand.
	 * 
	 * @param mCard
	 *            The card to be added to your hand.
	 */
	public void addCard(Card mCard);

	/**
	 * Removes a card from your own hand.
	 * 
	 * @param mIndex
	 *            The card to be removed from your hand.
	 */
	public void removeCard(int mIndex);

	/**
	 * Sets your own hand with cards.
	 * 
	 * @param mCards
	 *            The cards which should be set as the new hand.
	 */
	public void setHand(LinkedList<Card> mCards);

	/**
	 * Removes a card by its ID.
	 * 
	 * @param mID
	 * 			the id of the card to be removed
	 */
	public void removeCardByID(int mID);

}
