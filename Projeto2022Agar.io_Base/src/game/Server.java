package game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import environment.Coordinate;
import gui.GameGuiMain;

public class Server {
	private ServerSocket server;
	private GameGuiMain game;
	public static final int PORT = 8080;
	private int newId;
	ArrayList<InfoToSend> info;
	
	public Server() {
		this.game = new GameGuiMain();
		
		try {
			server = new ServerSocket(PORT);
		} catch (IOException e) {
			System.err.println("cannot init server... aborting!");
			System.exit(1);
		}
		this.newId =1000;
	}

	public void runServer() throws InterruptedException {
		game.init();
		while (true) {
			try {
				
				waitForConnection();
			
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}

	public void waitForConnection() throws IOException {
		System.out.println("waiting for connection...");
		Socket connection= server.accept();
		PhoneyHumanPlayer player = new PhoneyHumanPlayer(newId++, game.getGame(), (byte) 5);
		ConnectionHandler handler = new ConnectionHandler(connection,player);
		
		handler.start();
		
	}
	
	private class ConnectionHandler extends Thread{
		private Socket connection;
		private ObjectOutputStream output;
		private BufferedReader input;
		private PhoneyHumanPlayer player;
		
		public ConnectionHandler(Socket connection,PhoneyHumanPlayer player) {
			this.connection = connection;
			this.player = player;
		}
		
		@Override 
		public void run() {
			try {
				getStreams();
				while(!game.getGame().isOver()) {
					
					processConnection();
					Thread.sleep(game.getGame().REFRESH_INTERVAL);
				}
				closeConnection();
				

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void getStreams() throws IOException {
			output = new ObjectOutputStream(connection.getOutputStream()); //Saída de Objetos
			input = new BufferedReader(new InputStreamReader(connection.getInputStream())); //Entrada de Texto
		}

		public void processConnection() throws IOException, InterruptedException {
			
			info = game.getGame().GetBoardInfo();
			output.writeObject(info);
		
			
			if(input.ready()) {
			
			String dir = input.readLine();
			
			if (player.isInPlay()) {
				System.out.println(dir);
			
				player.moveHuman(game.getGame().getDirection(dir));
			}
			}
			
			if(game.getGame().isOver()) {
				this.interrupt();
				player.interrupt();
			}
		}

		public void closeConnection() {
			try {
				input.close();
				output.close();
				connection.close();
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		Server app = new Server();
		app.runServer();

	}

}
