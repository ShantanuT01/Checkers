import mayflower.*;
public class Runner 
{
    public static void main(String[] args) 
    {
    	//GUIClient client = new GUIClient();
    	new Mayflower("Checkers", 700, 700, new CheckersStage(new Checkers(), Piece.BLACK));
    }    
}
