/**
 * 
 */
package de.ativelox.rummy.client.view.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import de.ativelox.rummy.client.controller.RenderManager;
import de.ativelox.rummy.client.utils.Pair;
import de.ativelox.rummy.client.view.components.cards.Card;
import de.ativelox.rummy.client.view.properties.IRenderable;
import de.ativelox.rummy.properties.CardProperties;
import de.ativelox.rummy.properties.ECardIdentifier;
import de.ativelox.rummy.properties.ECardType;
import de.ativelox.rummy.utils.Utils;

/**
 * The GUI of your own hand to be drawn to the view.
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public class OwnHand extends AHand implements IRenderable {

	/**
	 * A list holding the x values of card pairs being able to score, from the
	 * first to the last.
	 */
	private List<Integer> cardHighlightCords;

	/**
	 * Initiates a new OwnHand instance view.
	 * 
	 * @param mManager
	 *            The render manager maintaining the render order.
	 */
	public OwnHand(RenderManager mManager) {
		super(mManager);

		cardHighlightCords = new LinkedList<>();
		mManager.addElementToRender(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.rummy.client.view.components.AHand#addCardByPosition(de.
	 * ativelox.rummy.client.view.components.cards.Card)
	 */
	@Override
	public boolean addCardByPosition(Card card) {
		boolean cardExists = super.addCardByPosition(card);

		cardHighlightCords = getValidCardGroups();

		return cardExists;
	}

	public Rectangle getBounds() {
		return new Rectangle(AHand.X_OFFSET, AHand.OWN_Y_OFFSET, AHand.HAND_WIDTH, CardProperties.SCALED_CARD_HEIGHT);

	}

	/**
	 * Checks the validity of card groups, meaning, that this method checks
	 * whether the cards in your hand, in the order they are displayed, are
	 * actually able to score points. Then returns the index and the length of
	 * the cards being able to score.
	 * 
	 * @param mIndex
	 *            The index to start searching for score streaks.
	 *
	 * @return A pair holding two integer values, the first one representing the
	 *         index where the score streak ends, and the second one
	 *         representing the length of the score streak.
	 * 
	 */
	public Pair<Integer, Integer> checkValidity(int mIndex) {

		Pair<Integer, Integer> pair = new Pair<>();

		LinkedList<Card> streets = new LinkedList<>();
		LinkedList<Card> sames = new LinkedList<>();

		ECardIdentifier lastIdentifier = null;
		ECardType lastType = null;
		Card currentCard = null;
		Card lastCard = null;

		for (int i = mIndex; i < cards.size(); i++) {
			currentCard = (Card) cards.get(i);

			ECardIdentifier currentIdentifier = currentCard.getIdentifier();
			ECardType currentType = currentCard.getType();

			if (lastIdentifier == null || lastType == null || lastCard == null) {

				lastCard = currentCard;
				lastIdentifier = currentIdentifier;
				lastType = currentType;
				continue;
			}
			// start checking for validity of cards groups to score

			// street possibility
			if (lastType.equals(currentType)) {

				int lastValue = de.ativelox.rummy.utils.Utils.getNumberOfIdentifier(lastIdentifier);
				int currentValue = de.ativelox.rummy.utils.Utils.getNumberOfIdentifier(currentIdentifier);

				// if theres an ace on the last value it has to be the 1,
				// because otherwise an ace is the last card in a street
				if ((lastIdentifier == ECardIdentifier.ACE) && (currentValue != 2)) {
					return pair.put(i, 0);

				}

				// if the street isnt of the type lastValue + 1 == currentValue
				// return
				if (currentValue != lastValue + 1) {
					// if the above isnt the case and theres an ace on the
					// current card, the last card cant be a king.
					if ((currentIdentifier == ECardIdentifier.ACE) && (lastIdentifier != ECardIdentifier.KING)) {
						return pair.put(i, 0);
					}
					return pair.put(i, 0);
				}

				streets.add(lastCard);
				streets.add(currentCard);

				// the current length of the street.
				int length = 2;

				int j = i + 1;

				lastIdentifier = currentIdentifier;
				lastType = currentType;
				lastValue = currentValue;

				// keep looking to see the length of the street if it even is
				// one.
				for (j = i + 1; j < cards.size(); j++) {
					currentIdentifier = ((Card) cards.get(j)).getIdentifier();
					currentType = ((Card) cards.get(j)).getType();
					currentValue = de.ativelox.rummy.utils.Utils.getNumberOfIdentifier(currentIdentifier);

					// next card is eligable for this street thus continue
					if (lastType.equals(currentType)) {

						// if theres an ace on the last value it has to be the
						// 1,
						// because otherwise an ace is the last card in a street
						if ((lastIdentifier == ECardIdentifier.ACE) && (currentValue != 2)) {
							return pair.put(j - 1, length);

						}

						// if the street isnt of the type lastValue + 1 ==
						// currentValue
						// return
						if (currentValue != lastValue + 1) {
							// if the above isnt the case and theres an ace on
							// the
							// current card, the last card cant be a king.
							if ((currentIdentifier == ECardIdentifier.ACE)
									&& (lastIdentifier != ECardIdentifier.KING)) {
								return pair.put(j - 1, length);
							}
							return pair.put(j - 1, length);
						}

						streets.add((Card) cards.get(j));
						lastIdentifier = currentIdentifier;
						lastType = currentType;
						lastValue = currentValue;

						length++;

						continue;

					} else {
						break;

					}
				}

				return pair.put(j - 1, length);

			} else {
				// pair, tripple, quadrupple possibility
				int lastValue = Utils.getNumberOfIdentifier(lastIdentifier);
				int currentValue = Utils.getNumberOfIdentifier(currentIdentifier);

				if (lastValue != currentValue) {
					return pair.put(i, 0);
				}

				int length = 2;

				sames.add(lastCard);
				sames.add(currentCard);

				int j = i + 1;

				lastIdentifier = currentIdentifier;
				lastType = currentType;
				lastValue = currentValue;

				for (j = i + 1; j < cards.size(); j++) {
					currentIdentifier = ((Card) cards.get(j)).getIdentifier();
					currentType = ((Card) cards.get(j)).getType();
					currentValue = de.ativelox.rummy.utils.Utils.getNumberOfIdentifier(currentIdentifier);

					if (lastValue == currentValue) {
						lastIdentifier = currentIdentifier;
						lastType = currentType;
						lastValue = currentValue;

						length++;

						continue;

					} else {
						break;
					}

				}

				return pair.put(j - 1, length);

			}
		}

		return pair.put(-1, 0);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.rummy.client.view.properties.IRenderable#getLayer()
	 */
	@Override
	public int getLayer() {
		return IRenderable.HUD_LAYER;
	}

	public List<Integer> getValidCardGroups() {
		List<Integer> cords = new LinkedList<>();

		int index = 0;
		int length = 0;
		Pair<Integer, Integer> pair;

		while (index != -1) {
			pair = checkValidity(index);
			index = pair.getFirstValue();
			length = pair.getSecondValue();

			if (length >= 3) {
				cords.add(cards.get(index - length + 1).getPositionOnScreen().x);
				cords.add(cards.get(index).getPositionOnScreen().x);
			}
		}

		return cords;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.ativelox.rummy.client.view.properties.IRenderable#render(java.awt.
	 * Graphics)
	 */
	@Override
	public void render(Graphics g) {
		if (cardHighlightCords.isEmpty()) {
			return;
		}

		for (int i = 0; i < cardHighlightCords.size(); i++) {
			if (i % 2 == 1) {
				g.setColor(new Color(130, 130, 130, 200));
				g.fillRect(cardHighlightCords.get(i - 1) + (CardProperties.SCALED_CARD_WIDTH / 2) - 20,
						AHand.OWN_Y_OFFSET + (CardProperties.SCALED_CARD_HEIGHT / 2) - 5,
						(cardHighlightCords.get(i) + (CardProperties.SCALED_CARD_WIDTH / 2))
								- (cardHighlightCords.get(i - 1) + (CardProperties.SCALED_CARD_WIDTH / 2)),
						10);

			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.rummy.client.view.components.AHand#setHand(java.util.
	 * LinkedList)
	 */
	@Override
	public void setHand(LinkedList<Card> mCards) {
		super.setHand(mCards);
		cardHighlightCords = getValidCardGroups();

	}

}
