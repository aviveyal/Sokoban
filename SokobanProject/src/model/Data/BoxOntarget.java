package model.Data;

public class BoxOntarget extends CommonObject {
	
	private int x;
	private int y;
	public static final char boxesontargetsymbol = 'V';
	
	

	public BoxOntarget(int x,int y)
	{
	 position(x,y);	
	}
	@Override
	public void position(int x, int y) {
		this.x=x;
		this.y=y;
		
	}
	public char boxesontargetsymbol() {
		return boxesontargetsymbol;
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
