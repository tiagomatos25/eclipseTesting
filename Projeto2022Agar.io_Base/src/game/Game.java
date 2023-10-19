package game;

import java.util.ArrayList;
import java.util.Observable;
import environment.Cell;
import environment.Coordinate;
import environment.Direction;

public class Game extends Observable {

	public static final int DIMY = 15;
	public static final int DIMX = 15;
	private static final int NUM_PLAYERS = 90;
	private static final int NUM_FINISHED_PLAYERS_TO_END_GAME = 3;

	public static final long REFRESH_INTERVAL = 400;
	public static final double MAX_INITIAL_STRENGTH = 3;
	public static final long MAX_WAITING_TIME_FOR_MOVE = 2000;
	public static final long INITIAL_WAITING_TIME = 10000;
	private ArrayList<InfoToSend> InfoToSends;
	protected Cell[][] board;
	private boolean isOver;
	private CDL cdl;

	public Game() {
		board = new Cell[Game.DIMX][Game.DIMY];
		cdl = new CDL(NUM_FINISHED_PLAYERS_TO_END_GAME);
		InfoToSends = new ArrayList<>();
		isOver = false;
		new EndGameControler(cdl, this);

		for (int x = 0; x < Game.DIMX; x++)
			for (int y = 0; y < Game.DIMY; y++)
				board[x][y] = new Cell(new Coordinate(x, y), this);

	}

	/**
	 * @param player
	 */
	public synchronized void addPlayerToGame(Player player) throws InterruptedException {
		Cell initialPos = getRandomCell();
		if(initialPos.isOcupied()) {
			synchronized (player) {
			new PlaceInBot(player,initialPos,this);
			player.wait();
			}
			System.out.println("Sou o Jogador: " + player.getIdentification() + " A posicao: " + initialPos.getPosition().toString() + " esta ocupada pelo jogador:" + initialPos.getPlayer().getIdentification());
		} else {
		initialPos.setPlayer(player);
		notifyChange();	
		
		}
	}

	public Cell getCell(Coordinate at) {
		return board[at.x][at.y];
	}

	public synchronized Cell getCell(Player player) {
		for (int x = 0; x < Game.DIMX; x++) {
			for (int y = 0; y < Game.DIMY; y++) {
				if (this.board[x][y].getPlayer() != null)
					if (this.board[x][y].isOcupied()) {
						if (this.board[x][y].getPlayer().equals(player)) {
							return board[x][y];
						}
					}
			}
		}
		return null;
	}

	/**
	 * Updates GUI. Should be called anytime the game state changes
	 */
	public void notifyChange() {
		setChanged();
		notifyObservers();
	}

	public Direction getDirection(String s) {
		switch (s) {
		case "LEFT":
			return Direction.LEFT;
		case "UP":
			return Direction.UP;
		case "RIGHT":
			return Direction.RIGHT;
		case "DOWN":
			return Direction.DOWN;
		default:
			return null;
		}
	}

	public Cell getRandomCell() {
		Cell newCell = getCell(new Coordinate((int) (Math.random() * DIMX), (int) (Math.random() * DIMY)));
		return newCell;
	}

	public Cell[][] getBoard() {
		return board;
	}

	public boolean isOver() {
		return isOver;
	}

	public void CDLCountDown() {
		cdl.countDown();
	}

	public void gameOver() {
		isOver = true;
		System.out.println("The Game is Over");
		for (int x = 0; x < Game.DIMX; x++) {
			for (int y = 0; y < Game.DIMY; y++) {
				if (board[x][y].isOcupied()) {
					if (board[x][y].PlayerIsAlive())
						board[x][y].getPlayer().interrupt();
				}
			}
		}
	}

	public ArrayList<InfoToSend> GetBoardInfo() {
		InfoToSends = new ArrayList<>();
		for (int x = 0; x < Game.DIMX; x++) {
			for (int y = 0; y < Game.DIMY; y++) {
				if (board[x][y].isOcupied()) {
					InfoToSends.add(new InfoToSend(board[x][y].getPosition(), board[x][y].getPlayer()));
				}else {
					InfoToSends.add(null);
				}
			}
		}
		return InfoToSends;
	}

}
