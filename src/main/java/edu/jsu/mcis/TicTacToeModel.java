package edu.jsu.mcis;

public class TicTacToeModel{										
	public enum Mark {
		X("X"), 
		O("O"), 
		EMPTY(" ");
		
		private String message;
		private Mark(String msg) {
			message = msg;
		}
		public String toString() {
			return message;
		}
	};
	public enum Result {
		X("X"), 
		O("O"), 
		TIE("Tie"), 
		NONE("none");
		
		private String message;
		private Result(String msg) {
			message = msg;
		}
		public String toString() {
			return message;
		}
	};
	private Mark[][] grid;
	private boolean xTurn;
	private int width;
	
	public TicTacToeModel(){
		this(3); 
	}
	
	public TicTacToeModel(int width){
		grid = new Mark[width][width];
		xTurn = true;
		this.width = width;
		for (int row = 0; row < width; row++){
			for (int col = 0; col < width; col++){
				grid[row][col] = Mark.EMPTY;
			}
		}
	}
	
	public boolean makeMark(int row, int col){
		if (isValidSquare(row, col)){
			if (!isSquareMarked(row, col)){
				grid[row][col] = (xTurn) ? Mark.X : Mark.O;
				xTurn = !xTurn; 
				return true;
			}
		}
		return false;
	}
	
	private boolean isValidSquare(int row, int col){
		return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
	}
	
	private boolean isSquareMarked(int row, int col){
		return Mark.EMPTY != getMark(row,col);
	}
	
	public Mark getMark(int row, int col){
		return grid[row][col];
	}
	
	public Result getResult(){
		if (isMarkWin(Mark.O)){
			return Result.O;
		}
		else if (isMarkWin(Mark.X)){
			return Result.X;
		}
		else if (isTie()){
			return Result.TIE;
		}
		return Result.NONE;
	}
	
	private boolean isMarkWin(Mark mark){
		boolean lDiagonal = true, rDiagonal = true;
		for (int i = 0; i < width; i++){
			boolean row = true, col = true;
			if (mark != getMark(i,i)){
				lDiagonal = false;
			}
			if (mark != getMark(width - i - 1, i)){
				rDiagonal = false;
			}
			for (int j = 0; j < width; j++){
				if (mark != getMark(i,j)){
					row = false;
				}
				if (mark != getMark(j,i)){
					col = false;
				}
			}
			if (row || col){
				return true;
			}
		}
		return lDiagonal || rDiagonal;
	}
	
	private boolean isTie(){
		boolean tie = true;
		for (int row = 0; row < width; row++){
			for (int col = 0; col < width; col++){
				if (!isSquareMarked(row,col)){
					tie = false;
					break;
				}
			}
		}
		return tie;
	}
	
	public int getWidth(){
		return width;
	}

}