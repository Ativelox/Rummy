/**
 * 
 */
package de.ativelox.rummy.client.view.properties;

import java.awt.Graphics;

/**
 * An Interface containing essential methods and fields for objects which are
 * renderable.
 * 
 * @author Ativelox <juliantischner27@web.de>
 */
public interface IRenderable {
	/**
	 * The smallest layer for objects.
	 */
	public static int BACKGROUND_LAYER = -20;
	/**
	 * The default layer for objects.
	 */
	public static int DEFAULT_LAYER = 0;
	/**
	 * Layer for HUD elements.
	 */
	public static int HUD_LAYER = 16;

	/**
	 * Gets the layer onto which this object should be rendered. This determines
	 * the order of objects being rendered. Objects with a lower layer get
	 * rendered behind those on higher layers.
	 * 
	 * @return The layer onto which this object should be rendered. Objects with
	 *         a lower layer get rendered behind those on higher layers.
	 */
	public int getLayer();

	/**
	 * Renders this object on the screen using the given graphics object.
	 * 
	 * @param g
	 *            Graphics to render with
	 */
	public void render(final Graphics g);

}
