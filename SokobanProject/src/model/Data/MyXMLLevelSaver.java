package model.Data;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * @author Aviv Eyal save a level to XML file
 */
public class MyXMLLevelSaver implements LevelSaver {

	@Override
	public void savelevel(FileOutputStream outputStream, Level levelSaver) throws IOException {

		XMLEncoder e = new XMLEncoder(new BufferedOutputStream(outputStream)); // using
																				// XML
																				// encoder

		ArrayList<Box> box = levelSaver.getBoxes();
		ArrayList<SokoChar> soko = levelSaver.getSokoCharas();
		ArrayList<Wall> wall = levelSaver.getWalls();
		ArrayList<Target> target = levelSaver.getTargets();
		ArrayList<BoxOntarget> BOT = levelSaver.getBoxOnTareget();

		/* get first in the array list of each object */

		int wallsnumber = 0;
		int targetsnumber = 0;
		int boxesnumber = 0;
		int sokonumber = 0;
		int maxcolumnsize = levelSaver.maxcolumnsize();
		int maxrowsize = levelSaver.maxrowsize();

		Wall firstwall = wall.get(wallsnumber);
		Target firsttarget = target.get(targetsnumber);
		Box firstbox = box.get(boxesnumber);
		SokoChar firstsoko = soko.get(sokonumber);

		BOT.add(new BoxOntarget(-1, -1));// in case there are no boxes on target

		boolean printedBOT = false;
		boolean printedbox = false;

		/* while there is object to write */
		while (wallsnumber < wall.size() && targetsnumber < target.size() && boxesnumber < box.size()
				&& sokonumber < soko.size()) {

			for (int x = 0; x <= maxrowsize; x++) {
				for (int y = 0; y <= maxcolumnsize; y++) {

					printedBOT = false;
					printedbox = false;

					for (int i = 0; i < levelSaver.getBoxes().size(); i++) // boxes
																			// can
																			// change
																			// the
																			// order
																			// in
																			// the
																			// array
																			// !
					{
						if ((levelSaver.getBoxes().get(i).getX() == x) && (levelSaver.getBoxes().get(i).getY() == y)) {
							for (int s = 0; s < levelSaver.getBoxOnTareget().size(); s++) // box
																							// arrived
																							// to
																							// the
																							// the
																							// target
																							// !
							{
								if ((levelSaver.getBoxOnTareget().get(s).getX() == x)
										&& (levelSaver.getBoxOnTareget().get(s).getY() == y)) {
									e.writeObject(levelSaver.getBoxOnTareget().get(s).boxesontargetsymbol());
									printedBOT = true;
									s = levelSaver.getBoxOnTareget().size();

									targetsnumber++; // printed V on a place
														// with target
									if (targetsnumber < levelSaver.getTargets().size())
										firsttarget = levelSaver.getTargets().get(targetsnumber);
								}
							}

							if (!printedBOT) {
								e.writeObject(firstbox.getBoxessymbol());
								printedbox = true;
							}

						}

					}

					if (firstwall.getX() == x && firstwall.getY() == y) {
						e.writeObject(firstwall.getWallsymbol());
						wallsnumber++;
						if (wallsnumber < levelSaver.getWalls().size())
							firstwall = levelSaver.getWalls().get(wallsnumber);
					}

					else if (firsttarget.getX() == x && firsttarget.getY() == y) {

						if (!printedBOT)

						{
							if (firstsoko.getX() == x && firstsoko.getY() == y) {
								e.writeObject(firstsoko.getSokosymbol());
								targetsnumber++;
								if (targetsnumber < levelSaver.getTargets().size())
									firsttarget = levelSaver.getTargets().get(targetsnumber);
							}

							else

							{
								e.writeObject(firsttarget.getTargetsymbol());
								targetsnumber++;
								if (targetsnumber < levelSaver.getTargets().size())
									firsttarget = levelSaver.getTargets().get(targetsnumber);

							}
						}

					}

					else if (firstsoko.getX() == x && firstsoko.getY() == y) {
						e.writeObject(firstsoko.getSokosymbol());
						sokonumber++;
						if (sokonumber < levelSaver.getSokoCharas().size())
							firstsoko = levelSaver.getSokoCharas().get(sokonumber);
					} else if (!printedBOT && !printedbox) {
						e.writeObject(" ");
					}

				}
				if (x <= maxrowsize) // avoid the last enter
					e.writeObject("\r\n");
			}
		}
		e.close();

	}
}