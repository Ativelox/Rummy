/**
 * 
 */
package de.ativelox.rummy.server.model.game;

import java.util.LinkedList;

import de.ativelox.rummy.commons.EMessage;
import de.ativelox.rummy.server.controller.RummyPlayer;
import de.ativelox.rummy.server.model.Card;
import de.ativelox.rummy.server.model.Table;

/**
 * The game object of the server containing all the logic.
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public class RummyGame extends Thread {

	/**
	 * The first player to be connected to the server.
	 */
	private RummyPlayer playerOne;

	/**
	 * The second player to be connected to the server.
	 */
	private RummyPlayer playerTwo;

	/**
	 * A boolean determining whether the game is running or not.
	 */
	private boolean stopGame;

	/**
	 * A Table containing all the needed classes for logic information
	 * retrieval.
	 */
	private Table table;

	/**
	 * Initiates a new RummyGame instance.
	 */
	public RummyGame(Table mTable) {
		table = mTable;
		stopGame = false;
	}

	@Override
	public void run() {
		while (!stopGame) {
			// TODO: implement logic.

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Sets the first player of this game.
	 * 
	 * @param mPlayer
	 *            The first player.
	 */
	public void setPlayerOne(RummyPlayer mPlayer) {
		playerOne = mPlayer;
	}

	/**
	 * Sets the hand of the first player by retrieving the first few cards of
	 * the deck and sending a message to the client containing all the needed
	 * information for setting up the hand as the client.
	 */
	public void setPlayerOneHand() {
		LinkedList<Card> cards = table.getDeck().getNewHand();

		table.getPlayerOneHand().setHand(cards);

		StringBuilder message = new StringBuilder();
		message.append(EMessage.S2C_STARTING_HAND_INFORMATION.ordinal() + "\t\t");
		// x \t y \t identifier \t type

		for (int i = 0; i < cards.size(); i++) {
			Card card = cards.get(i);
			message.append(card.getIdentifier().ordinal() + "\t" + card.getType().ordinal() + "\t\t");
		}
		playerOne.send(message.toString());
	}

	/**
	 * Sets the second player of this game.
	 * 
	 * @param mPlayer
	 *            The second player.
	 */
	public void setPlayerTwo(RummyPlayer mPlayer) {
		playerTwo = mPlayer;

	}

	/**
	 * Sets the hand of the second player by retrieving the first few cards of
	 * the deck and sending a message to the client containing all the needed
	 * information for setting up the hand as the client.
	 */
	public void setPlayerTwoHand() {
		LinkedList<Card> cards = table.getDeck().getNewHand();

		table.getPlayerTwoHand().setHand(cards);

		StringBuilder message = new StringBuilder();
		message.append(EMessage.S2C_STARTING_HAND_INFORMATION.ordinal() + "\t\t");
		// x \t y \t identifier \t type

		for (int i = 0; i < cards.size(); i++) {
			Card card = cards.get(i);
			message.append(card.getIdentifier().ordinal() + "\t" + card.getType().ordinal() + "\t\t");
		}
		playerTwo.send(message.toString());

	}

	/**
	 * stops the game.
	 */
	public void stopGame() {
		stopGame = true;
	}

}