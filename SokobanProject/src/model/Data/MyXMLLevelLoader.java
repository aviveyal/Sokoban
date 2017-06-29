package model.Data;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 
 * @author Aviv Eyal load a level from XML file
 */

public class MyXMLLevelLoader implements LevelLoader {

	private char startwall = Wall.wallsymbol;
	private char startsoko = SokoChar.sokosymbol;
	private char starttarget = Target.targetsymbol;
	private char startbox = Box.boxessymbol;
	private char startBOT = BoxOntarget.boxesontargetsymbol;

	@SuppressWarnings("finally")
	@Override
	public Level loadlevel(FileInputStream InputStream) throws IOException {
		Level levelloader = new Level();
		int x = 0;
		int y = 0;

		XMLDecoder e = new XMLDecoder(new BufferedInputStream(InputStream)); // using
																				// XML
																				// Decoder

		try {

			Object read2 = e.readObject();// read from an XML

			String read = read2.toString();

			while (true) {

				if (read.charAt(0) == startwall) {
					levelloader.getWalls().add(new Wall(x, y));

				} else if (read.charAt(0) == startbox) {
					levelloader.getBoxes().add(new Box(x, y));
				} else if (read.charAt(0) == starttarget) {
					levelloader.getTargets().add(new Target(x, y));
				}

				else if (read.charAt(0) == startsoko) {
					levelloader.getSokoCharas().add(new SokoChar(x, y));
				} else if (read.charAt(0) == startBOT) {
					levelloader.getBoxOnTareget().add(new BoxOntarget(x, y));
				} else if (read.equals("\r\n"))// condition for end of line ???
				{

					x++;
					y = -1; // it gets +1 later
				}

				y++;

				read2 = e.readObject();
				read = read2.toString();

			}
		}

		finally {

			e.close();

			return levelloader;

		}

	}

}
