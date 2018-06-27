/**
 * This is the class for the Slice object, contained in the Wheel class.
 * 
 * Different types of slices are indicated by an integer value, and are 
 * defined as follows:
 * 	1	Category 1 (leftmost)
 * 	2	Category 2
 * 	3	Category 3 
 * 	4	Category 4 
 * 	5	Category 5 
 * 	6	Category 6 (rightmost) 
 * 	7	Lose Turn
 * 	8	Free Turn
 * 	9	Player's Choice
 * 	10	Opponent's Choice
 * 	11	Bankruptcy
 * 	12	Double Your Score
 * 
 * @author Fiona Lyons
 * @version 1.0
 */

public class Slice
{
	//variables
	private String name;	// display name of slice
	private int type;		// type of slice (noted above)
	
	//constructor
	Slice(String name, int type)
	{
		this.name = name;
		this.type = type;
	}
	
	/**
	 * This method returns the display name of the slice.
	 * @return name
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * This method returns the type of this slice.
	 * @return type
	 */
	public int getType()
	{
		return this.type;
	}
	
}