package sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

// without shuffle

public class QuickSorted<T extends Comparable<T>> {

	public T[] sorted;

	public QuickSorted(T[] a) {
		sorted = Arrays.copyOf(a, a.length);
		sort(sorted, 0, sorted.length);
	}

	private void sort(T[] a, int lo, int hi) {

		if (hi - lo <= 1)
			return;

		T pivot = a[lo];
		int lt = lo;
		int gt = hi - 1;

		for (int i = lt + 1; i <= gt; i++) {
			int cmp = a[i].compareTo(pivot);

			if (cmp > 0) {
				exch(a, i--, gt--);
			} else if (cmp < 0) {
				exch(a, i, lt++);
			}
		}
		
		sort(a, lo, lt + 1);
		sort(a, gt + 1, hi);
	}

	private void exch(T[] a, int x, int y) {
		T tmp = a[x];
		a[x] = a[y];
		a[y] = tmp;
	}

	public static void print(Object[] a) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < a.length; i++) {
			sb.append(a[i]).append(' ');
		}
		System.out.println(sb.toString());
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/sort/string1"));
		String[] a = in.nextLine().trim().split("\\s+");
		in.close();

		print(a);

		QuickSorted<String> qs = new QuickSorted<String>(a);

		print(qs.sorted);

	}

}
