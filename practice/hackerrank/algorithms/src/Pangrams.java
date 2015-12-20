import java.util.Scanner;

public class Pangrams {

	public Pangrams() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);		
		String s = in.nextLine().toUpperCase();
		in.close();
		
		int N = s.length();
		
		boolean[] marked = new boolean[26];
		int alphabetCount = 0;
		
		boolean pan = false;
		
		for(int i=0; i<N; i++){
			int c = s.charAt(i) - 'A';
			if(c >= 0 && c < 26){
				if(! marked[c]){
					marked[c] = true;
					alphabetCount ++;
					if(alphabetCount >= 26){
						pan = true;
						break;
					}
				}
			}			
		}
		
		if(pan)
			System.out.println("pangram");
		else
			System.out.println("not pangram");
	}

}
