package lp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Simplex {

	private int m;
	private int n;
	private double[][] S;

	public double max() {
		return -1 * S[m][n + m];
	}

	public Simplex(double[][] A, double[] b, double[] c) {

		m = b.length;
		n = c.length;
		S = new double[m + 1][n + m + 1];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				S[i][j] = A[i][j];
			}
			S[i][n + m] = b[i];
			S[i][n + i] = 1;
		}
		for (int j = 0; j < n; j++)
			S[m][j] = c[j];

		System.out.println("tableau:");
		printArr(S);

		int pCol, pRow;
		for (pCol = pivotCol(); pCol >= 0; pCol = pivotCol()) {
			pRow = pivotRow(pCol);
			pivot(pRow, pCol);

			System.out.println("After pivoting with (" + pRow + ", " + pCol + "), tableau:");
			printArr(S);
		}

		System.out.println("max:" + max());
	}

	private int pivotCol() {
		for (int i = 0; i < n + m; i++) {
			if (S[m][i] > 0)
				return i;
		}
		return -1;
	}

	private int pivotRow(int col) {
		int r = 0;
		double minRatio = S[0][n + m] / S[0][col];
		for (int i = 1; i < m; i++) {
			double ratio = S[i][n + m] / S[i][col];
			if (ratio >= 0 && ratio < minRatio) {
				minRatio = ratio;
				r = i;
			}
		}
		return r;
	}

	private void pivot(int row, int col) {
		double scale = S[row][col];
		for (int j = 0; j <= n + m; j++) {
			S[row][j] = S[row][j] / scale;
		}

		for (int i = 0; i <= m; i++) {
			if (i != row) {
				double scale2 = S[i][col];
				for (int j = 0; j <= n + m; j++)
					S[i][j] -= S[row][j] * scale2;

			}
		}

	}

	private void printArr(double[][] x) {
		int m = x.length;
		int n = x[0].length;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(x[i][j] + " ");
			}
			System.out.println();
		}
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
