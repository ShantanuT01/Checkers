public class Move 
{
    //INSTANCE VARIABLES
    private Point piece;        //The original location of the Piece
    private Point destination;  //The destination of the Piece
    private Point arrow;        //Where the Arrow will be shot
    
    //CONSTRUCTORS
    public Move(Point piece, Point destination) 
    {
        //initialize instance variables
        this.piece = piece;
        this.destination = destination;
        
    }
    public Move(Point from){
        this.piece = from; 
    }
    //METHODS
    public Point getPiece()
    {
        return piece;
    }
    public String toString(){
        if(piece != null && destination != null){
            return "(" + piece.toString() + ", "  + destination.toString() + ")";
        }
        else if(piece != null){
            return piece.toString();
        }
        else if(destination != null){
            return destination.toString(); 
        }
        return "";
        
    }
    public Point getDestination()
    {
        return destination;
    }
    public void setTo(Point x){
        this.destination = x; 
    }
    public void setFrom(Point x){
        if(piece == null){
            piece = x; 
        }
    }
    
    
    
    
    /**
     *  Two Move objects are equal if all of their Point instance variables are equal
     *  (see the .equals method of the Point class)
     */
    public boolean equals(Object other)
    {
        if(null == other || !(other instanceof Move))
            return false;
        Move m = (Move)other;
        
        return piece.equals(m.getPiece()) 
            && destination.equals(m.getDestination())
    ;
    }
}