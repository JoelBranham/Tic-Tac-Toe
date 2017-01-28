package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TicTacToeGame{
	
	public static void main(String[] args) {
		int width = 3;
		if(args.length >= 1) {
			try {
				int n = Integer.parseInt(args[0]);
				if (n % 2 == 1 && n >= 3 && n <= 9){
					width = Integer.parseInt(args[0]);
				}
			}
			catch(NumberFormatException e) {}
		}
		TicTacToeViewController boardVC = new TicTacToeViewController(width);
        JFrame win = new JFrame("Tic Tac Toe");
		win.setJMenuBar(TicTacToeViewController.menuBar);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.add(boardVC);
        win.pack();
        win.setVisible(true);
	}
	
}