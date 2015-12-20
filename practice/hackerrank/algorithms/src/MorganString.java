import java.util.Scanner;

public class MorganString {

	public MorganString() {
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		int T = in.nextInt();
		for(int t=0; t<T; t++){
			String s1 = in.next(".+");
			String s2 = in.next(".+");
			
			StringBuilder sb = new StringBuilder();			
			int i=0;			
			int j=0;
			while(i < s1.length() && j < s2.length()){
				char c1 = s1.charAt(i);
				char c2 = s2.charAt(j);
				if(c1 < c2){
					sb.append(c1);
					i++;
				}else if(c1 > c2){
					sb.append(c2);
					j++;
				}else{
					// handling consecutive undecidables
					int cut = 0;
					
					for(int k=1; ;k++){
						if(i + k >= s1.length()){
							if(cut != 0){
								String ss1 = s1.substring(i, i+cut);
								String ss2 = s1.substring(i+cut, i+k);
								if((ss1.compareTo(ss2) < 0 && ss2.indexOf(ss1) != 0) || (ss1.compareTo(ss2) > 0 && ss1.indexOf(ss2) == 0)){
									sb.append(ss1).append(ss1);
									i += cut;
									j += cut;
									k -= cut;
								}
							}
							sb.append(s2.substring(j, j+k));
							j += k;
							break;
						}else if(j + k >= s2.length()){
							if(cut != 0){
								String ss1 = s1.substring(i, i+cut);
								String ss2 = s1.substring(i+cut, i+k);
								if((ss1.compareTo(ss2) < 0 && ss2.indexOf(ss1) != 0) || (ss1.compareTo(ss2) > 0 && ss1.indexOf(ss2) == 0)){
									sb.append(ss1).append(ss1);
									i += cut;
									j += cut;
									k -= cut;									
								}
							}
							sb.append(s1.substring(i, i+k));
							i += k;
							break;
						}else{
							int cmp = s1.charAt(i+k) - s2.charAt(j+k);
							if(cmp < 0){
								sb.append(s1.substring(i, i+k+1));
								i += k + 1;
								break;						
							}else if(cmp > 0){
								sb.append(s2.substring(j, j+k+1));
								j += k + 1;									
								break;
							}else{
								int cmp2 = s1.charAt(i+k) - s1.charAt(i); 
								if(cmp2 < 0)
									continue;
								else if(cmp2 == 0){
									if(cut == 0)
										cut = k;
									else{
										String ss1 = s1.substring(i, i+cut);
										String ss2 = s1.substring(i+cut, i+k);
										if((ss1.compareTo(ss2) < 0 && ss2.indexOf(ss1) != 0) || (ss1.compareTo(ss2) > 0 && ss1.indexOf(ss2) == 0)){
											sb.append(ss1).append(ss1);
											i += cut;
											j += cut;
											k -= cut;
											cut = k;
										}else{
											cut = k;
										}
									}
									continue;
								}else{
									String st = s1.substring(i, i+k);
									sb.append(st).append(st);
									i += k;
									j += k;
									break;
								}
							}
						}
					}
				}
			}
			if(i < s1.length()){
				sb.append(s1.substring(i));
			}else if(j < s2.length()){
				sb.append(s2.substring(j));
			}
			
			System.out.println(sb.toString());
		}
		
		in.close();
	}

}
