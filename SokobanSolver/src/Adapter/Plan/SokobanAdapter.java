package Adapter.Plan;

import java.util.Set;

import Adapter.Search.SokobanMove;

import Adapter.Search.SokobanStateMove;
import SerachLib.search.BFS;
import SerachLib.search.Position;
import SerachLib.search.Solution;
import StripLib.Plan.Action;
import StripLib.Plan.Clause;
import StripLib.Plan.Plannable;
import StripLib.Plan.Predicate;

public class SokobanAdapter {

	static char[][] l;

	public SokobanAdapter(char[][] l) {
		SokobanAdapter.l = l;
	}

	public static Clause getGoal(Clause kb) {
		Clause goal = new Clause();

		for (Predicate p : kb.getPredicates()) {
			if (p.getID().startsWith("t")) {

				goal.add(new Predicate("boxAt", "b" + p.getID().substring(1), p.getValue()));

			}
		}

		// System.out.println(goal);
		return goal;
	}

	public static Clause getKB() {
		char[][] level = l;
		Clause kb = new Clause();
		int boxCount = 0;
		int targetCount = 0;
		for (int i = 0; i < l.length; i++) {
			for (int j = 0; j < l[i].length; j++) {
				switch (level[i][j]) {
				case '#':
					kb.add(new Predicate("wallAt", "", i + "," + j));
					break;
				case ' ':
					kb.add(new Predicate("clearAt", "", i + "," + j));
					break;
				case 'A':
					kb.add(new Predicate("sokobanAt", "?", i + "," + j));
					break;
				case '@':
					boxCount++;
					kb.add(new Predicate("boxAt", "b" + boxCount, i + "," + j));
					break;
				case 'o':
					targetCount++;
					kb.add(new Predicate("targetAt", "t" + targetCount, i + "," + j));
					break;
				}
			}
		}
		return kb;
	}

	public static Plannable readLevel() {
		try {

			Clause kb = getKB();
			Clause goal = getGoal(kb);
			// System.out.println(kb);
			// System.out.println(goal);
			Plannable plannable = new Plannable() {

				@Override
				public Action getsatisfyingAction(Predicate top) {
					// int targetnum = 0;
					Action action = null;

					Position sokoban = null;
					Position target = null;
					Position box = null;

					// System.out.println(top.toString());
					if (top.getType().startsWith("boxAt")) {

						int rowb = 0; // box row
						int colb = 0; // box col
						// player position
						loop: for (Predicate player : kb.getPredicates()) {

							if (player.getType().startsWith("sokobanAt")) {
								int rowp = Integer
										.parseInt(player.getValue().substring(0, player.getValue().indexOf(',')));
								int colp = Integer.parseInt(player.getValue()
										.substring(player.getValue().indexOf(',') + 1, player.getValue().length()));
								sokoban = new Position(rowp, colp);
								break loop;

							}
						}

						// target position
						loop: for (Predicate p : kb.getPredicates()) {

							if (p.getType().startsWith("tar")
									&& p.getID().substring(1).equals(top.getID().substring(1))) {

								int rowt = Integer.parseInt(p.getValue().substring(0, p.getValue().indexOf(',')));
								int colt = Integer.parseInt(
										p.getValue().substring(p.getValue().indexOf(',') + 1, p.getValue().length()));
								target = new Position(rowt, colt);
								break loop;
								// System.out.println("target : "
								// +target.getRow()+","+target.getCol());
							}
						}
						// box position
						loop: for (Predicate p : kb.getPredicates()) {

							if (p.getType().startsWith("box") && p.getID().equals(top.getID())) {

								rowb = Integer.parseInt(p.getValue().substring(0, p.getValue().indexOf(',')));
								colb = Integer.parseInt(
										p.getValue().substring(p.getValue().indexOf(',') + 1, p.getValue().length()));
								box = new Position(rowb, colb);
								break loop;
								// System.out.println("box : "
								// +box.getRow()+","+box.getCol());
							}
						}

						// checking path box to target

						SokobanMove adapter = new SokobanMove(l, box, target);
						BFS<SokobanStateMove> bfs = new BFS<SokobanStateMove>();
						Solution sol1 = bfs.search(adapter);

						if (sol1 != null) {
							// System.out.println(sol1);
							SerachLib.search.Action BFSFirstAction = sol1.getActions().get(0);
							switch (BFSFirstAction.getName()) {
							case "move up":
								rowb += 1;
								break;
							case "move down":
								rowb -= 1;
								break;
							case "move left":
								colb += 1;
								break;
							case "move right":
								colb -= 1;
								break;
							}
							// checking path of player near the box
							SokobanMove adapter2 = new SokobanMove(l, sokoban, new Position(rowb, colb));

							BFS<SokobanStateMove> bfs2 = new BFS<SokobanStateMove>();
							Solution sol2 = bfs2.search(adapter2);

							if (sol2 != null) {

								action = new Action("push", box.getRow() + "," + box.getCol(), top.getValue());
								action.getPreconditions().add(new Predicate("sokobanAt", "?", rowb + "," + colb));// move
								action.getPreconditions()
										.add(new Predicate("targetAt", "t" + top.getID().substring(1), top.getValue()));// boxnumber=
								action.getEffects().add(new Predicate("clearAt", "", top.getValue()));
								action.getEffects().add(new Predicate("boxAt", top.getID(), top.getValue()));

								int newrow = Integer.parseInt(top.getValue().substring(0, top.getValue().indexOf(",")));
								int newcol = Integer.parseInt(top.getValue().substring(top.getValue().indexOf(",") + 1,
										top.getValue().length()));
								// System.out.println("last move : "
								// +sol2.getActions().get(sol2.getActions().size()-1).getName()
								// );

								if (sol2.getActions().size() > 0) {
									switch (sol1.getActions().get(sol1.getActions().size() - 1).getName()) {

									case "move up":
										newrow += 1;
										break;
									case "move down":
										newrow -= 1;
										break;
									case "move left":
										newcol += 1;
										break;
									case "move right":
										newcol -= 1;
										break;
									}
								}
								action.getEffects().add(new Predicate("sokobanAt", "?", newrow + "," + newcol));
								// System.out.println( newrow+","+newcol);
							} else {
								System.out.println("No Solution");
								System.exit(1);
							}
						} else {
							System.out.println("No Solution");
							System.exit(1);
						}

					} else if (top.getType().startsWith("sokoban")) {
						int rowp = 0;
						int colp = 0;
						int destrow = 0;
						int destcol = 0;
						// player position
						loop: for (Predicate player : kb.getPredicates()) {
							if (player.getType().startsWith("sokobanAt")) {
								rowp = Integer.parseInt(player.getValue().substring(0, player.getValue().indexOf(',')));
								colp = Integer.parseInt(player.getValue().substring(player.getValue().indexOf(',') + 1,
										player.getValue().length()));
								sokoban = new Position(rowp, colp);
								break loop;
							}
						}
						destrow = Integer.parseInt(top.getValue().substring(0, top.getValue().indexOf(',')));
						destcol = Integer.parseInt(
								top.getValue().substring(top.getValue().indexOf(',') + 1, top.getValue().length()));

						SokobanMove SA = new SokobanMove(l, sokoban, new Position(destrow, destcol));
						BFS<SokobanStateMove> bfs = new BFS<SokobanStateMove>();
						Solution sol2 = bfs.search(SA);
						if (sol2 != null) {
							action = new Action("move", rowp + "," + colp, top.getValue());
							action.getPreconditions().add(new Predicate("clearAt", "", top.getValue()));
							action.getEffects().add(new Predicate("clearAt", "", rowp + "," + colp));
							action.getEffects().add(new Predicate("sokobanAt", "?", top.getValue()));

						}

					}

					return action;

				}

				@Override
				public Set<Action> getsatisfyingActions(Predicate top) {
					return null;
				}

				@Override
				public Clause getKnowledgebase() {
					return kb;
				}

				@Override
				public Clause getGoal() {
					return goal;
				}
			};

			return plannable;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
