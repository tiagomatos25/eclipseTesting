package game;

import environment.Cell;
import environment.Coordinate;
import environment.Direction;
import gui.GameGuiMain;

public class Bot extends Player {

	public Bot(int id, Game game, byte strength) {
		super(id, game, strength);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isHumanPlayer() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void run() {
		try {
			sleep(10000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while(this.getCurrentStrength()<(byte) 10 && this.getCurrentStrength()>(byte)0 && !this.isInterrupted()) {
			int i =(int)(Math.random()*4);
			Direction dir = Direction.values()[i];
			try {
				this.move(dir);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
				this.interrupt();
			}
			
		}	
      
	} 


}
