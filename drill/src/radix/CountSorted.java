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
		// TODO
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
