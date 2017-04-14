/**
 * 
 */
package de.ativelox.rummy.client.controller;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;

import de.ativelox.rummy.client.assets.Assets;
import de.ativelox.rummy.client.controller.listeners.MouseManager;
import de.ativelox.rummy.client.controller.listeners.WindowCloseManager;
import de.ativelox.rummy.client.view.AView;
import de.ativelox.rummy.client.view.DefeatScreenView;
import de.ativelox.rummy.client.view.GameView;
import de.ativelox.rummy.client.view.TitleScreenView;
import de.ativelox.rummy.client.view.WinScreenView;
import de.ativelox.rummy.client.view.components.cards.Card;
import de.ativelox.rummy.client.view.components.cards.ICard;
import de.ativelox.rummy.commons.EMessage;
import de.ativelox.rummy.properties.ECardIdentifier;
import de.ativelox.rummy.properties.ECardType;
import de.ativelox.rummy.settings.IClientSetting;
import de.ativelox.rummy.settings.Settings;
import de.ativelox.rummy.settings.SettingsProvider;

/**
 * The Client being able to connect to the server and read server responses, as
 * well as containing the main loop of the client.
 * 
 * @author Ativelox <juliantischner27@web.de>
 */
public class RummyClient extends Thread {

	/**
	 * The height of the frame.
	 */
	private static final int HEIGHT = 800;

	/**
	 * The width of the frame.
	 */
	private static final int WIDTH = 1200;

	/**
	 * The main method starting a new client thread.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new RummyClient().start();

	}

	/**
	 * The BufferStrategy used.
	 */
	private BufferStrategy bs;

	/**
	 * The canvas on which is being drawn.
	 */
	private Canvas canvas;

	/**
	 * The current view of this client.
	 */
	private AView currentView;

	/**
	 * The view of the clients defeat screen, containing his UI elements.
	 */
	private DefeatScreenView defeatScreenView;

	/**
	 * The Graphics on which are being drawn.
	 */
	private Graphics g;

	/**
	 * The view of the client, containing all the UI elements.
	 */
	private GameView gameView;

	/**
	 * A boolean determining whether a card is currently selected or not.
	 */
	private boolean gotCard;

	/**
	 * The reader being able to read the input stream of the socket.
	 */
	private BufferedReader in;

	/**
	 * The last card which was hovered.
	 */
	private Card lastCard;

	/**
	 * The mouse manager added to the game view. Handles mouse events.
	 */
	private MouseManager m;

	/**
	 * The writer being able to write on the output stream of the socket.
	 */
	private PrintWriter out;

	/**
	 * The playerNumber of this client.
	 */
	private char playerNumber;

	/**
	 * The settings provider holding all the information of the settings data.
	 */
	private IClientSetting provider;

	/**
	 * The RenderManager used to pass the render call during the main loop.
	 */
	private RenderManager renderManager;

	/**
	 * A boolean determining whether the client is running or not.
	 */
	private boolean running;

	/**
	 * A boolean determining whether a card is selected or not.
	 */
	private boolean selected;

	/**
	 * The card which is currently selected by the user.
	 */
	private Card selectedCard;

	/**
	 * The settings object being able to load and save settings.
	 */
	private Settings settings;

	/**
	 * The socket connection to the server.
	 */
	private Socket socket;

	/**
	 * The view of the clients title screen, containing his UI elements.
	 */
	private TitleScreenView titleScreenView;

	/**
	 * The window listener added to the current view.
	 */
	private WindowListener w;

	/**
	 * The view of the clients win screen, containing his UI elements.
	 */
	private WinScreenView winScreenView;

	/**
	 * The x and y coordinate of the mouse in relation to the component firing
	 * the event.
	 */
	private int x, y;

	/**
	 * Creates a new Instances of this Client.
	 */
	public RummyClient() {
		selected = false;
	}

	/**
	 * Gets this clients current player number.
	 * 
	 * @return char: the player number of this client.
	 */
	public char getPlayerNumber() {
		return playerNumber;
	}

	/**
	 * Gets the card currently selected by the user.
	 * 
	 * @return THe selected card.
	 */
	public ICard getSelectedCard() {
		return selectedCard;
	}

	/**
	 * Sends a request to draw a new hand to the server.
	 */
	public void requestHandDraw() {
		out.println(EMessage.C2S_DRAW_HAND.ordinal() + "");
	}

	@Override
	public void run() {
		init();
		connect();

		// wait until server registrates you as a player.
		try {
			while (true) {
				render();

				if (in.ready()) {
					String response = in.readLine();

					if (response.startsWith(EMessage.S2C_WELCOME.ordinal() + "")) {
						playerNumber = response.charAt((EMessage.S2C_WELCOME.ordinal() + " ").length());
						gameView.setTitle("Player: " + playerNumber);
						break;
					}
				}
				Thread.sleep(200);
			}
		} catch (IOException e) {
			e.printStackTrace();

		} catch (InterruptedException e) {
			e.printStackTrace();

		}

		int fps = 60;
		double timePerTick = 1_000_000_000 / fps;
		double ticksToProcess = 0;
		int timer = 0;
		double lastTime = System.nanoTime();
		double now = 0;
		int ticksPerSecond = 0;

		// "game loop" for reading streams and fixed render and update rate at
		// 60 fps.
		while (running) {

			now = System.nanoTime();
			ticksToProcess += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (ticksToProcess >= 1) {
				ticksPerSecond %= fps;
				ticksToProcess--;

				render();
				update(ticksPerSecond);

				ticksPerSecond++;
			}

			if (timer > 1_000_000_000) {
				timer = 0;
			}

			// getting responses by the server when in game.
			try {
				if (in.ready()) {
					String response = in.readLine();

					if (response.startsWith(EMessage.S2C_VICTORY.ordinal() + "")) {
						setView(winScreenView);

					} else if (response.startsWith(EMessage.S2C_LOSE.ordinal() + "")) {
						setView(defeatScreenView);

					} else if (response.startsWith(EMessage.S2C_READY.ordinal() + "")) {
						// requesting to draw a new hand
						// both players are ready
						setView(gameView);

						requestHandDraw();

					} else if (response.startsWith(EMessage.S2C_OWN_HAND_UPDATE.ordinal() + "")) {
						// receiving information about your initial hand from
						// the server.
						// protocol
						// STARTING_HAND_UPDATE\t\tIDENTIFIER\tTYPE\t\t....
						// n times for n cards

						String cards[] = response.split("\t\t");
						String card[];
						LinkedList<Card> hand = new LinkedList<>();

						for (int i = 1; i < cards.length; i++) {
							// card array containing every information about the
							// card needed
							card = cards[i].split("\t");

							ECardIdentifier identifier = ECardIdentifier.valueOf(card[0]);
							ECardType type = ECardType.valueOf(card[1]);
							int ID = Integer.parseInt(card[2]);

							hand.add(new Card(identifier, type, ID));
						}
						gameView.getOwnHand().setHand(hand);

					} else if (response.startsWith(EMessage.S2C_OPPONENT_HAND_UPDATE.ordinal() + "")) {
						// receiving information about the hand of your enemy
						// (number of cards)
						// protocol
						// OPPONENT_HAND_INFORMATION\t\tNUMBER_OF_CARDS

						String args[] = response.split("\t\t");
						int numberOfCards = Integer.parseInt(args[1]);

						if (gameView.getOpponentHand().getCards().size() - numberOfCards == 1) {
							gameView.getOpponentHand().removeCard();

						} else if (gameView.getOpponentHand().getCards().size() - numberOfCards == -1) {
							gameView.getOpponentHand().addCard();

						} else {
							gameView.getOpponentHand().setHand(numberOfCards);

						}
					}
				}

			} catch (IOException e) {
				e.printStackTrace();

			}

			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Sets this clients x and y fields equivalent to the mouse position.
	 * 
	 * @param mPoint
	 *            The point of the current mouse Position.
	 */
	public void setMousePosition(Point mPoint) {
		setMouseX(mPoint.x);
		setMouseY(mPoint.y);
	}

	/**
	 * Sets the selected status of this client. (Whether a card is selected or
	 * not).
	 * 
	 * @param newStatus
	 *            The new value for the selected status.
	 */
	public void setSelectedStatus(boolean newStatus) {
		selected = newStatus;

	}

	/**
	 * Stops the client by exiting Threads run method.
	 */
	public void stopClient() {
		out.println(EMessage.C2S_QUIT.ordinal() + "");
		running = false;
	}

	/**
	 * Connects to the server and initializes the writer and reader.
	 */
	private void connect() {
		try {
			socket = new Socket(provider.getIP(), provider.getPort());
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		} catch (UnknownHostException e) {
			System.err.println("IP-Address couldn't be determined: " + provider.getIP());
			e.printStackTrace();

		} catch (IOException e) {
			System.err.println("An I/O Exception occured while trying to open a new socket.");
			e.printStackTrace();

		}
	}

	/**
	 * Initiates this client, gets automatically called by Threads run.
	 */
	private void init() {
		Assets.init();

		settings = new Settings();
		provider = new SettingsProvider();

		settings.loadSettings(provider);

		w = new WindowCloseManager(this);
		m = new MouseManager(this);

		renderManager = new RenderManager();

		gameView = new GameView("Rummy", WIDTH, HEIGHT, renderManager);
		titleScreenView = new TitleScreenView("Rummy", WIDTH, HEIGHT, renderManager);
		defeatScreenView = new DefeatScreenView("Rummy", WIDTH, HEIGHT, renderManager);
		winScreenView = new WinScreenView("Rummy", WIDTH, HEIGHT, renderManager);

		gameView.getCanvas().addMouseMotionListener(m);
		gameView.getCanvas().addMouseListener(m);

		setView(titleScreenView);
		currentView.startRendering();

		running = true;
	}

	/**
	 * The main render method of the client, creating the Graphics object and
	 * the buffer strategy, as well as calling the render method of the
	 * renderManager.
	 */
	private void render() {

		bs = canvas.getBufferStrategy();
		if (bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		// Clear Screen
		g.clearRect(0, 0, WIDTH, HEIGHT);
		// Draw Here!

		renderManager.render(g);

		// End Drawing!
		bs.show();
		g.dispose();

	}

	/**
	 * Sets this clients x field to the x coordinate of the mouse position.
	 * 
	 * @param mX
	 *            The x coordinate of the mouse.
	 */
	private void setMouseX(int mX) {
		x = mX;
	}

	/**
	 * Sets this clients y field to the y coordinate of the mouse position.
	 * 
	 * @param mY
	 *            The y coordinate of the mouse.
	 */
	private void setMouseY(int mY) {
		y = mY;
	}

	/**
	 * Sets the new view, as well as re-initializing the new canvas and
	 * disposing of the old view.
	 * 
	 * @param newView
	 *            The view which should be rendered.
	 */
	private void setView(AView newView) {
		if (currentView != null) {
			currentView.getFrame().dispose();
			currentView.stopRendering();
		}
		newView.initialize();
		newView.getFrame().setVisible(true);
		canvas = newView.getCanvas();
		newView.setWindowListener(w);
		currentView = newView;
		currentView.startRendering();
	}

	/**
	 * The main update method of the client, checking every tick for specific
	 * events.
	 * 
	 * @param ticks
	 *            The current tick this frame is on per second, ranges from 0 to
	 *            60.
	 */
	private void update(int ticks) {

		// hover logic, if mouse hovers a card highlight it, otherwise
		// dehighlight it.
		if ((ticks % 2) == 0 && !selected) {
			if (gameView.getOwnHand() != null) {
				Card card = (Card) gameView.getOwnHand().getCardByPosition(x, y);
				if (card != null) {
					card.highlight();
					if (lastCard != null && !card.equals(lastCard)) {
						lastCard.deHighlight();
					}

					lastCard = card;

				} else {
					if (lastCard != null) {
						lastCard.deHighlight();
						lastCard = null;
					}

				}

			}
		}

		if (selected && !gotCard) {
			selectedCard = (Card) gameView.getOwnHand().getAndRemoveCardByPosition(x, y);
			if (selectedCard != null) {
				gotCard = true;
				selected = false;
			}
		}

		if (selected && gotCard) {
			if (gameView.getScoreArea().getBounds().contains(selectedCard.getBounds())) {
				gameView.getScoreArea().addCard(selectedCard);
				selected = false;
				gotCard = false;
				selectedCard = null;
			} else if (gameView.getOwnHand().addCardByPosition(selectedCard)) {
				selected = false;
				gotCard = false;
				selectedCard = null;
			} else {
				selected = false;
			}
		}

		if (gotCard && selectedCard != null) {
			selectedCard.setPosition(new Point(x - selectedCard.getWidth() / 2, y - selectedCard.getHeight() / 2));

		}

		// TODO: add code for card dropping
		// if (event) {
		// out.println(EMessage.C2S_DROPPED_CARD.ordinal() + "\t\t" +
		// selectedCard.getID());
		//
		// selectedCard = null;
		// gotCard = false;
		// }

	}
}
