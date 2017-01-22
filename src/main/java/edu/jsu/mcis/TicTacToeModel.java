package edu.jsu.mcis;

import java.util.Scanner;

public class TicTacToeModel{										
	public enum Mark {X, O, EMPTY};
	public enum Result {X, O, TIE, NONE};
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
		if (validSquare(row, col)){
			if (squareUnmarked(row, col)){
				grid[row][col] = (xTurn) ? Mark.X : Mark.O;
				xTurn = !xTurn; 
				return true;
			}
		}
		return false;
	}
	
	private boolean validSquare(int row, int col){
		return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
	}
	
	private boolean squareUnmarked(int row, int col){
		return Mark.EMPTY == getMark(row,col);
	}
	
	public Mark getMark(int row, int col){
		return grid[row][col];
	}
	
	public Result getResult(){
		if (markWin(Mark.O)){
			return Result.O;
		}
		else if (markWin(Mark.X)){
			return Result.X;
		}
		else if (checkIfTie()){
			return Result.TIE;
		}
		return Result.NONE;
	}
	
	private boolean markWin(Mark mark){
		boolean diagonal = true;
		for (int i = 0; i < getWidth(); i++){
			boolean row = true, col = true;
			if (mark != getMark(i,i)){
				diagonal = false;
			}
			for (int j = 0; j < getWidth(); j++){
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
		return diagonal;
	}
	
	private boolean checkIfTie(){
		boolean tie = true;
		for (int row = 0; row < getWidth(); row++){
			for (int col = 0; col < getWidth(); col++){
				if (squareUnmarked(row,col)){
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
	
	public String getMarkString(int row, int col){
		return getMark(row,col).toString();
	}

	public String getWinner(){
		if (markWin(Mark.O)){
			return "O";
		}
		else if (markWin(Mark.X)){
			return "X";
		}
		else if (checkIfTie()){
			return "TIE";
		}
		return "NONE";
	}
	
	public String getWinnerPhrase(){
		return "The winner is " + getWinner();
	}
	
	private String getPlayerMessage(){
		return (xTurn) ? "Player 1 (X) Move: ": "Player 2 (O) Move: ";
	}
	
	private static String getWelcomeandGridSizeMessage(){
		return "Welcome to Tic-Tac-Toe!\nEnter an odd integer N (3-9) for an NxN (3x3-9x9) board: ";
	}

	private static String getInstructions(){
		return "Type in row followed by column number.\n";
	}
	
	public static int getGridSizeFromKeyInput(Scanner input){
		boolean valid = false;
		int size = 0;
		while (!valid){
			try{
				int check = Integer.parseInt(input.next());
				if(check  >= 3 && check % 2 == 1 && check <= 9){
					size = check;
					valid = true;
				}
				else{
					System.out.println("Please enter an odd integer N (3 <= N <= 9, N % 2 == 1): "); 
				}
			}
			catch(NumberFormatException e){System.out.println("Invalid input. Please enter numeric values. ");}
		}
		return size;
	}

	public void takeKeyInput(Scanner input){
		boolean valid = false;
		while (!valid){
			int row = input.nextInt(), col = input.nextInt();
			if (makeMark(row,col)){
				valid = true;
			}
			else{
				System.out.println("Entered location already marked or out of bounds.");
			}
		}
	}
	
	public void viewModel(){			
		String board = "\n  ";
		for (int row = 0; row < getWidth(); row++){
			board += row;
		}
		board += "\n\n";
		for (int row = 0; row < getWidth(); row++){
			board += row + " ";
			for (int col = 0; col < getWidth(); col++){
				board += (getMark(row, col) == Mark.EMPTY) ? "-": getMark(row,col);
			}
			board += "\n";
		}
		System.out.println(board + "\n");
	}
	
	public void printPlayerMessage(){
		System.out.println(getPlayerMessage());
	}
	
	public static void printWelcomeandPromptForGridSize(){
		System.out.println(getWelcomeandGridSizeMessage());
	}
	
	public static void printInstructions(){
		System.out.println(getInstructions());
	}
	
	public static void printGameoverMessage(String gameover){
		System.out.println(gameover);
	}

}
