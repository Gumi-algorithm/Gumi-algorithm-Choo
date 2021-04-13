import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_12852_1로만들기2 {
	static int mem[] =new int[1000001];
	static int back[] = new int[1000001];
	
	static int dp(int num) {
		if(num==1)
			return 0;
		if(mem[num]!=0)
			return mem[num];
		
		int tmp=dp(num-1);
		back[num]=1;
		if(num%2==0) {
			int tmp2=dp(num/2);
			if(tmp>tmp2) {
				tmp=tmp2;
				back[num]=2;
			}
			
		}
		if(num%3==0) {
			int tmp2=dp(num/3);
			if(tmp>tmp2) {
				tmp=tmp2;
				back[num]=3;
			}
		}
		mem[num]=tmp+1;
		return mem[num];
	}
		
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int n=Integer.parseInt(br.readLine());
		
		mem[1]=0;
		
		int ans=dp(n);
		pw.println(ans);
		
		pw.print(n+" ");
		while(n!=1) {			
			if(back[n]==3) {
				n=n/3;
			}else if(back[n]==2) {
				n=n/2;
			}else
				n=n-1;
			pw.print(n+" ");
		}
		
		br.close();
		pw.flush();
		pw.close();
	}
}