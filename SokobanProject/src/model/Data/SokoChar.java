package model.Data;

public class SokoChar extends CommonObject{
	
	private int x;
	private int y;
	public static final char sokosymbol = 'A';
	
	public SokoChar(int x,int y)
	{
	 position(x,y);	
	}
	@Override
	public void position(int x, int y) {
		this.x=x;
		this.y=y;
		
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
	public char getSokosymbol() {
		return sokosymbol;
	}

	
}
