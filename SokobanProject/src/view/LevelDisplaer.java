package view;

import model.Data.Box;
import model.Data.BoxOntarget;
import model.Data.Level;
import model.Data.SokoChar;
import model.Data.Target;
import model.Data.Wall;

public class LevelDisplaer {

	private Level Display;

	public LevelDisplaer(Level Display) {
		this.Display = Display;
	}

	public void displaylevel() {

		int wallsnumber = 0;
		int targetsnumber = 0;
		int boxesnumber = 0;
		int sokonumber = 0;

		int maxcolumnsize = Display.maxcolumnsize();
		int maxrowsize = Display.maxrowsize();

		Wall firstwall = Display.getWalls().get(wallsnumber);
		Target firsttarget = Display.getTargets().get(targetsnumber);
		Box firstbox = Display.getBoxes().get(boxesnumber);
		SokoChar firstsoko = Display.getSokoCharas().get(sokonumber);

		BoxOntarget fakebox = new BoxOntarget(-1, -1);// in case there are no
														// box on targets
		Display.getBoxOnTareget().add(fakebox);

		boolean printedBOT = false; // if printed a box - for not printing " "
									// too
		boolean printedbox = false;

		/* while there is object to display */
		while (wallsnumber < Display.getWalls().size() && targetsnumber < Display.getTargets().size()
				&& boxesnumber < Display.getBoxes().size() && sokonumber < Display.getSokoCharas().size()) {

			for (int x = 0; x <= maxrowsize; x++) {
				for (int y = 0; y <= maxcolumnsize; y++) {

					printedBOT = false;
					printedbox = false;

					for (int i = 0; i < Display.getBoxes().size(); i++) // boxes
																		// can
																		// change
																		// the
																		// order
																		// in
																		// the
																		// array
																		// !
					{
						if ((Display.getBoxes().get(i).getX() == x) && (Display.getBoxes().get(i).getY() == y)) {
							for (int s = 0; s < Display.getBoxOnTareget().size(); s++) // box
																						// arrived
																						// to
																						// the
																						// the
																						// target
																						// !
							{
								if ((Display.getBoxOnTareget().get(s).getX() == x)
										&& (Display.getBoxOnTareget().get(s).getY() == y)) {
									System.out.print(Display.getBoxOnTareget().get(s).boxesontargetsymbol());
									printedBOT = true;
									s = Display.getBoxOnTareget().size();

									targetsnumber++; // printed V on a place
														// with target
									if (targetsnumber < Display.getTargets().size())
										firsttarget = Display.getTargets().get(targetsnumber);
								}
							}

							if (!printedBOT) {
								System.out.print(firstbox.getBoxessymbol());
								printedbox = true;
							}

						}

					}

					if (firstwall.getX() == x && firstwall.getY() == y) {
						System.out.print(firstwall.getWallsymbol());
						wallsnumber++;
						if (wallsnumber < Display.getWalls().size())
							firstwall = Display.getWalls().get(wallsnumber);
					}

					else if (firsttarget.getX() == x && firsttarget.getY() == y) {

						if (!printedBOT)

						{
							if (firstsoko.getX() == x && firstsoko.getY() == y) {
								System.out.print(firstsoko.getSokosymbol());
								targetsnumber++;
								if (targetsnumber < Display.getTargets().size())
									firsttarget = Display.getTargets().get(targetsnumber);
							}

							else

							{
								System.out.print(firsttarget.getTargetsymbol());
								targetsnumber++;
								if (targetsnumber < Display.getTargets().size())
									firsttarget = Display.getTargets().get(targetsnumber);

							}
						}

					}

					else if (firstsoko.getX() == x && firstsoko.getY() == y) {
						System.out.print(firstsoko.getSokosymbol());
						sokonumber++;
						if (sokonumber < Display.getSokoCharas().size())
							firstsoko = Display.getSokoCharas().get(sokonumber);
					} else if (!printedBOT && !printedbox) {
						System.out.print(" ");
					}

				}
				if (x <= maxrowsize) // avoid the last enter
					System.out.print("\r\n");
			}
		}

	}
}