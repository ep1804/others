import java.util.Scanner;

public class Clique {

	public static int N;
	public static int M;
	
	public static int turanBound(int n, int r){
		
		int c = (int) Math.ceil((double)n / (double)r);
		int f = (int) Math.floor((double)n / (double)r);
		
		return ((n * n) - (n % r) * c * c  - (r - (n % r)) * f * f) / 2;
//		return (int)((1.0 - 1.0 / (double)r) * (double)n * (double)n / 2.0); 
	}
	
	public static int search(int lo, int hi, int clique){
		
		int cmp = turanBound(N, clique) - M;
		
		if(cmp < 0){ // increase cur
			if(clique + 1 == hi){
				if(turanBound(N, hi) < M)
					return clique;
				else
					return hi;
			}else
				return search(clique, hi, (clique + hi) / 2);
		}else if(cmp > 0){ // decrease cur
			return search(lo, clique, (lo + clique) / 2);
		}else{
			return clique;
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int T = in.nextInt();
		for(int t=0; t<T; t++){
			
			N = in.nextInt();
			M = in.nextInt();
			
			System.out.println(search(1, N, N/2));			
		}
		
		in.close();
	}

}
