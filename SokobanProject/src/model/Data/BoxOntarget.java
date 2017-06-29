package model.Data;

/**
 * 
 * @author Aviv Eyal Box on target position
 */
public class BoxOntarget extends CommonObject {

	public static final char boxesontargetsymbol = 'V';

	public BoxOntarget(int x, int y) {
		position(x, y);
	}

	public char boxesontargetsymbol() {
		return boxesontargetsymbol;
	}

}
