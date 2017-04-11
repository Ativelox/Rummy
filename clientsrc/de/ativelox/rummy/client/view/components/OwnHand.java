/**
 * 
 */
package de.ativelox.rummy.client.view.components;

import de.ativelox.rummy.client.controller.RenderManager;

/**
 * The GUI of your own hand to be drawn to the view.
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public class OwnHand extends AHand {

	/**
	 * Initiates a new OwnHand instance view.
	 * 
	 * @param mManager
	 *            The render manager maintaining the render order.
	 */
	public OwnHand(RenderManager mManager) {
		super(mManager);
	}

}
