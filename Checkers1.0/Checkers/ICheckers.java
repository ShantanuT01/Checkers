import java.util.List;

public interface ICheckers 
{

    /**
     *	Return a new instance of an Amazons object with the same state as *this* object
     *	The copy should contain all the same Pieces in all the same places and the same currentPlayer
     */
    public Checkers copy();

    /**
     *	Return the current turn holder's Piece, Piece.BLACK or Piece.WHITE
     */
    public Piece getCurrentPlayer();

    /**
     *	Change the current player to the specified piece
     */
    public void setCurrentPlayer(Piece piece);

    /**
     *	Change the currentPlayer
     *		Piece.BLACK -> Piece.WHITE
     *		Piece.WHITE -> Piece.BLACK
     */
    public void nextPlayer();

    /**
     *	Return the piece that is on the board at the specified Point
     *		Piece.BLACK, Piece.WHITE, or Piece.ARROW
     */
    public Piece getPiece(Point p);

    /**
     *  Add the specified piece to the board at the specified Point
     */
    public void setPiece(Piece piece, Point p);

    /**
     *	Move the piece located at Point from to Point to
     */
    public void move(Point from, Point to);

    /**
     *	Return true is there is no piece at Point p
     */
    public boolean isEmpty(Point p);

    /**
     *	Return a List of the Points that contain pieces that match the specified piece (Piece.BLACK, Piece.WHITE)
     */
    public List<Point> getPieces(Piece piece);


}