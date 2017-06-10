package Adapter.Search;

import SerachLib.search.Position;

public class SokobanStateMove {

	private Position player;
	private char[][] leveldata;

	public SokobanStateMove(Position player ,char [][] leveldata) {
		this.player = player;
		this.leveldata =leveldata;
	}

	public char[][] getLeveldata() {
		return leveldata;
	}

	public void setLeveldata(char[][] leveldata) {
		this.leveldata = leveldata;
	}

	public Position getPlayer() {
		return player;
	}

	public void setPlayer(Position player) {
		this.player = player;
	}

	@Override
	public String toString() {
		
		String str = "";
		
		for(int i=0 ; i<this.leveldata.length;i++)
			for(int j=0;j<this.leveldata[i].length;j++)
			{
				if(j==this.leveldata[i].length-1)
				{
					str+="\n";
				}
				else
				str+=this.leveldata[i][j];
			}
		
		
		/*str += this.player.getRow();
		str+=",";
		str += this.player.getCol();
		str += "\n";
		*/
		return str;
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		SokobanStateMove state = (SokobanStateMove) obj;
		
		for(int i=0 ; i<this.leveldata.length;i++)
			for(int j=0;j<this.leveldata[i].length;j++)
			{
				if(this.leveldata[i][j]!= state.leveldata[i][j] )
				{
					return false;
					
				}
			}
		/*
		if (this.getPlayer().getRow() != state.getPlayer().getRow()
				|| this.getPlayer().getCol() != state.getPlayer().getCol())
			return false;
		else
			return true;
			*/
		return true;
	}
}
