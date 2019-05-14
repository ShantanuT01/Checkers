import mayflower.*;
import mayflower.net.*;
import java.util.Scanner;

public class CheckersClient extends Client
{
	//INSTANCE VARIABLES
	private Checkers game;
	private Piece myPiece;
	
	//CONSTRUCTORS
	public CheckersClient()
	{
		//initialize instance variables
		game = null;
		myPiece = null;
	}
	
	//METHODS
	/**
	 *	Getter for game instance variable
	 */
	public Checkers getGame()
	{
		return game;
	}
	
	/**
	 *	Getter for myPiece instance variable
	 */
	public Piece getMyPiece()
	{
		return myPiece;
	}
	/**
	 *	Messages:
	 *		youare [piece]
	 *		move [fromRow] [fromCol] [toRow] [toCol] [arrowRow] [arrowCol]
	 *		gameover [piece]
	 *		winner [message]
	 *		error [message...]
	 */
	public void process(String message)
	{
		System.out.println("Message from server: " + message);
		
		String[] parts = message.split(" ");
		if("youare".equals(parts[0]))
		{
			game = new Checkers();
			myPiece = "BLACK".equals(parts[1]) ? Piece.BLACK : Piece.WHITE;
			
			onYouAre(myPiece);			
		}
		else if("move".equals(parts[0]))
		{
			
			int fromRow = Integer.parseInt(parts[1]);
			int fromCol = Integer.parseInt(parts[2]);
			
			int toRow = Integer.parseInt(parts[3]);
			int toCol = Integer.parseInt(parts[4]);
			
			int arrowRow = Integer.parseInt(parts[5]);
			int arrowCol = Integer.parseInt(parts[6]);
			
			Point from = new Point(fromRow, fromCol);
			Point to = new Point(toRow, toCol);
			
			Piece movingPiece = game.getPiece(new Point(fromRow, fromCol));
			game.move(new Point(fromRow, fromCol), new Point(toRow, toCol));
			game.nextPlayer();
	
			onMove(movingPiece, fromRow, fromCol, toRow, toCol, arrowRow, arrowCol);
			
		}
		else if("gameover".equals(parts[0]))
		{
			Piece piece = "BLACK".equals(parts[1]) ? Piece.BLACK : Piece.WHITE;
			onGameOver(piece);
		}
		else if("winner".equals(parts[0]))
		{
			//Congratulations, you win becuase you opponent quit!
			onWinner(parts[1]);
		}
		else if("error".equals(parts[0]))
		{
			onError(parts[1]);
		}
	}
	
	public void onDisconnect(String message)
	{
		System.out.println("Disconnected from server.");
	}
	
	public void onConnect()
	{
		System.out.println("Connected!");
	}
	
	public void onYouAre(Piece piece)
	{
		System.out.println("You are: " + piece);
	}
	
	public void onMove(Piece piece, int fromRow, int fromCol, int toRow, int toCol, int arrowRow, int arrowCol)
	{
		System.out.println("Moving...");	
	}
	
	public void onGameOver(Piece winner)
	{
		System.out.println("Game over. " + winner + " wins!");	
	}
	
	public void onWinner(String message)
	{
		System.out.println("You win! " + message);	
	}
	
	public void onError(String message)
	{
		System.out.println("Error: " + message);
	}
}
