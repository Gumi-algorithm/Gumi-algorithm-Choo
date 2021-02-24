import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_9663_NQueen {
	static int[][] arr;//대각선
	static int[] isvisited;//열
	static int n;
	static int ans=0;
	static void func(int idx) {
		if(idx==n) {
			ans++;
			return;
		}
		for(int i=0;i<n;i++) {
			if(isvisited[i]!=0 || arr[idx][i]!=0)
				continue;
			isvisited[i]=idx+1;
			for(int j=1;j<n-idx;j++) {
				if(i+j<n)
					arr[j+idx][i+j]+=1;
				if(i-j>=0)
					arr[j+idx][i-j]+=1;
			}
			func(idx+1);
			isvisited[i]=0;
			for(int j=1;j<n-idx;j++) {
				if(i+j<n)
					arr[j+idx][i+j]-=1;
				if(i-j>=0)
					arr[j+idx][i-j]-=1;
			}
		}
		
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		
		n=Integer.parseInt(br.readLine());
		arr=new int[n][n];
		isvisited=new int[n];
		
		func(0);
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}

}
