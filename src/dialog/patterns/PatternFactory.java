package dialog.patterns;

public class PatternsFactory {
	public Patterns createPattern(String patternName){
		switch (patternName) {
		case "Block":
			return new Block();

		default:
			return null;
		}
	}
}
