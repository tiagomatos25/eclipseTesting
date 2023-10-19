import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;

public class CLDraw {

	static FrameTest window = new FrameTest("Hello", 400, 300);
	static JLabel[] labels = {window.labelT1,window.labelT2,window.labelT3,window.labelT4,window.labelT5,window.labelT6,window.labelT7,window.labelT8};
	static JLabel[] labels2 = {window.labelT1P2,window.labelT2P2,window.labelT3P2,window.labelT4P2,window.labelT5P2,window.labelT6P2,window.labelT7P2,window.labelT8P2};
	static List<Team> pote1 = new ArrayList<Team>();
	static List<Team> pote2 = new ArrayList<Team>();
	static Team[][] matches = new Team[8][8];
	//Teams from Champions League
	static Team napoli = new Team("Napoli","IT","A");
	static Team porto = new Team("Porto","PT","B");
	static Team bayern = new Team("Bayer Munchen","GER","C");
	static Team tott = new Team("Tottenham","EN","D");
	static Team chelsea = new Team("Chelsea","EN","E");
	static Team madrid = new Team("Real Madrid","ESP","F");
	static Team city = new Team("Manchester City","EN","G");
	static Team benfica = new Team("Benfica","PT","H");
	//Teams from Europa League
	static Team liver = new Team("Liverpool","EN","A");
	static Team brugge = new Team("Club Brugge","BEL","B");
	static Team inter = new Team("Inter","IT","C");
	static Team frank = new Team("Frankfurt","GER","D");
	static Team milan = new Team("AC Milan","IT","E");
	static Team leipzig = new Team("Leipzig","GER","F");
	static Team dort = new Team("Dortmund","GER","G");
	static Team psg = new Team("PSG","FR","H");
	
	static int p1 = 0;
	static int p2 = 0;
	static boolean group = true;
	static Team lastTeam;
		

	public CLDraw() {
		
	}
	
	static void addTeamsToP1() {
		pote2.add(napoli);
		pote2.add(porto);
		pote2.add(bayern);
		pote2.add(tott);
		pote2.add(chelsea);
		pote2.add(madrid);
		pote2.add(city);
		pote2.add(benfica);
	}
	
	static void addTeamsToP2() {
		pote1.add(liver);
		pote1.add(brugge);
		pote1.add(inter);
		pote1.add(frank);
		pote1.add(milan);
		pote1.add(leipzig);
		pote1.add(dort);
		pote1.add(psg);
	}
	
	static void drawP1() {
		for(int i=0; i<8;i++) {
			int x = (int) (Math.random()*(8-i));
			Team tmp = pote1.get(x);
			matches[i][0]=tmp;
			pote1.remove(x);
			labels[i].setText(tmp.getName());
			
		}
	}
	static void drawP2() {
		for(int i=0; i<8;i++) {
			boolean done=false;
			int pos = 0;
			int x = (int) (Math.random()*(8-i));
			Team tmp = pote2.get(x); //Equipa a colocar
			
			
				while(done==false) {
				
					Team tmp1 = matches[pos][0];
				
					if(!Team.sameNation(tmp,tmp1) && !Team.sameGroup(tmp1, tmp)) {
						matches[pos][1]=tmp;
						labels2[pos].setText(tmp.getName());
						done=true;
					}
					else {
						pos++;
					}
				}
				
			pote2.remove(x);
		}
	}
	
	static void printGames() {
		for(int i=0; i<8; i++) {
			System.out.println("Game " + i + " - " + matches[i][0].getName() + " vs " + matches[i][1].getName());
		}
	}
	
	static void drawOne() {
		
		if(pote1.size()>0 && group) {
		int x = (int) (Math.random()*(8-p1));
		lastTeam = pote1.get(x);
		matches[p1][0]=lastTeam;
		pote1.remove(x);
		labels[p1].setText(lastTeam.getName());
		p1++;
		group = false;
	}else if(pote2.size()>0 && !group){
		
		boolean done=false;
		List<Team> teams = possibleTeams(lastTeam);
		int x = (int) (Math.random()*(teams.size()));
		Team tmp = teams.get(x); //Equipa a colocar
		/*
			while(done==false) {
				Team tmp1 = matches[p2][0];
				if(!Team.sameNation(tmp,tmp1) && matches[p2][1]==null) {
					matches[p2][1]=tmp;
					labels2[p2].setText(tmp.getName());
					done=true;
					p2++;
					group = true;
				}
				else {
					x = (int) (Math.random()*8);
				}
			}*/
		matches[p2][1]=tmp;	
		labels2[p2].setText(tmp.getName());
		p2++;
		group = true;
		pote2.remove(pote2.indexOf(tmp));
		}
	}
	
	static List<Team> possibleTeams(Team t){
		List<Team> tmp = new ArrayList<Team>();
		if(pote2.size()==2) {
			if((!Team.sameNation(t,pote2.get(0)) && !Team.sameNation(t,pote2.get(1)) && !Team.sameNation(pote1.get(0),pote2.get(0)) && Team.sameNation(pote1.get(0),pote2.get(1))) && 
					(!Team.sameGroup(t,pote2.get(0)) && !Team.sameGroup(t,pote2.get(1)) && !Team.sameGroup(pote1.get(0),pote2.get(0)) && Team.sameGroup(pote1.get(0),pote2.get(1)))) {
					tmp.add(pote2.get(1));
				
			}
			if((!Team.sameNation(t,pote2.get(0)) && !Team.sameNation(t,pote2.get(1)) && Team.sameNation(pote1.get(0),pote2.get(0)) && !Team.sameNation(pote1.get(0),pote2.get(1))) && 
					(!Team.sameGroup(t,pote2.get(0)) && !Team.sameGroup(t,pote2.get(1)) && Team.sameGroup(pote1.get(0),pote2.get(0)) && !Team.sameGroup(pote1.get(0),pote2.get(1)))) {			
				tmp.add(pote2.get(0));
				
			}
			if(Team.sameNation(t,pote2.get(0)) && !Team.sameNation(t,pote2.get(1)) && !Team.sameNation(pote1.get(0),pote2.get(0)) && !Team.sameNation(pote1.get(0),pote2.get(1)) && 
					(Team.sameGroup(t,pote2.get(0)) && !Team.sameGroup(t,pote2.get(1)) && !Team.sameGroup(pote1.get(0),pote2.get(0)) && !Team.sameGroup(pote1.get(0),pote2.get(1)))) {
				tmp.add(pote2.get(1));
			}
			if((!Team.sameNation(t,pote2.get(0)) && Team.sameNation(t,pote2.get(1)) && !Team.sameNation(pote1.get(0),pote2.get(0)) && !Team.sameNation(pote1.get(0),pote2.get(1))) && 
					(!Team.sameGroup(t,pote2.get(0)) && Team.sameGroup(t,pote2.get(1)) && !Team.sameGroup(pote1.get(0),pote2.get(0)) && !Team.sameGroup(pote1.get(0),pote2.get(1)))) {
				tmp.add(pote2.get(0));
			}
			tmp.add(pote2.get(0));
			tmp.add(pote2.get(1));
		} else {
		for(int i=0; i<pote2.size();i++) {
			Team tm = pote2.get(i);
			if(!Team.sameNation(t,tm) && !Team.sameGroup(t, tm)){
				tmp.add(tm);
				}
			}
		}
		return tmp;						
	}
	static void draw() {
		addTeamsToP1();
		addTeamsToP2();
		//drawP1();
		//drawP2();
		//printGames();
	}
	public static void main(String[] args) {
		draw();
		window.open();
		//draw();	
	}

}
