package Solver.StripsLib;

import java.util.Set;

/**
 * 
 * @author Aviv Eyal plannable problem bridge patten
 */
public interface Plannable {

	Clause getGoal();

	Clause getKnowledgebase();

	Set<Action> getsatisfyingActions(Predicate top);

	Action getsatisfyingAction(Predicate top);

}
