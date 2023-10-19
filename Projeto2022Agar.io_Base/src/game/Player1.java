package game;

public class Player1 {
	
	public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
		Client app = new Client("LocalHost",8080);
		app.runCliente();
		

	}
}
