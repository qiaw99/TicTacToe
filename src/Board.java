package Chess;

public class Board {
	Chess[] chesses;
	boolean[] occupied;
	int remain;
	
	public Board() {
		remain = 9;
		chesses = new Chess[9];
		intializeBoard();
		initializeChesses();
	}
	
	private void initializeChesses() {
		for(int i = 0; i < 9; i++) {
			this.chesses[i] = new Chess(" ", i);
		}
	}
	
	private void intializeBoard() {
		occupied = new boolean[9];
		for(int i = 0; i < 9; i ++) {
			occupied[i] = true;
		}
	}
}
