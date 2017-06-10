package Solver.SearchLib;

import java.util.ArrayList;
import java.util.List;

public class PositionAndActions {

	public Position pos ;
	public List <Action> act;
	
	public PositionAndActions(Position pos, List<Action> act) {
		
		this.pos = pos;
		this.act = act;
	}

	public Position getPos() {
		return pos;
		
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public List<Action> getAct() {
		return act;
	}

	public void setAct(ArrayList<Action> act) {
		this.act = act;
	}
	
	
	
}
