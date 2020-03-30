package Chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Play {
	Board board;
	ArrayList<String> repeatedArrayList = new ArrayList<String>();
	
	public Play() throws IOException {
		board = new Board();
		play();
	}
	
	public void printBoard() {
		for(int i = 0; i < 9; i ++) {
			if(board.chesses[i].colorString == "white") {
				System.out.print("O ");
			}else if(board.chesses[i].colorString == "black") {
				System.out.print("X ");
			}else {
				System.out.print("_ ");
			}
			if(i % 3 == 2) {
				System.out.println();
			}
		}
	}
	
	public void putChess(Chess chess) {
		this.board.chesses[chess.pos] = chess;
		this.board.remain -= 1;
		this.board.occupied[chess.pos] = false;
		System.out.println(chess.colorString);
	}
	
	public void play() throws IOException {
		String str = null;
		int flag = 0;
		
		while(!win()) {
			if(finished())
				break;
			str = getInput();
	        while(!rightInput(str) || repeated(str)) {
	        	str = getInput();
	        	if(repeated(str)) {
	        		System.out.println("The position is already occupied!");
	        	}
	        }
	        repeatedArrayList.add(str);
	        if(flag == 0) {
	        	putChess(new Chess("white", Integer.parseInt(str) - 1));
	        	flag = 1;
	        }else {
	        	putChess(new Chess("black", Integer.parseInt(str) - 1));
	        	flag = 0;
	        }
	        printBoard();
		}
		System.out.println("Game over!");
		
	}
	
	public boolean win() {
		Chess[] chesses = this.board.chesses;
		return hasSameColor(chesses[0], chesses[1], chesses[2]) || 
				hasSameColor(chesses[0], chesses[3], chesses[6]) ||
				hasSameColor(chesses[1], chesses[4], chesses[7]) ||
				hasSameColor(chesses[0], chesses[4], chesses[8]) ||
				hasSameColor(chesses[3], chesses[4], chesses[5]) ||
				hasSameColor(chesses[2], chesses[5], chesses[8]) ||
				hasSameColor(chesses[2], chesses[4], chesses[6]) ||
				hasSameColor(chesses[6], chesses[7], chesses[8]);
				
	}
	
	public boolean hasSameColor(Chess c1, Chess c2, Chess c3) {
		return (c1.colorString != null) && (c2.colorString != null) 
				&& (c3.colorString != null) && ((c1.colorString).equals(c2.colorString))
				&& ((c1.colorString).equals(c3.colorString))
				&& ((c1.colorString.equals("white") || c1.colorString.equals("black")) &&
				!(this.board.occupied[c1.pos] || this.board.occupied[c2.pos]) 
				&& !(this.board.occupied[c1.pos] || this.board.occupied[c3.pos]));
	}
	
	public String getInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		String str = null; 
		System.out.println("\nEnter your value:"); 
        str = br.readLine(); 
        return str;
	}
	
	public boolean repeated(String str) {
		return this.repeatedArrayList.contains(str);
	}
	
	public boolean rightInput(String str) {
		return (Integer.parseInt(str) >= 1) && (Integer.parseInt(str) <= 9);
	}
	
	public boolean finished() {
		int counter = 0;
		for(boolean temp: this.board.occupied) {
			if(temp == false) {
				counter += 1;
			}
		}
		return counter == 9;
	}
}
