/**
 * 
 */
package de.ativelox.rummy.client.view;

import java.awt.Graphics;

import de.ativelox.rummy.client.assets.Assets;
import de.ativelox.rummy.client.controller.RenderManager;
import de.ativelox.rummy.client.view.properties.IRenderable;

/**
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public class TitleScreenView extends AView {

	/**
	 * 
	 */
	public TitleScreenView(String title, int width, int height, RenderManager manager) {
		super(title, width, height, manager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.rummy.client.view.properties.IRenderable#getLayer()
	 */
	@Override
	public int getLayer() {
		return IRenderable.BACKGROUND_LAYER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.rummy.client.view.View#initialize()
	 */
	@Override
	public void initialize() {

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
		g.drawImage(Assets.BLACK_DECK, x, y, width, height, null);

	}
}
