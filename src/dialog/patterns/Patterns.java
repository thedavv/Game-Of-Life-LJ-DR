package dialog.patterns;

import java.util.List;

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
	public List<Integer[]> createPattern();
}
