package model.Data;

/**
 * 
 * @author Aviv Eyal this is the game player
 */
public class SokoChar extends CommonObject {

	public static final char sokosymbol = 'A';

	public SokoChar(int x, int y) {
		position(x, y);
	}

	public char getSokosymbol() {
		return sokosymbol;
	}

}
