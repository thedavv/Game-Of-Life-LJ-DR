package dialog.patterns;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * Creates a GosperGliderGun object and has method createPattern that stores the
 * positions of active squares
 */
public class GosperGliderGun implements Pattern, ImagePattern{

	@Override
	public BufferedImage createImage(String path) throws IOException {
		BufferedImage image = ImageIO.read(new File(path));
		return image;
	}

	@Override
	public List<Integer[]> createPattern() {
		List<Integer[]> storedPatternPositons = new ArrayList<>();
		storedPatternPositons.add(new Integer[] { 0, 24 });
		
		storedPatternPositons.add(new Integer[] { 1, 24 });
		storedPatternPositons.add(new Integer[] { 1, 22 });
		
		
		storedPatternPositons.add(new Integer[] { 2, 12 });
		storedPatternPositons.add(new Integer[] { 2, 13 });
		storedPatternPositons.add(new Integer[] { 2, 20 });
		storedPatternPositons.add(new Integer[] { 2, 21 });
		storedPatternPositons.add(new Integer[] { 2, 34 });
		storedPatternPositons.add(new Integer[] { 2, 35 });
		
		storedPatternPositons.add(new Integer[] { 3, 11 });
		storedPatternPositons.add(new Integer[] { 3, 15 });
		storedPatternPositons.add(new Integer[] { 3, 20 });
		storedPatternPositons.add(new Integer[] { 3, 21 });
		storedPatternPositons.add(new Integer[] { 3, 34 });
		storedPatternPositons.add(new Integer[] { 3, 35 });
		
		storedPatternPositons.add(new Integer[] { 4, 0 });
		storedPatternPositons.add(new Integer[] { 4, 1 });
		storedPatternPositons.add(new Integer[] { 4, 10 });
		storedPatternPositons.add(new Integer[] { 4, 16 });
		storedPatternPositons.add(new Integer[] { 4, 20 });
		storedPatternPositons.add(new Integer[] { 4, 21 });
		
		storedPatternPositons.add(new Integer[] { 5, 0 });
		storedPatternPositons.add(new Integer[] { 5, 1 });
		storedPatternPositons.add(new Integer[] { 5, 10 });
		storedPatternPositons.add(new Integer[] { 5, 14 });
		storedPatternPositons.add(new Integer[] { 5, 16 });
		storedPatternPositons.add(new Integer[] { 5, 17 });
		storedPatternPositons.add(new Integer[] { 5, 22 });
		storedPatternPositons.add(new Integer[] { 5, 24 });
		
		storedPatternPositons.add(new Integer[] { 6, 10 });
		storedPatternPositons.add(new Integer[] { 6, 16 });
		storedPatternPositons.add(new Integer[] { 6, 24 });

		storedPatternPositons.add(new Integer[] { 7, 11 });
		storedPatternPositons.add(new Integer[] { 7, 15 });
		
		storedPatternPositons.add(new Integer[] { 8, 12 });
		storedPatternPositons.add(new Integer[] { 8, 13 });
		
		return storedPatternPositons;
	}

}
