package sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

// without shuffle

public class QuickSorted<T extends Comparable<T>> {

	public T[] arr;

	public QuickSorted(T[] a) {
		arr = Arrays.copyOf(a, a.length);
		sort(0, arr.length);
	}

	private void sort(int lo, int hi) {
		if (hi - lo <= 1)
			return;

		T pvt = arr[lo];
		int lt = lo + 1;
		int gt = hi - 1;
		for (int i = lt; i <= gt; i++) {
			int cmp = arr[i].compareTo(pvt);

			if (cmp < 0)
				exch(i, lt++);
			else if (cmp > 0)
				exch(i--, gt--);
		}
		exch(lo, lt - 1);

		sort(lo, lt - 1);
		sort(gt + 1, hi);
	}

	private void exch(int i, int j) {
		T tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void print(Object[] a) {
		if (a == null) {
			System.out.println("null");
			return;
		}
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

		print(qs.arr);

	}

}
