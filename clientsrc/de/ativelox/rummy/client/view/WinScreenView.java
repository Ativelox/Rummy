/**
 * 
 */
package de.ativelox.rummy.client.view;

import java.awt.Graphics;

import de.ativelox.rummy.client.controller.RenderManager;

/**
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public class WinScreenView extends AView {

	/**
	 * 
	 */
	public WinScreenView(String title, int width, int height, RenderManager manager) {
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
