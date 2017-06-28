package view;


import java.util.Observable;
import model.Data.Level;
import model.modelCommands.Display;

public class CLI extends Observable implements View {


	@Override
	public void mDisplayCommand(Level level) {
		Display D = new Display(level);
		D.execute();

	
	}

	@Override
	public void getSolution(String solution) {
		// TODO Auto-generated method stub
		
	}

}