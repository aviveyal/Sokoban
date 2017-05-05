package SerachLib;

public class Run {

	public static void main(String[] args) {
		
		EightPuzzle game = new EightPuzzle();
		//int[][] board = { { 8, 0, 6}, { 5, 4, 7}, {2, 3, 1}};
		int[][] board = { {3, 1, 2 },  {0 , 4, 5}, {6, 7, 8}};
		game.setBoard(board);
		
		EightPuzzleAdapter adapter = new EightPuzzleAdapter(game);
		/*State<EightPuzzleState> initialState = adapter.getInitialState();
		System.out.println("Initial state: \n" + initialState);
		List<State<EightPuzzleState>> list = adapter.getAllPossibleStates(initialState);
		
		System.out.println("Neighbors of initial state:");
		for (State<EightPuzzleState> s : list) {
			System.out.println(s);
		}*/
		
		DFS<EightPuzzleState> dfs = new DFS<>();
		Solution sol = dfs.search(adapter);
		System.out.println(sol);
		

	}
}