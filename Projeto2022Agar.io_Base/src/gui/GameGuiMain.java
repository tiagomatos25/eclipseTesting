package gui;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

import game.Bot;
import game.Client;
import game.Game;
import game.PhoneyHumanPlayer;
import game.Player;
import game.Server;

import javax.swing.JFrame;

public class GameGuiMain implements Observer {
	private JFrame frame = new JFrame("pcd.io");
	private BoardJComponent boardGui;
	private Game game;
	private int newId = 1;
	private NewClientBoard newBoardGui;
	
	public GameGuiMain() {
		super();
		game = new Game();
		game.addObserver(this);

		buildGui();

	}
	public GameGuiMain(NewClientBoard newBGui) {
		super();
		this.newBoardGui = newBGui;
		
		buildClientGui();
	}
	
	
	public Game getGame() {
		return game;
	}
	private void buildGui() {
		boardGui = new BoardJComponent(game);
		frame.add(boardGui);
		frame.setSize(800,800);
		frame.setLocation(0, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	private void buildClientGui() {
		frame.add(newBoardGui);
		frame.setSize(500, 500);
		frame.setLocation(150, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public byte generatePower(){
		return (byte)((int)(Math.random()*2+1));
	}

	public void init()  throws InterruptedException{
		frame.setVisible(true);
		
		// Demo players, should be deleted
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i =0;i!=30;i++) {
			Player p = new Bot(i+1, game, generatePower());
			game.addPlayerToGame(p);
			//p.start();
			newId++;
		}
		
		
	}
	public int getNumPlayers(){
		return newId;
	}
	public void newInit() {
		frame.setVisible(true);
	}
	@Override
	public void update(Observable o, Object arg) {
		boardGui.repaint();
	}

	public void updateNewBoard(NewClientBoard newBGui) {
		newBoardGui = newBGui;
		newBoardGui.repaint();
		
	}
	public static void main(String[] args) throws InterruptedException{
	
	}

}
