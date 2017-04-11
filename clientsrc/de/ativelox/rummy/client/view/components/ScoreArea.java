/**
 * 
 */
package de.ativelox.rummy.client.view.components;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

import de.ativelox.rummy.client.assets.Assets;
import de.ativelox.rummy.client.controller.RenderManager;
import de.ativelox.rummy.client.view.components.cards.Card;
import de.ativelox.rummy.client.view.properties.IRenderable;

/**
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public class ScoreArea extends AGUIElement{
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 200;
	public static final int X_OFFSET = 300;
	public static final int Y_OFFSET = 200;

	private LinkedList<Card> cards;
	
	private RenderManager manager;
	
	public ScoreArea(RenderManager mManager) {
		super(X_OFFSET, Y_OFFSET, WIDTH, HEIGHT);
		
		cards = new LinkedList<>();
		manager = mManager;
		
		manager.addElementToRender(this);
	}
	
	public void addCard(Card mCard){
		cards.add(mCard);
		
		adjustView();
	}
	
	public void setCards(LinkedList<Card> mCards){
		clear();
		
		for(int i = 0; i < mCards.size(); i++){
			cards.add(mCards.get(i));
			
		}
		
		adjustView();
		
	}
	
	private void clear(){
		for(Card card : cards){
			manager.removeElementToRender(card);
			cards.remove(card);
		}
		
		adjustView();
	}
	
	private void adjustView(){
		for(int i = 0; i < cards.size(); i++){
			Card card = cards.get(i);
			card.setPosition(new Point(X_OFFSET + (i * (card.getWidth()/2)), Y_OFFSET));
		}
		
	}
	
	public Rectangle getBounds(){
		return new Rectangle(X_OFFSET, Y_OFFSET, WIDTH, HEIGHT);
	}

	/* (non-Javadoc)
	 * @see de.ativelox.rummy.client.view.properties.IRenderable#getLayer()
	 */
	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return IRenderable.DEFAULT_LAYER;
	}

	/* (non-Javadoc)
	 * @see de.ativelox.rummy.client.view.properties.IRenderable#render(java.awt.Graphics)
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.GREEN_DECK, x, y, width, height, null);
		
	}
}
