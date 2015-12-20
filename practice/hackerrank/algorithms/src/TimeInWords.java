import java.util.Scanner;

public class TimeInWords {

	public TimeInWords() {
		// TODO Auto-generated constructor stub
	}

	public static final String[] word = { 
				"", "one", "two", "three", "four", "five", 
				"six", "seven", "eight", "nine", "ten", 
				"eleven", "twelve", "thirteen", "fourteen", "fifteen", 
				"sixteen", "seventeen", "eighteen", "nineteen", "twenty",
				"twenty one", "twenty two", "twenty three", "twenty four", "twenty five", 
				"twenty six", "twenty seven", "twenty eight", "twenty nine"  
			};
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int H = in.nextInt();
		int M = in.nextInt();

		in.close();
		
		if(M == 0)
			System.out.println(word[H] + " o'clock");
		else if(M == 15)
			System.out.println("quarter past " + word[H]);
		else if(M == 30)
			System.out.println("half past " + word[H]);
		else if(M == 45)
			System.out.println("quarter to " + word[H + 1]);
		else if(M < 30)
			System.out.println(word[M] + " minutes past " + word[H]);
		else 
			System.out.println(word[60 - M] + " minutes to " + word[H + 1]);
	}

}
