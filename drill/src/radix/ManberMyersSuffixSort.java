package radix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ManberMyersSuffixSort {

	public ManberMyersSuffixSort() {
	}
	
	private static final int R = 256;	
	
	public static void suffixSort(String s){
		int N = s.length();
		Integer[] id = new Integer[s.length()];
		for(int i=0; i<N; i++)
			id[i] = i;
		
		print(id);
		
		System.out.println("count sort up to first char:");
		
		int[] count = new int[R+1];
		int[] idAux = new int[N];
		
		for(int i=0; i<N; i++)
			count[s.charAt(i) + 1]++; // first char of i-th suffix of s
		for(int i=0; i<R; i++)
			count[i+1] += count[i];
		for(int i=0; i<N; i++)
			idAux[count[s.charAt(i)]++] = id[i];
		for(int i=0; i<N; i++)
			id[i] = idAux[i];
		
		print(id);
		printSuffixes(s, id);
		
		suffixSort(s, id, 0, count[0] - 1, 1);
		for(int r=1; r<R; r++)
			suffixSort(s, id, count[r-1], count[r] - 1, 1);
		
		print(id);
		printSuffixes(s, id);		
	}
	
	private static void suffixSort(String s, Integer[] id, int lo, int hi, int d) {
		
		if(lo >= hi) return;
		
		System.out.println("suffixSort(" + lo + "~" + hi + ", d=" + d + ")");
		
		int N = id.length;
		int[] idIdx = new int[N];
		for(int i=0; i<N; i++)
			idIdx[id[i]] = i;
		
		print(idIdx);

		Arrays.sort(id, lo, hi + 1, new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) {
				int cmp = idIdx[o1 + d] - idIdx[o2 + d];
				if(cmp < 0) return -1;
				else if(cmp > 0) return 1;
				else return 0;
			}});
		
		print(id);
		printSuffixes(s, id);

		// grouping next phase
		int[] group = new int[hi - lo + 1];
		
		if(s.length() <= id[lo] + 2 * d)
			group[0] = -1;
		else
			group[0] = 0;
		
		for(int i=lo+1; i<=hi; i++){
			if(s.length() <= id[i] + 2 * d)
				group[i - lo] = -1;
			else{
				if(s.length() <= id[i-1] + 2 * d)
					group[i - lo] = group[i-1 - lo] + 1;
				else if(s.substring(id[i] + d, id[i] + d * 2).equals(s.substring(id[i-1] + d, id[i-1] + d * 2)))
					group[i - lo] = group[i-1 - lo];
				else
					group[i - lo] = group[i-1 - lo] + 1;
			}
		}
		System.out.println("grouping:");
		print(group);
		
		int g = 0;
		int nextLo = -1;
		int nextHi = -1;
		for(int i=lo; i<=hi; i++){
			if(group[i-lo] == -1) continue;

			if(group[i-lo] == g){
				if(nextLo == -1)
					nextLo = i;
				nextHi = i;				
			}else{ // g + 1
				System.out.println("next run: suffixSort(" + nextLo + "~" + nextHi + ", d=" + d * 2 + ")");
				suffixSort(s, id, nextLo, nextHi, d * 2);
				nextLo = -1;
				g++;
				i--;
			}			
		}
		if(group[group.length-1] != -1){
			System.out.println("next run: suffixSort(" + nextLo + "~" + nextHi + ", d=" + d * 2 + ")");
			suffixSort(s, id, nextLo, nextHi, d * 2);
		}
	}

	public static void print(Integer[] a){
		for(int i=0; i<a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}

	public static void print(int[] a){
		for(int i=0; i<a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}

	public static void printSuffixes(String s, Integer[] id){
		for(int i : id){
			System.out.println(s.substring(i));
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/radix/suffix"));

		String s = in.next(".+");
		suffixSort(s);
		
		in.close();

	}

}
