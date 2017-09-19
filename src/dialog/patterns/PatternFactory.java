package dialog.patterns;

public class PatternFactory {

	public Pattern createPattern(String patternName) {
		switch (patternName) {
		case "Block":
			return new Block();
		case "Beehive":
			return new Beehive();
		case "Loaf":
			return new Loaf();
		case "Boat":
			return new Boat();
		case "Tub":
			return new Tub();
		default:
			return null;
		}
	}
}
