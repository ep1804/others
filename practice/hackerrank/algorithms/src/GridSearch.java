import java.util.Scanner;

public class GridSearch {

	public GridSearch() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean match(String[] G, String[] P){
		
		int nRowG = G.length;
		int nRowP = P.length;
		
		for(int r = 0; r <= nRowG - nRowP; r++){
			int c = G[r].indexOf(P[0]);
			while(c != -1){

				boolean found = true;
				
				for(int r2=1; r2 < nRowP; r2++){
					if(G[r + r2].indexOf(P[r2], c) != c){
						found = false;
						break;
					}						
				}
				
				if(found) return true;
				
				c = G[r].indexOf(P[0], c + 1);
			}
		}		
		return false;
	}
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int t=0; t<T; t++){
			int R = in.nextInt();
			in.nextInt();
			String[] G = new String[R];
			for(int r=0; r<R; r++){
				G[r] = in.next(".+");
			}
			
//			for(int r=0; r<R; r++)
//				System.out.println(G[r]);
			
			R = in.nextInt();
			in.nextInt();
			
			String[] P = new String[R];
			for(int r=0; r<R; r++){
				P[r] = in.next(".+");
			}
			
//			for(int r=0; r<R; r++)
//				System.out.println(G[r]);

			if(match(G, P))
				System.out.println("YES");
			else
				System.out.println("NO");
			
		}
		
		in.close();
	}

}
