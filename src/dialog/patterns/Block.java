package dialog.patterns;

import java.util.ArrayList;
import java.util.List;

public class Block implements Patterns {

	@Override
	public List<Integer[]> createPattern() {
		List<Integer[]> storedPatternPositons = new ArrayList<>();
		storedPatternPositons.add(new Integer[] { 0, 0 });
		storedPatternPositons.add(new Integer[] { 0, 1 });
		storedPatternPositons.add(new Integer[] { 1, 0 });
		storedPatternPositons.add(new Integer[] { 1, 1 });

		return storedPatternPositons;
	}
}
