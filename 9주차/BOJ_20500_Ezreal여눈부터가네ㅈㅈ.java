import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

//(a+b)%m =((a%m)+(b%m))%m
//(a*b)%m =((a%m)*(b%m))%m


public class BOJ_20500_Ezreal여눈부터가네ㅈㅈ {
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb=new StringBuilder();		
		
		int mod=1000000007;
		
		/*
		//1,5로 이루어 져잇으면서 15의 배수를 출력하는 거
		for(long i=0;i<1000000000;i++) {
			long now=15*i;
			long tmp=now;
			int end=0;
			while(now>0) {
				int a=(int)(now%10);
				now=now/10;
				if(a!=1 && a!=5) {
					end=1;
					break;
				}					
			}
			if(end==1)
				continue;
			sb.append(tmp).append("\n");			
		}
		pw.print(sb);
		*/
		
		/*
		//입력받은거 몇개인지 카운트 하는애
		String str;
		int cnt=0;
		while((str=br.readLine())!=null) {
			if(str.equals("0"))
				break;
			cnt++;
		}
		pw.print(cnt);
		*/
		
		int n=Integer.parseInt(br.readLine());
		long mem[]=new long[n+3];
		mem[0]=0;
		mem[1]=0;
		mem[2]=1;
		mem[3]=1;
		
		for(int i=4;i<=n;i++) {
			mem[i]=((mem[i-2]*2)%mod + mem[i-1])%mod;
		}
		pw.print(mem[n]);
		br.close();
		pw.flush();
		pw.close();
	}
}
/*
n자릿수 / 1,5로 이루어진 15의배수 갯수
2 1
3 1
4 3
5 5
6 11
7 21
8 43
9 85
10 171
n mem[n-2]*2 + mem[n-1]
*/