/**
 * This file contains the Question class.
 * 
 * @author Fiona Lyons
 * @version 1.0
*/

public class Question
{
	// class variables
	private String question;		// display test of question
	private String answer;			// display answer of question
	private int value;				// how much answering is worth
	private boolean beenAnswered;	// true if the question is answered
	
	// constructor
	Question(String question, String answer, int value)
	{
		this.question = question;
		this.answer = answer;
		this.value = value;
		this.beenAnswered = false;	// default to not answered
	}
	
	/**
	 * This method retrieves the question string.
	 * @return question
	 */
	public String getQuestion()
	{
		return this.question;
	}
	
	/**
	 * This method retrieves the answer string.
	 * @return answer
	 */
	public String getAnswer()
	{
		return this.answer;
	}
	
	/**
	 * This method returns the question point value when answered
	 * @return question value
	 */
	public int getValue()
	{
		return this.value;
	}
	
	/**
	 * This method returns whether or not the question has been answered
	 * @return true if answered
	 */
	public boolean beenAnswered()
	{
		return this.beenAnswered;
	}
	
	/**
	 * This method simulates answering a question during the game, thus 
	 * setting the question as answered.
	 * @return value of the question
	 */
	public int answer()
	{
		this.beenAnswered = true;
		return this.value;
	}
}