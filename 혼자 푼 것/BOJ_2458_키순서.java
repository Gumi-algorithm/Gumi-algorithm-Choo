import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_2458_키순서 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n,m;
		String[] str=br.readLine().split(" ");
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		
		int[][] arr=new int[n][n];
		
		for(int i=0;i<m;i++) {
			str=br.readLine().split(" ");
			int a= Integer.parseInt(str[0])-1;
			int b= Integer.parseInt(str[1])-1;
			arr[a][b]=1;		
		}
		
		
		for(int i=0;i<n;i++) {//경유지
			for(int j=0;j<n;j++) {//출발지
				for(int k=0;k<n;k++) {//도착지
					if(i==j || i==k || j==k)
						continue;
					int tmp1=210000000;
					if(arr[j][k]!=0)
						tmp1=arr[j][k];
					int tmp2=210000000;
					if(arr[j][i]!=0 && arr[i][k]!=0)
						tmp2=arr[j][i]+arr[i][k];
					arr[j][k]= Math.min(tmp1,tmp2);
					if(arr[j][k]==210000000)
						arr[j][k]=0;
				}				
			}			
		}
		//각 좌표에서 다른좌표로 최단거리로 연결 했을때 각 정점에 들어오는 에지의 갯수+ 나가는 에지의 갯수가 n-1이면 자신의 위치 파악됨
		int ans=0;
		for(int i=0;i<n;i++) {
			int cnt=0;
			for(int j=0;j<n;j++) {
				if(i==j)
					continue;
				if(arr[i][j]!=0)
					cnt++;
				if(arr[j][i]!=0)
					cnt++;
			}
			if(cnt==n-1)
				ans++;
		}
		
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}