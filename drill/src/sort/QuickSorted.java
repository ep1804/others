package sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

// without shuffle

public class QuickSorted<T extends Comparable<T>> {

	public T[] arr;

	public QuickSorted(T[] a) {
		// TODO
	}

	// TODO private void sort

	// TODO private void exch

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
