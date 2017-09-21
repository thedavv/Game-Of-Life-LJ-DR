package dialog.patterns;

import javax.swing.Icon;

/**
 * All patterns that are gif patterns must implement this interface
 */
public interface GifPattern extends Pattern {
	/**
	 * Creates object type Icon of current pattern
	 * 
	 * @param path
	 *            is the path to gif file
	 * @return Icon object of set pattern
	 */
	public Icon createGif(String path);
}
