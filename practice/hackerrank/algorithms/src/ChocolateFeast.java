import java.util.Scanner;

public class ChocolateFeast {

	public ChocolateFeast() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args){
		
		Scanner in = new Scanner(System.in);
		
		int T = in.nextInt();
		
		for(int t=0; t<T; t++){
			int N = in.nextInt();
			int C = in.nextInt();
			int M = in.nextInt();
			
			int sum = 0;
			
			int wrappers = N / C;
			sum += wrappers;
			
			while(wrappers >= M){				
				int bonus = wrappers / M;								
				wrappers = wrappers % M + bonus;				
				sum += bonus;				
			}			

			System.out.println(sum);
		}
		
		in.close();		
	}

}
