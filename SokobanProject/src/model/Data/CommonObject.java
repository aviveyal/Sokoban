package model.Data;

/**
 * 
 * @author Aviv Eyal this is common object , for each object in the game we need
 *         to hold position objects extends this class to hold position
 */
public abstract class CommonObject {

	/* position is common */
	public int x;
	public int y;

	public void position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
