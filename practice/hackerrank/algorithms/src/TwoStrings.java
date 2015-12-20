import java.util.Arrays;
import java.util.Scanner;

public class TwoStrings {

	public TwoStrings() {
		
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int T = in.nextInt();
		
		boolean[] alphabet = new boolean[26];
		
		
		for(int t=0; t<T; t++){
			String s1 = in.next(".+");
			String s2 = in.next(".+");
			
			Arrays.fill(alphabet, false);
			
			for(int i=0; i<s1.length(); i++)
				alphabet[s1.charAt(i) - 'a'] = true;
			
			boolean found = false;
			
			for(int i=0; i<s2.length(); i++)
				if(alphabet[s2.charAt(i) - 'a'] == true){
					found = true;
					break;
				}
			
			if(found)
				System.out.println("YES");
			else
				System.out.println("NO");			
		}
		
		in.close();
	}

}
