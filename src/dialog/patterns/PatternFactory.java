package dialog.patterns;

import model.GridComponent;

/**
 * Factory Class that creates {@link GifPattern}, {@link ImagePattern} and
 * {@link Pattern} patterns
 */
public class PatternFactory {

	/**
	 * creates object type ImagePattern from image patterns
	 * 
	 * @param patternName
	 *            is the name of pattern
	 * @return new pattern type ImagePattern
	 */
	public ImagePattern createImagePattern(String patternName) {
		switch (patternName) {
		case "Block":
			return new Block();
		case "Beehive":
			return new Beehive();
		case "Loaf":
			return new Loaf();
		case "Boat":
			return new Boat();
		case "Tub":
			return new Tub();
		case "Gosper glider gun":
			return new GosperGliderGun();
		default:
			return null;
		}
	}

	/**
	 * Creates pattern that is oscillator based
	 * 
	 * @param patternName
	 *            is the name of pattern
	 * @return new GifPattern
	 */
	public GifPattern createGifPattern(String patternName) {
		switch (patternName) {
		case "Blinker":
			return new Blinker();
		case "Toad":
			return new Toad();
		case "Beacon":
			return new Beacon();
		case "Pulsar":
			return new Pulsar();
		case "Pentadecathon":
			return new Pentadecathon();
		case "Glider":
			return new Glider();
		case "LWSS":
			return new LWSS();
		default:
			return null;
		}
	}

	/**
	 * Creates a CustomPattern object
	 * 
	 * @param grid
	 *            is the grid where pattern is painted
	 * @return new Custom pattern object
	 */
	public Pattern createCustomPattern(GridComponent grid) {
		return new CustomPatten(grid);
	}
}
