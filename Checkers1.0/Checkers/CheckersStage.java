import mayflower.*;
import java.util.*;
import java.io.*;
public class CheckersStage extends Stage
{
    //INSTANCE VARIABLES
    private Checkers game;
    private CheckersClient client;
    private PieceActor[][] board;
    private Piece myPiece;
    private boolean nJump = false;
    private Point from;
    private Point to;
    private Point lastPoint;
    private Text turn;
    private int moveNumber = 1;
    private boolean gameover = false;
    private Move megaMove;
    //CONSTRUCTORS
    public CheckersStage(CheckersClient client, Checkers game, Piece piece)
    {
        //initialize instance variables
        this.game = game;
        this.client = client;
        this.myPiece = piece;

        int w = 75;
        int h = 75;

        board = new PieceActor[8][8];
        for(int r = 0; r < board.length; r++)
        {
            for(int c = 0; c < board[0].length; c++)
            {
                PieceActor actor = new PieceActor(r, c);
                board[r][c] = actor;
                actor.setPiece(game.getPiece(new Point(r, c)));
                addActor(actor, c * w + 37, r * h + 37);
            }
        }

        setBackground("img/background.png");

        //Display your color
        turn = new Text("");
        addActor(turn, 5, 610);

        //DoSomethingButton button = new DoSomethingButton(client);
        //addActor(button, 400, 600);
    }

    public CheckersStage(Checkers game, Piece piece)
    {
        //initialize instance variables
        this.game = game;

        this.myPiece = piece;

        int w = 75;
        int h = 75;

        board = new PieceActor[8][8];
        for(int r = 0; r < board.length; r++)
        {
            for(int c = 0; c < board[0].length; c++)
            {
                PieceActor actor = new PieceActor(r, c);
                board[r][c] = actor;
                actor.setPiece(game.getPiece(new Point(r, c)));
                addActor(actor, c * w + 37, r * h + 37);
            }
        }

        setBackground("img/background.png");

        //Display your color
        turn = new Text("");
        addActor(turn, 5, 610);

        //DoSomethingButton button = new DoSomethingButton(client);
        //addActor(button, 400, 600);
    }
    //METHODS
    public void update()
    {
      //modifies text file with all moves
        if(game.getMoves(game.getCurrentPlayer()).size() == 0)
        {
            //display game over!
            turn.setText("Game Over!");
            Piece winner = game.getWinner();
            if(gameover == false){
                try{
            PrintWriter writer = new PrintWriter("moves.txt", "UTF-8");
            Queue<Move> white = game.getWhiteMoves();
            Queue<Move> black = game.getBlackMoves();
            //System.out.println(white.remove());
            while(white.peek() != null && black.peek() != null){
            writer.println("BLACK plays: " + black.remove()+ "| WHITE plays: " + white.remove());

        }
        gameover = true;
            writer.close();
        }
            catch(Exception e){
                e.printStackTrace();
            }
        }
            if(winner == null)
            {
                turn.setText("Tie Game!");
            }
            else if(winner == myPiece)
            {
                turn.setText(winner.name() + " " + "wins!");
            }
            else
            {
                turn.setText(winner.name() + " " + "wins!");
            }
        }
        else
        {
            if(game.getCurrentPlayer() == myPiece)
            {
                String color = myPiece == Piece.BLACK ? "black" : "white";
                turn.setText("It is your turn! (You are " + color + ")");
            }
            else
            {
                String color = myPiece == Piece.WHITE ? "black" : "white";
                turn.setText("It is your turn! (You are " + color + ")");
            }
        }

    }

    /**
     *  Change the piece located a specified Point
     */
    public void updatePiece(Point p, Piece piece)
    {
        PieceActor actor = board[p.getRow()][p.getCol()];
        actor.setPiece(piece);
    }

    public void reset()
    {
        for(int r = 0; r < board.length; r++)
        {
            for(int c = 0; c < board[0].length; c++)
            {
                board[r][c].setPiece(game.getPiece(new Point(r, c)));
                board[r][c].setSelected(false);
            }
        }

        from = null;
        to = null;
    }

    public void click(int row, int col)
    {
        Point p = new Point(row, col);
        if(null == from)
            clickFrom(p);
        else if(null == to)
            clickTo(p);
    }

    private void clickFrom(Point p)
    {
        from = p;
        if(nJump){
            from = lastPoint;
            board[from.getRow()][from.getCol()].setSelected(true);
        }
        else if(game.isValidPieceToMove(p)){
            System.out.println("From: " + p);
            board[p.getRow()][p.getCol()].setSelected(true);
        }

        else{
            from = null;
        }
    }

    private void clickTo(Point p)
    {
        to = p;
        if(from == null || to == null){
            return;
        }
        Point x = from;
        Point y = to;
        Move move = new Move(x,y);
        megaMove = new Move(move.getPiece());
        if(x.equals(y)){
            from = null;
            megaMove = null;
            board[to.getRow()][to.getCol()].setSelected(false);
            to = null;
            return;
        }
        if(game.isLegalJump(move))
        {
            System.out.println("To: " + p);
            megaMove.setFrom(move.getPiece());
            megaMove.setTo(move.getDestination());
            board[to.getRow()][to.getCol()].setPiece(board[from.getRow()][from.getCol()].getPiece());
            board[to.getRow()][to.getCol()].setSelected(false);
            game.setPiece(board[to.getRow()][to.getCol()].getPiece(), to);
            Point lastfrom = from;
            board[from.getRow()][from.getCol()].setPiece(null);
            board[from.getRow()][from.getCol()].setSelected(false);
            game.setPiece(null, from);
            game.setPiece(null, game.getCapturedPiece(from,to));
            Point c = game.getCapturedPiece(from,to);
            board[c.getRow()][c.getCol()].setPiece(null);
            lastPoint = to;
            from = to;

            game.kingMe(from);
            board[from.getRow()][from.getCol()].setPiece(game.getPiece(from));
            LinkedList<Point> poss = game.getPossibleJumps(from);
            boolean canJumpAgain = false;

            if(poss.isEmpty()){
                game.recordMove(0, move, game.getCurrentPlayer());
                game.nextPlayer();
                myPiece=game.getCurrentPlayer();
                from = null;
                to= null;
                nJump = false;


                return;
            }
            inner:
            for(int i = 0; i < poss.size(); i++){
                if(game.isLegalJump(new Move(from, poss.get(i)))){
                    clickFrom(from);
                    canJumpAgain = true;
                    nJump = true;
                    megaMove = new Move(lastfrom);
                    break inner;
                }
            }

                if(megaMove == null){
               megaMove = new Move(lastfrom);
             }
             else{
                megaMove.setTo(to);
                }

            if(canJumpAgain == false){
                Move copy = megaMove;
                game.recordMove(0, copy, game.getCurrentPlayer());
                game.nextPlayer();
                myPiece=game.getCurrentPlayer();
                from = null;
                to = null;
                megaMove = null;
                nJump = false;
            }

        }

        if(game.isLegalMove(move) && !game.hasJumps()){
            System.out.println("To: " + p);

            board[to.getRow()][to.getCol()].setPiece(game.getPiece(from));
            board[to.getRow()][to.getCol()].setSelected(false);
            game.setPiece(board[to.getRow()][to.getCol()].getPiece(), to);
            game.recordMove(++moveNumber, move, game.getCurrentPlayer());
            board[from.getRow()][from.getCol()].setPiece(null);
            board[from.getRow()][from.getCol()].setSelected(false);
            game.setPiece(null, from);
            game.nextPlayer();
            game.kingMe(to);
            board[to.getRow()][to.getCol()].setPiece(game.getPiece(to));
            from = null;
            to = null;
            myPiece = game.getCurrentPlayer();
            return;
        }
        else{

            if(from != null){
                board[from.getRow()][from.getCol()].setSelected(false);}
                megaMove = null;
            from = null;
            to = null;
            //board[to.getRow()][to.getCol()].setSelected(false);
        }

    }
}
