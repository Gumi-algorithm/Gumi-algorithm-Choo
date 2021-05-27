import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_13302_리조트 {
	static int n,m;
	static int day[];
	static int mem[][];
	static int dfs(int nowday, int coupon) {
		
		if(nowday>=n) {			
			return 0;
		}
		//리조트 안가는날일경우
		if(day[nowday]==-1) {
			return dfs(nowday+1,coupon);
		}
		
		if(mem[nowday][coupon]!=0)
			return mem[nowday][coupon];
		
		//1일권
		int a=0;
		if(coupon>=3) {//1일권을 쿠폰으로 대체
			a=dfs(nowday+1,coupon-3);
		}else
			a=dfs(nowday+1,coupon)+10000;
	
		//3일권
		int b=dfs(nowday+3,coupon+1)+25000;

		//5일권
		int c=dfs(nowday+5,coupon+2)+37000;
	
		
		int min=Math.min(a, b);
		min=Math.min(min,c);
		mem[nowday][coupon]=min;
		
		return mem[nowday][coupon];
	}
	
	public static void main(String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw= new PrintWriter(System.out);
		
		String[] str=br.readLine().split(" ");		
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		
		day=new int[n];
		
		//n은 최대 100일이고 100일동안 얻는 쿠폰 최대량은 40임
		mem=new int[n][41];
		if(m>0) {
			str=br.readLine().split(" ");
			for(int i=0;i<m;i++) {
				int tmp=Integer.parseInt(str[i]);
				day[tmp-1]=-1;
			}
		}
		int ans=dfs(0,0);

		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();	
	}
}
