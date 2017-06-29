package controller.commands;

import controller.commands.Command;
import model.Model;

/**
 * 
 * @author Aviv Eyal update model with move command and also add param as
 *         direction
 */

public class MoveCommand extends Command {

	public MoveCommand(Model model) {
		this.model = model;
	}

	@Override
	public void execute() {
		String direction = params.get(0);
		model.mMoveCommand(direction);

	}

}