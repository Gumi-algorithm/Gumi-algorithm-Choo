import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_9465_스티커 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int t=Integer.parseInt(br.readLine());
		while(t-->0) {
			int n=Integer.parseInt(br.readLine());
			
			int[][] arr=new int[3][n];
			for(int i=0;i<2;i++) {
				String[] str=br.readLine().split(" ");
				for(int j=0;j<n;j++) {
					arr[i+1][j]=Integer.parseInt(str[j]);
				}
			}
			
			for(int i=1;i<n;i++) {
				arr[0][i]+=Math.max(arr[1][i-1], arr[2][i-1]);
				arr[1][i]+=Math.max(arr[0][i-1], arr[2][i-1]);
				arr[2][i]+=Math.max(arr[0][i-1], arr[1][i-1]);
			}
			
			int max= Math.max(arr[0][n-1], arr[1][n-1]);
			max=Math.max(max, arr[2][n-1]);
			pw.println(max);
		}
		br.close();
		pw.flush();
		pw.close();
	}
}
