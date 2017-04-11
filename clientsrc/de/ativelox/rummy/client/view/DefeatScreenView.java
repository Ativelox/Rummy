/**
 * 
 */
package de.ativelox.rummy.client.view;

import java.awt.Graphics;

import de.ativelox.rummy.client.controller.RenderManager;

/**
 * The view of the client when you lost the game.
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public class DefeatScreenView extends AView {

	/**
	 * 
	 */
	public DefeatScreenView(String title, int width, int height, RenderManager manager) {
		super(title, width, height, manager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.rummy.client.view.properties.IRenderable#getLayer()
	 */
	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.rummy.client.view.View#initialize()
	 */
	@Override
	public void initialize() {
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub

	}

}
