/**
 * This file contains the Game class, which contains the major gameplay
 * elements of Wheel of Jeopardy. This handles all the interactions between
 * the wheel and the board.
 * 
 *  @author Fiona Lyons
 *  @version 1.0
 */

public class Game
{
	//variables
	// player1, eventually
	// player2, eventually
	private Wheel gameWheel;
	private Board gameBoard;
	
	//constructor
	Game(String inputFile)
	{
		this.gameBoard = new Board();
		this.gameWheel = new Wheel();
		
		// read in input file
		
		// load data into board
		
		// load data into wheel
	}
}
