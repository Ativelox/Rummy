/**
 * 
 */
package de.ativelox.rummy.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

import de.ativelox.rummy.properties.ECardIdentifier;

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

	public static int getNumberOfIdentifier(ECardIdentifier mIdentifier) {

		if (mIdentifier == ECardIdentifier.TWO) {
			return 2;

		} else if (mIdentifier == ECardIdentifier.THREE) {
			return 3;

		} else if (mIdentifier == ECardIdentifier.FOUR) {
			return 4;

		} else if (mIdentifier == ECardIdentifier.FIVE) {
			return 5;

		} else if (mIdentifier == ECardIdentifier.SIX) {
			return 6;

		} else if (mIdentifier == ECardIdentifier.SEVEN) {
			return 7;

		} else if (mIdentifier == ECardIdentifier.EIGHT) {
			return 8;

		} else if (mIdentifier == ECardIdentifier.NINE) {
			return 9;

		} else if (mIdentifier == ECardIdentifier.TEN) {
			return 10;

		} else if (mIdentifier == ECardIdentifier.PRINCE) {
			return 11;

		} else if (mIdentifier == ECardIdentifier.QUEEN) {
			return 12;

		} else if (mIdentifier == ECardIdentifier.KING) {
			return 13;

		} else {
			// no default assertion possible, special cases needed by caller.
			return -1;
		}

	}

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
