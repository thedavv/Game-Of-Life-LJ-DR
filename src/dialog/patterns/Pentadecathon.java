package dialog.patterns;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Creates a Pentadecathon object and has method createPattern that stores the
 * positions of active squares
 */
public class Pentadecathon implements Pattern, GifPattern{

	@Override
	public List<Integer[]> createPattern() {
		List<Integer[]> storedPatternPositons = new ArrayList<>();
		storedPatternPositons.add(new Integer[] { 0, 1 });
		storedPatternPositons.add(new Integer[] { 1, 1 });
		
		storedPatternPositons.add(new Integer[] { 2, 0 });
		storedPatternPositons.add(new Integer[] { 2, 2 });
		
		storedPatternPositons.add(new Integer[] { 3, 1 });
		storedPatternPositons.add(new Integer[] { 4, 1 });
		storedPatternPositons.add(new Integer[] { 5, 1 });
		storedPatternPositons.add(new Integer[] { 6, 1 });
		
		storedPatternPositons.add(new Integer[] { 7, 0 });
		storedPatternPositons.add(new Integer[] { 7, 2 });
		
		storedPatternPositons.add(new Integer[] { 8, 1 });
		storedPatternPositons.add(new Integer[] { 9, 1 });
		
		return storedPatternPositons;
	}

	@Override
	public Icon createGif(String path) {
		 Icon icon = new ImageIcon(path);
		return icon;
	}

}
