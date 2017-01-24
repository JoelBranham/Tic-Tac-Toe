package edu.jsu.mcis;

import java.util.Scanner;

public class TicTacToeViewController{

	private TicTacToeModel model;
	private Scanner keyboard;
	
	public TicTacToeViewController(TicTacToeModel model){
		this.model = model;
		keyboard = new Scanner(System.in);
	}
	
	public void viewModel() {
		String board = "\n  ";
		for (int row = 0; row < model.getWidth(); row++){
			board += row;
		}
		board += "\n\n";
		for (int row = 0; row < model.getWidth(); row++){
			board += row + " ";
			for (int col = 0; col < model.getWidth(); col++){
				board += (model.getMark(row, col) == TicTacToeModel.Mark.EMPTY) ? "-": model.getMark(row,col);
			}
			board += "\n";
		}
		System.out.println("\n" + board);
	}
	
	public void controlModel() {
		String playerMessage = (model.isXTurn()) ? "Player 1 (X) Move: ": "Player 2 (O) Move: ";
		boolean valid = false;
		while (!valid){
			System.out.println(playerMessage);
			System.out.println("Type in row followed by column number.\n");
			int row = (int) keyboard.next().charAt(0), col = (int) keyboard.next().charAt(0);
			if (row > 47 && row < 57 && col > 47 && col < 57){
				if (model.makeMark(row - 48,col - 48)){
					valid = true;
				}
			}
			else{
				System.out.println("Entered location invalid, already marked, or out of bounds.");
			}
		}		
	}
}