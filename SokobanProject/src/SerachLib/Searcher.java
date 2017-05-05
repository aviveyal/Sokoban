package SerachLib;


public interface Searcher<T> {
	Solution search(Searchable<T> s);
	int getNumberOfNodesEvaluated();
}
