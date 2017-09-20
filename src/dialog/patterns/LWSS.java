package dialog.patterns;

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class LWSS implements Pattern, GifPattern {

	@Override
	public List<Integer[]> createPattern() {
		List<Integer[]> storedPatternPositons = new ArrayList<>();
		storedPatternPositons.add(new Integer[] { 0, 0 });
		storedPatternPositons.add(new Integer[] { 0, 3 });

		storedPatternPositons.add(new Integer[] { 1, 4 });

		storedPatternPositons.add(new Integer[] { 2, 0 });
		storedPatternPositons.add(new Integer[] { 2, 4 });

		storedPatternPositons.add(new Integer[] { 3, 1 });
		storedPatternPositons.add(new Integer[] { 3, 2 });
		storedPatternPositons.add(new Integer[] { 3, 3 });
		storedPatternPositons.add(new Integer[] { 3, 4 });

		return storedPatternPositons;
	}

	@Override
	public Icon createGif(String path) {
		Icon icon = new ImageIcon(path);
		return icon;
	}
}
