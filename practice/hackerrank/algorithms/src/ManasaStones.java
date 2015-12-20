import java.util.PriorityQueue;
import java.util.Scanner;

public class ManasaStones {

	public ManasaStones() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		
		int T = in.nextInt();
		for(int t=0; t<T; t++){
			int n = in.nextInt() - 1;
			int a = in.nextInt();
			int b = in.nextInt();
			
			q.clear();
			for(int i=0; i<=n; i++){
				int num = a * i + b * (n-i);
				if(!q.contains(num))
					q.add(num);
			}
			
			while(!q.isEmpty()){
				System.out.print(q.remove() + " ");
			}
			System.out.println();
			
		}
		
		in.close();
	}

}
