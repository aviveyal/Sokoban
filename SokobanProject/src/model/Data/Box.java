package model.Data;

public class Box extends CommonObject {
	
	private int x;
	private int y;
	private final char boxessymbol = '@';
	
	

	public Box(int x,int y)
	{
	 position(x,y);	
	}
	@Override
	public void position(int x, int y) {
		this.x=x;
		this.y=y;
		
	}
	public char getBoxessymbol() {
		return boxessymbol;
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
