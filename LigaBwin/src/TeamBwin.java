
public class TeamBwin {
	
	String name;
	int pontos;
	int win;
	int draw;
	int loses;
	int c;
	
	public TeamBwin(String n, int coef) {
		name=n;
		pontos=0;
		win=0;
		draw=0;
		loses=0;
		c=coef;
	}
	
	public void addWin() {
		win++;
		calculatePoints();
	}
	public void addLoss() {
		loses++;
		calculatePoints();
	}
	
	public void addDraww() {
		draw++;
		calculatePoints();
	}
	
	public void calculatePoints() {
		pontos = win*3 + draw;
	}
	
	public int getPoints() {
		return pontos;
	}
	
}
