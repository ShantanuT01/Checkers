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
        currentPlayer = Piece.BLACK;
        //zucc me
        //setup the board to its initial state
        board = new Piece[8][8];
        board[0][1] = Piece.WHITE;
        board[0][3] = Piece.WHITE;
        board[0][5] = Piece.WHITE;
        board[0][7] = Piece.WHITE;
        board[1][0] = Piece.WHITE;
        board[1][2] = Piece.WHITE;
        board[1][4] = Piece.WHITE;
        board[1][6] = Piece.WHITE;
        board[2][1] = Piece.WHITE;
        board[2][3] = Piece.WHITE;
        board[2][5] = Piece.WHITE;
        board[2][7] = Piece.WHITE;
        board[5][0] = Piece.BLACK;
        board[5][2] = Piece.BLACK;
        board[5][4] = Piece.BLACK;
        board[5][6] = Piece.BLACK;
        board[6][1] = Piece.BLACK;
        board[6][3] = Piece.BLACK;
        board[6][5] = Piece.BLACK;
        board[6][7] = Piece.BLACK;
        board[7][0] = Piece.BLACK;
        board[7][2] = Piece.BLACK;
        board[7][4] = Piece.BLACK;
        board[7][6] = Piece.BLACK;
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
    public boolean isLegalMove(Move move){
      boolean a = board[move.getPiece().getRow()][move.getPiece().getCol()] != null;
      Point from = move.getPiece();
      Point to = move.getDestination(); 
        if(getPiece(from) == null){return false; }
        if(getPiece(to) != null){return false; }
        if(board[from.getRow()][from.getCol()].equals(Piece.WHITE)){
            if(from.getRow() + 1 == to.getRow()){
                if(checker(from.getCol(),to.getCol())){
                    
                     return true; 
                }
            }
        
        }
         if(board[from.getRow()][from.getCol()].equals(Piece.BLACK)){
            if(from.getRow() - 1 == to.getRow()){
                if((from.getCol() + 1 == to.getCol()) || from.getCol() - 1 == to.getCol()){
                   
                     return true; 
                }
            }
        
        }
        return false; 
        
    }
    /**
     *  Return the piece that is on the board at the specified Point
     *      Piece.BLACK, Piece.WHITE, or Piece.ARROW
     */
    public Piece getPiece(Point p)
    {
        if(p.getRow() < 8 && p.getCol() < 8 && p.getRow() > -1 && p.getCol() > -1)
        return board[p.getRow()][p.getCol()];
        return null;
    }
    
    public void setPiece(Piece piece, Point p)
    {
        board[p.getRow()][p.getCol()] = piece;
    }
    public boolean isGameOver(){
        return false; 
    }
    public Piece getWinner(){
        return null;  
    }
    /**
     *  Move the piece located at Point from to Point to
     */
    public void move(Point from, Point to)
    {
       if(getPiece(from) != null){
           return;
        }
        else if(getPiece(from).equals(currentPlayer)){
            Piece moved = board[from.getRow()][from.getCol()];
            setPiece(moved, to);
            board[from.getRow()][from.getCol()] = null; 
            nextPlayer();
        
        }
           
        
       
       
    }
    
    /**
     *  Return true if there is no piece at Point p
     */
    public boolean isEmpty(Point p)
    {
        return getPiece(p) == null;
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
     *  Return a new instance of an Amazons object with the same state as *this* object
     *  The copy should contain all the same Pieces in all the same places and the same currentPlayer
     */
    public Checkers copy()
    {
        return null;
    }
    private boolean checker(int a, int b){
        return a+1 == b || a-1 == b; 
    }
}
