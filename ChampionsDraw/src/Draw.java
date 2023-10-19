import java.util.ArrayList;
import java.util.List;

public class Draw {
	
	static List<Team> pote1 = new ArrayList<Team>();
	static List<Team> pote2 = new ArrayList<Team>();
	static List<Team> pote3 = new ArrayList<Team>();
	static List<Team> pote4 = new ArrayList<Team>();
	
/*
	 static Group grupoA = new Group("A");
	 static Group grupoB = new Group("B");
	 static Group grupoC = new Group("C");
	 static Group grupoD = new Group("D");
	 static Group grupoE = new Group("E");
	 static Group grupoF = new Group("F");
	 static Group grupoG = new Group("G");
	 static Group grupoH = new Group("H");
*/	
	
	
	 static String[] gps = {"A","B","C","D","E","F","G","H"};
	 
	 static List<Group> grupos = new ArrayList<Group>();
	 
	/*
	 static Team BAYERN = new Team("Bayern Munich", "GER");
	 static Team MANCITY = new Team("Manchester City","ENG");
	 static Team REALMADRID = new Team("Real Madrid","ESP");
	 static Team PSG = new Team("PSG","FR");
	 static Team AJAX = new Team("Ajax","NED");
	 static Team PORTO = new Team("FC Porto","PT");
	 static Team FRANK = new Team("Frankfurt","GER");
	 static Team MILAN = new Team("AC Milan","ITA");
	 
	 static Team LIVER = new Team("Liverpool","ENG");
	 static Team CHELSEA = new Team("Chelsea","ENG");
	 static Team BAR�A = new Team("Barcelona","ESP");
	 static Team JUVE = new Team("Juventus","ITA");
	 static Team ATLETI = new Team("Atl�tico Madrid","ESP");
	 static Team SEVILLA = new Team("Sevilla","ESP");
	 static Team LEIPZIG = new Team("Leipzig","GER");
	 static Team TOTTENHAM = new Team("Tottenham","ENG");	
	 
	 static Team DORTMUND = new Team("Dortmund","GER");
	 static Team SALZBUGR = new Team("Salzburg","AST");
	 static Team SHAKTAR = new Team("Shaktar Donetsk","UKR");
	 static Team INTER = new Team("Inter Milano","ITA");
	 static Team NAPOLI = new Team("Napoli","ITA");
	 static Team SPORTING = new Team("Sporting CP","PT");
	 static Team LEVERKUSEN = new Team("Bayern Leverkusen","GER");
	 static Team BENFICA = new Team("SL Benfica","PT");
	 
	 static Team BRUGGE = new Team("Club Brugge","BEL");
	 static Team CELTIC = new Team("Celtic","ESC");
	 static Team MARSEILLE = new Team("Marseille","FR");
	 static Team PSV = new Team("PSV","NED");
	 static Team PLZEN = new Team("Viktoria Plzen","CZS");
	 static Team BODO = new Team("Bodo/Glimt","NOR");
	 static Team HAIFA = new Team("Maccabi Haifa","ISR");
	 static Team COPENHAGEN = new Team("Copenhagen","DEN");
	*/
	
	static String[] p1Nom = {"Bayern Munchen","Manchester City","Real Madrid","PSG","Ajax","FC Porto","Frankfurt","AC Milan"};
	static String[] p1Nat = {"GER","ENG","ESP","FR","NED","PT","GER","ITA"};
	
	public static class Group {
		static String name;
		static Team team1;
		static Team team2;
		static Team team3;
		static Team team4;
		
		public Group(String n) {			
		name=n;
		}
		
		void addToGroup(Team team, int pos) {

			if(pos==1) {
				team1=team;
			}
			if(pos==2) {
				team2=team;
			}
			if(pos==3) {
				team3=team;
			}
			if(pos==4) {
				team4=team;
			}
		}
		
		static void printGroup() {
			System.out.println("Grupo " + name +" :\n" + team1.getName() + "\n" + team2.getName() + "\n" + team3.getName() + "\n" + team4.getName() + "\n");
		}
		
		static String getGroupName() {
			return name;
		}
	}
	
	static boolean sameNation(Team a, Team b) {
		 return a.getNation().equals(b.getNation());
	}
	
	static void AddTeamsToPot1() {
		
		System.out.println(grupos.get(7).getGroupName());
		
		for(int i=0;i<8;i++) {
			Team tmp = new Team(null,null);
			
			tmp.setValue(p1Nom[i],p1Nat[i]);
			System.out.println(tmp.getName());
			pote1.add(tmp);
			}
	}
/*
	static void AddTeamsToPot2() {
		pote2.add(LIVER);
		pote2.add(CHELSEA);
		pote2.add(BAR�A);
		pote2.add(JUVE);
		pote2.add(ATLETI);
		pote2.add(SEVILLA);
		pote2.add(LEIPZIG);
		pote2.add(TOTTENHAM);	
	}
	
	static void AddTeamsToPot3() {
		pote3.add(DORTMUND);
		pote3.add(SALZBUGR);
		pote3.add(SHAKTAR);
		pote3.add(INTER);
		pote3.add(NAPOLI);
		pote3.add(SPORTING);
		pote3.add(LEVERKUSEN);
		pote3.add(BENFICA);	
	}
	
	static void AddTeamsToPot4() {

		pote4.add(BRUGGE);
		pote4.add(CELTIC);
		pote4.add(MARSEILLE);
		pote4.add(PSV);
		pote4.add(PLZEN);
		pote4.add(BODO);
		pote4.add(HAIFA);
		pote4.add(COPENHAGEN);	
		
	}
	*/
	static void createGroupList() {
		for(int i=0;i<8;i++) {
			Group tmp=new Group(gps[i]);
			grupos.add(tmp);
			System.out.println(tmp.getGroupName());
		}
			
	}
	
	static void addTeamToGroup(Team t, Group g, int pos) {
		g.addToGroup(t,pos);
	}
	
	static void drawPot1() {
		System.out.println("Drawing Teams on Pot 1");
		
		List<Group> tmp = grupos;
		
		for(int i=0; i<8; i++) {
			System.out.println(pote1.size());
			
			int a = (int) ((Math.random())*pote1.size());
			int g = (int) ((Math.random())*tmp.size());
			Team t = pote1.get(a);
			Group gp = grupos.get(g);
			addTeamToGroup(t,gp,1);
			pote1.remove(a);
			tmp.remove(g);
			System.out.println(t.getName() + " draw to group " + gp.getGroupName() + " remaining Teams: " + tmp.size());
			
		}
	}

	static void printGroups() {
		System.out.println("Todos os grupos");
		//grupos.get(0).printGroup();
		for(int i=0; i<grupos.size();i++) {
			System.out.println(i);
			grupos.get(i).printGroup();
		}
	}

	public static void main ( String args []) {
		createGroupList();
		AddTeamsToPot1();
		//AddTeamsToPot2();
		//AddTeamsToPot3();
		//AddTeamsToPot4();
		
		drawPot1();
		
	//	printGroups();
		
		System.out.println("End");
	}
	
}

