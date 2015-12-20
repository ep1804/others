package radix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MSD {

	private static final int R = 256;	
	
	public static void sort(String[] a) {
		print(a);
		String[] aux = new String[a.length];
		
		int[] id = new int[a.length];
		for(int i=0; i<id.length; i++)
			id[i] = i;
		int[] idAux = new int[a.length];
		
		
		sort(a, aux, id, idAux, 0, a.length - 1, 0);
		
		print(a);
		print(id);
	}
	
	private static void sort(String[] a, String[] aux, int[] id, int[] idAux, int lo, int hi, int d) {
		
		if(lo >= hi) return;
		
		int[] count = new int[R + 2];
		
		for(int i = lo; i <= hi; i++)
			count[charAt(a[i], d) + 2]++;
		for(int i = 0; i<R+1; i++)
			count[i+1] += count[i];
		for(int i = lo; i <= hi; i++){
			int i2 = count[charAt(a[i], d) + 1]++;
			idAux[i2 + lo] = id[i];
			aux[i2 + lo] = a[i];
		}
		for(int i = lo; i <= hi; i++){
			a[i] = aux[i];
			id[i] = idAux[i];
		}
		
		for(int i=0; i<R; i++)
			sort(a, aux, id, idAux, lo + count[i], lo + count[i+1] - 1, d+1);
	}	

	private static int charAt(String s, int i){
		if(i < s.length())
			return s.charAt(i);
		else
			return -1;
	}
	
	public static void print(String[] a){
		for(int i=0; i<a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}

	public static void print(int[] a){
		for(int i=0; i<a.length; i++)
			System.out.print(a[i] + " ");
		System.out.println();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File("input/radix/msd"));
		
		List<String> ws1 = new ArrayList<String>();
		
		while(in.hasNextLine()){
			ws1.add(in.nextLine());
		}
		
		String[] ws = new String[ws1.size()];
		ws1.toArray(ws);
		sort(ws);
		
		in.close();
	}
}

