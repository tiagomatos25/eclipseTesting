package environment;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import game.AwakeBot;
//import game.AwakeBot;
import game.Game;
import game.Player;

public class Cell {
	private Coordinate position;
	private Game game;
	private Player player = null;
	public Lock lock = new ReentrantLock();

	public Cell(Coordinate position, Game g) {
		super();
		this.position = position;
		this.game = g;
	}

	public void setPosition(Coordinate cor) {
		position = cor;
	}

	public Coordinate getPosition() {
		return position;
	}

	public synchronized boolean isOcupied() {
		return player != null;
	}

	public synchronized Player getPlayer() {
		return player;
	}

	// Should not be used like this in the initial state: cell might be occupied,
	// must coordinate this operation
	
	public void setPlayer(Player player) {
		lock.lock();
		
		this.player = player;
		
		lock.unlock();

	}

	
	  public void movePlayer(Player player,Coordinate c) throws InterruptedException { 
		  // meter na nova celula o jogador com o movimento 
		  lock.lock();
		 
	  if (!isOcupied()) { 
		 game.getCell(c).getLock().lock();
		 game.getCell(c).setPlayer(null); //NOVA CELL
		 setPlayer(player); //ESTA CELL
		 game.getCell(c).getLock().unlock();
	  } else if (this.player.getCurrentStrength() > (byte) 0 && this.player.getCurrentStrength() < (byte) 10) { 
		  fight(this.player, player); 
	  }	else { 
		  synchronized (player) {
			  new AwakeBot(player);
			  player.wait();
		  }
		
		}
	  lock.unlock();
	  }
	  public Lock getLock() {
		  return lock;
	  }
	  public synchronized void moveHumanPlayer(Player player,Coordinate c) throws InterruptedException { 
		// meter na nova celula o jogador com o movimento 
			 
			  if (!isOcupied()) { 
				  game.getCell(c).setPlayer(null);
				  setPlayer(player); 
			  } else if (this.player.getCurrentStrength() > (byte) 0 && this.player.getCurrentStrength() < (byte) 10) { 
				  fight(this.player, player); 
			  }	
			  }

	public synchronized void fight(Player p1, Player p2) throws InterruptedException {
		if (p1.getCurrentStrength() > p2.getCurrentStrength()) {
			fightAux_P1_kills_P2(p1, p2);
		} else if (p1.getCurrentStrength() == p2.getCurrentStrength()) {
			double i = Math.random();
			if (i < 0.5) {
				fightAux_P1_kills_P2(p1, p2);
			} else {
				fightAux_P1_kills_P2(p2, p1);
			} 
		} else {
			fightAux_P1_kills_P2(p2, p1);
		}
	}

	public synchronized void fightAux_P1_kills_P2(Player p1, Player p2) throws InterruptedException {
		if(p1.getCurrentStrength()+p2.getCurrentStrength()<(byte)10) {
		p1.addStrength(p2.getCurrentStrength());
		}else {
			p1.setStrength((byte)10);
			game.CDLCountDown();
		}
		killPlayer(p2);	
	}

	public synchronized void killPlayer(Player player) throws InterruptedException {
		player.setStrength((byte) 0);

	}

	public boolean PlayerIsAlive() {
		return (player.getCurrentStrength()<(byte) 10 && player.getCurrentStrength()>(byte)0);
	}
	
/*	public synchronized void movePlayer(Player player, Coordinate c) throws InterruptedException {

		if (!isOcupied()) {
			game.getCell(c).setPlayer(null);
			setPlayer(player);

		} else {
			if (this.player.getCurrentStrength() > player.getCurrentStrength()) {
				ajuda(this.player, player);
				System.out.println("olaoallaalla");

			}
			if (this.player.getCurrentStrength() < player.getCurrentStrength()) {
				ajuda(player, this.player);
			}
			if (this.player.getCurrentStrength() == player.getCurrentStrength()) {
				double i = Math.random();
				if (i < 0.5) {
					ajuda(player, this.player);
				} else {
					ajuda(this.player, player);
				}

			}
		}

	}

	public synchronized void ajuda(Player player1, Player player2) throws InterruptedException {
		player1.addStrength(player1.getCurrentStrength());
		setPlayer(player1);
		player2.currentThread().stop();
		player1.move(null);
		System.out.println("O player" + this.player.getId() + " matou o player " + player.getId());
	}*/
	
}
