package game;

import environment.Direction;
import gui.BoardJComponent;
import gui.GameGuiMain;

/**
 * Class to demonstrate a player being added to the game.
 * 
 * @author luismota
 *
 */
public class PhoneyHumanPlayer extends Player {
	private BoardJComponent gui;
	
	public PhoneyHumanPlayer(int id, Game game, byte strength) {
		super(id, game, strength);
	}

	public boolean isHumanPlayer() {
		return true;
	}

	@Override
	public void run() {
	
			try {
				game.addPlayerToGame(this);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	}
}
