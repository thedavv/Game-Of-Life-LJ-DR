package dialog.patterns;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * Patterns that are in game of life must implement this interface.
 */
public interface Pattern {

	/**
	 * Creates pattern and stores position of active fields into List<Integer[]>
	 * 
	 * @return stored pattern in List<Integer[]> form. Where Integers are X,Y
	 *         coordinations of active fields
	 */
	public List<Integer[]> createPattern();

	/**
	 * Returns representation of current pattern in Image form
	 * 
	 * @param path
	 *            is the image path
	 * @return Image of the current pattern
	 * @throws IOException
	 *             if there is error with reading an Image
	 */
	public BufferedImage createImage(String path) throws IOException;
}
