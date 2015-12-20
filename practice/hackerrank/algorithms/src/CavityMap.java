import java.util.Scanner;

public class CavityMap {

	public CavityMap() {
	}

	public static void main(String[] args) {		
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		
		char[][] a = new char[N][];
		
		for(int i=0; i<N; i++)
			a[i] = in.next(".+").toCharArray();
		
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				
				if(i > 0 && i < N - 1 && j > 0 && j < N - 1 && a[i][j] > a[i-1][j] && a[i][j] > a[i+1][j] && a[i][j] > a[i][j-1] && a[i][j] > a[i][j+1]){
					System.out.print('X');	
				}else{
					System.out.print(a[i][j]);
				}
			}
			System.out.println();
		}
		
		in.close();
	}

}
