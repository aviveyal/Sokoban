package Solver;

import java.io.FileOutputStream;
import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import Adapter.Search.SokobanAndBoxPush;
import Adapter.Search.SokobanStateMove;
import Solver.SearchLib.*;
import Solver.SearchLib.Action;
import Adapter.Plan.SokobanAdapter;
import Solver.StripsLib.*;

import model.Data.Level;
import model.modelCommands.Load;

/**
 * 
 * @author Aviv Eyal Main class, run the adpaters and return list of action to
 *         do to solve the level
 */
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

			List<Solver.StripsLib.Action> list = s.plan(SA.readLevel());

			List<String> finalsol = new ArrayList<>();

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
			// Dijkstra<SokobanStateMove> dij = new
			// Dijkstra<SokobanStateMove>();

			for (int x = 0; x < leveldata.length; x++)
				for (int y = 0; y < leveldata[x].length; y++) {
					if (y == leveldata[x].length - 1) {
						System.out.println();
					} else
						System.out.print(leveldata[x][y]);
				}

			// execute the actions from list
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
					// sol=dij.search(searchbfs);

					leveldata[playerStart.getRow()][playerStart.getCol()] = ' ';
					leveldata[playerDest.getRow()][playerDest.getCol()] = 'A';
					for (Action a : sol.getActions()) {
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
					// sol=dij.search(searchbfs2);
					if (list.size() > i + 1) {
						leveldata[box.getRow()][box.getCol()] = ' ';
						leveldata[playerDest.getRow()][playerDest.getCol()] = ' ';
						leveldata[destination.getRow()][destination.getCol()] = '@';

					/*	value = list.get(i).toString().substring(list.get(i).toString().indexOf('=')+1,
								list.get(i).toString().length());
						row = Integer.parseInt(value.substring(0, value.indexOf(',')));
						col = Integer.parseInt(value.substring(value.indexOf(',') + 1, value.length()));
*/
						switch(sol.getActions().get(sol.getActions().size()-1).toString())
						{
						case "move up": leveldata[destination.getRow()+1][destination.getCol()] = 'A';break;
						case "move down":leveldata[destination.getRow()-1][destination.getCol()] = 'A';break;
						case "move left":leveldata[destination.getRow()][destination.getCol()+1] = 'A';break;
						case "move right":leveldata[destination.getRow()][destination.getCol()-1] = 'A';break;
						}
						
					}

					if (sol != null) {

						for (Action a : sol.getActions()) {
							if (a.getMiniaction() != null) {
								for (Action b : a.getMiniaction()) {
									finalsol.add(b.getName());
								}
							}
							finalsol.add(a.getName());
							// System.out.println(a.toString());
						}
					}

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

	public static List<String> solve(char[][] leveldata) throws IOException {
		SokobanAdapter SA = new SokobanAdapter(leveldata);
		Strips s = new Strips();

		List<Solver.StripsLib.Action> list = s.plan(SA.readLevel());

		List<String> finalsol = new ArrayList<>();

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
		// Dijkstra<SokobanStateMove> dij = new
		// Dijkstra<SokobanStateMove>();

		for (int x = 0; x < leveldata.length; x++)
			for (int y = 0; y < leveldata[x].length; y++) {
				if (y == leveldata[x].length - 1) {
					System.out.println();
				} else
					System.out.print(leveldata[x][y]);
			}

		// execute the actions from list
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
				// sol=dij.search(searchbfs);

				leveldata[playerStart.getRow()][playerStart.getCol()] = ' ';
				leveldata[playerDest.getRow()][playerDest.getCol()] = 'A';
				for (Action a : sol.getActions()) {
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
				// sol=dij.search(searchbfs2);
				if (list.size() > i + 1) {
					leveldata[box.getRow()][box.getCol()] = ' ';
					leveldata[playerDest.getRow()][playerDest.getCol()] = ' ';
					leveldata[destination.getRow()][destination.getCol()] = '@';

					value = list.get(i).toString().substring(list.get(i).toString().indexOf('=')+1,
							list.get(i).toString().length());
					row = Integer.parseInt(value.substring(0, value.indexOf(',')));
					col = Integer.parseInt(value.substring(value.indexOf(',') + 1, value.length()));

					switch(sol.getActions().get(sol.getActions().size()-1).toString())
					{
					case "move up": leveldata[row+1][col] = 'A';break;
					case "move down":leveldata[row-1][col] = 'A';break;
					case "move left":leveldata[row][col+1] = 'A';break;
					case "move right":leveldata[row][col-1] = 'A';break;
					}

				}

				if (sol != null) {

					for (Action a : sol.getActions()) {
						if (a.getMiniaction() != null) {
							for (Action b : a.getMiniaction()) {
								finalsol.add(b.getName());
							}
						}
						finalsol.add(a.getName());
						// System.out.println(a.toString());
					}
				}

			}
		}

		return finalsol;
	}

}
