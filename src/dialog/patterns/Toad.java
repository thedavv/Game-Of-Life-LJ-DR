package dialog.patterns;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Toad implements Pattern, GifPattern {

	@Override
	public List<Integer[]> createPattern() {
		List<Integer[]> storedPatternPositons = new ArrayList<>();
		storedPatternPositons.add(new Integer[] { 0, 2 });
		
		storedPatternPositons.add(new Integer[] { 1, 0 });
		storedPatternPositons.add(new Integer[] { 1, 3 });
		
		storedPatternPositons.add(new Integer[] { 2, 0 });
		storedPatternPositons.add(new Integer[] { 2, 3 });
		
		storedPatternPositons.add(new Integer[] { 3, 1 });
		
		return storedPatternPositons;
	}

	@Override
	public Icon createGif(String path) {
		 Icon icon = new ImageIcon(path);
		return icon;
	}

}
