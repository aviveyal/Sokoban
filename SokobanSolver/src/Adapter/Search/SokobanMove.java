package Adapter.Search;


import java.util.HashMap;
import SerachLib.search.Action;
import SerachLib.search.Position;
import SerachLib.search.Searchable;
import SerachLib.search.State;


public class SokobanMove implements Searchable<SokobanStateMove>{

	char [][] leveldata;
	Position player;
	Position target;
	State <SokobanStateMove> initialState;
	State <SokobanStateMove> goalState;
	
	public SokobanMove(char[][] leveldata,Position player ,Position target) {
		this.leveldata = leveldata;
		this.player =player;
		this.target= target;
		
	}
	
	
	@Override
	public State<SokobanStateMove> getInitialState() {
		
		SokobanStateMove initstate = new SokobanStateMove(player,this.leveldata);
		this.initialState = new State<SokobanStateMove>(initstate);
		return initialState;		
	}
	

	@Override
	public State<SokobanStateMove> getGoalState() {
		
		char[][] board =copyBoard(this.leveldata);
		
		//Printlevel(board);
		
		board[this.target.getRow()][this.target.getCol()]=board[this.player.getRow()][this.player.getCol()];
		board[this.player.getRow()][this.player.getCol()]=' ' ;
		
		SokobanStateMove goalstate = new SokobanStateMove(this.target,board);
		this.goalState = new State<SokobanStateMove>(goalstate);
		return goalState;
	}
	
	public void Printlevel(char[][] levelstr)
	{
		for(int i=0;i<levelstr.length;i++)
			for(int j=0;j<levelstr[i].length;j++)
			{
				if(j==levelstr[i].length-1)
				{
					System.out.println();
				}
				else
				System.out.print(levelstr[i][j]);
			}
	}
	
	private char[][] copyBoard(char[][] board) {

		char[][] newBoard = new char[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				newBoard[i][j] = board[i][j];
			}

		}
		return newBoard;
	}

		
	@Override
	public HashMap<Action, State<SokobanStateMove>> getAllPossibleMoves(State<SokobanStateMove> state) {
		
		HashMap<Action, State<SokobanStateMove>> map = new HashMap<>();
		Position playerpos = state.getState().getPlayer();
		char [][]board = state.getState().getLeveldata();
		char[][] copyboard = copyBoard(board);
		
		//List<PositionAndActions> pos = getEmptyCell(board,playerpos);
						
					copyboard = copyBoard(board);
					
					if(copyboard[playerpos.getRow()+1][playerpos.getCol()]==' ' ||copyboard[playerpos.getRow()+1][playerpos.getCol()]=='o')
						{
							
						Position newpos = new Position(playerpos.getRow()+1,playerpos.getCol());
						copyboard[playerpos.getRow()+1][playerpos.getCol()] = copyboard[playerpos.getRow()][playerpos.getCol()];
						copyboard[playerpos.getRow()][playerpos.getCol()]=' ';
						
						
						
						SokobanStateMove s = new SokobanStateMove(newpos,copyboard);
						State<SokobanStateMove> newState = new State<SokobanStateMove>(s);
						map.put(new Action("move down",null), newState);
						//System.out.println("added pos : "+ newpos.getRow() +","+ newpos.getCol());
						
					}
					copyboard = copyBoard(board);
					if(copyboard[playerpos.getRow()-1][playerpos.getCol()]==' ' ||copyboard[playerpos.getRow()-1][playerpos.getCol()]=='o')
					{
						Position newpos = new Position(playerpos.getRow()-1,playerpos.getCol());
						copyboard[playerpos.getRow()-1][playerpos.getCol()] = copyboard[playerpos.getRow()][playerpos.getCol()];
						copyboard[playerpos.getRow()][playerpos.getCol()]=' ';
						
					
						
						SokobanStateMove s = new SokobanStateMove(newpos,copyboard);
						State<SokobanStateMove> newState = new State<SokobanStateMove>(s);
						map.put(new Action("move up",null), newState);
						//System.out.println("added pos : "+ newpos.getRow() +","+ newpos.getCol());

					}
					copyboard = copyBoard(board);
					if(copyboard[playerpos.getRow()][playerpos.getCol()+1]==' ' ||copyboard[playerpos.getRow()][playerpos.getCol()+1]=='o')
					{
						Position newpos = new Position(playerpos.getRow(),playerpos.getCol()+1);

						copyboard[playerpos.getRow()][playerpos.getCol()+1] = copyboard[playerpos.getRow()][playerpos.getCol()];
						copyboard[playerpos.getRow()][playerpos.getCol()]=' ';
						
						
						SokobanStateMove s = new SokobanStateMove(newpos,copyboard);
						State<SokobanStateMove> newState = new State<SokobanStateMove>(s);
						map.put(new Action("move right",null), newState);
						//System.out.println("added pos : "+ newpos.getRow() +","+ newpos.getCol());

					}
					copyboard = copyBoard(board);
					if(copyboard[playerpos.getRow()][playerpos.getCol()-1]==' ' ||copyboard[playerpos.getRow()][playerpos.getCol()-1]=='o')
					{
						Position newpos = new Position(playerpos.getRow(),playerpos.getCol()-1);

						copyboard[playerpos.getRow()][playerpos.getCol()-1] = copyboard[playerpos.getRow()][playerpos.getCol()];
						copyboard[playerpos.getRow()][playerpos.getCol()]=' ';
					
						
						SokobanStateMove s = new SokobanStateMove(newpos,copyboard);
						State<SokobanStateMove> newState = new State<SokobanStateMove>(s);
						map.put(new Action("move left",null), newState);
						//System.out.println("added pos : "+ newpos.getRow() +","+ newpos.getCol());

					}
					
			
		
		return map;	

	}
	
	
}