import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_1149_RGB거리 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		
		int n=Integer.parseInt(br.readLine());
		int[][] arr=new int[n][3];
		for(int i=0;i<n;i++) {
			String[] str=br.readLine().split(" ");
			arr[i][0]=Integer.parseInt(str[0]);
			arr[i][1]=Integer.parseInt(str[1]);
			arr[i][2]=Integer.parseInt(str[2]);			
		}
		
		//이전꺼만 영향을 주는거니까 세가지 색상을 모두 고려하면서 이전꺼랑 안겹치는 색 되게 하자
		for(int i=1;i<n;i++) {
			arr[i][0]=Math.min(arr[i][0]+arr[i-1][1],arr[i][0]+arr[i-1][2]);
			arr[i][1]=Math.min(arr[i][1]+arr[i-1][0],arr[i][1]+arr[i-1][2]);
			arr[i][2]=Math.min(arr[i][2]+arr[i-1][0],arr[i][2]+arr[i-1][1]);			
		}
		int ans=arr[n-1][0];
		ans=Math.min(ans, arr[n-1][1]);
		ans=Math.min(ans, arr[n-1][2]);
		
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();			
	}

}
