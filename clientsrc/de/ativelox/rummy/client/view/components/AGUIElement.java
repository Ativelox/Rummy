/**
 * 
 */
package de.ativelox.rummy.client.view.components;

import java.awt.Point;
import java.awt.Rectangle;

import de.ativelox.rummy.client.view.properties.IRenderable;

/**
 * Abstract class holding basic methods to manipulate elements on the screen.
 * 
 * @author Ativelox <juliantischner27@web.de>
 */
public abstract class AGUIElement implements IRenderable {

	/**
	 * The height of the element.
	 */
	protected int height;

	/**
	 * The width of the element.
	 */
	protected int width;

	/**
	 * The absolute x coordinate of the element on the component.
	 */
	protected int x;

	/**
	 * The absolute y coordinate of the element on the component.
	 */
	protected int y;

	/**
	 * An abstract class containing information about position related
	 * information.
	 * 
	 * @param mX
	 *            The x coordinate of this element.
	 * @param mY
	 *            The y coordinate of this element.
	 * @param mWidth
	 *            The width of this element.
	 * @param mHeight
	 *            The height of this element.
	 */
	public AGUIElement(int mX, int mY, int mWidth, int mHeight) {
		x = mX;
		y = mY;
		width = mWidth;
		height = mHeight;

	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);

	}

	/**
	 * Gets the height of this element.
	 * 
	 * @return int: the height.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Gets the position of this element on the screen.
	 * 
	 * @return Point: A point representing the position of this element on the
	 *         component.
	 */
	public Point getPositionOnScreen() {
		return new Point(x, y);
	}

	/**
	 * Gets the width of this element.
	 * 
	 * @return int: the width.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the position of this element on the screen.
	 * 
	 * @param p
	 *            The new Point of this element on the screen.
	 */
	public void setPosition(Point p) {
		x = p.x;
		y = p.y;
	}

}
