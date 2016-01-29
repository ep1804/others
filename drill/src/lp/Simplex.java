package lp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Simplex {

	private int m, n;
	private double[][] a;
	private double max;

	public double max() {
		return max;
	}

	public Simplex(double[][] A, double[] b, double[] c) {
		m = b.length;
		n = c.length;
		a = new double[m + 1][n + m + 1];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++)
				a[i][j] = A[i][j];
			a[i][n + i] = 1;
			a[i][n + m] = b[i];
		}
		for (int j = 0; j < n; j++)
			a[m][j] = c[j];

		while (true) {
			int col = pivotCol();
			if (col == -1)
				break;
			int row = pivotRow(col);
			if (row != -1)
				pivot(row, col);
		}

		max = -a[m][n + m];
	}

	private int pivotCol() {
		for (int j = 0; j < m + n; j++)
			if (a[m][j] > 0)
				return j;
		return -1;
	}

	private int pivotRow(int col) {
		int row = -1;
		double ratioMin = Double.POSITIVE_INFINITY;
		for (int i = 0; i < m; i++) {
			if (a[i][col] <= 0)
				continue;
			double ratio = a[i][m + n] / a[i][col];
			if (ratioMin > ratio) {
				ratioMin = ratio;
				row = i;
			}
		}
		return row;
	}

	private void pivot(int row, int col) {
		for (int i = 0; i <= m; i++) {
			if (i == row)
				continue;
			double ratio = a[i][col] / a[row][col];
			for (int j = 0; j <= m + n; j++) {
				if (j == col)
					a[i][j] = 0;
				else
					a[i][j] -= ratio * a[row][j];
			}
		}
		double pv = a[row][col];
		for (int j = 0; j <= m + n; j++)
			a[row][j] /= pv;
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/lp/brewer"));

		int M = in.nextInt();
		int N = in.nextInt();
		double[][] A = new double[M][N];
		double[] b = new double[M];
		double[] c = new double[N];
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++)
				A[i][j] = in.nextInt();
			b[i] = in.nextInt();
		}
		for (int j = 0; j < N; j++)
			c[j] = in.nextInt();

		Simplex sx = new Simplex(A, b, c);
		
		System.out.println(sx.max());

		in.close();
	}

}
