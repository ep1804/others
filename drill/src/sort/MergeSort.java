package sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@SuppressWarnings("rawtypes")
public class MergeSort {

	public static void sort(Comparable[] a) {
	}

	public static void sort(Comparable[] a, Comparable[] buf, int lo, int hi) {
		
	}

	private static void merge(Comparable[] from, Comparable[] to, int lo, int mid, int hi) {
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
