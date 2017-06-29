package Solver.SearchLib;

import java.awt.List;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 * @author Aviv Eyal
 *Simply Generic BFS algorithm
 *
 * @param <T>
 */
public class BFS<T> extends CommonSearcher<T> {

	@Override
	public Solution search(Searchable<T> s) {

		State<T> state = s.getInitialState();

		State<T> goal = s.getGoalState();

		this.open.add(state);

		while (this.open.size() > 0) {

			State<T> currState = this.open.poll();
			evaluatedNodes++;

			closed.add(currState);
			// System.out.print("currstate " +currState);

			if (currState.equals(s.getGoalState())) {
				return backTrace(currState);

			}

			successores = s.getAllPossibleMoves(currState);

			for (Action a : successores.keySet()) {
				State<T> n = successores.get(a);

				if (!closed.contains(n)) {
					if (!this.open.contains(n)) {

						n.setCameFrom(currState);
						n.setAction(a);
						n.setCost(currState.getCost() + 1);
						this.open.add(n);
					} else // state exist in open list -find him and delete
					{
						for (State<T> openStates : this.open) {
							if (state.equals(n)) {

								if (n.getCost() < state.getCost()) {
									open.remove(state); // remove old state
									n.setCameFrom(currState);
									n.setAction(a);
									n.setCost(currState.getCost() + 1);
									open.add(n);
								}

							}
						}

					}
				}

			}

		}

		return null;
	}

}