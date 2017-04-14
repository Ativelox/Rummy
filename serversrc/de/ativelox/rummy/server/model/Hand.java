/**
 * 
 */
package de.ativelox.rummy.server.model;

import java.util.LinkedList;

import de.ativelox.rummy.server.model.properties.IHand;

/**
 * A logic Hand object holding all the information needed for a hand.
 * 
 * @author Ativelox <juliantischner27@web.de>
 */
public class Hand implements IHand {

	/**
	 * Contains all the cards in ones hand.
	 */
	LinkedList<Card> cards;

	/**
	 * Initiates a new Hand instance.
	 */
	public Hand() {
		cards = new LinkedList<>();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.ativelox.rummy.server.model.properties.IHand#addCard(de.ativelox.rummy
	 * .server.model.Card)
	 */
	@Override
	public void addCard(Card mCard) {
		cards.add(mCard);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.rummy.server.model.properties.IHand#getCardById(int)
	 */
	@Override
	public Card getCardById(int mID) {
		for (Card card : cards) {
			if (card.getID() == mID) {
				return card;
			}
		}

		return null;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.rummy.server.model.properties.IHand#getCards()
	 */
	@Override
	public LinkedList<Card> getCards() {
		return cards;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.rummy.server.model.properties.IHand#removeCard(int)
	 */
	@Override
	public void removeCard(int mIndex) {
		cards.remove(mIndex);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.ativelox.rummy.server.model.properties.IHand#removeCardByProperties(
	 * int, int)
	 */
	@Override
	public void removeCardByID(int mID) {

		for (int i = 0; i < cards.size(); i++) {
			Card card = cards.get(i);
			int id = card.getID();

			if (id == mID) {
				cards.remove(i);
				break;
			}

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.rummy.server.model.properties.IHand#setHand(java.util.
	 * LinkedList)
	 */
	@Override
	public void setHand(LinkedList<Card> mCards) {

		for (int i = 0; i < mCards.size(); i++) {
			Card card = mCards.get(i);
			cards.add(card);
		}

	}
}
