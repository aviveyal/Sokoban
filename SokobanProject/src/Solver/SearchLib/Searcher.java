package Solver.SearchLib;

/**
 * 
 * @author Aviv Eyal run a solve function with a searchable problem also holds
 *         number of nodes evaluated
 * @param <T>
 */
public interface Searcher<T> {
	Solution search(Searchable<T> s);

	int getNumberOfNodesEvaluated();
}
