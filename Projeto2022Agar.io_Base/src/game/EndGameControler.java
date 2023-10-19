package game;

public class EndGameControler extends Thread {
	private CDL cdl;
	private Game game;

	public EndGameControler(CDL cdl, Game game) {
		this.cdl = cdl;
		this.game = game;
		this.start();
	}
	

	
	@Override
	public void run() {
		try {
			cdl.await();
			game.gameOver();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
