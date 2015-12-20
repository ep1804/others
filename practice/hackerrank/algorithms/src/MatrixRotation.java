import java.util.Scanner;

public class MatrixRotation {

	public MatrixRotation() {
		// TODO Auto-generated constructor stub
	}

	public static int[] move(int x1, int y1, int x2, int y2, int cx, int cy, int r){
		int[] res = new int[2];
		
		int perimeter = (x2 - x1) * 2 + (y2 - y1) * 2;
		r %= perimeter;
		
		int mv;
		while(r > 0){
			if(y1 == cy && x1 < cx){
				mv = Math.min(cx - x1, r);
				cx -= mv;
				r -= mv;
			}else if(y2 == cy && x2 > cx){
				mv = Math.min(x2 - cx, r);
				cx += mv;
				r -= mv;
			}else if(x1 == cx && y2 > cy){
				mv = Math.min(y2 - cy, r);
				cy += mv;
				r -= mv;
			}else{ // x2 == cx && y1 < cy 
				mv = Math.min(cy - y1, r);
				cy -= mv;
				r -= mv;
			}
		}
		
		res[0] = cx;
		res[1] = cy;
		return res;
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int M = in.nextInt();
		int N = in.nextInt();
		int R = in.nextInt();
		
		int[][] m = new int[M][N];
		
		for(int i=0; i<M; i++)
			for(int j=0; j<N; j++)
				m[i][j] = in.nextInt();				
		
//		for(int i=0; i<M; i++){
//			for(int j=0; j<N; j++)
//				System.out.print(m[i][j] + " ");
//			System.out.println();
//		}
		
		int [][] m1 = new int[M][N];
		
		for(int i=0; i<M; i++)
			for(int j=0; j<N; j++){
				
				int diff = Math.min(Math.min(i, M-1-i), Math.min(j, N-1-j));
				int x1 = diff;
				int y1 = diff;
				int x2 = N - 1 - diff;
				int y2 = M - 1 - diff;
				
				int[] next = move(x1, y1, x2, y2, j, i, R);
				
				//System.out.println("(" + j + ", " + i + ") --> move(" + x1 + ", " + y1 + ", " + x2 + ", " + y2 + ") --> (" + next[0] + ", " + next[1] + ")");
				
				m1[next[1]][next[0]] = m[i][j];
			}

		for(int i=0; i<M; i++){
			for(int j=0; j<N; j++)
				System.out.print(m1[i][j] + " ");
			System.out.println();
		}
		
		in.close();
	}

}
