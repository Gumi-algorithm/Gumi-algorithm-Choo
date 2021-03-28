import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_11404_플루이드 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb=new StringBuilder();
		
		int n=Integer.parseInt(br.readLine());
		int m=Integer.parseInt(br.readLine());
		
		int arr[][]=new int[n+1][n+1];
		
		for(int i=0;i<=n;i++) {
			Arrays.fill(arr[i], 0);
		}
		
		for(int i=0;i<m;i++) {
			String[] str=br.readLine().split(" ");
			int a=Integer.parseInt(str[0]);
			int b=Integer.parseInt(str[1]);
			int w=Integer.parseInt(str[2]);
			if(arr[a][b]==0)
				arr[a][b]=w;
			else
				arr[a][b]=Math.min(arr[a][b], w);
//			arr[b][a]=w;
		}
		
		for(int k=1;k<=n;k++) {//경유지
			for(int i=1;i<=n;i++) {//출발지
				for(int j=1;j<=n;j++) {//도착지
					if(i==k || i==j || k==j)
						continue;
					//경유하는 경우
					int tmp=0;//경유지가 연결되어있는 경우
					if(arr[i][k]>0 && arr[k][j]>0) {
						tmp=arr[i][k]+arr[k][j];
					}
					if(tmp!=0) {
						if(arr[i][j]!=0)//직선로가 연결 되어있는경우
							arr[i][j]=Math.min(arr[i][j], tmp);
						else
							arr[i][j]=tmp;
					}					
				}	
			}	
		}
		int ans=0;
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		pw.print(sb);
		br.close();
		pw.flush();
		pw.close();
	}
}