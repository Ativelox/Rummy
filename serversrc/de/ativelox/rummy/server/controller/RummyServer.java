/**
 * 
 */
package de.ativelox.rummy.server.controller;

import java.io.IOException;
import java.net.ServerSocket;

import de.ativelox.rummy.server.model.Table;
import de.ativelox.rummy.server.model.game.RummyGame;
import de.ativelox.rummy.settings.IServerSetting;
import de.ativelox.rummy.settings.Settings;
import de.ativelox.rummy.settings.SettingsProvider;

/**
 * <b>Message Protocol: </b><br>
 * TBD
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public class RummyServer extends Thread {

	/**
	 * The main method starting a new RummyServer thread.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new RummyServer().start();

	}

	/**
	 * The game created by this server.
	 */
	private RummyGame game;

	/**
	 * The settings provider which is able to provide this server with external
	 * data.
	 */
	private IServerSetting provider;

	/**
	 * A boolean determining whether the server is still running or not.
	 */
	private boolean serverRunning;

	/**
	 * The server socket created by this server.
	 */
	private ServerSocket serverSocket;

	/**
	 * The settings of which data can be loaded and saved.
	 */
	private Settings settings;

	/**
	 * Initiates a new RummyServer instance.
	 */
	public RummyServer() {
		serverRunning = true;
	}

	/**
	 * Closes this server.
	 */
	public void closeServer() {
		serverRunning = false;
	}

	@Override
	public void run() {
		init();

		RummyPlayer player1 = null;
		RummyPlayer player2 = null;

		try {
			player1 = new RummyPlayer(serverSocket.accept(), 1, game);
			player2 = new RummyPlayer(serverSocket.accept(), 2, game);
		} catch (IOException e) {
			e.printStackTrace();
		}

		player1.start();
		player2.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		game.setPlayerOne(player1);
		game.setPlayerTwo(player2);

		game.start();

		player1.ready();
		player2.ready();
	}

	/**
	 * Initializes this object, loading settings and opening the connection to
	 * other clients.
	 */
	private void init() {
		settings = new Settings();
		provider = new SettingsProvider();

		settings.loadSettings(provider);

		Table table = new Table();

		game = new RummyGame(table);

		openConnection();

	}

	/**
	 * Opens the connection, initializing the server socket.
	 */
	private void openConnection() {

		try {
			serverSocket = new ServerSocket(provider.getPort());

		} catch (IOException e) {
			System.err.println("An I/O Exception occured while trying to open the server socket.");
			e.printStackTrace();
		}
	}

}
