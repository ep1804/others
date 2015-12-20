package radix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LSD{

	private static final int R = 256;
	
	public static void sort(String[] a) {
		print(a);
		
		int N = a.length;
		int L = a[0].length();
		String[] aux = new String[N];
		int count[] = new int[R+1];
		
		int id[] = new int[N];
		for(int i=0; i<N; i++)
			id[i] = i;
		int idAux[] = new int[N];
		
		for(int d=L-1; d>=0; d--){
			Arrays.fill(count, 0);
			
			for(int i=0; i<N; i++)
				count[a[i].charAt(d) + 1]++;
			for(int i=0; i<R; i++)
				count[i+1] += count[i];
			for(int i=0; i<N; i++){
				int i2 = count[a[i].charAt(d)]++;
				aux[i2] = a[i];
				idAux[i2] = id[i];
			}			
			for(int i=0; i<N; i++){
				a[i] = aux[i];
				id[i] = idAux[i];
			}
		}
		
		print(a);
		print(id);
		
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
		Scanner in = new Scanner(new File("input/radix/lsd"));
		
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
