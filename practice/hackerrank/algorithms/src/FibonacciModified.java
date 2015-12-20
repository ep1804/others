import java.math.BigInteger;
import java.util.Scanner;

public class FibonacciModified {

	public FibonacciModified() {
		// TODO Auto-generated constructor stub
	}

	public static BigInteger A, B;
	
	public static BigInteger fibonacci(int n){
		if(n == 1) return A;
		if(n == 2) return B;
		BigInteger a = fibonacci(n-2);
		BigInteger b = fibonacci(n-1);
		return b.multiply(b).add(a);		
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		A = BigInteger.valueOf(in.nextLong());
		B = BigInteger.valueOf(in.nextLong());
		int N = in.nextInt();
		
		System.out.println(fibonacci(N).toString());
		
		in.close();
	}

}
