import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_20207_달력 {	
	public static void main(String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int n=Integer.parseInt(br.readLine());
		
		int[] arr=new int[367];
		for(int i=0;i<n;i++) {
			String[] str=br.readLine().split(" ");
			int a=Integer.parseInt(str[0]);
			int b=Integer.parseInt(str[1])+1;//시작일, 종료일이 같은경우가 있어서 +1
			
			arr[a]++;//시작일자는 배열에 ++
			arr[b]--;//종료일자는 배열에 --
		}
		
		int max=0;
		int length=0;
		int now=0;
		int ans=0;
		for(int i=1;i<=366;i++) {
			now+=arr[i];
			max=Math.max(max, now);
			if(now>0)
				length++;
			else if(now==0) {
				ans+=max*length;
				length=0;
				max=0;
			}
		}
		
		pw.print(ans);
		pw.flush();
		pw.close();
		br.close();
	}
}