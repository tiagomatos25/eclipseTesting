import java.util.Arrays;

public class randomTesting {
	private static int[] controle = new int[50];
	
	public static void roll(int f) {
		for(int i=0;i<f;i++) {
			int a = (int)(Math.random()*50);
			controle[a]+=1;			
		}
		
	}
	
	public static void printArray() {
		for(int i=0;i<50;i++) {
			System.out.println(controle[i]);
		}
	}
		public static void main(String[] args) {
	
		roll(10000000);
		printArray();
	}
}
