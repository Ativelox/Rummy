/**
 * 
 */
package de.ativelox.rummy.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

/**
 * A static utils class providing utility methods.
 * 
 * @author Ativelox <juliantischner27@web.de>
 *
 */
public class Utils {

	/**
	 * The path of the textures folder within res.
	 */
	private static final String texturePath = "/textures/";

	/**
	 * Loads an image and returns it. Throws a NullPointerException if the image
	 * couldn't be loaded.
	 * 
	 * @param mFileName
	 *            The name of the file which should be loaded.
	 * @return Optional&ltBufferedImage&gt: The BufferedImage which was
	 *         successfully loaded.
	 */
	public static Optional<BufferedImage> loadImage(String mFileName) {
		try {
			return Optional.of(ImageIO.read(Utils.class.getResource(texturePath + mFileName)));

		} catch (IOException e) {
			System.err.println("I/O Exception occured while trying to load an image: " + texturePath + mFileName);
			e.printStackTrace();

			return Optional.of(null);
		}
	}

	/**
	 * Utility class. No instance needed.
	 */
	private Utils() {
	}

}
