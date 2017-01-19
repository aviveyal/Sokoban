package controller.commands;



import java.io.IOException;
import java.util.List;

import controller.Controller;
import controller.SokobanController;
import model.Model;
import model.Data.Level;
import view.View;

public abstract class Command{
		
	List<String> params;
	
	/*SokobanController controller = null;
	
	public void setController(SokobanController controller) {
		this.controller = controller;
	}*/	
	
	public void setParams(List<String> params) {
		this.params = params;
	}
	
	public abstract void execute() throws IOException ;
}
