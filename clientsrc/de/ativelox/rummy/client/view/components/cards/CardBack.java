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

/**
 * A CardBack GUI element.
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public class CardBack extends AGUIElement implements ICard {

	/**
	 * An image used to draw the card back onto the view.
	 */
	private BufferedImage image;

	/**
	 * Initiates a new CardBack instance. The initial position is set at (0, 0),
	 * the Position has to be set by using setLocation in GUIElement.
	 */
	public CardBack() {
		super(0, 0, CardProperties.SCALED_CARD_WIDTH, CardProperties.SCALED_CARD_HEIGHT);

	}

	/**
	 * Initiates a new CardBack instance. The initial position is set at (0, 0),
	 * the Position has to be set by using setLocation in GUIElement.
	 * 
	 * @param mImage
	 *            The image to be drawn as card back.
	 */
	public CardBack(final BufferedImage mImage) {
		super(0, 0, CardProperties.SCALED_CARD_WIDTH, CardProperties.SCALED_CARD_HEIGHT);

		image = mImage;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.rummy.client.view.properties.IRenderable#getLayer()
	 */
	@Override
	public int getLayer() {
		return IRenderable.DEFAULT_LAYER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.ativelox.rummy.client.view.properties.IRenderable#render(java.awt.
	 * Graphics)
	 */
	@Override
	public void render(Graphics g) {
		if (image == null) {
			g.drawImage(Assets.BLACK_DECK, x, y, width, height, null);
		} else {
			g.drawImage(image, x, y, width, height, null);
		}
	}

}
