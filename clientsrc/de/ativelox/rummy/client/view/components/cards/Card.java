/**
 * 
 */
package de.ativelox.rummy.client.view.components.cards;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import de.ativelox.rummy.client.assets.Assets;
import de.ativelox.rummy.client.view.components.AGUIElement;
import de.ativelox.rummy.client.view.properties.IRenderable;
import de.ativelox.rummy.properties.CardProperties;
import de.ativelox.rummy.properties.ECardIdentifier;
import de.ativelox.rummy.properties.ECardType;

/**
 * A Card object, every card has a identifier and a type. For example:<br>
 * identifier: TWO<br>
 * type: DIAMONDS
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public class Card extends AGUIElement implements ICard {

	private BufferedImage cardImage;

	private int ID;

	/**
	 * The identifier of this Card object.
	 */
	private ECardIdentifier identifier;

	/**
	 * The layer of this card object.
	 */
	private int layer;

	/**
	 * The type of this Card object.
	 */
	private ECardType type;

	/**
	 * Initiates a new Card instance. The initial position on the component is
	 * (0, 0). The Position has to be set by setPosition in GUIElement.
	 * 
	 * @param mIdentifier
	 *            The identifier of this card.
	 * @param mType
	 *            The type of this card.
	 */
	public Card(ECardIdentifier mIdentifier, ECardType mType, int mID) {
		this(mIdentifier, mType, mID, 0, 0);
	}

	/**
	 * Initiates a new Card instance.
	 * 
	 * @param mIdentifier
	 *            The identifier of this card.
	 * @param mType
	 *            The type of this card.
	 * @param mX
	 *            The x coordinate of this card.
	 * @param mY
	 *            The y coordinate of this card.
	 */
	public Card(ECardIdentifier mIdentifier, ECardType mType, int mID, int mX, int mY) {
		super(mX, mY, CardProperties.SCALED_CARD_WIDTH, CardProperties.SCALED_CARD_HEIGHT);

		ID = mID;
		identifier = mIdentifier;
		type = mType;

		cardImage = getCorrespondingImage();

		layer = IRenderable.BACKGROUND_LAYER;
	}

	/**
	 * DeHighlights this card, meaning that it gets put back to its default
	 * layer.
	 */
	public void deHighlight() {
		layer = IRenderable.DEFAULT_LAYER;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.rummy.client.view.components.cards.ICard#getBounds()
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public int getID() {
		return ID;
	}

	/**
	 * Gets the current identifier of this card.
	 * 
	 * @return CardIdentifier: the current identifier of this card.
	 */
	public ECardIdentifier getIdentifier() {
		return identifier;
	}

	@Override
	public int getLayer() {
		return layer;
	}

	/**
	 * Gets the current type of this card.
	 * 
	 * @return CardType: the current type of this card.
	 */
	public ECardType getType() {
		return type;
	}

	/**
	 * Highlights this card by putting it to the front layer.
	 */
	public void highlight() {
		layer = IRenderable.HUD_LAYER;
	}

	@Override
	public void render(final Graphics g) {
		g.drawImage(cardImage, x, y, width, height, null);
	}

	public void setID(int mID) {
		ID = mID;
	}

	/**
	 * Gets the correct Image corresponding to the card created. Throws a
	 * NullPointerException if no image could be associated with the identifier
	 * and type of this card.
	 * 
	 * @return Optional&ltBufferedImage&gt: The BufferedImage for the card
	 *         initialized.
	 */
	private BufferedImage getCorrespondingImage() {

		BufferedImage[] cardImages = null;

		if (type == ECardType.DIAMONDS) {
			cardImages = Assets.DIAMONDS;

		} else if (type == ECardType.CLUBS) {
			cardImages = Assets.CLUBS;

		} else if (type == ECardType.HEART) {
			cardImages = Assets.HEARTS;

		} else if (type == ECardType.SPADE) {
			cardImages = Assets.SPADES;

		} else if (type == ECardType.JOKERS) {
			cardImages = Assets.JOKERS;

		}

		BufferedImage image = null;

		if (identifier == ECardIdentifier.TWO) {
			image = cardImages[0];

		} else if (identifier == ECardIdentifier.THREE) {
			image = cardImages[1];

		} else if (identifier == ECardIdentifier.FOUR) {
			image = cardImages[2];

		} else if (identifier == ECardIdentifier.FIVE) {
			image = cardImages[3];

		} else if (identifier == ECardIdentifier.SIX) {
			image = cardImages[4];

		} else if (identifier == ECardIdentifier.SEVEN) {
			image = cardImages[5];

		} else if (identifier == ECardIdentifier.EIGHT) {
			image = cardImages[6];

		} else if (identifier == ECardIdentifier.NINE) {
			image = cardImages[7];

		} else if (identifier == ECardIdentifier.TEN) {
			image = cardImages[8];

		} else if (identifier == ECardIdentifier.PRINCE) {
			image = cardImages[9];

		} else if (identifier == ECardIdentifier.QUEEN) {
			image = cardImages[10];

		} else if (identifier == ECardIdentifier.KING) {
			image = cardImages[11];

		} else if (identifier == ECardIdentifier.ACE) {
			image = cardImages[12];

		} else if (identifier == ECardIdentifier.RED_JOKER) {
			image = cardImages[0];

		} else if (identifier == ECardIdentifier.BLACK_JOKER) {
			image = cardImages[1];

		} else {
			throw new AssertionError();
		}

		return image;

	}

}
