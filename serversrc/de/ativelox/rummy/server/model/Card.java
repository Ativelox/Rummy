/**
 * 
 */
package de.ativelox.rummy.server.model;

import de.ativelox.rummy.properties.ECardIdentifier;
import de.ativelox.rummy.properties.ECardType;

/**
 * The logic Card element holding every information needed for a card.
 * 
 * @author Ativelox <juliantischner27@web.de>
 */
public class Card {

	/**
	 * The identifier of this card.
	 */
	private ECardIdentifier identifier;

	/**
	 * The type of this card.
	 */
	private ECardType type;

	/**
	 * Initiates a new Card instance. Every card has to have an identifier and a
	 * type.
	 * 
	 * @param mIdentifier
	 *            The identifier for this card.
	 * @param mType
	 *            The type for this card.
	 */
	public Card(ECardIdentifier mIdentifier, ECardType mType) {

		type = mType;
		identifier = mIdentifier;

	}

	/**
	 * Gets the current identifier of this card.
	 * 
	 * @return The current identifier of this card.
	 */
	public ECardIdentifier getIdentifier() {
		return identifier;
	}

	/**
	 * Gets the current type of this card.
	 * 
	 * @return The current type of this card.
	 */
	public ECardType getType() {
		return type;

	}

}
