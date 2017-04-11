/**
 * 
 */
package de.ativelox.rummy.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * This class can load and save data to a file, by using the internal hashmap of
 * the SettingsProvider class.
 * 
 * @author Julian <juliantischner27@web.de>
 *
 */
public class Settings {

	/**
	 * The header of the file.
	 */
	private static final String FILE_COMMENT = "Rummy Settings";

	/**
	 * The name of the file.
	 */
	private static final String FILE_PATH = "config.ini";

	/**
	 * The properties object holding and storing the settings information.
	 */
	private final Properties properties;

	/**
	 * Initiates a new Settings object, being able to load and save data.
	 */
	public Settings() {
		properties = new Properties();

	}

	/**
	 * Loads settings from the settings file and stores it in the given
	 * SettingsProvider.
	 * 
	 * @param mProvider
	 *            The Provider which should hold the data.
	 */
	public void loadSettings(ISetting mProvider) {
		try {
			try {
				properties.load(new FileInputStream(FILE_PATH));

			} catch (FileNotFoundException e) {
				this.saveSettings(mProvider);
				properties.load(new FileInputStream(FILE_PATH));
			}

			for (Entry<Object, Object> entry : properties.entrySet()) {
				mProvider.setSetting((String) entry.getKey(), (String) entry.getValue());

			}

		} catch (IOException e) {
			e.printStackTrace();

		}

	}

	/**
	 * Saves settings to the settings file by getting the data out of the given
	 * SettingsProvider.
	 * 
	 * @param mProvider
	 *            The Provider from which the data should be extracted.
	 */
	public void saveSettings(ISetting mProvider) {
		FileOutputStream target = null;

		for (Entry<String, String> entry : mProvider.getAllSettings().entrySet()) {
			properties.put(entry.getKey(), entry.getValue());

		}

		try {
			target = new FileOutputStream(new File(FILE_PATH));
			properties.store(target, FILE_COMMENT);

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			try {
				if (target != null) {
					target.close();
				}

			} catch (IOException e) {
				e.printStackTrace();

			}

		}
	}

}
