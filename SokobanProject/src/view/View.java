package view;

import java.io.IOException;


import model.Data.Level;

/**
 * 
 * @author Aviv Eyal the view layer of the project,main responsible for display
 *         the game
 */
public interface View {

	public void mDisplayCommand(Level level) throws IOException;
	public void getSolution(String solution);

	



}
