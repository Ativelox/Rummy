/**
 * 
 */
package de.ativelox.rummy.commons;

/**
 * An enum holding all the message traffic being sent between the client and the
 * server.
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public enum EMessage {
	// TODO: javadoc when protocol is finished.

	C2S_DESELECTED, C2S_DRAW_CARD, C2S_DRAW_HAND, C2S_QUIT,

	C2S_SELECTED, S2C_DRAW_HAND, S2C_LOSE, S2C_READY, S2C_SELECTED, S2C_STARTING_HAND_INFORMATION, S2C_TICK, S2C_VICTORY, S2C_WELCOME;
}
