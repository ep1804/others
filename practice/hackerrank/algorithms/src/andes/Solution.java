package andes;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Solution {

	public Solution() {
		
	}

	public static String toStr(int[] a){
		StringBuilder sb = new StringBuilder();
		int len = a.length;
		if (len > 0)
			sb.append(a[0]);
		else
			return "";
		for (int i = 1; i < len; i++)
			sb.append(' ').append(a[i]);
		return sb.toString();
	}

	public static String toStr(double[] a){
		StringBuilder sb = new StringBuilder();
		int len = a.length;
		if (len > 0)
			sb.append(a[0]);
		else
			return "";
		for (int i = 1; i < len; i++)
			sb.append(' ').append(a[i]);
		return sb.toString();
	}
	
	//
	// Canonical approach to solve this problem is Linear Programming,
	// But due to shortage of time to implement the Simplex algorithm, I resorted to a heuristic.
	//
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		int N = in.nextInt();
		int B = in.nextInt();
		int[] a = new int[N];
		
		long aSum = 0;
		
		for(int i=0; i<N; i++){
			int ai = in.nextInt();
			a[i] = ai;
			aSum += ai;
		}		  
		in.close();

		//System.out.println(toStr(a));
		
		// Declare buffers
		int[] b = new int[N];             // clinics at each city
		double[] cover = new double[N];   // avg. number of citizens covered by a clinic at each city 
		int sumB = 0;                     // sum of b

		// Build b near to optimal population cover
		double opt = (double)aSum / (double)B;
		for(int i=0; i<N; i++){
			int bi = Math.max(1,  (int) Math.floor((double)a[i] / opt));
			b[i] = bi;
			cover[i] = (double)a[i] / (double)bi;
			sumB += bi;
		}

		//System.out.println(toStr(b));
		//System.out.println(toStr(cover));

		// Modify b while reducing max of cover numbers
		
		if(sumB < B){
			PriorityQueue<Integer> q = new PriorityQueue<Integer>(N, new Comparator<Integer>(){
				@Override
				public int compare(Integer a0, Integer a1) {
					double cmp = cover[a1] - cover[a0]; 
					if(cmp > 0) return 1;
					else if(cmp < 0) return -1;
					else return 0;
				}});
			
			for(int i=0; i<N; i++)
				q.add(i);
			
			for(int i=sumB; i<B; i++){
				int c = q.remove();
				b[c]++;
				cover[c] = (double)a[c] / (double)b[c];
				q.add(c);
			}

			//System.out.println(toStr(b));
			//System.out.println(toStr(cover));
			System.out.println((int)Math.ceil(cover[q.remove()]));
			
		}else if(sumB > B){
			PriorityQueue<Integer> q = new PriorityQueue<Integer>(N, new Comparator<Integer>(){
				@Override
				public int compare(Integer a0, Integer a1) {
					double cmp = cover[a0] - cover[a1]; 
					if(cmp > 0) return 1;
					else if(cmp < 0) return -1;
					else{
						int cmp2 = b[a1] - b[a0];
						if(cmp2 > 0) return 1;
						else if(cmp2 < 0) return -1;
						else return 0;
					}	
				}});
			
			for(int i=0; i<N; i++)
				q.add(i);

			for(int i=sumB; i>B; i--){
				int c = q.remove();
				b[c]--;
				cover[c] = (double)a[c] / (double)b[c];
				q.add(c);
			}
			
			//System.out.println(toStr(b));
			//System.out.println(toStr(cover));
			
			Arrays.sort(cover);
			System.out.println((int)cover[cover.length - 1]);
		}else{
			Arrays.sort(cover);
			System.out.println((int)cover[cover.length - 1]);
		}

	}

}
