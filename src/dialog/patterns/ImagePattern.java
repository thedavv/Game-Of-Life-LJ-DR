package dialog.patterns;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface ImagePattern extends Pattern{
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
