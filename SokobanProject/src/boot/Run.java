package boot;

import controller.SokobanController;
import model.MyModel;
import view.CLI;

public class Run {

	public static void main(String[] args) {
		MyModel model = new MyModel();
		CLI view = new CLI();
		SokobanController controller = new SokobanController(view, model);
		model.addObserver(controller);
		view.addObserver(controller);

		view.start();

	}

}
