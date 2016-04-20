package radix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Quick3StringSorted {

	public String[] arr;

	public Quick3StringSorted(String[] a) {
		arr = Arrays.copyOf(a, a.length);

		sort(0, a.length, 0);
	}

	private void sort(int lo, int hi, int d) {
		if (lo >= hi)
			return;

		int pvt = charAt(arr[lo], d);

		int le = lo;
		int ge = hi;

		for (int i = lo + 1; i < ge; i++) {
			int cmp = charAt(arr[i], d) - pvt;

			if (cmp < 0) {
				exch(i, ++le);
			} else if (cmp > 0) {
				exch(i, --ge);
				i--;
			}
		}
		exch(lo, le);

		sort(lo, le, d);
		if (pvt > 0)
			sort(le, ge, d + 1);
		sort(ge, hi, d);
	}

	private void exch(int i, int j) {
		if (i == j)
			return;
		String tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	private int charAt(String s, int at) {
		if (at < s.length())
			return s.charAt(at);
		else
			return -1;
	}

	public static void print(String[] a) {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}

	public static void print(int[] a) {
		for (int i = 0; i < a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}

	public static void main(String[] args) throws FileNotFoundException {
		List<String> buf = new LinkedList<String>();
		Scanner in = new Scanner(new File("input/radix/msd"));
		while (in.hasNextLine())
			buf.add(in.nextLine());
		in.close();

		String[] a = new String[buf.size()];
		buf.toArray(a);

		print(a);

		Quick3StringSorted sorted = new Quick3StringSorted(a);

		print(sorted.arr);

	}
}
