import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;

public class ELDraw {
	
	static FrameTest window = new FrameTest("Hello", 400, 300);
	static JLabel[] labels = {window.labelT1,window.labelT2,window.labelT3,window.labelT4,window.labelT5,window.labelT6,window.labelT7,window.labelT8};
	static JLabel[] labels2 = {window.labelT1P2,window.labelT2P2,window.labelT3P2,window.labelT4P2,window.labelT5P2,window.labelT6P2,window.labelT7P2,window.labelT8P2};
	static List<Team> pote1 = new ArrayList<Team>();
	static List<Team> pote2 = new ArrayList<Team>();
	static Team[][] matches = new Team[8][8];
	//Teams from Champions League
	static Team scp = new Team("Sporting","PT");
	static Team ajax = new Team("Ajax","NED");
	static Team lever = new Team("Bayer Leverkusen","GER");
	static Team barca = new Team("Barcelona","ES");
	static Team salz = new Team("Salzburg","AT");
	static Team sevi = new Team("Sevilla","ESP");
	static Team shak = new Team("Shaktar","UK");
	static Team juve = new Team("Juventus","IT");
	//Teams from Europa League
	static Team psv = new Team("PSV","NED");
	static Team rennes = new Team("Rennes","FR");
	static Team roma = new Team("Roma","IT");
	static Team berlin = new Team("Union Berlin","GER");
	static Team man = new Team("Manchester United","EN");
	static Team midt = new Team("Midtjyland","DEN");
	static Team nantes = new Team("Nantes","FR");
	static Team mona = new Team("Monaco","FR");
	static int p1 = 0;
	static int p2 = 0;
	static boolean group = true;
	static Team lastTeam;
		
	public ELDraw() {
		
	}
	
	static void addTeamsToP1() {
		pote1.add(scp);
		pote1.add(ajax);
		pote1.add(lever);
		pote1.add(barca);
		pote1.add(salz);
		pote1.add(sevi);
		pote1.add(shak);
		pote1.add(juve);
	}
	
	static void addTeamsToP2() {
		pote2.add(psv);
		pote2.add(rennes);
		pote2.add(roma);
		pote2.add(berlin);
		pote2.add(man);
		pote2.add(midt);
		pote2.add(nantes);
		pote2.add(mona);
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
				
					if(!Team.sameNation(tmp,tmp1) && matches[pos][1]==null) {
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
			if(!Team.sameNation(t,pote2.get(0)) && !Team.sameNation(t,pote2.get(1)) && !Team.sameNation(pote1.get(0),pote2.get(0)) && Team.sameNation(pote1.get(0),pote2.get(1))) {
				tmp.add(pote2.get(1));
			}
			if(!Team.sameNation(t,pote2.get(0)) && !Team.sameNation(t,pote2.get(1)) && Team.sameNation(pote1.get(0),pote2.get(0)) && !Team.sameNation(pote1.get(0),pote2.get(1))) {
				tmp.add(pote2.get(0));
			}
			if(Team.sameNation(t,pote2.get(0)) && !Team.sameNation(t,pote2.get(1)) && !Team.sameNation(pote1.get(0),pote2.get(0)) && !Team.sameNation(pote1.get(0),pote2.get(1))) {
				tmp.add(pote2.get(1));
			}
			if(!Team.sameNation(t,pote2.get(0)) && Team.sameNation(t,pote2.get(1)) && !Team.sameNation(pote1.get(0),pote2.get(0)) && !Team.sameNation(pote1.get(0),pote2.get(1))) {
				tmp.add(pote2.get(0));
			}
			tmp.add(pote2.get(0));
			tmp.add(pote2.get(1));
		} else {
		for(int i=0; i<pote2.size();i++) {
			Team tm = pote2.get(i);
			if(!Team.sameNation(t,tm)){
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
	
	 public int solution(int[] A, String[] D) {
		 int total=0;
		 int k=0;
	      int[][] month = new int[12][2];//matriz, cada linha é 1 mês 1º valor das transações, 2º valor número de transações
	      for(int i=0;i<A.length;i++) {
	    	  String[] date = D[i].split("-");
	    	  if(A[i]<0)
	    		  month[Integer.parseInt(date[1])][0]+=A[i];
	    	  	month[Integer.parseInt(date[1])][1]++;
	      }
	      for(int j=0;j<A.length;j++) {
	    	  total+=A[j];
	      }
	      for(int l=0;l<12;l++) {
	    	  if(month[l][0]<=-100 && month[l][1]>=3)
	    		  k++;
	      }
	      
	      return (total-((12-k)*5));
	    }
		    
	 
	public static void main(String[] args) {
		//draw();
		//window.open();
		//draw();	
		int[] A = {-1,-2};
		String B = "2020-01-20";
		 String[] date = B.split("-");
		 char c= '1';
		 boolean a = Character.isDigit(c);
		 System.out.println(a);
	}

}
