/**
 * 
 */
package de.ativelox.rummy.client.view.components;

import java.util.LinkedList;

import de.ativelox.rummy.client.controller.RenderManager;
import de.ativelox.rummy.client.view.components.cards.CardBack;
import de.ativelox.rummy.client.view.components.cards.ICard;

/**
 * The GUI of the opponents hand to be drawn to the view.
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public class OpponentHand extends AHand {

	/**
	 * Initiates a new OpponentHand view.
	 * 
	 * @param mManager
	 *            the render manager maintaining the render order.
	 */
	public OpponentHand(RenderManager mManager) {
		super(mManager);
	}
	
	public void addCard(){
		ICard card = new CardBack();
		cards.add(card);
		manager.addElementToRender(card);
		
		adjustView(cards);
	}
	
	public LinkedList<ICard> getCards(){
		return cards;
	}
	
	public void removeCard(){
		manager.removeElementToRender(cards.poll());
		
		adjustView(cards);
	}
	
	public void setHand(int numberOfCards){
		
		for(int i = 0; i < numberOfCards; i++){
			ICard card = new CardBack();
			cards.add(card);
			manager.addElementToRender(card);
		}
		
		adjustView(cards);
		
	}

}
