/**
 * This file contains the Board class.  
 * 
 * @author Fiona Lyons
 * @version 1.0
 */

public class Board
{
	//variables
	private Category [] categories;	// array of categories loaded in
	private int currentCategory;	// for adding category
	
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
	 * This method returns the current category we are on, for adding 
	 * new categories
	 * @return current category
	 */
	public int getCurrentCategory()
	{
		return this.currentCategory;
	}
	
	/**
	 * This method shows the category's next question as a string
	 * @param category category to check
	 * @return question string
	 */
	public String getQuestion(int category)
	{
		try
		{
			if (category > 0 && category < 5)
			{	// make sure category is valid
				return this.categories[category].getQuestion().getQuestion();
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
	 * This method returns the category's next answer as a string
	 * @param category category to check
	 * @return answer string
	 */
	public String getAnswer(int category)
	{
		try
		{
			if (category > 0 && category < 5)
			{	// make sure category is valid
				return this.categories[category].getQuestion().getAnswer();
			}
			else
			{
				throw new Exception("Unable to show answer - invalid " +
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
	 * This method returns the value of the category's next wuestion
	 * @param category category to check
	 * @return question value
	 */
	public int getValue(int category)
	{
		try
		{
			if (category > 0 && category < 5)
			{	// make sure category is valid
				return this.categories[category].getQuestion().getValue();
			}
			else
			{
				throw new Exception("Unable to show value - invalid " +
						"category selection.");
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception caught: " + e.getMessage());
			return -1; 
		}
	}
	
	/**
	 * This function sets the question in the given category as answered.
	 * @param category category to be checked.
	 */
	public void answer(int category)
	{
		try
		{
			if (category > 0 && category < 5)
			{	// make sure category is valid
				this.categories[category].getQuestion().answer();
			}
			else
			{
				throw new Exception("Unable to show answer - invalid " +
						"category selection.");
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception caught: " + e.getMessage());
		}
	}
	
	/**
	 * This method adds a new category type to the board. This should be done 
	 * before any questions are added to the category.
	 * @param category name of category
	 */
	public void addCategory(String category)
	{
		try
		{
			if (this.currentCategory < 5)
			{
				this.categories[this.currentCategory] = new Category(category);
				this.currentCategory ++;
			}
			else
			{
				throw new Exception("Unable to add cateogry " + category +
						" to board.");
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception caught: " + e.getMessage());
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
			if (category > 0 && category < 5)
			{	// make sure category is valid
				if (!this.categories[category].isFull())
				{
					this.categories[category].addQuestion(question, answer, value);
				}
				else
				{
					throw new Exception("Requested category " + this.categories[category].getCategory()
							+ " is full! Cannot add another question. ");
				}
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
	
	/**
	 * This function returns the board as a plaintext string.
	 */
	public String toString()
	{
		String rtn = "";
		for (int i=0;i<this.currentCategory;i++)
		{
			this.categories[i].toString();
		}
		return rtn;
	}
	
	
}