import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;

public class BOJ_2407_조합 {
	
	static BigInteger[][] num;
	
	static BigInteger combination(int n,int r) {
		
		if(num[n][r]!=null)
			return num[n][r];
		
		if(r==1 || n-r==1) {			
			return BigInteger.valueOf(n);
		}
		if(n==r) {			
			return BigInteger.valueOf(0);
		}
		num[n][r]=combination(n-1, r).add(combination(n-1, r-1));
		return num[n][r];
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		
		int n,m;
		String[] str=br.readLine().split(" ");
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		
		num=new BigInteger[n+1][m+1];
		
		pw.print(combination(n, m));
		
		br.close();
		pw.flush();
		pw.close();
	}
}