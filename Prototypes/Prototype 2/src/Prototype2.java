import java.io.BufferedReader;
import java.io.FileReader;
/**
	This is the main startup file for Prototype 1 of the Wheel of Jeopardy
	game. This prototype covers the following requirements.
	IO Interface
	Question class
	Category class
	Basic data loading and display
	
	@author Fiona Lyons
	@version 1.0
*/

public class Prototype2
{
	public static void main(String [] args)
	{
		String myInputFile = "input/real_questions.txt"; //args[0];
		try
		{
			BufferedReader readIn = new BufferedReader(new FileReader(myInputFile));
			System.out.println("Able to read input file!");
			readIn.close();
			
			// Make sure data is loaded in properly
			Game newGame = new Game(myInputFile);
			System.out.println(newGame.toString());
			
			int turnsLeft = 18; // for testing
			while (turnsLeft > 0)
			{
				System.out.println("It's Player " + newGame.getTurn() + "'s turn!");
				newGame.takeTurn();
				turnsLeft --;
				System.out.println("----- SCORES -----");
				System.out.println(newGame.displayPoints());
				System.out.println("------------------");
			}
			
			//Test answering questions
			/*
			newGame.takeTurn();
			System.out.println("===========");
			newGame.takeTurn();
			System.out.println("===========");
			newGame.takeTurn();
			System.out.println("===========");
			newGame.takeTurn();
			System.out.println("===========");
			newGame.takeTurn();
			System.out.println("===========");
			newGame.takeTurn();
			System.out.println("===========");
			*/
		}
		catch (Exception e)
		{
			System.out.println("Exception caught: " + e.getMessage());
			System.out.println("Stack Trace: " + e.getStackTrace());
			System.out.println("Unable to read input file :-(");
		}
	}

}