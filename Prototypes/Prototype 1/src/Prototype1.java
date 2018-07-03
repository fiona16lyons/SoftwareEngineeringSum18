import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

public class Prototype1
{
	public static void main(String [] args)
	{
		String myInputFile = "input/sample_questions.txt";
		try
		{
			BufferedReader readIn = new BufferedReader(new FileReader(myInputFile));
			System.out.println("Able to read input file!");
			readIn.close();
			
			// Make sure data is loaded in properly
			Game newGame = new Game(myInputFile);
			System.out.println(newGame.toString());
			
			//Test answering questions
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
		}
		catch (Exception e)
		{
			System.out.println("Exception caught: " + e.getMessage());
			System.out.println("Stack Trace: " + e.getStackTrace());
			System.out.println("Unable to read input file :-(");
		}
	}

}