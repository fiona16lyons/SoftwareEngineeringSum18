import java.util.Random;

/**
 * This file contains the Wheel class, which represents the wheel in Wheel
 * of Jeopardy. 
 * 
 * @author Fiona Lyons
 * @version 1.0
 */

public class Wheel
{
	//variables
	private Slice [] slices;	// All of the slices on the wheel
	private int currentSlice;	// for adding slices to wheel
	
	//constructor
	Wheel()
	{
		this.slices = new Slice[12];
		this.currentSlice = 1;	// slices are 1 indexed instead of 0 indexed
		addDefaultSlices();	// propogate default slices
	}
	
	/**
	 * This method adds the default slices (lose turn, bankruptcy) to the 
	 * wheel that are constant across games.
	 */
	private void addDefaultSlices()
	{	// #FixMe test defaultNames!!
		String [] defaultNames = {"Lose Turn",  "Free Turn", "Player's Choice", "Opponent's Choice", "Bankruptcy", "Double Your Score"}; 
		for (int i=0;i<6;i=i+1)
		{	// default slices will be between 7 and 12
			addSlice(defaultNames[i], i+6);
		}
	}
	
	/**
	 * This method adds a slice to the wheel. This method should only be 
	 * called outside this object to add category slices.
	 * @param name display name of slice
	 * @param type type of slice
	 */
	public void addSlice(String name, int type)
	{
		try
		{
			if (this.currentSlice < 7)
			{	// we're adding a category, use current slice; don't iterate
				// past 6
				this.slices[this.currentSlice] = new Slice(name,this.currentSlice);
				this.currentSlice ++;
			}
			else if (this.currentSlice < 12)
			{
				this.slices[this.currentSlice] = new Slice(name,type);
			}
			else
			{
				throw new Exception("Unable to add slice " + name + " to wheel.");
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception caught: " + e.getMessage());
		}
	}
	
	/**
	 * This method returns the string name of a slice
	 * @param type which slice to query
	 * @return name of slice
	 */
	public String getSlice(int type)
	{
		try
		{
			if (type > 0 && type < 5)
			{
				return this.slices[type].getName();
			}
			else
			{
				throw new Exception("Unable to return slice; invalid type.");
			}
		}
		catch (Exception e)
		{
			System.out.println("Exception caught: " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * This method replicates spinning the wheel, and returns the number of
	 * slice chosen.
	 * @return slice chosen
	 */
	public int spin()
	{	// #FixMe test that random works
		return new Random().nextInt(12);
	}
	
	/**
	 * This function prints the wheel as a plaintext string.
	 */
	public String toString()
	{
		String rtn = "";
		for (int i=0;i<this.slices.length;i++)
		{
			rtn += this.slices[i].getType();
			rtn += "\n";
			rtn += this.slices[i].getName();
			rtn += "\n";
		}
		return rtn;
	}
	
	
}
