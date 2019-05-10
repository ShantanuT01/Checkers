import mayflower.*;

public class CheckersStage extends Stage
{
    //INSTANCE VARIABLES
    private Checkers game;
    private CheckersClient client;
    private PieceActor[][] board;
    private Piece myPiece;
    
    private Point from;
    private Point to;
    
    private Text turn;

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
        if(game.isGameOver())
        {
            //display game over!
            turn.setText("Game Over!");
            
            if(null == null)
            {
                turn.setText("Tie Game!");
            }
            else if(null == myPiece)
            {
                turn.setText("You win!");
            }
            else
            {
                turn.setText("You lose!");
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
        
        
        if(game.getPiece(from) == myPiece){
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
            if(x.equals(y)){
                from = null;
                
                board[to.getRow()][to.getCol()].setSelected(false);
                to = null;
                return; 
            }
            if(game.isLegalJump(move))
        {
            System.out.println("To: " + p);

            board[to.getRow()][to.getCol()].setPiece(myPiece);
            board[to.getRow()][to.getCol()].setSelected(false);
            game.setPiece(board[to.getRow()][to.getCol()].getPiece(), to);

            board[from.getRow()][from.getCol()].setPiece(null);
            board[from.getRow()][from.getCol()].setSelected(false);
            game.setPiece(null, from);
            game.setPiece(null, game.getCapturedPiece(from,to));
            Point c = game.getCapturedPiece(from,to);
            board[c.getRow()][c.getCol()].setPiece(null);
            game.nextPlayer();
            from = null;
            to = null;
            if(myPiece.equals(Piece.BLACK)){
                myPiece = Piece.WHITE;
            }
            else{
                 myPiece = Piece.BLACK; 
            }
             return; 
        }
        if(game.isLegalMove(move)){
            System.out.println("To: " + p);

            board[to.getRow()][to.getCol()].setPiece(myPiece);
            board[to.getRow()][to.getCol()].setSelected(false);
            game.setPiece(board[to.getRow()][to.getCol()].getPiece(), to);

            board[from.getRow()][from.getCol()].setPiece(null);
            board[from.getRow()][from.getCol()].setSelected(false);
            game.setPiece(null, from);
            game.nextPlayer();
            from = null;
            to = null;
            if(myPiece.equals(Piece.BLACK)){
                myPiece = Piece.WHITE;
            }
            else{
                 myPiece = Piece.BLACK; 
            }
             return; 
        }
        else{
             board[from.getRow()][from.getCol()].setSelected(false);
             from = null;
             to = null; 
             //board[to.getRow()][to.getCol()].setSelected(false);
        }
        
        
    }
    
    
    
    
    
}