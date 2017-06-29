package model.Data;

/**
 * 
 * @author Aviv Eyal Box position
 */
public class Box extends CommonObject {
	
	public static final char boxessymbol = '@';
	
	public Box(int x,int y)
	{
	 position(x,y);	
	}
	
	public char getBoxessymbol() {
		return boxessymbol;
	}
	
		
}
