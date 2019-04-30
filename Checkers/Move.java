public class Move 
{
	//INSTANCE VARIABLES
	private Point piece;		//The original location of the Piece
	private Point destination;	//The destination of the Piece
	private Point arrow;		//Where the Arrow will be shot
	
	//CONSTRUCTORS
    public Move(Point piece, Point destination, Point arrow) 
    {
    	//initialize instance variables
    	this.piece = piece;
    	this.destination = destination;
    	this.arrow = arrow;
    }
    
    //METHODS
    public Point getPiece()
    {
    	return piece;
    }
    
    public Point getDestination()
    {
    	return destination;
    }
    
    public Point getArrow()
    {
    	return arrow;
    }
    
    public String toString()
    {
    	return piece.toString() + " " + destination.toString() + " " + arrow.toString();
    }
    
    /**
     *	Two Move objects are equal if all of their Point instance variables are equal
     *	(see the .equals method of the Point class)
     */
    public boolean equals(Object other)
    {
    	if(null == other || !(other instanceof Move))
    		return false;
    	Move m = (Move)other;
    	
    	return piece.equals(m.getPiece()) 
    		&& destination.equals(m.getDestination())
    		&& arrow.equals(m.getArrow());
    }
}