
public class Jogo {
	TeamBwin t1;
	TeamBwin t2;
	
	public Jogo(TeamBwin t1, TeamBwin t2) {
		this.t1=t1;
		this.t2=t2;
	}
	
	public String printGame() {
		return (t1.name + " x " + t2.name);
	}
	public void play() {
		if(mostLikelyToWin()==null) {
			double prob = Math.random();
			if(prob<0.33) {
				t1.addWin();
				t2.addLoss();
				System.out.println(printGame() + ": venceu " + t1.name);
			}else if(prob > 0.66) {
				t1.addLoss();
				t2.addWin();
				System.out.println(printGame() + ": venceu " + t2.name);
			} else {
				t1.addDraww();
				t2.addDraww();
				System.out.println(printGame() + ": empate");
			}
		}
		if(mostLikelyToWin()==t1) {
			double prob = Math.random();
			if(prob<0.85) {
				t1.addWin();
				t2.addLoss();
				System.out.println(printGame() + ": venceu " + t1.name);
			}else if(prob > 0.95) {
				t1.addLoss();
				t2.addWin();
				System.out.println(printGame() + ": venceu " + t2.name);
			} else {
				t1.addDraww();
				t2.addDraww();
				System.out.println(printGame() + ": empate");
			}
		}
		if(mostLikelyToWin()==t2) {
			double prob = Math.random();
			if(prob<0.85) {
				t2.addWin();
				t1.addLoss();
				System.out.println(printGame() + ": venceu " + t2.name);
			}else if(prob > 0.95) {
				t2.addLoss();
				t1.addWin();
				System.out.println(printGame() + ": venceu " + t1.name);
			} else {
				t1.addDraww();
				t2.addDraww();
				System.out.println(printGame() + ": empate");
			}
		}
	}
	
	public TeamBwin mostLikelyToWin() {
		if(t1.c + 10 - t2.c > 15) {
			return t1;
		}
		if(t1.c + 10 - t2.c < 15) {
			return t2;
		}
		return null;
	}
}
