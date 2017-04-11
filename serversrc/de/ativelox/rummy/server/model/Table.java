/**
 * 
 */
package de.ativelox.rummy.server.model;

/**
 * A table object holding every information needed to for the game.
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public class Table {

	/**
	 * The card graveyard of this table.
	 */
	private CardGraveyard cardGraveyard;

	/**
	 * The deck of this table.
	 */
	private Deck deck;

	/**
	 * The hand of the first player.
	 */
	private Hand handPlayerOne;

	/**
	 * The hand of the second player.
	 */
	private Hand handPlayerTwo;

	/**
	 * The score area of this table.
	 */
	private ScoreArea scoreArea;

	/**
	 * Initiates a new Table instance containing information about every
	 * subclass needed to run this game.
	 */
	public Table() {
		deck = new Deck();
		deck.generateNewDeck();

		handPlayerOne = new Hand();
		handPlayerTwo = new Hand();

		cardGraveyard = new CardGraveyard();
	}

	/**
	 * Gets the current card graveyard of this table.
	 * 
	 * @return the specifier card graveyard.
	 */
	public CardGraveyard getCardGraveyard() {
		return cardGraveyard;

	}

	/**
	 * Gets the current deck of this table.
	 * 
	 * @return the current deck.
	 */
	public Deck getDeck() {
		return deck;
	}

	/**
	 * Gets the hand of the first Player.
	 * 
	 * @return the hand specified.
	 */
	public Hand getPlayerOneHand() {
		return handPlayerOne;
	}

	/**
	 * Gets the hand of the second Player.
	 * 
	 * @return the hand specified.
	 */
	public Hand getPlayerTwoHand() {
		return handPlayerTwo;
	}

	/**
	 * Gets the score area of this table.
	 * 
	 * @return the specified score area.
	 */
	public ScoreArea getScoreArea() {
		return scoreArea;

	}

	/**
	 * Sets the card graveyard of this table.
	 * 
	 * @param mCardGraveyard
	 *            the card graveyard to be set as this tables new card
	 *            graveyard.
	 */
	public void setCardGraveyard(CardGraveyard mCardGraveyard) {
		cardGraveyard = mCardGraveyard;

	}

	/**
	 * Sets the current deck of this table.
	 * 
	 * @param newDeck
	 *            The new deck to be set.
	 */
	public void setDeck(Deck newDeck) {
		deck = newDeck;
	}

	/**
	 * Sets the hand of the first Player.
	 * 
	 * @param mPlayerOneHand
	 *            the hand to be set as the new first players hand.
	 */
	public void setPlayerOneHand(Hand mPlayerOneHand) {
		handPlayerOne = mPlayerOneHand;

	}

	/**
	 * Sets the hand of the second Player.
	 * 
	 * @param mPlayerTwoHand
	 *            the hand to be set as the new second players hand.
	 */
	public void setPlayerTwoHand(Hand mPlayerTwoHand) {
		handPlayerTwo = mPlayerTwoHand;

	}

	/**
	 * Sets the score area of this table.
	 * 
	 * @param mScoreArea
	 *            the new score area to be set to this table.
	 */
	public void setScoreArea(ScoreArea mScoreArea) {
		scoreArea = mScoreArea;

	}

}
