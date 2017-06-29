package Solver.SearchLib;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Aviv Eyal holds an action for box (which way need to move) the mini
 *         action are list of action for player to get to the right place make
 *         it possible push the box
 */
public class Action {
	private String name;
	private List<Action> miniaction;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Action(String name, List<Action> mini) {
		this.name = name;
		this.miniaction = mini;
	}

	public List<Action> getMiniaction() {
		return miniaction;
	}

	public void setMiniaction(List<Action> miniaction) {
		this.miniaction = miniaction;
	}

	@Override
	public boolean equals(Object obj) {
		Action a = (Action) obj;

		if (a.name.equals(name)) {
			for (int i = 0; i < a.getMiniaction().size(); i++) {
				if (a.getMiniaction().get(i) != this.getMiniaction().get(i))
					return false;
			}
		} else
			return false;

		return true;

	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		if (this.miniaction != null) {
			for (Action m : this.miniaction) {
				sb.append(m.getName()).append("\n");
			}
		}
		sb.append(this.getName());

		return sb.toString();

	}

}
