package game;

import environment.Cell;
import environment.Coordinate;
import environment.Direction;

/**
 * Represents a player.
 * 
 * @author luismota
 *
 */
public abstract class Player extends Thread {

	protected Game game;

	private int id;
	private Cell currentPos;
	private byte currentStrength;
	protected byte originalStrength;

	// TODO: get player position from data in game
	public Cell getCurrentCell() {
		return null;
	}

	public Player(int id, Game game, byte strength) {
		super();
		this.id = id;
		this.game = game;
		currentStrength = strength;
		originalStrength = strength;
		this.start();
	}

	public abstract boolean isHumanPlayer();

	@Override
	public String toString() {
		return "Player [id=" + id + ", currentStrength=" + currentStrength + ", getCurrentCell()=" + getCurrentCell()
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public byte getCurrentStrength() {
		return currentStrength;
	}

	public void addStrength(byte b) {
		currentStrength += b;
	}

	public void setStrength(byte b) {
		currentStrength = b;
	}

	public int getIdentification() {
		return id;
	}

	@Override
	public void run() {
		int StrengthOriginal = (int) (originalStrength);
		int WaitingTime = StrengthOriginal * 400;
		try {
			this.sleep(WaitingTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void move(Direction dir) throws InterruptedException {

		Cell oldPos = game.getCell(this); 
		 if(oldPos !=null) {
			 if (oldPos.getPosition().translate(dir.getVector()).x > game.DIMX - 1
				|| oldPos.getPosition().translate(dir.getVector()).x < 0
				|| oldPos.getPosition().translate(dir.getVector()).y > game.DIMY - 1
				|| oldPos.getPosition().translate(dir.getVector()).y < 0) {
			game.notifyChange();
			Thread.sleep(game.REFRESH_INTERVAL * originalStrength);
		} else {
			// game.getCell(oldPos.getPosition()).setPlayer(null);
			Cell newPos = game.getCell(oldPos.getPosition().translate(dir.getVector()));
			newPos.movePlayer(this, oldPos.getPosition());
			game.notifyChange(); // noticar a alterÃ§Ã£o
			Thread.sleep(game.REFRESH_INTERVAL * originalStrength); // Ciclo de jogo
		}
		 }
	}
	
	public void moveHuman(Direction dir) throws InterruptedException {

		Cell oldPos = game.getCell(this); 
		 if(!oldPos.equals(null))
		if (oldPos.getPosition().translate(dir.getVector()).x > game.DIMX - 1
				|| oldPos.getPosition().translate(dir.getVector()).x < 0
				|| oldPos.getPosition().translate(dir.getVector()).y > game.DIMY - 1
				|| oldPos.getPosition().translate(dir.getVector()).y < 0) {
			game.notifyChange();
		} else {
			// game.getCell(oldPos.getPosition()).setPlayer(null);
			Cell newPos = game.getCell(oldPos.getPosition().translate(dir.getVector()));
			newPos.moveHumanPlayer(this, oldPos.getPosition());
			game.notifyChange(); // noticar a alterÃ§Ã£o
			Thread.sleep(game.REFRESH_INTERVAL * originalStrength); // Ciclo de jogo
		}

	}
	

	public synchronized void placeIn(Cell cell) {
		currentPos = cell;
	}

	public synchronized Cell getCurrentPosition() {
		return currentPos;
	}
	
	public boolean isInPlay() {
		return (this.getCurrentStrength()<(byte) 10 && this.getCurrentStrength()>(byte)0);
	}
	
}
