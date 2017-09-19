package dialog.patterns;

public class PatternFactory {

	public ImagePattern createImagePattern(String patternName) {
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
		case "Gosper glider gun":
			return new GosperGliderGun();
		default:
			return null;
		}
	}

	public GifPattern createGifPattern(String patternName) {
		switch (patternName) {
		case "Blinker":
			return new Blinker();
		case "Toad":
			return new Toad();
		case "Beacon":
			return new Beacon();
		case "Pulsar":
			return new Pulsar();
		case "Pentadecathon":
			return new Pentadecathon();
		case "Glider":
			return new Glider();
		case "LWSS":
			return new LWSS();
		default:
			return null;
		}
	}
}
