import java.util.Scanner;

public class Anagram {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int T = in.nextInt();
		
		for(int t=0; t<T; t++){
			String s = in.next(".+").toLowerCase();
			
			int len = s.length();
			
			if(len % 2 == 1){
				System.out.println("-1");
				continue;
			}
			
			int hLen = len/2;
			
			int[] count = new int[26];
			
			for(int i=0; i<hLen; i++)
				count[s.charAt(i) - 'a'] ++;
			
			int replace = 0;
			
			for(int i=hLen; i<len; i++){
				int c = s.charAt(i) - 'a';
				if(count[c] > 0)
					count[c] --;
				else
					replace ++;
			}
			System.out.println(replace);				
		}
		
		in.close();
	}

}
