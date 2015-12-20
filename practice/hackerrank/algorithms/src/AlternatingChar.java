import java.util.Scanner;

public class AlternatingChar {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int T = in.nextInt();
		
		for(int t=0; t<T; t++){
			String s = in.next(".+");
			
			int sum = 0;
			int len = s.length();
			
			for(int i=1; i<len; i++)
				if(s.charAt(i-1) == s.charAt(i)) sum++;
			
			System.out.println(sum);
		}
		
		in.close();
	}

}
