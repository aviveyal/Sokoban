package Solver.SearchLib;

public interface Searcher<T> {
	Solution search(Searchable<T> s);
	int getNumberOfNodesEvaluated();
}
