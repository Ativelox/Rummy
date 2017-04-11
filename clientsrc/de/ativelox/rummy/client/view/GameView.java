/**
 * 
 */
package de.ativelox.rummy.client.view;

import java.awt.Graphics;

import de.ativelox.rummy.client.assets.Assets;
import de.ativelox.rummy.client.controller.RenderManager;
import de.ativelox.rummy.client.view.components.Deck;
import de.ativelox.rummy.client.view.components.OpponentHand;
import de.ativelox.rummy.client.view.components.OwnHand;
import de.ativelox.rummy.client.view.components.ScoreArea;
import de.ativelox.rummy.client.view.properties.IRenderable;

/**
 * The game view of the client.
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public class GameView extends AView {

	/**
	 * The deck view.
	 */
	private Deck deck;

	/**
	 * The render manager used to render objects onto this view.
	 */
	private RenderManager manager;

	/**
	 * Your opponents hand view.
	 */
	private OpponentHand opponentHand;

	/**
	 * Your own hand view.
	 */
	private OwnHand ownHand;
	
	private ScoreArea scoreArea;

	/**
	 * Initiates a new GameView instance, holding all the UI related information
	 * while playing the game.
	 * 
	 * @param mTitle
	 *            The title which should be displayed.
	 * @param mWidth
	 *            The width of this view.
	 * @param mHeight
	 *            The height of this view.
	 * @param mManager
	 *            The render manager of this view.
	 */
	public GameView(String mTitle, int mWidth, int mHeight, RenderManager mManager) {
		super(mTitle, mWidth, mHeight, mManager);

		manager = mManager;
	}

	/**
	 * Gets the current deck view.
	 * 
	 * @return The current deck view.
	 */
	public Deck getDeck() {
		return deck;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.jolly.client.view.properties.IRenderable#getLayer()
	 */
	@Override
	public int getLayer() {
		return IRenderable.BACKGROUND_LAYER;
	}

	/**
	 * Gets the current opponent hand view.
	 * 
	 * @return The current opponent hand view.
	 */
	public OpponentHand getOpponentHand() {
		return opponentHand;
	}

	/**
	 * Gets the current own hand view.
	 * 
	 * @return The current own hand view.
	 */
	public OwnHand getOwnHand() {
		return ownHand;
	}
	
	public ScoreArea getScoreArea(){
		return scoreArea;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.rummy.client.view.View#initialize()
	 */
	@Override
	public void initialize() {
		ownHand = new OwnHand(manager);
		opponentHand = new OpponentHand(manager);
		deck = new Deck(manager);
		scoreArea = new ScoreArea(manager);

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
		g.drawImage(Assets.TABLETOP, x, y, width, height, null);

	}

}
