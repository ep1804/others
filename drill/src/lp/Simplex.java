package lp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Simplex {

	private int m;
	private int n;
	private double[][] M;
	private double Z;

	public double max() {
		return Z;
	}

	public Simplex(double[][] A, double[] b, double[] c) {
		this.m = b.length;
		this.n = c.length;
		M = new double[m + 1][m + n + 1];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				M[i][j] = A[i][j];
			}
			M[i][n + i] = 1;
			M[i][n + m] = b[i];
		}
		for (int j = 0; j < n; j++)
			M[m][j] = c[j];

		print(M);

		int col = pivotCol();
		int row;

		while (col != -1) {
			row = pivotRow(col);
			if (row == -1)
				break;

			System.out.println("Pivot: (" + row + ", " + col + ")");
			pivot(row, col);
			print(M);

			col = pivotCol();
		}

		Z = - M[m][m + n];
		
		double k = M[m-1][m+n];
		System.out.println(k);
		System.out.println(Math.round(k));
		
	}

	private int pivotCol() {
		for (int j = 0; j < m + n; j++)
			if (M[m][j] > 0)
				return j;
		return -1;
	}

	private int pivotRow(int col) {
		double minRatio = Double.POSITIVE_INFINITY;
		int idx = -1;

		for (int i = 0; i < m; i++) {
			if (M[i][col] < 0)
				continue;

			double ratio = M[i][m + n] / M[i][col];
			if (minRatio > ratio) {
				minRatio = ratio;
				idx = i;
			}
		}

		return idx;
	}

	private void pivot(int row, int col) {

		double pvt = M[row][col];

		for (int j = 0; j <= m + n; j++) {
			M[row][j] /= pvt;
		}

		for (int i = 0; i <= m; i++) {
			if (i == row)
				continue;

			double u = M[i][col];
			for (int j = 0; j <= m + n; j++) {
				M[i][j] -= M[row][j] * u;
			}
		}
	}

	public void print(double[][] a) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < a.length; i++) {
			sb.append(a[i][0]);
			for (int j = 1; j < a[0].length; j++) {
				sb.append("\t").append(a[i][j]);
			}
			sb.append('\n');
		}
		System.out.print(sb.toString());
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
		System.out.println("Max: " + sx.max());
	}

}
