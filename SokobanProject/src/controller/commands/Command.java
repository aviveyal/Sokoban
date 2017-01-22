package controller.commands;



import java.io.IOException;
import java.util.List;

import controller.Controller;
import controller.SokobanController;
import model.Model;
import model.Data.Level;
import view.View;

public abstract class Command implements SokobanCommand{
		
	List<String> params;
	
	protected Model model;
	protected View view;
		
		
	public void setParams(List<String> params) {
		this.params = params;
	}
	
	
}
