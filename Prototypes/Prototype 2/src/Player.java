/**
 * This file introduces the Player class, which represents a player of the
 * game Wheel of Jeopardy.
 * 
 * @author Fiona Lyons
 * @version 1.0
 */

class Player
{
	// variables
	private int roundScore;		// round score
	private int totalScore;		// total score
	private int playerIndex; 	// which player this is
	private int tokens;			// how many tokens the player has
	
	// constructor
	Player(int playerIndex)
	{
		this.playerIndex = playerIndex;
		this.roundScore = 0;
		this.totalScore = 0;
		this.tokens = 0;
	}
	
	/**
	 * Gives the player a token
	 */
	public void awardToken()
	{
		this.tokens ++;
	}
	
	/**
	 * This function uses one of the player's tokens, if they have any available
	 * @return true if successful.
	 */
	public boolean useToken()
	{
		if (this.tokens > 0)
		{
			this.tokens --;
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Returns how many tokens a player has
	 * @return tokens
	 */
	public int getTokens()
	{
		return this.tokens;
	}
	
	/**
	 * This function returns the index of the current player.
	 * @return
	 */
	public int getPlayerIndex()
	{
		return this.playerIndex;
	}
	
	/**
	 * This function returns the player's round score
	 * @return round score
	 */
	public int getRoundScore()
	{
		return this.roundScore;
	}
	
	/**
	 * This function retrieves the player's total score across rounds.
	 * @return total score
	 */
	public int getTotalScore()
	{
		return this.totalScore;
	}
	
	/**
	 * This function awards a postiive or negative number of points to the player.
	 * @param points points awarded
	 */
	public void awardPoints(int points)
	{
		this.roundScore += points;
	}
	
	/**
	 * This function zeros out the player's round score; if it is negative,
	 * the player retains their negative round score.
	 */
	public void bankruptcy()
	{
		if (this.roundScore > 0)
		{	// only set to zero if your score is negative
			this.roundScore = 0;
		}
	}
	
	/**
	 * This method doubles the player's round score, even if it is negative
	 */
	public void doubleScore()
	{
		this.roundScore = this.roundScore*2;		
	}
}