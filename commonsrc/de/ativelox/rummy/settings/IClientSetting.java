/**
 * 
 */
package de.ativelox.rummy.settings;

/**
 * An interface for the settings needed for the client.
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public interface IClientSetting extends IServerSetting {

	/**
	 * Gets the value of the IP-key of the server.
	 * 
	 * @return The IP-Address of the server.
	 */
	public String getIP();

}
