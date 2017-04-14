/**
 * 
 */
package de.ativelox.rummy.client.view.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import de.ativelox.rummy.client.controller.RenderManager;
import de.ativelox.rummy.client.view.properties.IRenderable;

/**
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public class Button extends AGUIElement {

	private final static int x = 550;

	public Button(int mX, int mY, int mWidth, int mHeight, RenderManager mManager) {
		super(mX, mY, mWidth, mHeight);

		mManager.addElementToRender(this);
	}

	public Button(RenderManager mManager) {
		this(x, 725, 1200 - (2 * x), 30, mManager);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.rummy.client.view.properties.IRenderable#getLayer()
	 */
	@Override
	public int getLayer() {
		return IRenderable.HUD_LAYER;
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
		g.setColor(new Color(0, 0, 0, 155));
		g.fillRect(x, y, width, height);
		g.setColor(Color.white);
		g.drawRect(x - 2, y - 2, width + 2, height + 2);
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 20));
		g.drawString("Score", x + 23, y + 23);

	}

}
