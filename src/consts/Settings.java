package consts;

public enum Settings {
	INSTANCE;

	private Settings() {
	}

	public static Settings getInstance() {
		return INSTANCE;
	}

	// tu budu ulozene veci pre Menu > Settings tab + default settings
}
