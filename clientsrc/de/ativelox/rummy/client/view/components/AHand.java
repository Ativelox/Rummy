/**
 * 
 */
package de.ativelox.rummy.client.view.components;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.Comparator;
import java.util.LinkedList;

import de.ativelox.rummy.client.controller.LayerComparator;
import de.ativelox.rummy.client.controller.RenderManager;
import de.ativelox.rummy.client.view.components.cards.Card;
import de.ativelox.rummy.client.view.components.cards.ICard;
import de.ativelox.rummy.client.view.properties.IRenderable;
import de.ativelox.rummy.properties.CardProperties;

/**
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public abstract class AHand {

	public static final int HAND_WIDTH = 900;

	public static final int OPPONENT_Y_OFFSET = 50;

	public static final int OWN_Y_OFFSET = 600;

	public static final int X_OFFSET = 150;

	private Rectangle bounds;

	private Comparator<IRenderable> layerComparator;

	/**
	 * A list holding all the Card views for yourself.
	 */
	protected LinkedList<ICard> cards;

	/**
	 * The render manager maintaining the render order.
	 */
	protected RenderManager manager;

	/**
	 * Initiates a new Hand instance view.
	 * 
	 * @param mManager
	 *            The render manager maintaining the render order.
	 */
	public AHand(RenderManager mManager) {
		cards = new LinkedList<>();
		manager = mManager;

		bounds = new Rectangle(AHand.X_OFFSET, AHand.OWN_Y_OFFSET, AHand.HAND_WIDTH, CardProperties.SCALED_CARD_HEIGHT);
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
	public void addCardByIndex(int mIndex, ICard mCard) {
		cards.add(mIndex, mCard);

		adjustView(cards);

	}

	public boolean addCardByPosition(Card card) {
		if (!bounds.intersects(card.getBounds())) {
			return false;
		}

		layerComparator = new LayerComparator();

		int i;
		for (i = 0; i < cards.size(); i++) {
			Card tempCard = (Card) cards.get(i);
			int result = layerComparator.compare(tempCard, card);

			if (result == 1) {
				i -= 1;
				break;
			}
		}

		if (i + 1 < cards.size() + 1) {
			cards.add(i + 1, card);

		} else {
			cards.add(i, card);

		}

		adjustView(cards);

		return true;

	}

	/**
	 * Adjusts the view for the hand object by calculating the new position for
	 * every card when changes are made to the size of the linked list
	 * containing the cards.
	 * 
	 * @param cards
	 *            The linked list containing the cards.
	 */
	public void adjustView(LinkedList<ICard> cards) {
		if (cards.size() <= 0) {
			return;
		}

		int internalOffset = AHand.HAND_WIDTH / cards.size();

		for (int i = 0; i < cards.size(); i++) {
			if (this instanceof OwnHand) {
				cards.get(i).setPosition(new Point(AHand.X_OFFSET + i * internalOffset, AHand.OWN_Y_OFFSET));

			} else if (this instanceof OpponentHand) {
				cards.get(i).setPosition(new Point(AHand.X_OFFSET + i * internalOffset, AHand.OPPONENT_Y_OFFSET));

			}
		}
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
	public ICard getAndRemoveCardByPosition(int mX, int mY) {
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
	public ICard getCardByIndex(int mIndex) {
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
	public ICard getCardByPosition(int mX, int mY) {

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
	public ICard removeCard(ICard mCard) {
		if (mCard == null) {
			return null;
		}

		ICard card = cards.get(cards.indexOf(mCard));
		cards.remove(card);

		if (cards.size() <= 0) {
			return card;
		}

		adjustView(cards);

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
	public ICard removeCardByIndex(int mIndex) {
		ICard card = cards.remove(mIndex);

		adjustView(cards);

		return card;
	}

	/**
	 * Sets the hand for your own hands view.
	 * 
	 * @param mCards
	 *            The cards to be set as your new hand.
	 */
	public void setHand(LinkedList<Card> mCards) {

		int internalOffset = AHand.HAND_WIDTH / mCards.size();

		for (int i = 0; i < mCards.size(); i++) {
			Card card = mCards.get(i);
			card.setPosition(new Point(AHand.X_OFFSET + i * internalOffset, AHand.OWN_Y_OFFSET));
			manager.addElementToRender(card);
			cards.add(card);
		}
	}

}
