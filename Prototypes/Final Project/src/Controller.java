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
	
	public String printOptions()
	{
		String rtn = "";
		rtn += "Options:\n";
		rtn += "	1: Take Turn\n";
		return rtn;	
	}
	
	/**
	 * Simulates switching rounds of game
	 * @return true if game is over
	 */
	private boolean switchRounds()
	{
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
		if (this.turnsLeft < 0 || this.game.isEmpty())
		{ //switch rounds
			this.gameOver =switchRounds();
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
	
}