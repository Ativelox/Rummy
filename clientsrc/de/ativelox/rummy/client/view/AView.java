/**
 * 
 */
package de.ativelox.rummy.client.view;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import de.ativelox.rummy.client.controller.RenderManager;
import de.ativelox.rummy.client.view.components.AGUIElement;

/**
 * Abstract view superclass containing necessary information about every view
 * there can be.
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public abstract class AView extends AGUIElement {

	/**
	 * The canvas of the frame on which should be drawn.
	 */
	private Canvas canvas;

	/**
	 * The frame of this view.
	 */
	private JFrame frame;

	/**
	 * The render manager of this view maintaining the render order.
	 */
	private RenderManager manager;

	/**
	 * The title of this view.
	 */
	private String title;

	/**
	 * Initiates a new View instance.
	 * 
	 * @param title
	 *            The title displayed on the frame.
	 * @param width
	 *            The width of the frame.
	 * @param height
	 *            The height of the frame.
	 * @param manager
	 *            The RenderManager of this View, managing render layers.
	 */
	public AView(String mTitle, int mWidth, int mHeight, RenderManager mManager) {
		super(0, 0, mWidth, mHeight);

		manager = mManager;
		title = mTitle;
		height = mHeight;
		width = mWidth;

		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(width, height);
		frame.setLocationRelativeTo(null);
		frame.setVisible(false);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);
		canvas.setBounds(0, 0, width, height);

		frame.add(canvas);

	}

	/**
	 * Gets the current canvas of the frame.
	 * 
	 * @return Canvas: the current canvas on which is being drawn.
	 */
	public Canvas getCanvas() {
		return canvas;
	}

	/**
	 * Gets the current frame of this view.
	 * 
	 * @return JFrame: the current frame.
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Initializes all the GUIElements a view contains.
	 */
	public abstract void initialize();

	/**
	 * Sets the title of this view.
	 * 
	 * @param title
	 *            The title to be displayed.
	 */
	public void setTitle(String title) {
		getFrame().setTitle(title);
	}

	/**
	 * Sets the WindowListener for the frame.
	 * 
	 * @param w
	 *            the new WindowListener for the frame.
	 */
	public void setWindowListener(WindowListener w) {
		getFrame().addWindowListener(w);
	}

	/**
	 * Starts to render this view.
	 */
	public void startRendering() {
		manager.addElementToRender(this);
	}

	/**
	 * Stops rendering this view.
	 */
	public void stopRendering() {
		manager.removeElementToRender(this);

	}
}
