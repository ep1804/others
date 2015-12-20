import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class BigDecimalTest {

	public BigDecimalTest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		String[] s = new String[n + 2];
		for (int i = 0; i < n; i++) {
			s[i] = in.next();			
		}
		in.close();

		Integer[] id = new Integer[n];
		for(int i=0; i<n; i++)
			id[i] = i;
		
		Arrays.sort(id, new Comparator<Integer>(){

			@Override
			public int compare(Integer o1, Integer o2) {
				
				BigDecimal d1 = new BigDecimal(s[o1]);
				BigDecimal d2 = new BigDecimal(s[o2]);
				int cmp = d1.compareTo(d2); 
				if(cmp < 0) return 1;
				else if(cmp > 0) return -1;
				else{
					if( id[o1] < id[o2] ) return -1;
					else return 1;
				}
			}});

		for (int i = 0; i < n; i++) {
			System.out.println(s[id[i]]);
		}
	}

}
