/**
 * 
 */
package de.ativelox.rummy.client.view.components;

import java.awt.Point;
import java.util.LinkedList;

import de.ativelox.rummy.client.controller.RenderManager;
import de.ativelox.rummy.client.view.components.cards.Card;
import de.ativelox.rummy.server.model.properties.IHand;

/**
 * The GUI of your own hand to be drawn to the view.
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public class OwnHand {

	/**
	 * A list holding all the Card views for yourself.
	 */
	private LinkedList<Card> cards;

	/**
	 * The render manager maintaining the render order.
	 */
	private RenderManager manager;

	/**
	 * Initiates a new OwnHand instance view.
	 * 
	 * @param mManager
	 *            The render manager maintaining the render order.
	 */
	public OwnHand(RenderManager mManager) {
		cards = new LinkedList<>();
		manager = mManager;
	}

	/**
	 * Adds the card to the hand by index.
	 * 
	 * @param mIndex
	 *            The index to set the card at.
	 * 
	 * @param mCard
	 *            The card to be set at the index.
	 */
	public void addCardByIndex(int mIndex, Card mCard) {
		cards.add(mIndex, mCard);

		// TODO: readjust view.

	}

	/**
	 * Gets the card by the position as well as removing and returning it.
	 * 
	 * @param mX
	 *            The x coordinate of the point.
	 * @param mY
	 *            The y coordinate of the point.
	 * @return The Card if one was found at the given position, <b>null</b>
	 *         otherwise.
	 */
	public Card getAndRemoveCardByPosition(int mX, int mY) {
		return removeCard(getCardByPosition(mX, mY));

	}

	/**
	 * Gets the card by an index.
	 * 
	 * @param mIndex
	 *            The index of the card.
	 * 
	 * @return The card specified by the index.
	 */
	public Card getCardByIndex(int mIndex) {
		return cards.get(mIndex);

	}

	/**
	 * Gets the card by the position as well as returning it.
	 * 
	 * @param mX
	 *            The x coordinate of the point.
	 * @param mY
	 *            The y coordinate of the point.
	 * @return The Card if one was found at the given position, <b>null</b>
	 *         otherwise.
	 */
	public Card getCardByPosition(int mX, int mY) {

		int index;
		Point mousePosition = new Point(mX, mY);

		for (index = 0; index < cards.size(); index++) {
			if (cards.get(index).getBounds().contains(mousePosition)) {
				break;
			}
		}

		if (index + 1 == cards.size()) {
			return getCardByIndex(index);

		} else if (index + 1 > cards.size()) {
			return null;

		} else {
			while (index + 1 < cards.size()) {
				if (cards.get(index + 1).getBounds().contains(mousePosition)) {
					index++;
					continue;

				} else {
					return getCardByIndex(index);
				}

			}
		}
		return null;

	}

	/**
	 * Removes a card from the hand.
	 * 
	 * @param mCard
	 *            The card to be removed.
	 * 
	 * @return The card which got removed.
	 */
	public Card removeCard(Card mCard) {
		if (mCard == null) {
			return null;
		}

		Card card = cards.get(cards.indexOf(mCard));
		cards.remove(card);

		if (cards.size() <= 0) {
			return card;
		}

		int internalOffset = IHand.HAND_WIDTH / cards.size();

		for (int i = 0; i < cards.size(); i++) {
			Card tempCard = cards.get(i);
			tempCard.setPosition(new Point(IHand.X_OFFSET + i * internalOffset, IHand.Y_OFFSET));
		}

		return card;

	}

	/**
	 * Removes a card from the hand by index.
	 * 
	 * @param mIndex
	 *            The index the card is at.
	 * 
	 * @return The Card removed by the index.
	 */
	public Card removeCardByIndex(int mIndex) {
		Card card = cards.remove(mIndex);

		int internalOffset = IHand.HAND_WIDTH / cards.size();

		for (int i = 0; i < cards.size(); i++) {
			cards.get(i).setPosition(new Point(IHand.X_OFFSET + i * internalOffset, IHand.Y_OFFSET));
		}

		return card;
	}

	/**
	 * Sets the hand for your own hands view.
	 * 
	 * @param mCards
	 *            The cards to be set as your new hand.
	 */
	public void setHand(LinkedList<Card> mCards) {

		int internalOffset = IHand.HAND_WIDTH / mCards.size();

		for (int i = 0; i < mCards.size(); i++) {
			Card card = mCards.get(i);
			card.setPosition(new Point(IHand.X_OFFSET + i * internalOffset, IHand.Y_OFFSET));
			manager.addElementToRender(card);
			cards.add(card);
		}
	}
}
