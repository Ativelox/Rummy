/**
 * 
 */
package de.ativelox.rummy.client.view.components.cards;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Optional;

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
public class Card extends AGUIElement implements ICard{

	/**
	 * The identifier of this Card object.
	 */
	private int identifier;

	/**
	 * The layer of this card object.
	 */
	private int layer;

	/**
	 * The type of this Card object.
	 */
	private int type;
	
	private int ID;

	/**
	 * Initiates a new Card instance. The initial position on the component is
	 * (0, 0). The Position has to be set by setPosition in GUIElement.
	 * 
	 * @param mIdentifier
	 *            The identifier of this card.
	 * @param mType
	 *            The type of this card.
	 */
	public Card(int mIdentifier, int mType, int mID) {
		super(0, 0, CardProperties.SCALED_CARD_WIDTH, CardProperties.SCALED_CARD_HEIGHT);

		ID = mID;
		identifier = mIdentifier;
		type = mType;

		layer = IRenderable.BACKGROUND_LAYER;
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
	public Card(int mIdentifier, int mType, int mID, int mX, int mY) {
		super(mX, mY, CardProperties.SCALED_CARD_WIDTH, CardProperties.SCALED_CARD_HEIGHT);

		ID = mID;
		identifier = mIdentifier;
		type = mType;
	}

	/**
	 * DeHighlights this card, meaning that it gets put back to its default
	 * layer.
	 */
	public void deHighlight() {
		layer = IRenderable.DEFAULT_LAYER;

	}

	/**
	 * Gets the current identifier of this card.
	 * 
	 * @return CardIdentifier: the current identifier of this card.
	 */
	public int getIdentifier() {
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
	public int getType() {
		return type;
	}

	/**
	 * Highlights this card by putting it to the front layer.
	 */
	public void highlight() {
		layer = IRenderable.HUD_LAYER;
	}
	
	public void setID(int mID){
		ID = mID;
	}
	
	public int getID(){
		return ID;
	}

	@Override
	public void render(final Graphics g) {
		g.drawImage(getCorrespondingImage().get(), x, y, width, height, null);
	}

	/**
	 * Gets the correct Image corresponding to the card created. Throws a
	 * NullPointerException if no image could be associated with the identifier
	 * and type of this card.
	 * 
	 * @return Optional&ltBufferedImage&gt: The BufferedImage for the card
	 *         initialized.
	 */
	private Optional<BufferedImage> getCorrespondingImage() {

		BufferedImage[] cardImages = null;

		if (type == ECardType.DIAMONDS.ordinal()) {
			cardImages = Assets.DIAMONDS;

		} else if (type == ECardType.CLUBS.ordinal()) {
			cardImages = Assets.CLUBS;

		} else if (type == ECardType.HEART.ordinal()) {
			cardImages = Assets.HEARTS;

		} else if (type == ECardType.SPADE.ordinal()) {
			cardImages = Assets.SPADES;

		} else if (type == ECardType.JOKERS.ordinal()) {
			cardImages = Assets.JOKERS;

		}

		Optional<BufferedImage> image = null;

		if (identifier == ECardIdentifier.TWO.ordinal()) {
			image = Optional.of(cardImages[0]);

		} else if (identifier == ECardIdentifier.THREE.ordinal()) {
			image = Optional.of(cardImages[1]);

		} else if (identifier == ECardIdentifier.FOUR.ordinal()) {
			image = Optional.of(cardImages[2]);

		} else if (identifier == ECardIdentifier.FIVE.ordinal()) {
			image = Optional.of(cardImages[3]);

		} else if (identifier == ECardIdentifier.SIX.ordinal()) {
			image = Optional.of(cardImages[4]);

		} else if (identifier == ECardIdentifier.SEVEN.ordinal()) {
			image = Optional.of(cardImages[5]);

		} else if (identifier == ECardIdentifier.EIGHT.ordinal()) {
			image = Optional.of(cardImages[6]);

		} else if (identifier == ECardIdentifier.NINE.ordinal()) {
			image = Optional.of(cardImages[7]);

		} else if (identifier == ECardIdentifier.TEN.ordinal()) {
			image = Optional.of(cardImages[8]);

		} else if (identifier == ECardIdentifier.PRINCE.ordinal()) {
			image = Optional.of(cardImages[9]);

		} else if (identifier == ECardIdentifier.QUEEN.ordinal()) {
			image = Optional.of(cardImages[10]);

		} else if (identifier == ECardIdentifier.KING.ordinal()) {
			image = Optional.of(cardImages[11]);

		} else if (identifier == ECardIdentifier.ACE.ordinal()) {
			image = Optional.of(cardImages[12]);

		} else if (identifier == ECardIdentifier.RED_JOKER.ordinal()) {
			image = Optional.of(cardImages[0]);

		} else if (identifier == ECardIdentifier.BLACK_JOKER.ordinal()) {
			image = Optional.of(cardImages[1]);

		} else {
			image = Optional.of(null);
		}

		return image;

	}

	/* (non-Javadoc)
	 * @see de.ativelox.rummy.client.view.components.cards.ICard#getBounds()
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

}
