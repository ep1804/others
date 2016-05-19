package sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

@SuppressWarnings("rawtypes")
public class MergeSorted<T extends Comparable<T>> {

	public T[] arr;
	private T[] buf;

	@SuppressWarnings("unchecked")
	public MergeSorted(T[] a) {
		arr = Arrays.copyOf(a, a.length);
		buf = (T[]) new Comparable[a.length];

		sort(0, arr.length);
	}

	private void sort(int from, int until) {
		if (until - from <= 1)
			return;

		int mid = (from + until) / 2;
		sort(from, mid);
		sort(mid, until);
		merge(from, mid, until);
	}

	private void merge(int from, int mid, int until) {
		int i1 = from;
		int i2 = mid;

		for (int i = from; i < until; i++) {
			if (i1 >= mid)
				buf[i] = arr[i2++];
			else if (i2 >= until)
				buf[i] = arr[i1++];
			else {
				int cmp = arr[i1].compareTo(arr[i2]);
				if (cmp < 0)
					buf[i] = arr[i1++];
				else if (cmp > 0)
					buf[i] = arr[i2++];
				else
					buf[i] = arr[i1++];
			}
		}

		for (int i = from; i < until; i++)
			arr[i] = buf[i];

	}

	public static void print(Comparable[] a) {
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

		MergeSorted<String> ms = new MergeSorted<String>(a);

		print(ms.arr);
	}
}
