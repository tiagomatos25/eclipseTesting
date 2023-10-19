package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JFrame;

import environment.Cell;
import environment.Direction;
import gui.GameGuiMain;
import gui.NewClientBoard;

public class Client implements KeyListener {

	private Socket socket;
	private ObjectInputStream input;
	private PrintWriter output;
	private String hostName;
	private int PORT;
	private NewClientBoard board;
	private GameGuiMain clientGui;
	private boolean opening;
	private ArrayList<InfoToSend> inf;

	public Client(String host, int PORT) throws InterruptedException {
		hostName = host;
		this.PORT = PORT;
		opening = true;
	}

	public void runCliente() throws ClassNotFoundException, InterruptedException {
		connectToServer();
		try {

			getStreams();
			processConnection();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConnection();
		}
	}

	public void connectToServer() {
		try {
			socket = new Socket(InetAddress.getByName(hostName), PORT);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Erro connecting to server.... aborting!");
			System.exit(1);
		}
	}

	public void getStreams() throws IOException {
		// System.out.println("getStreams");
		input = new ObjectInputStream(socket.getInputStream()); // Entrada de Objetos
		output = new PrintWriter(socket.getOutputStream(), true); //Saída de Texto

	}

	public void processConnection() throws IOException, InterruptedException, ClassNotFoundException {
		System.out.println("processConnection");
		while (true) {

			inf = (ArrayList<InfoToSend>) input.readObject();
			
			if (opening) {
				System.out.println("Opening the client board");
				board = new NewClientBoard(inf);
				clientGui = new GameGuiMain(board);
				clientGui.newInit();
				opening = false;
			} else {
			
				board.update(inf);
				board.repaint();
				clientGui.updateNewBoard(board);
		
			}
			
			if (board.getLastPressedDirection() != null) {
				Direction dir = board.getLastPressedDirection();
				System.out.println("Client :" + dir.dirToString());
				output.println(dir.dirToString());
				board.clearLastPressedDirection();
			}
		}

	}

	public void closeConnection() {
		try {
			input.close();
			output.close();
			// socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
