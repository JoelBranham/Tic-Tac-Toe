package edu.jsu.mcis;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Scanner;
import java.io.*;

public class TicTacToeViewControllerTest{
	
	private TicTacToeModel board;
	private TicTacToeViewController boardVC;
	private ByteArrayOutputStream output;

	@Before
	public void setUp(){
		board = new TicTacToeModel(3);
		boardVC = new TicTacToeViewController(board);
		output = new ByteArrayOutputStream();
		System.setOut(new PrintStream(output));
	}
	
    @After
    public void cleanUp() {
        System.setOut(null);
    }
	
	@Test
	public void testViewModelForBlankGrid(){
		boardVC.viewModel();
		String s = "\n  012\n\n0 ---\n1 ---\n2 ---\n\n\n\n";
		assertEquals(s.length(), output.size());
	}
	
}