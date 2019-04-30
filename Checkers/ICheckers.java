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
     *	Add an arrow to Point p
     */
    public void arrow(Point p);

    /**
     *	Return true is there is no piece at Point p
     */
    public boolean isEmpty(Point p);

    /**
     *	Return a List of Points that form a straigh line between Point from and Point to
     *	This line can be horizontal, vertical, or diagonal
     *	If there is no straight line between from and to, return an empty List
     */
    public List<Point> getPath(Point from, Point to);

    /**
     *	Return true is all of the Points on path are empty
     *	Return false if path is the empty List
     */
    public boolean isPathEmpty(List<Point> path);

    /**
     *	Return a List of the Points that contain pieces that match the specified piece (Piece.BLACK, Piece.WHITE)
     */
    public List<Point> getPieces(Piece piece);

    /**
     *	Return a List of Moves that are legal for the point located at Point from
     *	Return the empty List if there is no piece at Point from or if the Piece is an arrow
     *	Do not worry about the currentPlayer.
     */
    public List<Move> getLegalMoves(Point from);

    /**
     *	Return a List of Moves that are legal for pieces that match the specified piece (Piece.BLACK, Piece.WHITE)
     *	Do not worry about the currentPlayer
     *	Return the empty List if piece is an arrow
     */
    public List<Move> getLegalMoves(Piece piece);

    /**
     *	Return true if the specified Move is a legal move
     *	Do not worry about the currentPlayer
     */
    public boolean isLegalMove(Move move);

    /**
     *	Return true if the piece at the specified Point has any legal moves
     *	Return false if there is no piece at the specified Point, or if that piece is an arrow
     *	Do not worry about the currentPlayer
     */
    public boolean hasMoves(Point piecePosition);

    /**
     *	If the game is over, return the piece that is the winner
     *	The winner is the piece that still has legal moves left
     *	If neither piece has any legal moves, return null
     *	If the game is not over, return null
     */
    public Piece getWinner();

    /**
     *	Return true if at least one of the pieces (Piece.BLACK, Piece.WHITE) has 0 legal moves left
     */
    public boolean isGameOver();
}