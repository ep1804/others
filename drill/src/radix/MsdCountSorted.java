package radix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MsdCountSorted {

	private final int R = 256;

	public String[] arr;

	public MsdCountSorted(String[] a) {
		arr = Arrays.copyOf(a, a.length);
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
		while (in.hasNextLine()) {
			buf.add(in.nextLine());
		}
		in.close();

		String[] ws = new String[buf.size()];
		buf.toArray(ws);

		print(ws);

		MsdCountSorted sorted = new MsdCountSorted(ws);

		print(sorted.arr);
	}
}
