package levels;

public class Wall extends CommonObject {
	
	private int x;
	private int y;
	
	public final char wallsymbol = '#';
	
	public Wall(int x,int y)
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

			public char getWallsymbol() {
				return wallsymbol;
			}
	
}
