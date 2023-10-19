package game;

public class AwakeBot extends Thread{
	
	private Thread t;

	public AwakeBot(Thread t) {
		this.t = t;
		this.start();
	}
	
	public void run() throws IllegalMonitorStateException {
		synchronized(t) {
			try {
				sleep(2000);
				t.notifyAll();
			} catch (InterruptedException e) {
			
			e.printStackTrace();
			}
		}
	}
}