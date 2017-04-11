/**
 * 
 */
package de.ativelox.rummy.client.controller;

import java.awt.Graphics;
import java.util.LinkedList;

import de.ativelox.rummy.client.view.properties.IRenderable;

/**
 * Manages render calls of every class implementing IRenderable. For instance
 * handles on which layer to render the given element.
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public class RenderManager {

	/**
	 * The comparator used to sort the renderable elements by their layer.
	 */
	private LayerComparator comparator;

	/**
	 * A LinkedList containing every element currently being rendered onto the
	 * screen.
	 */
	private LinkedList<IRenderable> renderableElements;

	/**
	 * Creates a new instance of a RenderManager.
	 */
	public RenderManager() {
		comparator = new LayerComparator();
		renderableElements = new LinkedList<>();

	}

	/**
	 * Adds element to a list containing every element currently being rendered.
	 * 
	 * @param element
	 *            The element which should be rendered.
	 */
	public void addElementToRender(IRenderable element) {
		renderableElements.add(element);
	}

	/**
	 * Removes element from a list containing every element currently being
	 * rendered.
	 * 
	 * @param element
	 *            The element which should be removed from rendering.
	 * 
	 */
	public void removeElementToRender(IRenderable element) {
		renderableElements.remove(element);

	}

	/**
	 * Renders content on the given Graphics g.
	 * 
	 * @param g
	 *            The graphics on which to be drawn.
	 */
	public void render(final Graphics g) {
		renderableElements.sort(comparator);

		for (IRenderable element : renderableElements) {
			element.render(g);

		}

	}
}
