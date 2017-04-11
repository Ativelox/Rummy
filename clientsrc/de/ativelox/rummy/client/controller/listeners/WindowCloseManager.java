/**
 * 
 */
package de.ativelox.rummy.client.controller.listeners;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import de.ativelox.rummy.client.controller.RummyClient;

/**
 * A WindowListener being able to correctly shut down the client.
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public class WindowCloseManager implements WindowListener {

	private RummyClient client;

	/**
	 * Initiates a new OnWindowCloseListener instance, being able to correctly
	 * shut down the client and the GUI.
	 * 
	 * @param mClient
	 *            The client which is being shut down on close.
	 */
	public WindowCloseManager(RummyClient mClient) {
		client = mClient;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowActivated(WindowEvent e) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosed(WindowEvent e) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		client.stopClient();
		e.getWindow().dispose();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.
	 * WindowEvent)
	 */
	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.
	 * WindowEvent)
	 */
	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowIconified(WindowEvent e) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowOpened(WindowEvent e) {

	}

}
