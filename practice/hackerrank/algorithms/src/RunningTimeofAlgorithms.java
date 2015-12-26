import java.util.Scanner;

public class RunningTimeofAlgorithms {

	public static int count = 0;
	
	public static void exch(int[] ns, int i, int j){
		int tmp = ns[i];
		ns[i] = ns[j];
		ns[j] = tmp;
		
		count ++;
	}
	
	public static void print(int[] ns){
		if(ns.length > 0)
			System.out.print(ns[0]);
		for(int i=1; i<ns.length; i++)
			System.out.print(" " + ns[i]);
		System.out.println();
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int s = in.nextInt();
		int[] ns = new int[s];
		for(int i=0; i<s; i++){
			ns[i] = in.nextInt();
		}
		
		for(int i=1; i<s; i++){
			for(int j=i-1; j>=0; j--){
				if(ns[j+1] < ns[j]) exch(ns, j+1, j);
			}
		}
		
		System.out.println(count);

		in.close();
	}
}
