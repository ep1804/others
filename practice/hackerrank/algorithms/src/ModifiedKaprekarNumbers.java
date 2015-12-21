import java.util.Scanner;

public class ModifiedKaprekarNumbers {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		int p = in.nextInt();
		int q = in.nextInt();		
		in.close();
		
		for(long i=p; i<=q; i++){
			long d = String.valueOf(i).length();
			long i2 = i * i;
			long divisor = (long)Math.pow(10, d);
			long r = i2 % divisor;
			long l = i2 / divisor;
			if(r + l == i) System.out.print(i + " ");
		}
	}

}
