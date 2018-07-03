/**
 * This file contains the Category class. The questions in a category are 
 * stored in an array, which are loaded in descending order, but read out
 * to the game in ascending order. The beginning of the array is the highest
 * valued question, and the end is the lowest valued question.
 * 
 *  @author Fiona Lyons
 *  @version 1.0
 */

public class Category
{
	// variables
	private String category;		// display name of category
	private Question [] questions;	// list of questions in category
	private int currentQuestion;	// points to current question in list
	
	// constructor
	Category(String category)
	{
		this.category = category;
		this.questions = new Question[5];
		this.currentQuestion = 0;	// top of questions array
	}
	
	/**
	 * This function adds a question to the end of the list of existing 
	 * questions in the category. If the added question does not obey 
	 * descending order in the category, or if it attempts to add more 
	 * past the maximum number of questions, an error is thrown
	 * @param question question string
	 * @param answer answer string
	 * @param value point value of question
	 */
	public void addQuestion(String question, String answer, int value)
	{
		try
		{	// Set the top question
			if (!this.isFull())
			{
				this.questions[this.currentQuestion] = new Question(question, answer, value);
				for (int i=0;i<this.currentQuestion;i++)
				{	// Check if values of questions are in descending order
					if (value > this.questions[i].getValue())
					{	// if not, throw exception.
						throw new Exception("Unable to add requested question " +
								question + " to category " + this.category + ",\n " +
								" Values of questions must be in descending" +
								" order : " + value + " > " + 
								this.questions[i].getValue());
					}
				}
				this.currentQuestion++;	// point to new top
			}
			else
			{
				throw new Exception("Category " + this.category + " is full! " +
						" unable to add new question: " + question);
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception caught: " + e.getMessage());
		}
	
	}
	
	/**
	 * This method returns the name of the requested category
	 * @return category name
	 */
	public String getCategory()
	{
		return this.category;
	}
	
	/**
	 * This method returns the next question
	 * @return question
	 */
	public Question getQuestion()
	{
		try
		{	// check if the category is empty
			if (this.currentQuestion > 0)
			{	// return the question
				return this.questions[this.currentQuestion];
			}
			else
			{
				throw new Exception("Unable to retrieve question from category " +
						this.category + ", category is empty. ");
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception caught: " + e.getMessage());
			return null;
		}
		
	}
	
	/**
	 * This function answers the next question in a category.
	 */
	public void answerQuestion()
	{
		try
		{	// check if the category is empty
			if (this.currentQuestion > 0)
			{	// return the 
				this.currentQuestion--;	// decrement currentQuestion ptr
			}
			else
			{
				throw new Exception("Unable to answer question from category " +
						this.category + ", category is empty. ");
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception caught: " + e.getMessage());
		}
	}
	
	/**
	 * This method returns whether or not the category contains the maximum
	 * allowed five questions.
	 * @return true if contains five questions, false otherwise
	 */
	public boolean isFull()
	{
		return (this.currentQuestion == 5);
	}
	
	/**
	 * This function returns the category as a plaintext string.
	 */
	public String toString()
	{
		String rtn = "";
		rtn += this.category;
		rtn += "\n----------------------\n";
		for (int j=0;j<this.currentQuestion;j++)
		{
			rtn += this.questions[j].getQuestion();
			rtn += "	";
			rtn += this.questions[j].getAnswer();
			rtn += "	";
			rtn += this.questions[j].getValue();
			rtn += "\n";
		}
		return rtn;
	}
}
