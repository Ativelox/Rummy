/**
 * 
 */
package de.ativelox.rummy.client.view.components;

import java.awt.Graphics;

import de.ativelox.rummy.client.assets.Assets;
import de.ativelox.rummy.client.controller.RenderManager;
import de.ativelox.rummy.client.view.properties.IRenderable;
import de.ativelox.rummy.properties.CardProperties;

/**
 * The GUI Element of a Deck.
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public class Deck extends AGUIElement {

	/**
	 * The render manager maintaining the render order.
	 */
	private RenderManager manager;

	/**
	 * Initiates a new Deck GUI Element.
	 * 
	 * @param mManager
	 *            The render manager maintaining the render order.
	 */
	public Deck(RenderManager mManager) {
		super(50, 50, CardProperties.SCALED_CARD_WIDTH, CardProperties.SCALED_CARD_HEIGHT);

		manager = mManager;

		manager.addElementToRender(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.jolly.client.view.properties.IRenderable#getLayer()
	 */
	@Override
	public int getLayer() {
		return IRenderable.DEFAULT_LAYER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.ativelox.jolly.client.view.properties.IRenderable#render(java.awt.
	 * Graphics)
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.BLACK_DECK, x, y, width, height, null);
	}

}
