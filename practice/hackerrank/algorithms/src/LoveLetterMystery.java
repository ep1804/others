import java.util.Scanner;

public class LoveLetterMystery {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int T = in.nextInt();
		
		for(int t=0; t<T; t++){
			String s = in.next(".+");
			
			int len = s.length();			
			int hLen = len / 2;
			
			int count = 0;
			for(int i=0; i<hLen; i++){
				count += (int) Math.abs(s.charAt(i) - s.charAt(len - i - 1));
			}
			
			System.out.println(count);
		}
		
		in.close();
	}

}
