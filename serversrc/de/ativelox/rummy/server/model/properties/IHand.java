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
	 * the width of ones own hand view.
	 */
	public static final int HAND_WIDTH = 900;

	/**
	 * The x offset of ones own hand view.
	 */
	public static final int X_OFFSET = 150;

	/**
	 * the y offset of ones own hand view.
	 */
	public static final int Y_OFFSET = 600;

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

}
