import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_1912_연속합 {

	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw= new PrintWriter(System.out);
		
		int ans=0;
		int tmp=0;
		int pre=0;
		int n=Integer.parseInt(br.readLine());
		String[] str=br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			int now=Integer.parseInt(str[i]);
			if(i==0) {
				tmp=now;
				pre=now;
				ans=now;
				continue;
			}
			
			tmp=Math.max(tmp+now,pre+now);			
			ans=Math.max(Math.max(tmp, now), ans);
			pre=now;
		}
		
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}