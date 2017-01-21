package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Scanner;

public class TicTacToeTest {
	
	private TicTacToeModel board;
	
	@Before
	public void setUp(){
		board = new TicTacToeModel(3);
	}
	
	@Test
	public void testInitialBoardIsEmpty() {
		for (int row = 0; row < board.getWidth(); row++){    					
			for (int col = 0; col < board.getWidth(); col++){
				assertEquals(TicTacToeModel.Mark.EMPTY, board.getMark(row,col));   
			}
		}
	}
	
	@Test
	public void testMarkXInUpperRightCorner() {					
		assertEquals(TicTacToeModel.Mark.EMPTY, board.getMark(0,2));
		board.makeMark(0,2);
		assertEquals(TicTacToeModel.Mark.X, board.getMark(0,2));
	}
	
	@Test
	public void testMarkOInBottomLeftCorner() {
		assertEquals(TicTacToeModel.Mark.EMPTY, board.getMark(1,1));
		board.makeMark(1,1);
		assertEquals(TicTacToeModel.Mark.X, board.getMark(1,1));
		assertEquals(TicTacToeModel.Mark.EMPTY, board.getMark(2,0));
		board.makeMark(2,0);
		assertEquals(TicTacToeModel.Mark.O, board.getMark(2,0));
	}
	
	@Test
	public void testUnableToMarkOverExistingMark() {
		assertEquals(TicTacToeModel.Mark.EMPTY, board.getMark(1,1));
		board.makeMark(1, 1);
		assertEquals(TicTacToeModel.Mark.X, board.getMark(1,1));
		board.makeMark(1, 1);
		assertEquals(TicTacToeModel.Mark.X, board.getMark(1,1));
	}
	
	@Test
	public void testGameIsNotOverAfterTheFirstMark() {
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());
		board.makeMark(0, 0);
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());
	}
	
	@Test
	public void testGameIsWonByXHorizontallyAcrossTopRow() {
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());
		board.makeMark(0, 0);
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());
		board.makeMark(1, 1);
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());
		board.makeMark(0, 1);
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());
		board.makeMark(2, 2);
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());
		board.makeMark(0, 2);
		assertEquals(TicTacToeModel.Result.XWIN, board.getResult());
	}
	
	@Test
	public void testGameIsOverByTieIfAllLocationsAreFilled() {
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());   // X X O
		board.makeMark(0, 0);										   // O O X
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());   // X X O
		board.makeMark(0, 2);
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());
		board.makeMark(0, 1);
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());
		board.makeMark(1, 0);
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());
		board.makeMark(1, 2);
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());
		board.makeMark(1, 1);
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());
		board.makeMark(2, 0);
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());
		board.makeMark(2, 2);
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());
		board.makeMark(2, 1);
		assertEquals(TicTacToeModel.Result.TIE, board.getResult());
	}
	
	
	
	@Test
	public void testOutOfBoundsMark(){
		board.makeMark(0,3);
		board.makeMark(-1,0);
		board.makeMark(0,-1);
		board.makeMark(40000, 3);
	}
	
	@Test  
	public void testGameIsWonByOWithFirstCol(){
		board.makeMark(1, 1);
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());
		board.makeMark(0, 0);
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());
		board.makeMark(0, 1);
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());
		board.makeMark(1, 0);
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());
		board.makeMark(0, 2);
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());
		board.makeMark(2, 0);
		assertEquals(TicTacToeModel.Result.OWIN, board.getResult());
	}

	@Test  
	public void testGameIsWonByXDiagonallyFromTopLeftToBottomRight(){
		board.makeMark(0, 0);
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());
		board.makeMark(0, 1);
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());
		board.makeMark(1, 1);
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());
		board.makeMark(0, 2);
		assertEquals(TicTacToeModel.Result.NONE, board.getResult());
		board.makeMark(2, 2);
		assertEquals(TicTacToeModel.Result.XWIN, board.getResult());
	}
	
	@Test
	public void testXWinDiagonallyOn5x5Board(){
		TicTacToeModel largerBoard = new TicTacToeModel(5);
		assertEquals(5, largerBoard.getWidth());
		largerBoard.makeMark(0,0);
		assertEquals(TicTacToeModel.Mark.X, largerBoard.getMark(0,0));
		largerBoard.makeMark(0,1);
		assertEquals(TicTacToeModel.Mark.O, largerBoard.getMark(0,1));
		largerBoard.makeMark(1,1);
		assertEquals(TicTacToeModel.Mark.X, largerBoard.getMark(1,1));
		largerBoard.makeMark(0,2);
		assertEquals(TicTacToeModel.Mark.O, largerBoard.getMark(0,2));
		largerBoard.makeMark(2,2);
		assertEquals(TicTacToeModel.Mark.X, largerBoard.getMark(2,2));
		largerBoard.makeMark(0,3);
		assertEquals(TicTacToeModel.Mark.O, largerBoard.getMark(0,3));
		largerBoard.makeMark(3,3);
		assertEquals(TicTacToeModel.Mark.X, largerBoard.getMark(3,3));
		largerBoard.makeMark(0,4);
		assertEquals(TicTacToeModel.Mark.O, largerBoard.getMark(0,4));
		largerBoard.makeMark(4,4);
		assertEquals(TicTacToeModel.Mark.X, largerBoard.getMark(4,4));
		assertEquals(TicTacToeModel.Result.XWIN, largerBoard.getResult());
	}
	
	@Test
	public void testGridSizeFromKeyboardInput(){
		Scanner inputTest1 = new Scanner("3");
		assertEquals(3, TicTacToeModel.getGridSizeFromKeyInput(inputTest1));
		Scanner inputTest2 = new Scanner("4 7");
		assertEquals(7, TicTacToeModel.getGridSizeFromKeyInput(inputTest2));
		Scanner inputTest3 = new Scanner("duck 2 quack 5");
		assertEquals(5, TicTacToeModel.getGridSizeFromKeyInput(inputTest3));
	}
	
	@Test
	public void testInvalidKeyboardInput(){
		Scanner inputTest1 = new Scanner("-1 2 0 1");
		board.takeKeyInput(inputTest1);
		assertEquals(TicTacToeModel.Mark.X, board.getMark(0,1));
		Scanner inputTest2 = new Scanner("4000 30 1 0");
		board.takeKeyInput(inputTest2);
		assertEquals(TicTacToeModel.Mark.O, board.getMark(1,0));
		Scanner inputTest3 = new Scanner("-80 0 20 0 2 0");
		board.takeKeyInput(inputTest3);
		assertEquals(TicTacToeModel.Mark.X, board.getMark(2,0));
		Scanner inputTest4 = new Scanner("-401 1 400000 2 1 0 2 2");
		board.takeKeyInput(inputTest4);
		assertEquals(TicTacToeModel.Mark.O, board.getMark(2,2));
		Scanner inputTest5 = new Scanner("-401 -30 400000 300 10 000 2 2 1 1");
		board.takeKeyInput(inputTest5);
		assertEquals(TicTacToeModel.Mark.X, board.getMark(1,1));
	}
	
}
