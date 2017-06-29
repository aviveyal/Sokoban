package Solver.StripsLib;

import java.util.List;
/**
 * 
 * @author Aviv Eyal
 *	execute plan for a plannable algorithm
 */
public interface Planner {

	List<Action> plan(Plannable plannable);
}

