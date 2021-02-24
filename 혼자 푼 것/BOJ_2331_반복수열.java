import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class BOJ_2331_반복수열 {

	static Set<Integer> s=new HashSet<>();
	
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		
		String[] str=br.readLine().split(" ");
		int a=Integer.parseInt(str[0]);
		int p=Integer.parseInt(str[1]);
		int b=0;
		int state=0;
		s.add(a);
		
		while(true) {	
			b=0;
			while(a>0) {
				b+=Math.pow(a%10,p);
				a=a/10;			
			}
			a=b;
			
			if(s.contains(a)) {
				s.remove(a);
				state=1;
			}else {
				if(state==1)
					break;
				s.add(a);				
			}
		}
		pw.print(s.size());
		br.close();
		pw.flush();
		pw.close();
	}
}