package boot;


import controller.SokobanController;
import model.Model;
import model.MyModel;
import view.CLI;
import view.GUI;
import view.View;

public class Run {

	public static void main(String[] args) {
		MyModel model = new MyModel();
	//	CLI view = new CLI();
		GUI view = new GUI();
		SokobanController controller = new SokobanController(view, model);
		model.addObserver(controller);
		view.addObserver(controller);
		
		view.start();
		//controller.update(model, "Load");
		
	}
	

}
