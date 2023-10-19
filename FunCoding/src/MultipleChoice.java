import java.util.Arrays;

public class MultipleChoice {

	public static int[] key = {1,3,2,1,0,0,1,2,3,3,3,2,1,1,1,0,2,1,2,1};
	public static int[] last = new int[20];
	public static double totalgardes = 0;
	public static double highgrade = 0;
	public static int[] best = new int[20];
	
	public static void ansGen() {
		int[] ans = new int[20];
		for(int i=0; i<ans.length; i++) {
			ans[i] = (int) (Math.random()*4);
		}
		
		last=ans;
	}
	
	public static void calculate() {
		double note = 0;
		for(int i=0; i<key.length; i++) {
			if(key[i]==last[i]) {
				note++;
			}
			//else {
			//	note -= 0.25;
			//}
		}
		
		if(note<0) {
			note=0;
		}
		
		if(note>highgrade) {
			highgrade=note;
			best=last;
		}
		
		totalgardes+=note;
		//System.out.println(note);
	}
	
	public static void runNtimes(int n) {
		
		for(int i=0; i<n; i++) {
			ansGen();
			calculate();
		}
		
		System.out.println("Média: " + (totalgardes/n));
		System.out.println("Melhor nota: " + highgrade);
		System.out.println(Arrays.toString(best));
		System.out.println(Arrays.toString(key));
	}
	public static void main(String[] args) {
		runNtimes(100000);
	}

}
