package WheelOfJeopardy;
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
	 * This function checks to see if a category on the board is empty, i.e.
	 * every question has been answered
	 * @param index index of category
	 * @return true if empty
	 */
	public boolean categoryEmpty(int index)
	{
		if (this.categories[index].isEmpty())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * This functions checks if all of the questions on the board have been 
	 * answered, and so the gameboard is empty.
	 * @return true if empty
	 */
	public boolean isEmpty()
	{
		for (int i=0;i<this.categories.length;i++)
		{
			if ((this.categories[i] == null) )
			{
				//pass
			}
			else if (!this.categories[i].isEmpty())
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This function checks if all the questions on the board have been 
	 * loaded in
	 * @return true if full
	 */
	public boolean isFull()
	{
		for (int i=0;i<this.categories.length;i++)
		{
			if ((this.categories[i] == null) || (!this.categories[i].isFull()))
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * This function returns the name of a category
	 * @param index where category is stored
	 * @return name of category
	 */
	public String getCategory(int index)
	{
		if (index >= 0 && index < this.categories.length)
		{
			return this.categories[index].getCategory();
		}
		else
		{
			System.out.println("Invalid category selection.");
			return "";
		}
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
			if (category >= 0 && category < this.categories.length)
			{	// make sure category is valid
				return this.categories[category].getQuestion().getQuestion();
			}
			else
			{
				System.out.println("Unable to show question - invalid " +
						"category selection.");
				throw new Exception("Invalid category");
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
			if (category >= 0 && category < this.categories.length)
			{	// make sure category is valid
				return this.categories[category].getQuestion().getAnswer();
			}
			else
			{
				System.out.println("Unable to show answer - invalid " +
						"category selection.");
				throw new Exception("Invalid category");
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
			if (category >= 0 && category < this.categories.length)
			{	// make sure category is valid
				return this.categories[category].getQuestion().getValue();
			}
			else
			{
				System.out.println("Unable to show value - invalid " +
						"category selection.");
				throw new Exception("Invalid category");
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
	 * @return the value of the question answered
	 */
	public int answer(int category)
	{
		try
		{
			if (category >= 0 && category < this.categories.length)
			{	// make sure category is valid
				return this.categories[category].answerQuestion();
			}
			else
			{
				System.out.println("Unable to answer - invalid " +
						"category selection.");
				return -1;
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception caught: " + e.getMessage());
			return -1;
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
			if (this.currentCategory <= this.categories.length)
			{
				this.categories[this.currentCategory] = new Category(category);
				this.currentCategory ++;
			}
			else
			{
				System.out.println("Unable to add cateogry " + category +
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
			if (category >= 0 && category < this.categories.length)
			{	// make sure category is valid
				if (!this.categories[category].isFull())
				{
					this.categories[category].addQuestion(question, answer, value);
				}
				else
				{
					System.out.println("Requested category " + this.categories[category].getCategory()
							+ " is full! Cannot add another question. ");
				}
			}
			else
			{
				System.out.println("Invalid category call while trying " +
						"to add question: " + question);
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception caught: " + e.getMessage());
			System.out.println("Stack Trace: " + e.getStackTrace());
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
			rtn += this.categories[i].toString();
		}
		return rtn;
	}
	
	
}