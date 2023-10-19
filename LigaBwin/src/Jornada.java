
public class Jornada {
	Jogo[] jogos;
	TeamBwin[] equipas;
	int i = 18;
	public Jornada(TeamBwin[] t) {
		equipas = t;
		jogos = new Jogo[t.length/2];
		for(int i=0; i<jogos.length; i++) {
			jogos[i] = new Jogo(pickTeam(),pickTeam());
		}
	}
	
	public void jogarJornada() {
		for(int i=0; i<jogos.length; i++) {
			jogos[i].play();		}
	}
	
	public TeamBwin pickTeam() {
		TeamBwin t = null;
		TeamBwin[] ti = availableTeams();
		
		int f = (int) (Math.random()*i);
		
		
		t = ti[f];
		ti[f] = null;
		i--;
		equipas = ti;
		return t;
		
	}
	public TeamBwin[] availableTeams() {
		TeamBwin[] t = new TeamBwin[i];
		for(int j=0; j<equipas.length; j++) {
			if(equipas[i-1]!=null)
				t[i-1]= equipas[i-1];
		}
		return t;
	}
	public boolean hasEqualGame(Jornada j) {
		int z = 0;
		for(int i=0; i<this.jogos.length; i++) {
			for(int f=0; f<j.jogos.length; f++) {
				if((this.jogos[i].t1==j.jogos[f].t1 && this.jogos[i].t2==j.jogos[f].t2 )||(this.jogos[i].t1==j.jogos[f].t2 && this.jogos[i].t2==j.jogos[f].t1 )) {
					z++;
				}
			}
		}
		if(z==0) {
			return false;
		}
		return true;
	}
}
