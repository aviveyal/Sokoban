package Solver.SearchLib;

import java.util.HashSet;

/**
 * 
 * @author Aviv Eyal
 *	Simply Dijkstra algorithm
 * @param <T>
 */
public class Dijkstra<T> extends CommonSearcher<T> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see Solver.SearchLib.Searcher#search(Solver.SearchLib.Searchable)
	 */
	@Override
	public Solution search(Searchable<T> s) {

		open.add(s.getInitialState());

		while (!open.isEmpty()) {
			State<T> currentState = open.poll();// add source

			closed.add(currentState); // add to visited nodes

			this.evaluatedNodes++;
			successores = s.getAllPossibleMoves(currentState);
			for (Action a : successores.keySet()) {
				State<T> n = successores.get(a);

				double cost = currentState.getCost() + 1;// previous cost + next

				if (!closed.contains(n)) {
					if (!open.contains(n)) {
						n.setAction(a);
						n.setCameFrom(currentState);
						n.setCost(cost);
						open.add(n);

					} else // if this state exists in open list - update his
							// info
					{
						// find the state
						for (State<T> state : open) {
							if (state.equals(n)) {

								if (n.getCost() < state.getCost()) {
									open.remove(state); // remove old state
									n.setCameFrom(currentState);
									n.setAction(a);
									n.setCost(cost);
									open.add(n);
								}

							}

						}
					}

				}

			}

		}
		//return the shortest path
		for(State<T> state : closed)
		{
			if(state.equals(s.getGoalState()))
				return backTrace(state);
		}
		return null;
	}
}