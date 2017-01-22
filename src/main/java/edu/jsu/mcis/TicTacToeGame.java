package edu.jsu.mcis;

import java.util.Scanner; 

public class TicTacToeGame{
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		TicTacToeModel.printWelcomeandPromptForGridSize();
		TicTacToeModel board = new TicTacToeModel(TicTacToeModel.getGridSizeFromKeyInput(in));
		TicTacToeModel.printInstructions();
		while (TicTacToeModel.Result.NONE == board.getResult()){
			board.viewModel();
			board.printPlayerMessage();
			board.takeKeyInput(in);
		}
		board.viewModel();
		board.printGameoverMessage();
	}
}