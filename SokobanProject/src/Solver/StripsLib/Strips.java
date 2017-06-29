package Solver.StripsLib;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * 
 * simply strips algorithm
 * 
 */
public class Strips implements Planner {

	private Plannable plannable;

	@Override
	public List<Action> plan(Plannable plannable) {
		LinkedList<Action> plan = new LinkedList<>();
		this.plannable = plannable;
		Stack<Predicate> stack = new Stack<>();
		stack.push(plannable.getGoal());
		while (!stack.isEmpty()) {
			Predicate top = stack.peek();
			// System.out.println(plannable.getKnowledgebase());// unsatisfied
			if (!(top instanceof Action)) {
				if (!plannable.getKnowledgebase().satisfies(top)) {
					if (top instanceof Clause) { // multipart
						Clause c = (Clause) top;
						stack.pop();
						for (Predicate p : c.predicates) {
							stack.push(p);
						}
					} else { // single and unsatisfied
						stack.pop();
						Action action = plannable.getsatisfyingAction(top);
						stack.push(action);
						stack.push(action.preconditions);
					}
				} else {
					stack.pop();
				}
			} else { // top is an action at the top of the stack
				stack.pop();
				Action a = (Action) top;
				plannable.getKnowledgebase().update(a.effects);
				plan.add(a);
				// System.out.println("plan: "+ plan);
			}
		}
		return plan;
	}

}
