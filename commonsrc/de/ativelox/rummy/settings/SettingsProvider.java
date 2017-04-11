/**
 * 
 */
package de.ativelox.rummy.settings;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * This Class holds the data of the settings file, and necessary methods of
 * getting and setting the data to this provider.
 * 
 * @author Julian <juliantischner27@web.de>
 *
 */
public class SettingsProvider implements IClientSetting {

	/**
	 * The key for the IP-Address of the Server.
	 */
	private static final String KEY_IDENTIFIER_IP = "IP";

	/**
	 * The key for the PORT of the Server.
	 */
	private static final String KEY_IDENTIFIER_PORT = "PORT";

	/**
	 * The HashMap containing all the settings.
	 */
	private final HashMap<String, String> settingsStore;

	/**
	 * Initiates a new SettingsProvider instance, being able to get and set new
	 * settings.
	 */
	public SettingsProvider() {
		settingsStore = new HashMap<>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.rummy.settings.ISetting#getAllSettings()
	 */
	@Override
	public Map<String, String> getAllSettings() {
		return Collections.unmodifiableMap(settingsStore);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.rummy.settings.IClientSetting#getIP()
	 */
	@Override
	public String getIP() {
		return settingsStore.get(KEY_IDENTIFIER_IP);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.rummy.settings.IServerSetting#getPort()
	 */
	@Override
	public int getPort() {
		return Integer.parseInt(settingsStore.get(KEY_IDENTIFIER_PORT));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.rummy.settings.ISetting#getSetting(java.lang.String)
	 */
	@Override
	public String getSetting(String key) {
		return settingsStore.get(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.ativelox.rummy.settings.ISetting#setSetting(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void setSetting(String key, String value) {
		settingsStore.put(key, value);

	}

}
