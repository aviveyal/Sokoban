package Solver.StripsLib;

/**
 * 
 * @author Aviv Eyal
 *	action to satisfy any predicate
 *each action has it preconditions and effects
 *with this data we can change predicates and make strips algorithm
 */
public class Action extends Predicate{

	public Action(String type, String id, String value) {
		super(type, id, value);
		preconditions=new Clause(null);
		effects=new Clause(null);
	}

	Clause preconditions,effects;

	
	public Clause getPreconditions() {
		return preconditions;
	}

	public void setPreconditions(Clause preconditions) {
		this.preconditions = preconditions;
	}

	public Clause getEffects() {
		return effects;
	}

	public void setEffects(Clause effects) {
		this.effects = effects;
	}
	
}
