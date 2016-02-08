package symbolTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SweepLine {

	public SweepLine() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		BST<Integer, Integer> tree = new BST<Integer, Integer>();
		
		Scanner in = new Scanner(new File("input/symTab/sweepLine"));
		int H = in.nextInt();
		int hx1[] = new int[H];
		int hy1[] = new int[H];
		int hx2[] = new int[H];
		int hy2[] = new int[H];		
		int V = in.nextInt();
		int vx1[] = new int[V];
		int vy1[] = new int[V];
		int vx2[] = new int[V];
		int vy2[] = new int[V];
		for(int i=0; i<H; i++){
			hx1[i] = in.nextInt(); 
			hy1[i] = in.nextInt();
			hx2[i] = in.nextInt();
			hy2[i] = in.nextInt();			
		}
		for(int i=0; i<V; i++){
			vx1[i] = in.nextInt(); 
			vy1[i] = in.nextInt();
			vx2[i] = in.nextInt();
			vy2[i] = in.nextInt();			
		}
		in.close();

	}

}
