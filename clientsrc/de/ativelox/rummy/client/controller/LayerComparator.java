/**
 * 
 */
package de.ativelox.rummy.client.controller;

import java.util.Comparator;

import de.ativelox.rummy.client.view.components.cards.Card;
import de.ativelox.rummy.client.view.components.cards.ICard;
import de.ativelox.rummy.client.view.properties.IRenderable;

/**
 * A Comparator comparating renderable objects by their layer specified in
 * IRenderable. If the renderable objects is an instance of card it will also
 * check for position related information due to overlapping issues.
 * 
 * @author Ativelox <juliantischner27@web.de>
 */
public class LayerComparator implements Comparator<IRenderable> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(final IRenderable o1, final IRenderable o2) {
		int firstResult = Integer.compare(o1.getLayer(), o2.getLayer());

		if (firstResult != 0) {
			return firstResult;
		}

		if (o1 instanceof Card && o2 instanceof Card) {
			return Integer.compare(((Card) o1).getPositionOnScreen().x + ((Card) o1).getWidth(),
					((Card) o2).getPositionOnScreen().x + ((Card) o2).getWidth());
		}

		return 0;
	}

}
