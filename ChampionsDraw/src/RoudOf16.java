import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;

public class RoudOf16 {
	static FrameTest window = new FrameTest("Hello", 400, 300);
	static JLabel[] labels = {window.labelT1,window.labelT2,window.labelT3,window.labelT4,window.labelT5,window.labelT6,window.labelT7,window.labelT8};
	static JLabel[] labels2 = {window.labelT1P2,window.labelT2P2,window.labelT3P2,window.labelT4P2,window.labelT5P2,window.labelT6P2,window.labelT7P2,window.labelT8P2};
	static List<Team> pote1 = new ArrayList<Team>();
	static List<Team> pote2 = new ArrayList<Team>();
	static Team[][] matches = new Team[8][8];
	//Teams from Champions League
	static Team scp = new Team("Sporting","PT");
	static Team man = new Team("Manchester United","EN");
	static Team shaktar = new Team("Shaktar","UKR");
	static Team roma = new Team("Roma","IT");
	static Team berlin = new Team("Union Berlin","GER");
	static Team lever = new Team("Bayer Leverkusen","GER");
	static Team sevi = new Team("Sevilla","ESP");
	static Team juve = new Team("Juventus","IT");
	//Teams from Europa League
	static Team arsenal = new Team("Arsenal","ENG");
	static Team betis = new Team("Betis","ESP");
	static Team realsoc = new Team("Real Sociedad","ESP");
	static Team freib = new Team("Freiburg","GER");
	static Team fener = new Team("Fernebahçe","TUR");
	static Team usg = new Team("Union Saint-Gilloise","BEL");
	static Team feyer = new Team("Feyenoord","NED");
	static Team ferenc = new Team("Ferencváros","HUN");
	
	static int p1 = 0;
	static int p2 = 0;
	static boolean group = true;
	static Team lastTeam;
		

	public RoudOf16() {
		
	}
	
	static void addTeamsToP1() {
		pote2.add(scp);
		pote2.add(man);
		pote2.add(shaktar);
		pote2.add(roma);
		pote2.add(berlin);
		pote2.add(lever);
		pote2.add(sevi);
		pote2.add(juve);
	}
	
	static void addTeamsToP2() {
		pote1.add(arsenal);
		pote1.add(betis);
		pote1.add(realsoc);
		pote1.add(freib);
		pote1.add(fener);
		pote1.add(usg);
		pote1.add(feyer);
		pote1.add(ferenc);
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
		drawP1();
		drawP2();
		printGames();
	}
	public static void main(String[] args) {
		draw();
		window.open();
		//draw();	
	}
		
	
}
