package dialog.patterns;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Creates a Beacon object and has method createPattern that stores the
 * positions of active squares
 */
public class Beacon implements Pattern, GifPattern{

	@Override
	public List<Integer[]> createPattern() {
		List<Integer[]> storedPatternPositons = new ArrayList<>();
		storedPatternPositons.add(new Integer[] { 0, 0 });
		storedPatternPositons.add(new Integer[] { 0, 1 });
		
		storedPatternPositons.add(new Integer[] { 1, 0 });
		storedPatternPositons.add(new Integer[] { 1, 1 });
		
		storedPatternPositons.add(new Integer[] { 2, 2 });
		storedPatternPositons.add(new Integer[] { 2, 3 });
		
		storedPatternPositons.add(new Integer[] { 3, 2 });
		storedPatternPositons.add(new Integer[] { 3, 3 });
		

		return storedPatternPositons;
	}

	@Override
	public Icon createGif(String path) {
		 Icon icon = new ImageIcon(path);
		return icon;
	}

}
