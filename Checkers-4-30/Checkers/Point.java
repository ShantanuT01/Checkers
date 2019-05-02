class Point 
{
	//INSTANCE VARIABLES
	private int row, col;
	
	//CONSTRUCTORS
	public Point(int row, int col)
	{
		//initialize instance variables
		this.row = row;
		this.col = col;
	}
	
	//METHODS
	public int getRow()
	{
		return row;
	}
	
	public int getCol()
	{
		return col;
	}
	
	public String toString()
	{
		return row + " " + col;
	}
	
	/**
	 *	Two Point objects are equals if they have the same row and col
	 */
	public boolean equals(Object other)
	{
		if(null == other || !(other instanceof Point))
			return false;
		Point p = (Point)other;
		return row == p.row && col == p.col;
	}
}
