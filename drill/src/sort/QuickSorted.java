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

		sort(0, a.length);
	}

	private void sort(int lo, int hi) {

		if (hi - lo < 2)
			return;

		T pvt = arr[lo];
		int lt = lo;
		int ge = hi;

		for (int i = lo + 1; i < ge; i++) {
			int cmp = arr[i].compareTo(pvt);
			if (cmp < 0) {
				lt++;
				exch(i, lt);
			} else if (cmp > 0) {
				ge--;
				exch(i, ge);
				i--;
			}
		}
		exch(lo, lt);
		
		sort(lo, lt);
		sort(ge, hi);
	}

	private void exch(int i, int j) {
		if(i == j) return;
		T tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
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

		print(qs.arr);

	}

}
