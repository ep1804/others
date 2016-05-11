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
		// TODO
	}

	// TODO private void sort

	// TODO private void exch

	// TODO private int charAt
	
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
