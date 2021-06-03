import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_1629_곱셈 {

	static long cal(long a, long b,long c) {
		if(b==1) {
			return a%c; 
		}
		
		
		
		long mid=b/2;
		
		long val=cal(a,mid,c);
		
		if(b%2==0) {
			val= (val%c * val%c)%c;
		}else
			val= (val%c * (val%c*a%c)%c)%c;
		
		
		return val;
	}
	
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		long a,b,c;
		
		String[] str=br.readLine().split(" ");
		
		a=Integer.parseInt(str[0]);
		b=Integer.parseInt(str[1]);
		c=Integer.parseInt(str[2]);
		
		long ans=cal(a,b,c);
		
		
		
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}