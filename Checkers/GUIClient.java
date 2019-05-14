import java.util.List;
import mayflower.*;

public class GUIClient extends CheckersClient
{
	private CheckersStage stage;
	private Mayflower mayflower;
	
	public GUIClient()
	{
		stage = null;
		mayflower = null;
	}
	
	/**
	 *	Getter for stage instance variable
	 */
	public CheckersStage getStage()
	{
		return stage;
	}
	
	/**
	 *	Create a new AmazonStage and open a Mayflower window
	 */
	public void onYouAre(Piece piece)
	{
		super.onYouAre(piece);
		
		stage = new CheckersStage(this, getGame(), getMyPiece());
		if(null == mayflower)
			mayflower = new Mayflower("Amazons & Arrows", 605, 680, stage);
		else
			mayflower.setStage(stage);
	}
	
	/**
	 *	Move the pieces on the AmazonsStage
	 */
	public void onMove(Piece piece, int fromRow, int fromCol, int toRow, int toCol, int arrowRow, int arrowCol)
	{		
		super.onMove(piece, fromRow, fromCol, toRow, toCol, arrowRow, arrowCol);
		
		stage.updatePiece(new Point(fromRow, fromCol), null);
		stage.updatePiece(new Point(toRow, toCol), piece);
		stage.updatePiece(new Point(arrowRow, arrowCol), Piece.ARROW);
	}
    
}