import java.util.Scanner;

public class StairCase {

	public StairCase() {
		// TODO Auto-generated constructor stub
	}

	public static <T> String toStr(T[] a){
		StringBuilder sb = new StringBuilder();
		int len = a.length;
		if (len > 0)
			sb.append(a[0]);
		else
			return "";
		for (int i = 1; i < len; i++)
			sb.append(' ').append(a[i]);
		return sb.toString();
	}
	
	public static String repeat(char c, int times){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<times; i++)
			sb.append(c);
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int N = in.nextInt();
		
		for(int i=0; i<N; i++){
			System.out.print(repeat(' ', N - i - 1));
			System.out.print(repeat('#', i + 1));
			System.out.println();
		}
		
		in.close();
	}

}
