import java.io.BufferedReader;
import java.io.FileReader;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
	This is the main startup file for Prototype 2 of the Wheel of Jeopardy
	game. 
	
	@author Fiona Lyons
	@version 1.0
*/

public class WheelOfJeopardy
{
	public static Scanner userInput = new Scanner(System.in);
	public static void main(String [] args)
	{
		String myInputFile = "input/real_questions.txt"; //args[0];//
		try
		{
			BufferedReader readIn = new BufferedReader(new FileReader(myInputFile));
			System.out.println("Able to read input file!");
			readIn.close();
			
			// Make sure data is loaded in properly
			Controller newGame = new Controller(myInputFile);
			//Game newGame = new Game(myInputFile);
			System.out.println(newGame.toString());
			System.out.println("=================================");
			System.out.println("Welcome to Wheel of Jeopardy!");
			System.out.println("=================================");
			
			
	        int userResponse = -1;
			while (!newGame.isGameOver())
			{
				while (!((userResponse ==0) || (userResponse == 1))) 
	            {
                    System.out.println(newGame.displayPoints());
					System.out.println(newGame.printOptions());
	                // Make sure user input is valid
	                if (userInput.hasNext())
                    try
	                {
	                    userResponse = userInput.nextInt();
	                }
	                catch (InputMismatchException a)
	                { // Non integer input, reset user input
	                    userInput.next();                     
	                }

	            }
				if (userResponse == 1)
				{
					newGame.takeTurn();
				}
				userResponse = -1;
			}
			userInput.close();
		}
		catch (Exception e)
		{
			System.out.println("Exception caught: " + e);
			System.out.println("Stack Trace: " + e.getStackTrace());
			System.out.println("Unable to read input file :-(");
		}
	}

}