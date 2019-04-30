import java.util.*;
import java.util.LinkedList;

public class Checkers implements ICheckers
{
    //INSTANCE VARIABLES
    private Piece[][] board;
    private Piece currentPlayer;
    
    //CONSTRUCTORS
    public Checkers()
    {
        //initialize instance variables
        currentPlayer = Piece.WHITE;
        //zucc me
        //setup the board to its initial state
        board = new Piece[10][10];
        board[3][9] = Piece.BLACK;
        board[3][0] = Piece.BLACK;
        board[0][3] = Piece.BLACK;
        board[0][6] = Piece.BLACK;
        board[6][0] = Piece.WHITE;
        board[9][3] = Piece.WHITE;
        board[9][6] = Piece.WHITE;
        board[6][9] = Piece.WHITE;
    }
    
    //METHODS       
    /**
     *  Return the current turn holder's Piece, Piece.BLACK or Piece.WHITE
     */
    public Piece getCurrentPlayer()
    {
        return currentPlayer;
    }
    
    /**
     *  Setter for currentPlayer instance variable
     */
    public void setCurrentPlayer(Piece piece)
    {
        currentPlayer = piece;
    }
    
    /**
     *  Change the currentPlayer
     *      Piece.BLACK -> Piece.WHITE
     *      Piece.WHITE -> Piece.BLACK
     */
    public void nextPlayer()
    {
        if(getCurrentPlayer().equals(Piece.WHITE))
        {
            setCurrentPlayer(Piece.BLACK);
        }
        else
        {
            setCurrentPlayer(Piece.WHITE);
        }
    }
    
    /**
     *  Return the piece that is on the board at the specified Point
     *      Piece.BLACK, Piece.WHITE, or Piece.ARROW
     */
    public Piece getPiece(Point p)
    {
        if(p.getRow() < 10 && p.getCol() < 10 && p.getRow() > -1 && p.getCol() > -1)
        return board[p.getRow()][p.getCol()];
        return null;
    }
    
    public void setPiece(Piece piece, Point p)
    {
        board[p.getRow()][p.getCol()] = piece;
    }
    
    /**
     *  Move the piece located at Point from to Point to
     */
    public void move(Point from, Point to)
    {
        setPiece(getPiece(from), to);
        board[from.getRow()][from.getCol()] = null;
    }
    
    /**
     *  Add an arrow to Point p
     */
    public void arrow(Point p)
    {
        setPiece(Piece.ARROW, p);
    }
    
    /**
     *  Return true if there is no piece at Point p
     */
    public boolean isEmpty(Point p)
    {
        return getPiece(p) == null;
    }
    
    /**
     *  Return a List of Points that form a straigh line between Point from and Point to
     *  This line can be horizontal, vertical, or diagonal
     *  If there is no straight line between from and to, return an empty List
     */
    public List<Point> getPath(Point from, Point to)
    {
        List<Point> list = new ArrayList<Point>();
        if(from.getRow() == to.getRow()) //same row
        {
            if(from.getCol() < to.getCol()) //left to right
            {
                for(int i = from.getCol(); i <= to.getCol(); i++)
                {
                    list.add(new Point(from.getRow(), i));
                }
            }
            else //right to left
            {
                for(int i = from.getCol(); i >= to.getCol(); i--)
                {
                    list.add(new Point(from.getRow(), i));
                }
            }
        }
        else if(from.getCol() == to.getCol()) //same col
        {
            if(from.getRow() < to.getRow()) //bottom to top
            {
                for(int i = from.getRow(); i <= to.getRow(); i++)
                {
                    list.add(new Point(i, from.getCol()));
                }
            }
            else //top to bottom
            {
                for(int i = from.getRow(); i >= to.getRow(); i--)
                {
                    list.add(new Point(i, from.getCol()));
                }
            }
        }
        else if(((from.getRow() - to.getRow()) / (from.getCol() - to.getCol()) == 1) || ((from.getRow() - to.getRow()) / (from.getCol() - to.getCol()) == -1)) //diagonal
        {
            if(from.getRow() < to.getRow()) //down
            {
                if(from.getCol() < to.getCol()) //right
                {
                    for(int i = from.getRow(); i <= to.getRow(); i++)
                    {
                        list.add(new Point(i, from.getCol() + (i - from.getRow())));
                    }
                }
                else //left
                {
                    for(int i = from.getRow(); i <= to.getRow(); i++)
                    {
                        list.add(new Point(i, from.getCol() - (i - from.getRow())));
                    }
                }
            }
            else //up
            {   
                if(from.getCol() < to.getCol()) //right
                {
                    for(int i = from.getRow(); i >= to.getRow(); i--)
                    {
                        list.add(new Point(i, from.getCol() - (i - from.getRow())));
                    }
                }
                else //left
                {
                    for(int i = from.getRow(); i >= to.getRow(); i--)
                    {
                        list.add(new Point(i, from.getCol() + (i - from.getRow())));
                    }
                }
            }
        }
        return list;
    }
    /**
     *  Return true if all of the Points on path are empty EXCLUDING the first Point
     *  Return false if the path is size 1
     *  Return false if path is the empty List
     */
    public boolean isPathEmpty(List<Point> path)
    {
        /*if(path.size() > 0)
        {
            path.remove(0);
        }   */ 
        if(path.size() == 1)
        {
            return false;
        }
        for(int i = 1; i < path.size(); i++)
        {
            if(!isEmpty(path.get(i))) //zucc was here
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     *  Return a List of the Points that contain pieces that match the specified piece (Piece.BLACK, Piece.WHITE)
     */
    public List<Point> getPieces(Piece piece)
    {
        List<Point> list = new ArrayList<Point>();
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[0].length; j++)
            {
                if(piece.equals(board[i][j]))
                {
                    list.add(new Point(i, j));
                }
            }
        }
        return list;
    }
    
    /**
     *  Return a List of Moves that are legal for the point located at Point from
     *  Return the empty List if there is no piece at Point from or if the Piece is an arrow
     *  Do not worry about the currentPlayer.
     */
    public List<Move> getLegalMoves(Point from)
    {
        List<Move> list = new ArrayList<Move>();
        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                for(int k = 0; k < 10; k++)
                {
                    for(int l = 0; l < 10; l++)
                    {
                        Move move = new Move(from, new Point(i, j), new Point(k, l));
                        if(isLegalMove(move))
                        {
                            list.add(move);
                        }
                    }    
                }
            }
        }
        return list;
    }
    
    /**
     *  Return a List of Moves that are legal for pieces that match the specified piece (Piece.BLACK, Piece.WHITE)
     *  Do not worry about the currentPlayer
     *  Return the empty List if piece is an arrow
     */
    public List<Move> getLegalMoves(Piece piece)
    {
        List<Move> list = new ArrayList<Move>();
        if(piece.equals(Piece.ARROW))
        {
            return list;
        }
        else
        {
            for(Point elem : getPieces(piece))
            {
                for(Move x : getLegalMoves(elem))
                {
                    list.add(x);
                }
            }
        }
        return list;
    }
    
    /**
     *  Return true if the specified Move is a legal move
     *  Do not worry about the currentPlayer
     */
    public boolean isLegalMove(Move move)
    {
        Point p = move.getPiece();
        Point d = move.getDestination();
        Point a = move.getArrow();
        boolean test = false;
        if(getPath(d, a).indexOf(p) != -1)
        {
            test = true;
        }
        if(!isEmpty(move.getPiece()))
        {
            if(!(this.getPiece(move.getPiece()).equals(Piece.ARROW)))
            {
                if(isPathEmpty(getPath(move.getPiece(), move.getDestination())))
                {
                    if(isPathEmpty(getPath(move.getDestination(), move.getArrow())) || test)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /**
     *  Return true if the piece at the specified Point has any legal moves
     *  Return false if there is no piece at the specified Point, or if that piece is an arrow
     *  Do not worry about the currentPlayer
     */
    public boolean hasMoves(Point piecePosition)
    {
        if(piecePosition.equals(Piece.ARROW))
        {
            return false;
        }
        else if(isEmpty(piecePosition))
        {
            return false;
        }
        else if(getLegalMoves(piecePosition).size() >= 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     *  If the game is over, return the piece that is the winner
     *  The winner is the piece that still has legal moves left
     *  If neither piece has any legal moves, return null
     *  If the game is not over, return null
     */
    public Piece getWinner()
    {
        if(isGameOver())
        {
            if(getLegalMoves(Piece.WHITE).size() >= 1)
            {
                return Piece.WHITE;
            }
            else
            {
                return Piece.BLACK;
            }
        }
        else
        {
            return null;
        }
    }
    
    /**
     *  Return true if at least one of the pieces (Piece.BLACK, Piece.WHITE) has 0 legal moves left
     */
    
    public boolean isGameOver()
    {
        List<Move> movesW = getLegalMoves(Piece.WHITE);
        if(movesW.isEmpty())
        {
            return true;
        }
        List<Move> movesB = getLegalMoves(Piece.BLACK);
        if(movesB.isEmpty())
        {
            return true;
        }
        return false;
    }
    /**
     *  Return a new instance of an Amazons object with the same state as *this* object
     *  The copy should contain all the same Pieces in all the same places and the same currentPlayer
     */
    public Checkers copy()
    {
        return null;
    }
}
