package controller.commands;



import controller.commands.Command;
import model.Model;

public class MoveCommand extends Command{
	
private Model model;
	
	public MoveCommand(Model model) {
		this.model = model;
	}

	@Override
	public void execute() {
		String direction=params.get(0);
		System.out.println(direction);
		model.mMoveCommand(direction);	
		
	}

	
}