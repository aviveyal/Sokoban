package controller.commands;

import java.io.IOException;

import model.Model;
import model.Data.Level;
import view.View;

public class DisplayCommand extends Command{

private Model model;
private View view;
	
	public DisplayCommand(Model model,View view) {
		this.model = model;
		this.view=view;
	}
	@Override
	public void execute() {
		Level level=model.getCurrentLevel();
		
		/*String[][] st=level.makestring();
		System.out.println("");
		for(int i=0;i<=level.maxrowsize();i++)
		{
			for(int j=0;j<=level.maxcolumnsize();j++)
			{
				System.out.print(st[i][j]);
			
			if(j==level.maxcolumnsize())
				System.out.println("");
			}
		}*/
		
		view.mDisplayCommand(level);
		
	
	}

}
