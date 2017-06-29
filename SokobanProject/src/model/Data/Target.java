package model.Data;

/**
 * 
 * @author Aviv Eyal targets on level
 */

public class Target extends CommonObject {

	public static final char targetsymbol = 'o';

	public Target(int x, int y) {
		position(x, y);
	}

	public char getTargetsymbol() {
		return targetsymbol;
	}

}
