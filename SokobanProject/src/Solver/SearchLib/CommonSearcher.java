package Solver.SearchLib;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public abstract class CommonSearcher<T> implements Searcher<T> {
	
	protected PriorityQueue<State<T>> open;
	HashSet<State<T>> closed;
	HashMap<Action, State<T>> successores;
	protected int evaluatedNodes;
	
	public CommonSearcher()
	{
		evaluatedNodes =0;
		open=new PriorityQueue<>((p1, p2) -> (int)(p1.getCost()-p2.getCost()));
		closed=new HashSet<>();
	}


	
	@Override
	public int getNumberOfNodesEvaluated() {		
		return evaluatedNodes;
	}
	
	protected Solution backTrace(State<T> goalState) {
		LinkedList<Action> actions = new LinkedList<Action>();
		
		State<T> goal = goalState;
		
		while (goal.getCameFrom() != null) {	
			actions.addFirst(goal.getAction());
			goal = goal.getCameFrom();
		}
		
		Solution sol = new Solution();
		sol.setActions(actions);
		return sol;
	
}
}