package dialog.patterns;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Blinker implements Pattern, GifPattern {

	public List<Integer[]> createPattern() {
		List<Integer[]> storedPatternPositons = new ArrayList<>();
		storedPatternPositons.add(new Integer[] { 0, 0 });
		storedPatternPositons.add(new Integer[] { 1, 0 });
		storedPatternPositons.add(new Integer[] { 2, 0 });
		
		return storedPatternPositons;
	}

	@Override
	public Icon createGif(String path) {
		 Icon icon = new ImageIcon(path);
		return icon;
	}
}
