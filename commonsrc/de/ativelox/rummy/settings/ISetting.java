/**
 * 
 */
package de.ativelox.rummy.settings;

import java.util.Map;

/**
 * An Interface providing necessary methods for every settings provider.
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public interface ISetting {

	/**
	 * Gets the HashMap containing every key,value pair of the settings.
	 * 
	 * @return Map&ltString, String&gt: The Map containing the settings.
	 */
	public Map<String, String> getAllSettings();

	/**
	 * Gets a specific settings value of the given key.
	 * 
	 * @param key
	 *            The key of which to get the value of.
	 * @return String: the value of the given key.
	 */
	public String getSetting(final String key);

	/**
	 * Sets a specific setting by using a key, value pair.
	 * 
	 * @param key
	 *            The key of this setting.
	 * @param value
	 *            The value of this setting.
	 */
	public void setSetting(final String key, final String value);
}
