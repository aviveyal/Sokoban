package model.Data;

public class Clean extends CommonObject {

	public static final char cleansymbol = ' ';

	public Clean(int x, int y) {
		position(x, y);
	}

	public char getCleansymbol() {
		return cleansymbol;
	}

}
