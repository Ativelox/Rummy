/**
 * 
 */
package de.ativelox.rummy.settings;

/**
 * An interface for the settings needed by the server.
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public interface IServerSetting extends ISetting {

	/**
	 * Gets the value of the PORT-key of the server.
	 * 
	 * @return int: the specified port of the server.
	 */
	public int getPort();

}
