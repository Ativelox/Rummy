/**
 * 
 */
package de.ativelox.rummy.server.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;

import de.ativelox.rummy.commons.EMessage;
import de.ativelox.rummy.server.model.game.RummyGame;

/**
 * RummyPlayer is a player object which handles reading messages sent to the
 * server.
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public class RummyPlayer extends Thread {

	/**
	 * The game holding every information needed about it
	 */
	private RummyGame game;

	/**
	 * A reader which is able to read from the sockets input stream.
	 */
	private BufferedReader in;

	/**
	 * A writer which is able to write onto the sockets output stream.
	 */
	private PrintWriter out;

	/**
	 * The number of this player object.
	 */
	private int playerNumber;

	/**
	 * The socket of the client which connected to this player object.
	 */
	private Socket socket;

	/**
	 * A boolean determining whether the player is still in game or not.
	 */
	private boolean stopGame;

	/**
	 * Initiates a new RummyPlayer instance. RummyPlayer is a player object
	 * which handles reading messages sent to the server.
	 * 
	 * @param mSocket
	 *            The clients socket connection which connected to this player
	 *            object.
	 * @param mPlayerNumber
	 *            The number of the player who connected to this player object.
	 * @param mTable
	 *            The table which holds all the information about every logical
	 *            object needed to play this game.
	 */
	public RummyPlayer(Socket mSocket, int mPlayerNumber, RummyGame mGame) {
		socket = mSocket;
		playerNumber = mPlayerNumber;
		stopGame = false;
		game = mGame;

		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println(EMessage.S2C_WELCOME.ordinal() + " " + playerNumber);

		} catch (IOException e) {
			e.printStackTrace();

			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	/**
	 * Writes to the sockets output stream that this player lost thus stopping
	 * the game.
	 */
	public void lose() {
		out.println(EMessage.S2C_LOSE.ordinal());
		stopGame = true;

	}

	/**
	 * Writes to the sockets output stream that this player is ready.
	 */
	public void ready() {
		out.println(EMessage.S2C_READY.ordinal() + "");
	}

	@Override
	public void run() {

		try {
			// starts the main loop, reads messages sent to the server and
			// executes those commands.
			while (!stopGame) {

				if (!in.ready()) {
					Thread.sleep(100);
					continue;
				}

				String command = in.readLine();

				if (command.startsWith(EMessage.C2S_DRAW_CARD.ordinal() + "")) {

					if (playerNumber == 1) {
						game.drawCard(1);

					} else if (playerNumber == 2) {
						game.drawCard(2);

					}

				} else if (command.startsWith(EMessage.C2S_QUIT.ordinal() + "")) {
					// stop the game if one player quits the game
					break;

				} else if (command.startsWith(EMessage.C2S_DRAW_HAND.ordinal() + "")) {

					if (playerNumber == 1) {
						game.setPlayerOneHand();

					} else if (playerNumber == 2) {
						game.setPlayerTwoHand();
					}

				} else if (command.startsWith(EMessage.C2S_DROPPED_CARDS.ordinal() + "")) {
					String args[] = command.split("\t\t");
					LinkedList<Integer> ids = new LinkedList<>();

					for (int i = 1; i < args.length; i++) {
						ids.add(Integer.parseInt(args[i]));
					}

					if (playerNumber == 1) {
						game.removeCards(1, ids);

					} else if (playerNumber == 2) {
						game.removeCards(2, ids);
					}

				}

				Thread.sleep(100);
			}

		} catch (IOException e) {
			e.printStackTrace();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				socket.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Writes any given message to the sockets output stream.
	 * 
	 * @param s
	 *            The message to be written.
	 */
	public void send(String s) {
		out.println(s);

	}

	/**
	 * Writes to the sockets output stream that this player won thus stopping
	 * the game.
	 */
	public void win() {
		out.println(EMessage.S2C_VICTORY.ordinal() + "");
		stopGame = true;
	}
}
