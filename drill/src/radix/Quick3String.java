package radix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quick3String {

	public static void sort(String[] a) {
		print(a);		

		int N = a.length;
		
		int[] id = new int[N];
		for(int i=0; i<N; i++)
			id[i] = i;

		sort(a, id, 0, N - 1, 0);
		
		print(a);
		print(id);
	}

	private static void sort(String[] a, int[] id, int lo, int hi, int d) {

		if (lo >= hi)
			return;

		int pvt = charAt(a[lo], d);
		int lt = lo;
		int gt = hi;

		int i = lo + 1;
		while (i <= gt) {
			int cmp = charAt(a[i], d) - pvt;
			if (cmp < 0)
				exch(a, id, i++, lt++);
			else if (cmp > 0)
				exch(a, id, i, gt--);
			else
				i++;
		}

		sort(a, id, lo, lt - 1, d);
		if(pvt >= 0) sort(a, id, lt, gt, d + 1);
		sort(a, id, gt + 1, hi, d);
	}

	private static void exch(String[] a, int[] id, int i, int j) {
		String tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
		
		int t = id[i];
		id[i] = id[j];
		id[j] = t;
	}

	private static int charAt(String s, int i) {
		if (i < s.length())
			return s.charAt(i);
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
		Scanner in = new Scanner(new File("input/radix/msd"));

		List<String> ws1 = new ArrayList<String>();

		while (in.hasNextLine()) {
			ws1.add(in.nextLine());
		}

		String[] ws = new String[ws1.size()];
		ws1.toArray(ws);
		sort(ws);

		in.close();
	}
}
