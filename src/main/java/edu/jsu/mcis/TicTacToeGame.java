package edu.jsu.mcis;

import java.util.Scanner; 

public class TicTacToeGame{
	public static void main(String[] args) {
		int width = 3;
		if(args.length >= 1) {
			try {
				width = Integer.parseInt(args[0]);
			}
			catch(NumberFormatException e) {}
		}
		TicTacToeModel board = new TicTacToeModel(width);
		TicTacToeViewController boardVC = new TicTacToeViewController(board);
		while (!board.isGameover()){
			boardVC.viewModel();
			boardVC.controlModel();
		}
		boardVC.viewModel();
		System.out.println(board.getResult() + "!");
	}
}