package sort;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class QuickSort {

	public QuickSort() {
		// TODO Auto-generated constructor stub
	}

	public static void exch(Comparable[] a, int x, int y){
		Comparable tmp = a[x];
		a[x] = a[y];
		a[y] = tmp;
	}
	
	public static void sort(Comparable[] a, int lo, int hi){
		if(lo >= hi) return;
		
		Comparable pvt = a[lo];
		int lt = lo;
		int gt = hi;
		
		for(int i=lt; i<=gt; i++){
			int cmp = a[i].compareTo(pvt);
			
			if(cmp < 0) exch(a, i, lt++);
			else if(cmp > 0) exch(a, i--, gt--);
		}
		
		sort(a, lo, lt - 1);
		sort(a, gt + 1, hi);
	}
	
	public static void print(String[] a){
		System.out.print(a[0]);
		for(int i=1; i<a.length; i++)
			System.out.print(" " + a[i]);
		System.out.println();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/sort/string1"));
		
		String[] ws = in.nextLine().trim().split("\\s+");
		print(ws);
		
		sort(ws, 0, ws.length - 1);
		print(ws);
		
		in.close();		
	}

}
