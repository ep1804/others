package radix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CountSort{

	private static final int R = 256;
	
	public static void sort(String[] a) {
		print(a);		
		
		int[] count = new int[R+1];
		String[] aux = new String[a.length];
		
		for(int i=0; i<a.length; i++)
			count[a[i].charAt(0) + 1]++;
		for(int i=0; i<R; i++)
			count[i+1] += count[i];
		for(int i=0; i<a.length; i++)
			aux[count[a[i].charAt(0)]++] = a[i];
		for(int i=0; i<a.length; i++)
			a[i] = aux[i];
		
		print(a);
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
		Scanner in = new Scanner(new File("input/radix/count"));
		
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
