package radix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CountSorted {

	private static final int R = 256;

	public String[] arr;

	public CountSorted(String[] a) {
		arr = new String[a.length];

		int[] count = new int[R + 1];

		for (int i = 0; i < a.length; i++)
			count[a[i].charAt(0) + 1]++;

		for (int i = 0; i < R; i++)
			count[i + 1] += count[i];
		
		for(int i=0; i<a.length; i++)
			arr[count[a[i].charAt(0)]++] = a[i];
	}

	public static <T> void print(T[] count) {
		for (int i = 0; i < count.length; i++)
			System.out.print(count[i] + " ");
		System.out.println();
	}

	public static void main(String[] args) throws FileNotFoundException {
		List<String> ws0 = new ArrayList<String>();
		Scanner in = new Scanner(new File("input/radix/count"));
		while (in.hasNextLine()) {
			ws0.add(in.nextLine().trim());
		}
		in.close();

		String[] ws = ws0.toArray(new String[ws0.size()]);

		print(ws);

		CountSorted sorted = new CountSorted(ws);

		print(sorted.arr);

	}

}
