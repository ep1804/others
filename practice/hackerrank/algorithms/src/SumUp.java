import java.util.Scanner;

public class SumUp {

	public SumUp() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		
		int sum = 0;
		for(int n=0; n<N; n++){
			sum += in.nextInt();
		}
		System.out.println(sum);
		
		in.close();

	}

}
