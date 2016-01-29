package lp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Simplex {
	
	protected double[][] a;
	protected int m, n;

	public Simplex(double[][] A, double[] b, double[] c) {
		// TODO
	}
	
	private int pivitCol(){
		// TODO		
		return -1;
	}
	
	private int pivotRow(int col){
		// TODO		
		return -1;
	}
	
	private void pivot(int row, int col){
		
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/lp/brewer"));
		
		int M = in.nextInt();
		int N = in.nextInt();
		double[][] A = new double[M][N];
		double[] b = new double[M];
		double[] c = new double[N];
		for(int i=0; i<M; i++){
			for(int j=0; j<N; j++)
				A[i][j] = in.nextInt();
			b[i] = in.nextInt();			
		}
		for(int j=0; j<N; j++)
			c[j] = in.nextInt();
		
		
		in.close();
	}

}
