import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_11401_이항계수3 {
	static int mod=1000000007;
	
	//O(lgn)
	static long _pow(long a,long b) {
		long ret=1;
		while(b>0) {
			if(b%2==1)
				ret=(ret*a)%mod;
			b=b>>1;
			a=(a*a)%mod;
		}
		return ret;
	}
	
	static long combination(int n,int r) {
		long[] fac=new long[n+1];
		fac[0]=1;
		//팩토리얼 구함 (mod연산 되잇는걸로)
		for(int i=1;i<=n;i++) {
			fac[i]=(fac[i-1]*i)%mod;
		}
		
		return fac[n]* _pow(fac[r],mod-2)%mod * _pow(fac[n-r],mod-2)%mod;		
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		long ans=0;
		int n,r;
		String[] str=br.readLine().split(" ");
		n=Integer.parseInt(str[0]);
		r=Integer.parseInt(str[1]);
		
		ans=combination(n,r);

		
		pw.print(ans);
		pw.flush();
		br.close();
		pw.close();	
	}
}