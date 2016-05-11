package sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

@SuppressWarnings("rawtypes")
public class MergeSorted<T extends Comparable<T>> {

	public T[] arr;

	@SuppressWarnings("unchecked")
	public MergeSorted(T[] a) {
		// TODO
	}
	
	// TODO private void sort

	// TODO private void merge

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

		print(ms.arr);
	}
}
