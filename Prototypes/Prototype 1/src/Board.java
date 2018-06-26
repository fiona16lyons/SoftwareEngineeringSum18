/**
 * This file contains the Board class.  
 */

public class Board
{
	//variables
	private Category [] categories;	// array of categories loaded in
	private int currentCategory;	// for adding categories
	
	//constructor 
	Board()
	{
		this.categories = new Category[6];
		this.currentCategory = 0;
	}
	
	// need a lot of checking to ensure we don't add more than 6 categories;
	// perhaps only read in the first category name and assume next 5 questions
	// are in that category? maybe new category on a certain line??
	
	/**
	 * This method shows the category's next question as a string
	 * @param category category to check
	 * @return question string
	 */
	public String showQuestion(int category)
	{
		try
		{
			if (category < 0 || category > 5)
			{	// make sure category is valid
				return this.categories[category].getQuestion().printQuestion();
			}
			else
			{
				throw new Exception("Unable to show question - invalid " +
						"category selection.");
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception caught: " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * This method adds a question to the specified category
	 * @param category category to be added to 
	 * @param question question string
	 * @param answer answer string
	 * @param value point value of question
	 */
	public void addQuestion(int category, String question, String answer, int value)
	{
		try
		{
			if (category < 0 || category > 5)
			{	// make sure category is valid
				this.categories[category].addQuestion(question, answer, value);
			}
			else
			{
				throw new Exception("Invalid category call while trying " +
						"to add question: " + question);
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception caught: " + e.getMessage());
		}
	}
}