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
	
	private int roundsLeft;
	private int round;	// current round we're on
	
	// player objects
	private int turn;			// current turn
	private Player [] players;	// array of players in the game
	
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

		this.roundsLeft = 8; // 50 - spins per round
		this.round = 0;
		this.turn = 0;
		
		// add players into the game
		this.players = new Player[2];
		addPlayers();
		
		// load data into board & wheel
		loadData(inputFile);
	}
	
	/**
	 * This function adds two players to the game.
	 */
	private void addPlayers()
	{
		for (int i=0;i<2;i++)
		{
			this.players[i] = new Player(i);
		}
	}
	
	/**
	 * Returns whose turn it is to play
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
						System.out.println("New Category!");
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
							int val = 2*(500 - questionIndex*100); // determine question value, descending order
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
		rtn += this.gameWheels[0].toString();
		rtn += "\n";
		rtn += this.gameWheels[1].toString();
		rtn += "\n";
		rtn += this.gameBoards[0].toString();
		rtn += "\n";
		rtn += this.gameBoards[1].toString();
		return rtn;
	}
	
	/**
	 * This function has the basic functionality of answering a question
	 */
	public void takeTurn()
	{
		System.out.println("Spins Left: " + this.roundsLeft);
		// decrement rounds
		this.roundsLeft --;
		if (this.roundsLeft < 0 || this.gameBoards[this.round].isEmpty())
		{ //switch rounds
			if (this.round == 1)
			{
				System.out.println("GAME OVER!!!");
				System.out.println("Total Scores:");
				this.players[0].switchRounds();
				this.players[1].switchRounds();
				System.out.println("Player 0 " + this.players[0].getTotalScore());
				System.out.println("Player 1 " + this.players[1].getTotalScore());
				int winner = declareWinner();
				if (winner != -1)
				{
					System.out.println(" PLAYER " + winner + " WINS!!!");
				}
				else
				{
					System.out.println("ITS A TIE!!!!");
				}
				return;
			}
			System.out.println("----------");
			System.out.println("NEW ROUND!!");
			System.out.println("----------");
			
			this.roundsLeft = 8; 	// reset round counter
			this.round ++;			// move to next round
			
			// set player scores 
			this.players[0].switchRounds();
			this.players[1].switchRounds();
			return;
		}
		
		// spin the wheel
		int slice = this.gameWheels[this.round].spin();

		//print category
		System.out.println("You got " + slice + " : " + this.gameWheels[this.round].getSlice(slice) + "!");
		
		// select the question in the category if possible
		if (slice >= 0 && slice < 6)
		{
			System.out.println("Selected category: " + this.gameBoards[this.round].getCategory(slice));
			if (!this.gameBoards[this.round].categoryEmpty(slice))
			{
				System.out.println("Upcoming Question: " + this.gameBoards[this.round].getQuestion(slice));
				System.out.println("Upcoming Answer: " + this.gameBoards[this.round].getAnswer(slice));
				
				// assume the player always answers right for now
				int val = this.gameBoards[this.round].answer(slice);
				System.out.println("Awarding " + val + " points to player " + this.turn);
				this.players[this.turn].awardPoints(val);
				
				// increment turn
				this.turn = (this.turn +1) % this.players.length;
			}
			else
			{
				System.out.println("Category empty! Spin again.");
			}
		}
		else if (slice == 6)
		{ // lose turn
			this.turn = (this.turn +1) % this.players.length;
		}
		else if (slice == 7)
		{ // free turn; assume the player gets to spin again after winning a free turn
			this.players[this.turn].awardToken();
		}
		else if (slice == 8)
		{ // Player's choice
			this.turn = (this.turn +1) % this.players.length;
		}
		else if (slice == 9)
		{ // Opponents choice
			this.turn = (this.turn +1) % this.players.length;
		}
		else if (slice == 10)
		{ // bankruptcy
			this.players[this.turn].bankruptcy();
			this.turn = (this.turn +1) % this.players.length;
		}
		else if (slice == 11)
		{ // double your score
			this.players[this.turn].doubleScore();
			this.turn = (this.turn +1) % this.players.length;
		}
		
	}
	
	public String displayPoints()
	{
		String rtn = "";
		rtn += "Player 0 : " + this.players[0].getRoundScore() + "\t" + this.players[0].getTotalScore() + "\tTokens: " + this.players[0].getTokens() + "\n";
		rtn += "Player 1 : " + this.players[1].getRoundScore() + "\t" + this.players[1].getTotalScore()+ "\tTokens: " + this.players[1].getTokens();
		return rtn;
	}
	
	/**
	 * This function returns the index of the player that wins, or declares a
	 * tie
	 * @return index of winner, -1 if tie
	 */
	public int declareWinner()
	{
		if (this.players[0].getTotalScore() > this.players[1].getTotalScore())
		{
			return 0;
		}
		else if (this.players[0].getTotalScore() < this.players[1].getTotalScore())
		{
			return 1;
		}
		else
		{ //tie!
			return -1;
		}
	}
}


