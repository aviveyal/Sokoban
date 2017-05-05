package SerachLib;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;


public class BFS<T> extends CommonSearcher<T> {

	@Override
	public Solution search(Searchable<T> s) {
		HashSet<State<T>> visited = new HashSet<>();
		State<T> state = s.getInitialState();

		
		PriorityQueue<State<T>> queue = new PriorityQueue<>(new Comparator<State<T>>() {
			@Override
			public int compare(State<T> o1, State<T> o2) {
				if (o1.getCost() - o2.getCost() < 0)
					return -1;
				if (o1.getCost() - o2.getCost() > 0)
					return 1;
				else
					return 0;

			}
		});

		queue.add(state);

		while (!queue.isEmpty()) {
			State<T> currState = queue.poll();

			if (currState.equals(s.getGoalState()))
				return backTrace(currState);

			if (!visited.contains(currState)) {
				visited.add(currState);

				HashMap<Action, State<T>> map = s.getAllPossibleMoves(currState);

				for (Action a : map.keySet()) {
					State<T> n = map.get(a);

					if (!visited.contains(n) && !queue.contains(n)) {
						queue.add(n);
						n.setCameFrom(currState);
						n.setAction(a);
						n.setCost(currState.getCost());
					}
				}

			}

		}
		return null;
	}
}