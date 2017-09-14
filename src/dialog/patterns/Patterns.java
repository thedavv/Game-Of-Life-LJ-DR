package dialog.patterns;

import model.GridComponent;

/**
 * Patterns that are in game of life must implement this interface.
 */
public interface Patterns {
	/**
	 * Retutns GridComponent with active LifeSquare acording to a pattern
	 * 
	 * @param grid
	 *            is the current square grid that we want to paint pattern on
	 * @param patternType
	 *            is the patern type that we want to draw on GridComponent
	 * @return changed GridComponent with patern
	 */
	public GridComponent createPattern(GridComponent grid, String patternType);
}

//private String[]	  stillLifePatterns	= { "Block", "Beehive", "Loaf", "Boat", "Tub" };
//private String[]	  oscilatorPatterns	= { "Blinker", "Toad", "Beacon", "Pulsar", "Pentadecathon" };
//private String[]	  spaceshipPatterns	= { "Glider", "LWSS" };
//private String[]	  breederPatterns	= { "Gosper glider gun" };