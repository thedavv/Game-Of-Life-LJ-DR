package dialog.patterns;

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
}
