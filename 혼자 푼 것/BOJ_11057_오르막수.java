import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_11057_오르막수 {

	static int[][] mem;
	
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n=Integer.parseInt(br.readLine());
		
		mem=new int[n+1][10];//n자리수 , 가장 왼쪽 숫자
		
		for(int i=0;i<10;i++) {
			mem[1][i]=1;
		}
	
		for(int i=1;i<=n;i++) {//n자리수
			for(int j=0;j<10;j++) {//가장 왼쪽 숫자
				for(int k=j;k<10;k++) {
					mem[i][j]= (mem[i][j] + mem[i-1][k])%10007;
				}
			}
		}
		int ans=0;
		for(int i=0;i<10;i++) {
			ans= (ans+ mem[n][i])%10007;
		}
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}