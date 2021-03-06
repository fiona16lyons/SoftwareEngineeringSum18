package WheelOfJeopardy;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;

/**
 * This file contains the Controller class, which communicates to the Player
 * instances, the Game instance, and the Actor.
 * @author Fiona Lyons
 * @version 1.0
 */

public class Controller
{
	// variables
	private Game game;
	private Player [] players;
	private int turnsLeft;
	private int turn;
	private Timer timer;
	private boolean gameOver;
	
	// constructor
	Controller(String inputFile)
	{
		this.gameOver = false;
		this.turnsLeft = 50;
		this.turn = 0;
		this.timer = new Timer();
		
		// add players into the game
		this.players = new Player[2];
		addPlayers();
		
		// load data into board & wheel
		this.game = new Game(inputFile);
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
	 * Prints the game board and wheel
	 */
	public String toString()
	{
		return this.game.toString();
	}
	
	/**
	 * returns if game is over
	 * @return true if over
	 */
	public boolean isGameOver()
	{
		return this.gameOver;
	}
	
	/**
	 * This function returns the game board and wheel as a string
	 * @return
	 */
	public String displayBoard()
	{
		return this.game.toString();
	}
	
	/**
	 * This function prints the options available on a given turn
	 * @return
	 */
	public String printOptions()
	{
		String rtn = "";
		rtn += "Options:\n";
		rtn += "\t1: Take Turn\n";
		rtn += "\t2: Display Board\n";
		return rtn;	
	}
	
	/**
	 * Simulates switching rounds of game
	 * @return true if game is over
	 */
	private boolean switchRounds()
	{
		System.out.println("=================================");
		System.out.println("		ROUND OVER !!");
		System.out.println("=================================");
		this.turnsLeft = 50;
		this.players[0].switchRounds();
		this.players[1].switchRounds();
		return this.game.switchRounds();
	}
	
	/**
	 * This function has the basic functionality of answering a question
	 */
	public void takeTurn()
	{
		// decrement rounds
		this.turnsLeft--;
		System.out.println("Player " + this.turn + " taking turn! ");
		
		if (this.turnsLeft <= 0 || this.game.isEmpty())
		{ //switch rounds
			this.gameOver =switchRounds();
			return;
		}
		
		// spin the wheel
		
		int slice = -1;
		try
		{
			slice = this.game.takeTurn();
		}
		catch (Exception e)
		{
			System.out.println("Unknown exception occured while taking turn.");
			System.exit(0);
		}
		
		System.out.println("You got: " + this.game.printSlice(slice));
		
		// select the question in the category if possible
		if (slice >= 0 && slice < 6)
		{
			System.out.println("Press 1 for correct answer, 0 for incorrect answer.");
			answerQuestion(slice);
		}
		else if (slice == 6)
		{ // lose turn
			if (this.players[this.turn].getTokens() > 0)
			{
				System.out.println("Would you like to use a token? Press 1 for yes, 0 for no. ");
				int response = processInput(0, 1);
				if (response == 1) // yes!
				{
					this.players[this.turn].useToken();
					return;
				}
			}
			this.turn = (this.turn +1) % this.players.length;
		}
		else if (slice == 7)
		{ // free turn; assume the player gets to spin again after winning a free turn
			this.players[this.turn].awardToken();
		}
		else if (slice == 8)
		{ // Player's choice
			System.out.println(displayBoard());
			System.out.println("Select the number of the category you wish to choose (0-5)");
			answerQuestion(processInput(0,5));
		}
		else if (slice == 9)
		{ // Opponents choice
			// switch player
			System.out.println(displayBoard());
			System.out.println("Select the number of the category you wish to choose (0-5)");
			// make sure we don't increment turn if the question is invalid
			boolean hasQuestion = answerQuestion(processInput(0,5));
			if (hasQuestion) this.turn = (this.turn +1) % this.players.length;
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
	
	/**
	 * 
	 * @param category
	 * @return true if category not empty
	 */
	private boolean answerQuestion(int category)
	{
		if (!this.game.categoryEmpty(category))
		{
			System.out.println("The question is: " + this.game.getQuestion(category));
			// query user for if the response is correct or not
			int isCorrect = processInput(0,1);
			
			switch (isCorrect)
			{
			case 0: //incorrect
				System.out.println("Incorrect!");
				System.out.println("The answer is: " + this.game.getAnswer(category));
				this.players[this.turn].awardPoints(-this.game.answer(category));
				break;
			case 1: //correct
				System.out.println("Correct!");
				System.out.println("The answer is: " + this.game.getAnswer(category));
				this.players[this.turn].awardPoints(this.game.answer(category));
				break;
			}
			// increment turn
			this.turn = (this.turn +1) % this.players.length;
			return true;
		}
		else
		{
			System.out.println("Category empty! Spin again.");
			return false;
		}
	}
	
	/**
	 * This function processes actor input into an integer
	 * @param min minimum acceptable integer
	 * @param max maximum acceptable integer
	 * @return integer input by actor
	 */
	private int processInput(int min, int max)
	{
		int userResponse = -1;
		while (!((userResponse >= min) && (userResponse <= max))) 
        {
            // Make sure user input is valid
            try
            {
                userResponse = WheelOfJeopardy.userInput.nextInt();
            }
            catch (InputMismatchException a)
            { // Non integer input, reset user input
            	WheelOfJeopardy.userInput.next();                     
            }

        }
		return userResponse;
	}
	
	public String displayFinalPoints()
	{
		String rtn = "";
		rtn += "=================================\n";
		rtn += "Player 0 : " + this.players[0].getTotalScore()+ "\n";
		rtn += "Player 1 : " + this.players[1].getTotalScore()+ "\n";
		rtn += "=================================";
		return rtn;
	}
	
	public String displayPoints()
	{
		String rtn = "";
		rtn += "=================================\n";
		rtn += "Player 0 : " + this.players[0].getRoundScore() + "\t" + this.players[0].getTotalScore() + "\tTokens: " + this.players[0].getTokens() + "\n";
		rtn += "Player 1 : " + this.players[1].getRoundScore() + "\t" + this.players[1].getTotalScore()+ "\tTokens: " + this.players[1].getTokens() + "\n";
		rtn += "\tTurns Left in Round: " + this.turnsLeft + "\n";
		rtn += "=================================\n";
		rtn += "\tIt's Player " + this.turn + "'s turn!\n";
		rtn += "=================================";
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