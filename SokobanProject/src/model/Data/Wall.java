package model.Data;

public class Wall extends CommonObject {

	public static final char wallsymbol = '#';

	public Wall(int x, int y) {
		position(x, y);
	}

	public char getWallsymbol() {
		return wallsymbol;
	}

}
