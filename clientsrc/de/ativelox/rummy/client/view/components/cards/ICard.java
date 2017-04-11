/**
 * 
 */
package de.ativelox.rummy.client.view.components.cards;

import java.awt.Point;
import java.awt.Rectangle;

import de.ativelox.rummy.client.view.properties.IRenderable;

/**
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public interface ICard extends IRenderable{
	

	/**
	 * Gets the height of this element.
	 * 
	 * @return int: the height.
	 */
	public int getHeight();

	/**
	 * Gets the position of this element on the screen.
	 * 
	 * @return Point: A point representing the position of this element on the
	 *         component.
	 */
	public Point getPositionOnScreen();

	/**
	 * Gets the width of this element.
	 * 
	 * @return int: the width.
	 */
	public int getWidth();

	/**
	 * Sets the position of this element on the screen.
	 * 
	 * @param p
	 *            The new Point of this element on the screen.
	 */
	public void setPosition(Point p);
	

	/**
	 * Gets the current bounds of this card.
	 * 
	 * @return The current bounds.
	 */
	public Rectangle getBounds();

}
