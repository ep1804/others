import java.util.Scanner;

public class Encryption {

	public Encryption() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);		
		String s = in.next(".+");
		in.close();
		
		int L = s.length();
		double rootL = Math.sqrt((double)L);
		int loL = (int) Math.floor(rootL);
		int hiL = (int) Math.ceil(rootL);
		
		int R, C;
		if(loL == hiL){
			R = loL;
			C = loL;
		}else{
			if(loL * hiL >= L){
				R = loL;
				C = hiL;
			}else{
				R = hiL;
				C = hiL;
			}
		}
		
		//System.out.println(r + " " + c);
		
		StringBuilder sb = new StringBuilder();
		for(int c=0; c<C; c++){
			for(int r=0; r<R; r++){
				int idx = r * C + c;
				if(idx < L)
					sb.append(s.charAt(idx));
			}
			sb.append(' ');
		}
		System.out.println(sb.toString());
		


	}

}
