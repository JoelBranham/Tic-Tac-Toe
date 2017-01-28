package edu.jsu.mcis;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeViewController extends JPanel implements ActionListener{
	
	private TicTacToeModel board;
	private JButton[][] squares;
	private JPanel squaresPanel;
	private JLabel resultLabel;
	protected static JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem newGame;
	
	public TicTacToeViewController(){
		this(3);
	}													
	
	public TicTacToeViewController(int width){
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		squares = new JButton[width][width];
		squaresPanel = new JPanel(new GridLayout(width,width));
		resultLabel = new JLabel();
		resultLabel.setName("ResultLabel");
		for (int row = 0; row < width; row++){
			for (int col = 0; col < width; col++){
				squares[row][col] = new JButton(); 
				squares[row][col].addActionListener(this);
				squares[row][col].setName("Location" + row + col);
				squares[row][col].setPreferredSize(new Dimension(60,60));
				squaresPanel.add(squares[row][col]);
			}
		}
		reset();
		add(squaresPanel);
		add(resultLabel);
		menuBar = new JMenuBar();
		menu = new JMenu("Options");
		newGame = new JMenuItem("New Game");
		newGame.addActionListener(this);
		menu.add(newGame);
		menuBar.add(menu);
	}
	
	public void actionPerformed(ActionEvent event) {
		if (newGame == event.getSource()){
			reset();
		}
		else{
			String name = ((JButton) event.getSource()).getName();
			int row = (int) name.charAt(8) - 48, col = (int) name.charAt(9) - 48;
			board.makeMark(row,col);
			squares[row][col].setText(board.getMark(row,col).toString());
			squares[row][col].setEnabled(false);
			resultLabel.setText(""); 
			if (TicTacToeModel.Result.NONE != board.getResult()){
				resultLabel.setText(board.getResult().toString().toUpperCase());
				for (int r = 0; r < board.getWidth(); r++){
					for (int c = 0; c < board.getWidth(); c++){
						squares[r][c].setEnabled(false);
					}
				}
			}
		}
	}
	
	public void reset(){ 
		board = new TicTacToeModel(squares.length);
		for (int row = 0; row < board.getWidth(); row++){
			for (int col = 0; col < board.getWidth(); col++){
				squares[row][col].setEnabled(true);
				squares[row][col].setText("");
			}
		}
		resultLabel.setText("Welcome to Tic-Tac-Toe!");
	}
}