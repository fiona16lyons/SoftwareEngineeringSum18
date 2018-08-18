package WheelOfJeopardy;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.Enum;

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
	// Game Objects
	//private Wheel gameWheel;
	//private Board gameBoard;
	private Board [] gameBoards; // round 1 and 2 boards
	private Wheel [] gameWheels; // round 1 and 2 boards
	
	//private int roundsLeft;
	private int round;	// current round we're on
	
	// player objects
	private int turn;			// current turn
	//private Player [] players;	// array of players in the game
	
	//constructor
	Game(String inputFile)
	{
		//this.gameBoard = new Board();
		//this.gameWheel = new Wheel();
		
		this.gameBoards = new Board[2];
		this.gameBoards[0] = new Board();
		this.gameBoards[1] = new Board();
		this.gameWheels = new Wheel[2];
		this.gameWheels[0] = new Wheel();
		this.gameWheels[1] = new Wheel();

		//this.roundsLeft = 8; // 50 - spins per round
		this.round = 0;
		this.turn = 0;
		
		loadData(inputFile);
	}
	
	
	/**
	 * Returns whose turn it is to play
	 * @return whose turn it is
	 */
	public int getTurn()
	{
		return this.turn;
	}
	
	/**
	 * This method reads in the specified input file, and loads the contained
	 * data into both the game board and wheel. 
	 * @param inputFile file containing questions
	 */
	public void loadData(String inputFile)
	{
		try
		{
			// initialize variables
			BufferedReader readIn = new BufferedReader(new FileReader(inputFile));
			String inString = "";
			String [] inDelim;
			int questionIndex = 0;
			int lineCounter = 0;
			try
			{
				inString = readIn.readLine();
				while (inString != null)
				{
					inDelim = inString.split(",");	// assume comma delimited
					if (inDelim.length == 1)
					{	// its a new category!
						if (this.gameBoards[0].isEmpty() || !this.gameBoards[0].isFull()) 
						{ // add to round 1
							this.gameWheels[0].addSlice(inDelim[0], this.gameBoards[0].getCurrentCategory());
							// add it to game board
							this.gameBoards[0].addCategory(inDelim[0]);
							questionIndex = 0;	// reset question index
						}
						else if (!this.gameBoards[1].isFull())
						{ // add to round 2
							this.gameWheels[1].addSlice(inDelim[0], this.gameBoards[1].getCurrentCategory());
							// add it to game board
							this.gameBoards[1].addCategory(inDelim[0]);
							questionIndex = 0;	// reset question index
						}
					}
					else
					{	// add this question to the current category!
						if (!this.gameBoards[0].isFull()) 
						{ // add to round 1
							int val = 2*(500 - questionIndex*100); // determine question value, descending order
							this.gameBoards[0].addQuestion(this.gameBoards[0].getCurrentCategory()-1, inDelim[0], inDelim[1],val);
							questionIndex++;
						}
						else if (!this.gameBoards[1].isFull()) 
						{ // add to round 2
							int val = 2*2*(500 - questionIndex*100); // determine question value, descending order
							this.gameBoards[1].addQuestion(this.gameBoards[1].getCurrentCategory()-1, inDelim[0], inDelim[1],val);
							questionIndex++;
						}
					}
					lineCounter ++;
					inString = readIn.readLine();
				}
				readIn.close();
			}
			catch (Exception e)
			{
				System.out.println("Exception caught: " + e.getMessage());
				System.out.println("Stack Trace: " + e.getStackTrace());
				System.out.println("Unable to read in specified input file; "
						+ "error on line " + lineCounter);
			}
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Exception caught: " + e.getMessage());
			System.out.println("Stack Trace: " + e.getStackTrace());
			System.out.println("Unable to find specified input file.");
		}
		catch (Exception e)
		{
			System.out.println("Exception caught: " + e.getMessage());
			System.out.println("Stack Trace: " + e.getStackTrace());
			System.out.println("Unable to read in specified input file.");
		}
	}
	
	/**
	 * This function returns both the gameWheel and gameBoard as a plain
	 * text string.
	 */
	public String toString()
	{
		String rtn = "";
		rtn += this.gameWheels[this.round].toString();
		rtn += "\n";
		rtn += this.gameBoards[this.round].toString();
		return rtn;
	}
	
	/**
	 * This function returns the slice we select
	 * @param slice slice we select
	 * @return string of slice name
	 */
	public String printSlice(int slice)
	{
		return this.gameWheels[this.turn].getSlice(slice);
	}
	
	/**
	 * Returns whether all the questions have been answered in the current 
	 * round or not
	 * @return
	 */
	public boolean isEmpty()
	{
		return this.gameBoards[this.round].isEmpty();
	}
	
	/**
	 * Returns if the given category is empty
	 * @param category chosen cateogry
	 * @return true if empty
	 */
	public boolean categoryEmpty(int category)
	{
		return this.gameBoards[this.round].categoryEmpty(category);
	}
	
	/**
	 * Returns the question in the given category
	 * @param category chosen category
	 * @return question string
	 */
	public String getQuestion(int category)
	{
		return this.gameBoards[this.round].getQuestion(category);
	}
	
	/**
	 * Returns the answer in the given category
	 * @param category chosen category
	 * @return answer string
	 */
	public String getAnswer(int category)
	{
		return this.gameBoards[this.round].getAnswer(category);
	}
	
	/**
	 * Simulates answering a question of a given category
	 * @param category chosen category
	 * @return value of question
	 */
	public int answer(int category)
	{
		return this.gameBoards[this.round].answer(category);
	}
	
	/**
	 * Switches round of the game.
	 * @return true if game over
	 */
	public boolean switchRounds()
	{
		if (this.round == 1)
		{
			return true;
		}
		else
		{
			this.round ++;
		}
		return false;
	}
	
	/**
	 * This function has the basic functionality of answering a question
	 */
	public int takeTurn()
	{
		// spin the wheel
		int slice = this.gameWheels[this.round].spin();
		return slice;
	}
}


