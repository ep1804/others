import java.util.Arrays;
import java.util.Scanner;

public class Gemstones {
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		boolean[] marked = new boolean[26];
		int[] appears = new int[26];
		
		for(int n=0; n<N; n++){
			
			String s = in.next(".+").toLowerCase();
			
			Arrays.fill(marked, false);
			
			int len = s.length();
			for(int i=0; i<len; i++){
				int c = s.charAt(i) - 'a';
				if(! marked[c]){
					marked[c] = true;
					appears[c] ++;
				}
			}
		}
		
		int count = 0;
		for(int i=0; i<26; i++){
			if(appears[i] == N)
				count++;
		}
		
		System.out.println(count);
		
		in.close();
	}
	
}
