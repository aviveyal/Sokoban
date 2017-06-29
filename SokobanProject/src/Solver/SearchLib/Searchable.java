package Solver.SearchLib;

import java.util.HashMap;

/**
 * 
 * @author Aviv Eyal bridge pattern searchable problem
 * @param <T>
 */

public interface Searchable<T> {
	State<T> getInitialState();

	State<T> getGoalState();

	// List<State<T>> getAllPossibleStates(State<T> state);
	HashMap<Action, State<T>> getAllPossibleMoves(State<T> state);

}
