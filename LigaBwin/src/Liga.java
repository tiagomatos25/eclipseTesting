
public class Liga {
	TeamBwin[] teams;
	Jornada[] campeonato;
	int size = 1;

	public Liga(TeamBwin[] t) {
		teams=t;
		campeonato = new Jornada[(t.length-1)*2];
		campeonato[0] = new Jornada(teams);
		//gerarCamps();
	}
	
	public void gerarCamps() {
		for(int i=0; i< campeonato.length;i++) {
			Jornada j = new Jornada(teams);
			while(!possible(j)) {
				j = new Jornada(teams);
				System.out.println("t1");
			}
			campeonato[i] = new Jornada(teams);
			size++;
		}
	}
	
	public boolean possible(Jornada j) {
		int z=0;
		for(int i=0; i< size;i++) {
			if(campeonato[i].hasEqualGame(j)) {
				z++;
			}
		}
		if(z==0)
			return true;
		return false;
	}
	
	public void startLiga() {
		for(int i=0; i< campeonato.length;i++) {
			campeonato[i].jogarJornada();
		}
	}
	
	public void sortTeams() {
		for(int i=0; i< teams.length;i++) {
			for(int j=1; j< teams.length;j++) {
				if(teams[i].getPoints()>teams[j].getPoints()) {
					TeamBwin tmp = teams[i];
					teams[i] = teams[j];
					teams[j]=tmp;
				}
			}
		}
	}
	
	public void display() {
		for(int i=0; i< teams.length;i++) {
			System.out.println(i + "º " + teams[i].name + " " + teams[i].getPoints() + "P " + teams[i].win  + "W " + teams[i].draw  + "D " + teams[i].loses +"L");
		}
	}
	
	public void alt() {
		for(int i=0; i< teams.length;i++) {
			for(int j=0; j< teams.length;j++) {
				if(teams[i]!=teams[j]) {
					Jogo jogo = new Jogo(teams[i],teams[j]);
					jogo.play();
				}
			}
		}		
	}
	
	public static void main(String[] args) {
		TeamBwin scp = new TeamBwin("Sporting CP",80);
		TeamBwin slb = new TeamBwin("SL Benfica",90);
		TeamBwin fcp = new TeamBwin("FC Porco",90);
		TeamBwin cpia = new TeamBwin("Casa Pia",45);
		TeamBwin brg = new TeamBwin("Braga",70);
		TeamBwin vsc = new TeamBwin("Vitória SC",65);
		TeamBwin boav = new TeamBwin("Boavista",50);
		TeamBwin mar = new TeamBwin("Marítimo",45);
		TeamBwin est = new TeamBwin("Estoril",45);
		TeamBwin viz = new TeamBwin("Vizela",45);
		TeamBwin arc = new TeamBwin("Arouca",50);
		TeamBwin rio = new TeamBwin("Rio Ave",55);
		TeamBwin fam = new TeamBwin("Famalicão",45);
		TeamBwin sclara = new TeamBwin("Santa Clara",45);
		TeamBwin chv = new TeamBwin("Chaves",45);
		TeamBwin porti = new TeamBwin("Portimonense",45);
		TeamBwin fcpf = new TeamBwin("Paços",45);
		TeamBwin gil = new TeamBwin("Gil Vicente",45);
		TeamBwin[] t = new TeamBwin[]{scp,slb,fcp,cpia,brg,vsc,boav,mar,est,viz,arc,rio,fam,sclara,chv,porti,fcpf,gil};
		Liga l = new Liga(t);
		l.alt();
		l.sortTeams();
		l.display();
	}

}
