package sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

@SuppressWarnings("rawtypes")
public class MergeSorted<T extends Comparable<T>> {

	public T[] sorted;

	public MergeSorted(T[] a) {
		T[] buf1 = Arrays.copyOf(a, a.length);
		T[] buf2 = Arrays.copyOf(a, a.length);

		sort(buf1, buf2, 0, a.length);
		sorted = buf2;
	}

	private void sort(T[] a, T[] b, int ge, int lt) {

		if (lt - ge <= 1)
			return;

		int mid = (ge + lt) / 2;

		sort(b, a, ge, mid);
		sort(b, a, mid, lt);
		merge(a, b, ge, lt, mid);
		
//		System.out.println(" " + ge + " " + mid + " " + lt);
//		print(a);
	}

	private void merge(T[] a, T[] b, int ge, int lt, int mid) {
		int i = ge;
		int j = mid;

		for (int k = ge; k < lt; k++) {
			if (i >= mid) {
				b[k] = a[j++];
			} else if (j >= lt) {
				b[k] = a[i++];
			} else {
				if (a[i].compareTo(a[j]) <= 0) {
					b[k] = a[i++];
				} else {
					b[k] = a[j++];
				}
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

		MergeSorted<String> ms = new MergeSorted<String>(a);

		print(ms.sorted);
	}
}
