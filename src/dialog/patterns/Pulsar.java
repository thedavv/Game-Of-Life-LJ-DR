package dialog.patterns;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * Creates a Pulsar object and has method createPattern that stores the
 * positions of active squares
 */
public class Pulsar implements Pattern, GifPattern{

	@Override
	public List<Integer[]> createPattern() {
		List<Integer[]> storedPatternPositons = new ArrayList<>();
		storedPatternPositons.add(new Integer[] { 0, 2 });
		storedPatternPositons.add(new Integer[] { 0, 3 });
		storedPatternPositons.add(new Integer[] { 0, 4 });
		storedPatternPositons.add(new Integer[] { 0, 8 });
		storedPatternPositons.add(new Integer[] { 0, 9 });
		storedPatternPositons.add(new Integer[] { 0, 10 });
		
		storedPatternPositons.add(new Integer[] { 2, 0 });
		storedPatternPositons.add(new Integer[] { 2, 5 });
		storedPatternPositons.add(new Integer[] { 2, 7 });
		storedPatternPositons.add(new Integer[] { 2, 12 });
		
		storedPatternPositons.add(new Integer[] { 3, 0 });
		storedPatternPositons.add(new Integer[] { 3, 5 });
		storedPatternPositons.add(new Integer[] { 3, 7 });
		storedPatternPositons.add(new Integer[] { 3, 12 });
		
		storedPatternPositons.add(new Integer[] { 4, 0 });
		storedPatternPositons.add(new Integer[] { 4, 5 });
		storedPatternPositons.add(new Integer[] { 4, 7 });
		storedPatternPositons.add(new Integer[] { 4, 12 });
		
		storedPatternPositons.add(new Integer[] { 5, 2 });
		storedPatternPositons.add(new Integer[] { 5, 3 });
		storedPatternPositons.add(new Integer[] { 5, 4 });
		storedPatternPositons.add(new Integer[] { 5, 8 });
		storedPatternPositons.add(new Integer[] { 5, 9 });
		storedPatternPositons.add(new Integer[] { 5, 10 });
		
		storedPatternPositons.add(new Integer[] { 7, 2 });
		storedPatternPositons.add(new Integer[] { 7, 3 });
		storedPatternPositons.add(new Integer[] { 7, 4 });
		storedPatternPositons.add(new Integer[] { 7, 8 });
		storedPatternPositons.add(new Integer[] { 7, 9 });
		storedPatternPositons.add(new Integer[] { 7, 10 });
		
		storedPatternPositons.add(new Integer[] { 8, 0 });
		storedPatternPositons.add(new Integer[] { 8, 5 });
		storedPatternPositons.add(new Integer[] { 8, 7 });
		storedPatternPositons.add(new Integer[] { 8, 12 });
		
		storedPatternPositons.add(new Integer[] { 9, 0 });
		storedPatternPositons.add(new Integer[] { 9, 5 });
		storedPatternPositons.add(new Integer[] { 9, 7 });
		storedPatternPositons.add(new Integer[] { 9, 12 });
		
		storedPatternPositons.add(new Integer[] { 10, 0 });
		storedPatternPositons.add(new Integer[] { 10, 5 });
		storedPatternPositons.add(new Integer[] { 10, 7 });
		storedPatternPositons.add(new Integer[] { 10, 12 });
		
		storedPatternPositons.add(new Integer[] { 12, 2 });
		storedPatternPositons.add(new Integer[] { 12, 3 });
		storedPatternPositons.add(new Integer[] { 12, 4 });
		storedPatternPositons.add(new Integer[] { 12, 8 });
		storedPatternPositons.add(new Integer[] { 12, 9 });
		storedPatternPositons.add(new Integer[] { 12, 10 });
		
		return storedPatternPositons;
	}

	@Override
	public Icon createGif(String path) {
		 Icon icon = new ImageIcon(path);
		return icon;
	}

}
