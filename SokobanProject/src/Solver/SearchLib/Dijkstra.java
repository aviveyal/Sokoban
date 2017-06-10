package Solver.SearchLib;

import java.util.HashSet;

public class Dijkstra<T> extends CommonSearcher<T> {

	@Override
	public Solution search(Searchable<T> s) {

		open.add(s.getInitialState());

		while (!open.isEmpty()) {
			State<T> currentState = open.poll();
			this.evaluatedNodes++;
			closed.add(currentState);
			successores = s.getAllPossibleMoves(currentState);
			for (Action a : successores.keySet())
			{
				State<T> n = successores.get(a);

				n.setCameFrom(currentState);
				if (!closed.contains(n))
				{
					if (!open.contains(n))
						open.add(n);
					else
					{
						for (State<T> state : open) 
						{
							if (state.equals(n))
							{
								if (n.getCost() < state.getCost())
								{
									open.remove(state);
									open.add(n);
								}
							}
						}
					}
				}
			}
		}
		if (this.closed.contains(s.getGoalState())) {
			for (State<T> state : closed) {
				if (state.equals(s.getGoalState()))
					return this.backTrace(state);
			}
		}
		return null;
	}

}
