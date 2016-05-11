package lp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Simplex {

	// TODO

	public double max() {
		// TODO
		return 0;
	}

	public Simplex(double[][] A, double[] b, double[] c) {
		// TODO
	}

	private int pivotCol() {
		// TODO
		return 0;
	}

	private int pivotRow(int col) {
		// TODO
		return 0;
	}

	private void pivot(int row, int col) {
		// TODO
	}


	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/lp/brewer"));
		int m = in.nextInt();
		int n = in.nextInt();
		double[][] A = new double[m][n];
		double[] b = new double[m];
		double[] c = new double[n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				A[i][j] = in.nextDouble();
			}
			b[i] = in.nextDouble();
		}
		for (int j = 0; j < n; j++)
			c[j] = in.nextDouble();
		in.close();

		Simplex sx = new Simplex(A, b, c);
	}

}
