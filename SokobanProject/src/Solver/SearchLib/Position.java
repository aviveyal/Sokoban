package Solver.SearchLib;

public class Position {
	int row, col;
	
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	@Override
	public String toString() {
		
	String str="";
	str+=this.getRow();
	str+=",";
	str+=this.getCol();
	str+="\n";
	
	return str;
	}
}
