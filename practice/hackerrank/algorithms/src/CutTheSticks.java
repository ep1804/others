import java.util.Arrays;
import java.util.Scanner;

public class CutTheSticks {

	public CutTheSticks() {
		// TODO Auto-generated constructor stub
	}

//	private static void print(int[] as){
//		if(as == null || as.length == 0)
//			return;
//		
//		System.out.print(as[0]);
//		
//		for(int i=1; i<as.length; i++){
//			System.out.print(" " + as[i]);
//		}
//		
//		System.out.println();
//	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		
		int[] ns = new int[N];
		
		for(int i=0; i<N; i++)
			ns[i] = in.nextInt();
		
		in.close();
		
		Arrays.sort(ns);
		
		for(int i=0; i<N; i++){
			System.out.println(N - i);
			
			int min = ns[i];
			for(int j=i; j<N; j++)
				ns[j] -= min;			
		
			for(int j=i+1; j<N; j++){
				if(ns[j] > 0){					
					break;
				}else
					i++;
				
			}
		}
	}

}
