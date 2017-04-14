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
	 * Adds a card to your own hand.
	 * 
	 * @param mCard
	 *            The card to be added to your hand.
	 */
	public void addCard(Card mCard);

	/**
	 * Gets a card from the hand by a specific ID set when generating a card.
	 * 
	 * @param mID
	 *            The ID of the card object.
	 * 
	 * @return the card associated with the ID.
	 */
	public Card getCardById(int mID);

	/**
	 * Gets all the cards currently held.
	 * 
	 * @return the cards.
	 */
	public LinkedList<Card> getCards();

	/**
	 * Removes a card from your own hand.
	 * 
	 * @param mIndex
	 *            The card to be removed from your hand.
	 */
	public void removeCard(int mIndex);

	/**
	 * Removes a card by its ID.
	 * 
	 * @param mID
	 *            the id of the card to be removed
	 */
	public void removeCardByID(int mID);

	/**
	 * Sets your own hand with cards.
	 * 
	 * @param mCards
	 *            The cards which should be set as the new hand.
	 */
	public void setHand(LinkedList<Card> mCards);

}
