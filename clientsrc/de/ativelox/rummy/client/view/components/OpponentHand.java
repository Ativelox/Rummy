/**
 * 
 */
package de.ativelox.rummy.client.view.components;

import java.util.LinkedList;

import de.ativelox.rummy.client.controller.RenderManager;
import de.ativelox.rummy.client.view.components.cards.CardBack;

/**
 * The GUI of the opponents hand to be drawn to the view.
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public class OpponentHand {

	/**
	 * A list containing every card back to be displayed as the opponents hand.
	 */
	private LinkedList<CardBack> cards;

	/**
	 * The render manager maintaining the render order.
	 */
	private RenderManager manager;

	/**
	 * Initiates a new OpponentHand instance, being the view for the opponents
	 * hand.
	 * 
	 * @param mManager
	 *            The render manager maintaining the render order.
	 */
	public OpponentHand(RenderManager mManager) {
		cards = new LinkedList<>();
		manager = mManager;
	}

}
