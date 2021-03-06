package Solver.SearchLib;

/**
 * 
 * @author Aviv Eyal for each step the search algorithem does it need to know
 *         where came from what the cost and the action made to make it possible
 *         calculate the solution
 * @param <T>
 */
public class State<T> {
	private T state;
	private State<T> cameFrom;
	private Action action;
	private double cost;

	public T getState() {
		return state;
	}

	public void setState(T state) {
		this.state = state;
	}

	public State<T> getCameFrom() {
		return cameFrom;
	}

	public void setCameFrom(State<T> cameFrom) {
		this.cameFrom = cameFrom;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public State(T state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return state.toString();
	}

	@Override
	public int hashCode() {
		return state.hashCode();
	}

	@Override
	public boolean equals(Object o) {

		State<T> s = (State<T>) o;
		return this.state.equals(s.getState());
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
}
