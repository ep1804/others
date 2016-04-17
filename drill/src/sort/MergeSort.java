package sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@SuppressWarnings("rawtypes")
public class MergeSort {

	private static Comparable[] buf;

	public static void sort(Comparable[] a) {
		buf = new Comparable[a.length];
		for (int i = 0; i < a.length; i++)
			buf[i] = a[i];
		sort(a, buf, 0, a.length);
	}

	public static void sort(Comparable[] a, Comparable[] buf, int lo, int hi) {
		if (hi - lo < 2)
			return;
		int mid = (lo + hi) / 2;
		sort(buf, a, lo, mid);
		sort(buf, a, mid, hi);
		merge(buf, a, lo, mid, hi);
	}

	private static void merge(Comparable[] from, Comparable[] to, int lo, int mid, int hi) {
		int i = lo;
		int j = mid;

		for (int k = lo; k < hi; k++) {
			if (i == mid) {
				to[k] = from[j++];
			} else if (j == hi) {
				to[k] = from[i++];
			} else if (from[i].compareTo(from[j]) < 0) {
				to[k] = from[i++];
			} else {
				to[k] = from[j++];
			}
		}

	}

	public static void print(Comparable[] a) {
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

		sort(a);

		print(a);
	}
}
