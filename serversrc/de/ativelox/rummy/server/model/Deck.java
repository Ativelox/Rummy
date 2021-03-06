/**
 * 
 */
package de.ativelox.rummy.server.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Stack;

import de.ativelox.rummy.properties.ECardIdentifier;
import de.ativelox.rummy.properties.ECardType;

/**
 * A logic Deck holding all the information needed to maintain a deck.
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public class Deck {

	/**
	 * The number of cards one receives every game start.
	 */
	private static final int HAND_SIZE = 20;

	/**
	 * The stack of cards representing this deck.
	 */
	private Stack<Card> deck;

	private int IDcounter;

	/**
	 * Initializes a new Deck instance.
	 */
	public Deck() {
		deck = new Stack<>();
		IDcounter = 0;

	}

	/**
	 * Generates a new Deck holding every Card contained in this games card set
	 * and shuffles it.
	 */
	public void generateNewDeck() {

		for (int j = 0; j < 3; j++) {

			for (ECardType type : ECardType.values()) {
				if (type.ordinal() == ECardType.JOKERS.ordinal()) {
					continue;
				}

				for (ECardIdentifier identifier : ECardIdentifier.values()) {
					if ((identifier.ordinal() == ECardIdentifier.BLACK_JOKER.ordinal())
							|| (identifier.ordinal() == ECardIdentifier.RED_JOKER.ordinal())) {
						continue;
					}

					deck.push(new Card(identifier, type, IDcounter));
					IDcounter++;
				}

			}

			deck.push(new Card(ECardIdentifier.BLACK_JOKER, ECardType.JOKERS, IDcounter));
			IDcounter++;
			deck.push(new Card(ECardIdentifier.RED_JOKER, ECardType.JOKERS, IDcounter));
			IDcounter++;

		}

		Collections.shuffle(deck);
	}

	/**
	 * Gets the decks card stack.
	 * 
	 * @return This decks card stack.
	 */
	public Stack<Card> getCardStack() {
		return deck;
	}

	/**
	 * Gets all the cards needed for a new hand per game start off the deck.
	 * 
	 * @return all the cards needed for a new hand per game start off the deck.
	 */
	public LinkedList<Card> getNewHand() {
		LinkedList<Card> cards = new LinkedList<>();

		for (int i = 0; i < HAND_SIZE; i++) {
			cards.add(deck.pop());

		}

		return cards;
	}

	/**
	 * Gets the top card of this decks stack.
	 * 
	 * @return The top card.
	 */
	public Optional<Card> getTopCard() {
		if(deck.isEmpty()){
			return Optional.empty();
		}else{
			return Optional.of(deck.pop());
		}
	}

}
