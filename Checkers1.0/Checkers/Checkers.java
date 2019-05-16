import java.util.*;
import java.util.LinkedList;

public class Checkers implements ICheckers
{
    //INSTANCE VARIABLES
    private Piece[][] board;
    private Piece currentPlayer;
    private int blackPieces = 12;
    private int whitePieces = 12; 
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
        if(!isLegalPoint(from) || !isLegalPoint(to)){
            return false; 
        }
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
        if(getPiece(from)==Piece.BLACKKING || getPiece(from)==Piece.WHITEKING){
            if(from.getRow() -1 == to.getRow() || from.getRow() +1 == to.getRow()){
                if(from.getCol() +1 == to.getCol() || from.getCol() -1 == to.getCol()){
                    return true; 
                }

            }

        }
        return false; 
    }

    public Point getCapturedPiece(Point from, Point to)
    {

        int row = (from.getRow()+to.getRow())/2;
        int col = (from.getCol()+to.getCol())/2;
        return new Point(row,col);

    }
    public boolean isLegalJump(Move move)
    {
        Point from = move.getPiece();
        Point to = move.getDestination();
        Piece cap = getPiece(getCapturedPiece(from,to));
        if(isOfSameType(from,getCapturedPiece(from,to)) || cap == null){
            return false; 
        }
        if(getPiece(from) == null)
        {
            return false;
        }
        if(getPiece(to) != null)
        {
            return false;
        }
        if(board[from.getRow()][from.getCol()].equals(Piece.WHITE)){
            if(from.getRow() + 2 == to.getRow()){
                if(from.getCol() + 2 == to.getCol() || from.getCol() - 2 == to.getCol()){

                    return true; 
                }
            }

        }
        if(board[from.getRow()][from.getCol()].equals(Piece.BLACK)){
            if(from.getRow() - 2 == to.getRow()){
                if((from.getCol() + 2 == to.getCol()) || from.getCol() - 2 == to.getCol()){

                    return true; 
                }
            }

        }
        if(getPiece(from)==Piece.BLACKKING || getPiece(from)==Piece.WHITEKING){
            if(from.getRow() -2 == to.getRow() || from.getRow() +2 == to.getRow()){
                if(from.getCol() +2 == to.getCol() || from.getCol() -2 == to.getCol()){
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
        if(piece == null){
            Piece old = getPiece(p);
            if(old != null){
                Piece side = getSide(old);
                if(side == Piece.WHITE){
                    whitePieces--;
                }
                else if(side == Piece.BLACK){
                    blackPieces--;
                }
            }
        }
        board[p.getRow()][p.getCol()] = piece;
    }

    public boolean isGameOver(){
        if(whitePieces == 0 || blackPieces == 0){

            return true; 
        }
        if((!hasMoves(Piece.WHITE) && getMoves(Piece.WHITE).isEmpty()) || (!hasMoves(Piece.BLACK)&& getMoves(Piece.BLACK).isEmpty())){
            System.out.println("i am here");
            return true; 
        }
        return false; 
    }

    public Piece getWinner(){
        if(!isGameOver()){return null; }
        if(whitePieces == 0){
            return Piece.BLACK;
        } 
        else if(blackPieces == 0){
            return Piece.WHITE; 
        }
        else if(!hasMoves(Piece.WHITE) && !hasMoves(Piece.BLACK)){
            return null;
        }
        else if(hasMoves(Piece.WHITE) && !hasMoves(Piece.BLACK)){
            System.out.println("correct");
            return Piece.WHITE; 
        }
        else if(!hasMoves(Piece.WHITE) && hasMoves(Piece.BLACK)){
            return Piece.BLACK;
        }
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
                if(isOfSameType(board[i][j], piece))
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

    public boolean hasMoves(Piece side){

        List<Point> coords = getPieces(side);
        LinkedList<Move> poss = new LinkedList<>();
        for(int i = 0; i < coords.size(); i++){
            LinkedList<Move> moves = getPossibleMoves(coords.get(i));
            for(int j = 0; j < moves.size(); j++){
                if(isValidMove(moves.get(j))){
                    poss.add(moves.get(j));
                }
            }
        }

        return poss.size() > 0; 

    }

    public LinkedList<Move> getMoves(Piece side){

        List<Point> coords = getPieces(side);
        LinkedList<Move> poss = new LinkedList<>();
        for(int i = 0; i < coords.size(); i++){
            LinkedList<Move> moves = getPossibleMoves(coords.get(i));
            for(int j = 0; j < moves.size(); j++){
                if(isValidMove(moves.get(j))){
                    poss.add(moves.get(j));
                }
            }
        }

        return poss;  

    }

    private boolean checker(int a, int b){
        return a+1 == b || a-1 == b; 
    }

    public LinkedList<Point> getPossibleJumps(Point from){
        LinkedList<Point> points = new LinkedList<Point>();
        if(getPiece(from) == Piece.WHITE){
            Point to1 = new Point(from.getRow()+2, from.getCol()-2);
            Point to2 = new Point(from.getRow()+2, from.getCol()+2);
            points.add(to1);
            points.add(to2);
        }
        else if(getPiece(from) == Piece.BLACK){
            Point to3 = new Point(from.getRow()-2, from.getCol()-2);
            Point to4 = new Point(from.getRow()-2, from.getCol()+2);
            points.add(to3);
            points.add(to4);
        }
        else if(getPiece(from) == Piece.BLACKKING || getPiece(from) == Piece.WHITEKING){
            Point to5 = new Point(from.getRow()+2, from.getCol()-2);
            Point to6 = new Point(from.getRow()+2, from.getCol()+2);
            Point to7 = new Point(from.getRow()-2, from.getCol()-2);
            Point to8 = new Point(from.getRow()-2, from.getCol()+2);
            points.add(to5);
            points.add(to6);
            points.add(to7);
            points.add(to8);
        }
        for(int i = points.size()-1; i >= 0; i--){
            if(!isLegalPoint(points.get(i))){
                points.remove(points.get(i));
            }
        }
        return points; 
    }

    public boolean isLegalPoint(Point x){
        int row = x.getRow();
        int col = x.getCol();
        boolean r = row >=0 && row <=7;
        boolean c = col >= 0 && col <=7;
        return r && c; 
    }

    public void kingMe(Point x){
        if(getPiece(x) == Piece.WHITE){
            if(x.getRow() == 7){
                setPiece(Piece.WHITEKING, x);
            }

        }
        else if(getPiece(x) == Piece.BLACK){
            if(x.getRow() == 0){
                setPiece(Piece.BLACKKING, x);
            }
        }
    }

    public boolean isValidPieceToMove(Point x){
        Piece curr = getCurrentPlayer();
        if(curr == Piece.WHITE){
            return getPiece(x)==Piece.WHITE || getPiece(x) == Piece.WHITEKING;
        }
        else if(curr == Piece.BLACK){
            return getPiece(x)==Piece.BLACK || getPiece(x) == Piece.BLACKKING;
        }
        return false; 
    }

    public boolean isOfSameType(Point x, Point y){
        if(getPiece(x) == Piece.WHITE ||getPiece(x) == Piece.WHITEKING ){
            return getPiece(y) == Piece.WHITE ||  getPiece(y) == Piece.WHITEKING; 
        }
        else if(getPiece(x) == Piece.BLACK ||getPiece(x) == Piece.BLACKKING ){

            return getPiece(y) == Piece.BLACK ||getPiece(y) == Piece.BLACKKING; 
        }
        return false; 
    }

    public boolean isOfSameType(Piece a, Piece b){
        if(a==null || b == null){
            return false; 
        }
        if(a==b){
            return true;

        }
        else{
            String name = a.name();
            String name2 = b.name();
            return name.substring(0,5).equals(name2.substring(0,5));
        }
    }

    public Piece getSide(Piece p){
        if(p == Piece.WHITE || p == Piece.WHITEKING){
            return Piece.WHITE;
        }
        else if(p == Piece.BLACK || p == Piece.BLACKKING){
            return Piece.BLACK; 
        }
        else{
            return null; 
        }
    }

    public LinkedList<Move> getPossibleMoves(Point start){
        LinkedList<Move> moves = new LinkedList<>();
        if(getPiece(start) == Piece.WHITE){
           int row = start.getRow();
           int col = start.getCol();
           moves.add(new Move(start, new Point(row+1, col+1)));
           moves.add(new Move(start, new Point(row+1, col+1)));
        }
        else if(getPiece(start) == Piece.BLACK){
            int wrow = start.getRow()-1;
            int col1 = start.getCol()+1;
            int col2 = start.getCol()-1;
            int crow = start.getRow()-2;
            int ccol = start.getCol()+2;
            int ccol1 = start.getCol()+2; 
            Point a = new Point(wrow,col1);
            Point b = new Point(wrow,col2);
            Point x = new Point(crow, ccol);
            Point y = new Point(crow, ccol1);
            Move cx = new Move(start, x);
            Move cy = new Move(start, y);
            Move am = new Move(start, a);
            Move bm = new Move(start, b);
            moves.add(am);
            moves.add(bm);
            moves.add(cx);
            moves.add(cy);
        }
        else if(getPiece(start) == Piece.WHITEKING || getPiece(start) == Piece.BLACKKING){
            int wrow = start.getRow()+1;
            int col1 = start.getCol()+1;
            int col2 = start.getCol()-1;
            int wrow2 = start.getRow()-1;
            int wrow3 = start.getRow()+1;
            int ccol14 = start.getCol()+1;
            int ccol2 = start.getCol()-1;
            int wrow4 = start.getRow()-1;
            Point a = new Point(wrow,col1);
            Point b = new Point(wrow,col2);
            Point c = new Point(wrow2,col1);
            Point d = new Point(wrow2,col2);
            Move am = new Move(start, a);
            Move bm = new Move(start, b);
            Move cm = new Move(start, c);
            Move dm = new Move(start, d);

            Move em = new Move(start, new Point(start.getRow()+2, start.getCol()+2));
            Move fm = new Move(start, new Point(start.getRow()+2, start.getCol()-2));
            Move gm = new Move(start, new Point(start.getRow()-2, start.getCol()+2));
            Move hm = new Move(start, new Point(start.getRow()-2, start.getCol()-2));

            moves.add(am);
            moves.add(bm);
            moves.add(cm);
            moves.add(dm);
            moves.add(em);
            moves.add(fm);
            moves.add(gm);
            moves.add(hm);
        }

        return moves; 
    }

    public boolean isValidMove(Move move){
        boolean jumpy = isLegalJump(move);
        boolean movey = isLegalMove(move);
        return jumpy || movey; 
    }
}