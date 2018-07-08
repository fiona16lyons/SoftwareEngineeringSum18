/**
 * This is the class for the Slice object, contained in the Wheel class.
 * 
 * Different types of slices are indicated by an integer value, and are 
 * defined as follows:
 * 	0	Category 1 (leftmost)
 * 	1	Category 2
 * 	2	Category 3 
 * 	3	Category 4 
 * 	4	Category 5 
 * 	5	Category 6 (rightmost) 
 * 	6	Lose Turn
 * 	7	Free Turn
 * 	8	Player's Choice
 * 	9	Opponent's Choice
 * 	10	Bankruptcy
 * 	11	Double Your Score
 * 
 * @author Fiona Lyons
 * @version 1.1
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