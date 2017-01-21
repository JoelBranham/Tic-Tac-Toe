package keywords;

import edu.jsu.mcis.*;

public class TicTacToeKeywords {
	
	private TicTacToeModel board;
	
	public void startNewGame() {
		board = new TicTacToeModel();
	}
	
	public void markLocation(int row, int col) {
		board.makeMark(row, col);
	}
	
	public String getMark(int row, int col) {
		return board.getMark(row,col) + "";
	}
    
	public String getWinner() {
		return TicTacToeModel.getResultString(board.getResult());
	}
}
