package view;

import java.io.IOException;


import model.Data.Level;

public interface View {

	public void mDisplayCommand(Level level) throws IOException;
	public void getSolution(String solution);

	



}
