package game;

import environment.Cell;

public class PlaceInBot extends Thread {
	private Player p;
	private Cell c;
	private Game g;

	public PlaceInBot(Player p,Cell c,Game g) {
		this.p = p;
		this.c=c;
		this.g=g;
		this.start();
	}
	
	public void run() throws IllegalMonitorStateException {
		System.out.println("Sou o bot de colocação");
		while(true) {
		if(!c.isOcupied()) 
			c.setPlayer(p);
			synchronized(p) {	
				p.notifyAll();
			}	
			interrupt();
			g.notifyChange();	
		
		}
		
	}
}
