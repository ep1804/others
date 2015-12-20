import java.util.Scanner;

public class PalindromeIndex {

	public PalindromeIndex() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int T = in.nextInt();
		for(int t=0; t<T; t++){
			String s = in.next(".+");
			
			boolean found = false;
			int len = s.length();
			
			for(int i=0; i<len/2; i++){
				char a = s.charAt(i);
				char b = s.charAt(len -1 - i);
				if(a != b){
					boolean aIsWrong = true;
					
					for(int j=i+1; j<len/2; j++)
						if(s.charAt(j) != s.charAt(len - j)){
							aIsWrong = false;
							break;
						}
					
					if(aIsWrong){
						System.out.println(i);						
					}else
						System.out.println(len - 1 - i);
					found = true;
					break;
				}
			}
			if(! found)
				System.out.println("-1");
		}
		
		in.close();

	}

}
