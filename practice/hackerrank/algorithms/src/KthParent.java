import java.util.Arrays;
import java.util.Scanner;

public class KthParent {

	  private static final int maxE = 100001;
		private static final int maxPow = (int) (Math.log(maxE) / Math.log(2));
		
		private static int[][] p;

		public static void main(String[] args) {
			
			p = new int[maxPow + 1][maxE];

			Scanner in = new Scanner(System.in);
			
			int T = in.nextInt();
			for(int t=0; t<T; t++){
				
				int E = in.nextInt();
				int po = (int) (Math.log(E) / Math.log(2));
				
				// initialize array
				for(int i=0; i<=po; i++)
					Arrays.fill(p[i], 0);

				// set 1st parents
				for(int e=0; e<E; e++) 
					p[0][in.nextInt()] = in.nextInt();

				// set grand parents
				for(int i=1; i<=po; i++)
					for(int e=0; e<maxE; e++)
						p[i][e] = p[i-1][p[i-1][e]];
			
				int Q = in.nextInt();
				
				int x, y, k;
				for(int q=0; q<Q; q++){
					switch(in.nextInt()){
					case 0:
						y = in.nextInt();
						x = in.nextInt();
						p[0][x] = y;
						for(int i=1; i<=po; i++)
							p[i][x] = p[i-1][p[i-1][x]];
						break;
					case 1:
						x = in.nextInt();
						for(int i=0; i<=po; i++)
							p[i][x] = 0;
						break;
					case 2:
						x = in.nextInt();
						k = in.nextInt();

						while(k > 0 && x != 0){
							int kPo  = (int) (Math.log(k) / Math.log(2));
							
							x = p[kPo][x];
							k = k - (int) Math.pow(2, kPo);
						}
						
						System.out.println(x);
						
						break;
					}
				}
			}
					
			in.close();
		}

}
