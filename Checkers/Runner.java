public class Runner 
{
    public static void main(String[] args) 
    {
    	CheckersClient client = new GUIClient();    	
    	client.connect("localhost", 1241);
    }    
}
