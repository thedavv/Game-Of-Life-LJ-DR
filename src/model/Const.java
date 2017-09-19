package model;

import java.awt.Color;

public enum Const {
	INSTANCE;

	private Const() {
	}

	public static Const getInstance() {
		return INSTANCE;
	}

	public final static Color TRUE_BLUE = new Color(45, 104, 196);
	public final static Color MAT_BLUE = new Color(59, 89, 182);
	public final static Color PALE_GRAY = new Color(205, 205, 205);
	public final static Color SILVER = new Color(192, 192, 192);
	public final static Color DIM_GRAY = new Color(105, 105, 105);
	public final static Color STEEL_GRAY = new Color(77, 136, 153);
	public final static Color BLURRY_WOOD = new Color(222,184,135);
	public final static Color WHITE = Color.WHITE;
	

	// public final static Color lightGray = new Color(192, 192, 192);
}
