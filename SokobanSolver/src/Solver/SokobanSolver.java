package Solver;

import java.io.FileOutputStream;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import Adapter.Search.SokobanAndBoxPush;
import Adapter.Search.SokobanStateMove;
import SerachLib.search.BFS;
import SerachLib.search.Position;
import SerachLib.search.Solution;
import Adapter.Plan.SokobanAdapter;
import StripLib.Plan.Action;
import StripLib.Plan.Strips;
import model.Data.Level;
import model.modelCommands.Load;

public class SokobanSolver {

	static Position playerDest;
	static Position playerStart;

	public static void main(String[] args) throws IOException {
		if (args.length == 2) {
			String strload = args[0];
			String strsave = args[1];

			Load level1 = new Load("levels/" + strload);
			Level level = level1.execute();
			char[][] leveldata = level.makestring();

			SokobanAdapter SA = new SokobanAdapter(leveldata);
			Strips s = new Strips();

			List<Action> list = s.plan(SA.readLevel());

			List<String> finalsol = new ArrayList<>();

			// player position
			/*
			 * Position player = null; for (int i = 0; i < level.maxrowsize();
			 * i++) for (int j = 0; j < level.maxcolumnsize(); j++) if
			 * (leveldata[i][j] == 'A') { player = new Position(i, j); //
			 * System.out.println(player.toString()); }
			 */
			System.out.println(list);

			// int i=0;

			Adapter.Search.SokobanMove searchbfs;
			int row = 0;
			int col = 0;
			String value;
			Position destination;
			Position box;

			Solution sol = null;

			BFS<SokobanStateMove> bfs = new BFS<SokobanStateMove>();

			for (int x = 0; x < leveldata.length; x++)
				for (int y = 0; y < leveldata[x].length; y++) {
					if (y == leveldata[x].length - 1) {
						System.out.println();
					} else
						System.out.print(leveldata[x][y]);
				}

			for (int i = 0; i < list.size(); i++) {

				if (list.get(i).toString().startsWith("move")) {
					value = list.get(i).toString().substring(list.get(i).toString().indexOf('=') + 1,
							list.get(i).toString().length());
					row = Integer.parseInt(value.substring(0, value.indexOf(',')));
					col = Integer.parseInt(value.substring(value.indexOf(',') + 1, value.length()));
					playerDest = new Position(row, col);

					value = list.get(i).toString().substring(list.get(i).toString().indexOf('_') + 1,
							list.get(i).toString().indexOf('='));
					row = Integer.parseInt(value.substring(0, value.indexOf(',')));
					col = Integer.parseInt(value.substring(value.indexOf(',') + 1, value.length()));

					playerStart = new Position(row, col);

					searchbfs = new Adapter.Search.SokobanMove(leveldata, playerStart, playerDest);

					sol = bfs.search(searchbfs);

					leveldata[playerStart.getRow()][playerStart.getCol()] = ' ';
					leveldata[playerDest.getRow()][playerDest.getCol()] = 'A';
					for (SerachLib.search.Action a : sol.getActions()) {
						finalsol.add(a.getName());
						// System.out.println(a.toString());
					}

				} else if (list.get(i).toString().startsWith("push")) {

					value = list.get(i).toString().substring(list.get(i).toString().indexOf('=') + 1,
							list.get(i).toString().length());
					row = Integer.parseInt(value.substring(0, value.indexOf(',')));
					col = Integer.parseInt(value.substring(value.indexOf(',') + 1, value.length()));
					destination = new Position(row, col);

					value = list.get(i).toString().substring(list.get(i).toString().indexOf('_') + 1,
							list.get(i).toString().indexOf('='));
					row = Integer.parseInt(value.substring(0, value.indexOf(',')));
					col = Integer.parseInt(value.substring(value.indexOf(',') + 1, value.length()));
					box = new Position(row, col);

					SokobanAndBoxPush searchbfs2 = new SokobanAndBoxPush(leveldata, playerDest, box, destination);
					sol = bfs.search(searchbfs2);

					if (list.size() > i + 1) {
						leveldata[box.getRow()][box.getCol()] = ' ';
						leveldata[playerDest.getRow()][playerDest.getCol()] = ' ';
						leveldata[destination.getRow()][destination.getCol()] = '@';

						value = list.get(i + 1).toString().substring(list.get(i + 1).toString().indexOf('_') + 1,
								list.get(i + 1).toString().indexOf('='));
						row = Integer.parseInt(value.substring(0, value.indexOf(',')));
						col = Integer.parseInt(value.substring(value.indexOf(',') + 1, value.length()));

						leveldata[row][col] = 'A';

					}

					if (sol != null) {

						for (SerachLib.search.Action a : sol.getActions()) {
							if (a.getMiniaction() != null) {
								for (SerachLib.search.Action b : a.getMiniaction()) {
									finalsol.add(b.getName());
								}
							}
							finalsol.add(a.getName());
							// System.out.println(a.toString());
						}
					}

					// update the levelboard after moved and pushed one box
					/*
					 * for (int x = 0; x < leveldata.length; x++) for (int y =
					 * 0; y < leveldata[x].length; y++) { if (y ==
					 * leveldata[x].length - 1) { System.out.println(); } else
					 * System.out.print(leveldata[x][y]); }
					 */
				}
			}
			PrintWriter outputStream = new PrintWriter(new FileOutputStream("levels/" + strsave));
			for (String move : finalsol) {
				outputStream.println(move);
				outputStream.flush();
				System.out.println(move);
			}

			outputStream.close();

		} else
			System.out.println("no arguments- cant solve");
	}

}
