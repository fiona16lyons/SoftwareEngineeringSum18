import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

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
		
		// load data into board & wheel
		loadData(inputFile);
	}
	
	/**
	 * This method reads in the specified input file, and loads the contained
	 * data into both the game board and wheel. 
	 * @param inputFile file containing questions
	 */
	private void loadData(String inputFile)
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
						// add it to game board
						this.gameBoard.addCategory(inDelim[0]);
						// add it to wheel - 1 indexing 
						this.gameWheel.addSlice(inDelim[0], this.gameBoard.getCurrentCategory());
						questionIndex = 0;	// reset question index
					}
					else
					{	// add this question to the current category!
						int val = 2*(500 - questionIndex*100); // determine question value, descending order
						this.gameBoard.addQuestion(this.gameBoard.getCurrentCategory()-1, inDelim[0], inDelim[1],val);
						questionIndex++;
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
		rtn += this.gameWheel.toString();
		rtn += "\n";
		rtn += this.gameBoard.toString();
		return rtn;
	}
	
	/**
	 * This function has the basic functionality of answering a question
	 */
	public void takeTurn()
	{
		// spin the wheel
		int slice = this.gameWheel.spin();
		
		//print category
		System.out.println("You got: " + this.gameWheel.getSlice(slice) + "!");
		slice = 0;
		// select the question in the category if possible
		if (slice >= 0 && slice < 6)
		{
			System.out.println("Selected category: " + this.gameBoard.getCategory(slice));
			if (!this.gameBoard.categoryEmpty(slice))
			{
				System.out.println("Upcoming Question: " + this.gameBoard.getQuestion(slice));
				System.out.println("Upcoming Answer: " + this.gameBoard.getAnswer(slice));
				this.gameBoard.answer(slice);
			}
			else
			{
				System.out.println("Category empty! Spin again.");
			}
		}
		
		
	}
}


