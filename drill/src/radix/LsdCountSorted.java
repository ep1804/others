package radix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LsdCountSorted {

	private final int R = 256;

	public String[] arr;

	public LsdCountSorted(String[] a) {
		arr = Arrays.copyOf(a, a.length);
		String[] buf = new String[arr.length];

		int len = a[0].length();
		int[] count = new int[R + 1];

		for (int d = len - 1; d >= 0; d--) {
			Arrays.fill(count, 0);
			for (int i = 0; i < arr.length; i++)
				count[arr[i].charAt(d) + 1]++;
			for (int i = 0; i < R; i++)
				count[i + 1] += count[i];
			for (int i = 0; i < arr.length; i++)
				buf[count[arr[i].charAt(d)]++] = arr[i];
			for (int i = 0; i < arr.length; i++)
				arr[i] = buf[i];
		}
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

		Scanner in = new Scanner(new File("input/radix/lsd"));
		while (in.hasNextLine()) {
			buf.add(in.nextLine());
		}
		in.close();

		String[] ws = new String[buf.size()];

		buf.toArray(ws);

		print(ws);

		LsdCountSorted sorted = new LsdCountSorted(ws);

		print(sorted.arr);
	}

}
