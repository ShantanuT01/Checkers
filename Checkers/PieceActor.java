import mayflower.*;

public class PieceActor extends Actor
{
	//INSTANCE VARIABLES
	private int row, col;
	private Piece myPiece;
	private boolean selected;
	private boolean wasClicked;
	
	//CONSTRUCTORS
    public PieceActor(int row, int col) 
    {
    	//initialize instance variables
    	this.row = row;
    	this.col = col;
    	
    	setPiece(null);
    	selected = false;
    	wasClicked = false;
    }
    
    //METHODS
	public void update()
	{
		if(isClicked() && !wasClicked)
		{
			//Tell the stage that this piece was clicked
			CheckersStage stage = (CheckersStage)getStage();
			stage.click(row, col);
			
			wasClicked = true;
		}
		
		//set wasClicked to false if the mouse is not clicked
		wasClicked = isClicked();
	}    
	
	/**
	 *	Change which piece this PieceActor is displaying
	 *		null, Piece.BLACK, Piece.WHITE, Piece.ARROW
	 */
	public void setPiece(Piece piece)
	{
		myPiece = piece;
		
		String img = "img/empty";
		if(piece == Piece.BLACK)
			img = "img/black";
		else if(piece == Piece.WHITE)
			img = "img/white";
		else if(piece == Piece.ARROW)
			img = "img/arrow";
			
		if(selected)
			img += "Selected";
			
		setPicture(img + ".png");
	}
	
	public void setSelected(boolean selected)
	{
		this.selected = selected;
		setPiece(myPiece);
	}
}