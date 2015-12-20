import java.util.Scanner;

public class CaesarCipher {

	public CaesarCipher() {
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		in.nextInt();
		char[] s = in.next(".+").toCharArray();
		int K = in.nextInt();
		
		for(int i=0; i<s.length; i++){
			
			if('a' <= s[i] && s[i] <= 'z'){
				System.out.print((char)('a' + (s[i] - 'a' + K) % 26));
			}else if('A' <= s[i] && s[i] <= 'Z'){
				System.out.print((char)('A' + (s[i] - 'A' + K) % 26));				
			}else{
				System.out.print(s[i]);
			}
		}
		
		System.out.println();
		
		in.close();
	}

}
