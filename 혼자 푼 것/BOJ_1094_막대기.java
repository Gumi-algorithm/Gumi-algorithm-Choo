import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_1094_막대기 {
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);

		int x=Integer.parseInt(br.readLine());
		int now=64;
		int min=64;
		int ans=1;
		
		while(now>x) {
			
			min=min/2;
			
			if(now-min >= x) {
				now=now-min;
			}else
				ans++;
		}
				
		pw.print(ans);
		br.close();
		pw.close();
	}
}