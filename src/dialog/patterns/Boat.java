package dialog.patterns;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Boat implements Pattern {

	@Override
	public List<Integer[]> createPattern() {
		List<Integer[]> storedPatternPositons = new ArrayList<>();
		storedPatternPositons.add(new Integer[] { 0, 0 });
		storedPatternPositons.add(new Integer[] { 0, 1 });
		storedPatternPositons.add(new Integer[] { 1, 0 });
		storedPatternPositons.add(new Integer[] { 1, 2 });
		storedPatternPositons.add(new Integer[] { 2, 1 });

		return storedPatternPositons;
	}

	@Override
	public BufferedImage createImage(String path) throws IOException {
		BufferedImage image = ImageIO.read(new File(path));
		return image;
	}
}
