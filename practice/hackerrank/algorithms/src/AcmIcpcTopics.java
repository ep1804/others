import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class AcmIcpcTopics {

	public AcmIcpcTopics() {
		// TODO Auto-generated constructor stub
	}
	
	public static int sumKnowledge(String s1, String s2){
		int count = 0;
		for(int i=0; i<s1.length(); i++)
			if(s1.charAt(i) == '1' || s2.charAt(i) == '1')
				count ++;
		return count;
	}
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		in.nextInt();
		
		String[] k = new String[N]; // knowledge of persons
		
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(new Comparator<Integer>(){
			@Override
			public int compare(Integer a0, Integer a1) {
				return a1 - a0;
			}});
		
		for(int n=0; n<N; n++)
			k[n] = in.next(".+");

		for(int i=0; i<N; i++){
			for(int j=0; j<i; j++){
				q.add(sumKnowledge(k[i], k[j]));
			}
		}
		
		int max = q.remove();
		int numMax = 1;
		while(!q.isEmpty()){
			if(q.remove() == max)
				numMax ++;
			else
				break;
		}
		System.out.println(max);
		System.out.println(numMax);
		
		in.close();		
	}

}
