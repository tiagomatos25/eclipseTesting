
public class Team {
	 private String name;
	 private String nation;
	 private String group;

	public Team(String n, String nat) {
		 name = n;
		 nation = nat;
	 }
	public Team(String n, String nat,String g) {
		 name = n;
		 nation = nat;
		 group = g;
	 }
	
	public void setValue(String na,String n) {
		 name = na;
		 nation = n;
	}
	
	String getName() {
		 return name;
	 }
	 String getNation() {
		 return nation;
	 }
	 String getGroup() {
		 return group;
	 }
	 
	 static boolean sameNation(Team t1, Team t2) {
		 String s1 = t1.getNation();
		 String s2 = t2.getNation();
				 
		 return s1.equals(s2);
	 }
	 
	 static boolean sameGroup(Team t1, Team t2) {
		 String s1 = t1.getGroup();
		 String s2 = t2.getGroup();
				 
		 return s1.equals(s2);
	 }
}
