/**
 * 
 */
package de.ativelox.rummy.server.model;

import java.util.LinkedList;
import java.util.Stack;

/**
 * The logic CardGraveyard holding all the information needed to maintain a card
 * graveyard.
 * 
 * @author Ativelox <juliantischner27@web.de>
 */
public class CardGraveyard {

	/**
	 * The stack of cards representing this card graveyard.
	 */
	Stack<Card> cards;

	/**
	 * Initiates a new CardGraveyard instance.
	 */
	public CardGraveyard() {
		cards = new Stack<>();
	}

	/**
	 * Adds a card to this card graveyard.
	 * 
	 * @param mCard
	 *            The card to be pushed onto this stack.
	 */
	public void addCard(Card mCard) {
		cards.push(mCard);

	}

	/**
	 * Gets all the cards on the card graveyard.
	 * 
	 * @return Every card on the card graveyard.
	 */
	public Stack<Card> getAllCards() {
		return cards;
	}

	/**
	 * Gets a set amount of cards from the top of this card graveyard.
	 * 
	 * @param quantity
	 *            The amount of cards to get from the top of this card
	 *            graveyard.
	 * 
	 * @return All the cards determined by the quantity.
	 */
	public LinkedList<Card> getCards(int quantity) {
		LinkedList<Card> cardsToGet = new LinkedList<>();

		for (int i = 0; i < quantity; i++) {
			cardsToGet.addLast(cards.pop());
		}

		return cardsToGet;
	}

}
