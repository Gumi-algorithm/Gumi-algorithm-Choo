import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;

public class BOJ_4485_녹색옷입은애가젤다지 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		StringBuilder sb=new StringBuilder();
		int t=0;
		while(true) {
			t++;
			int n=Integer.parseInt(br.readLine());
		
			if(n==0)
				break;
			int arr[][]=new int[n][n];
			
			for(int i=0;i<n;i++) {
				String[] str=br.readLine().split(" ");
				for(int j=0;j<n;j++) {
					arr[i][j]=Integer.parseInt(str[j]);
				}
			}
			
			int isvisited[][]=new int[n][n];
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					isvisited[i][j]=-1;
				}
			}
			
			PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->(a[2]-b[2]));
		
			pq.offer(new int[] {0,0,arr[0][0]});
			
			int[][] dir= {{1,0},{0,1},{-1,0},{0,-1}};
			
			while(!pq.isEmpty()) {
				int[]tmp=pq.poll();
				int x=tmp[0];
				int y=tmp[1];
				int w=tmp[2];
				
				if(isvisited[x][y]!=-1 && w>=isvisited[x][y])
					continue;
				isvisited[x][y]=w;
				
				for(int i=0;i<4;i++) {
					int nx=x+dir[i][0];
					int ny=y+dir[i][1];
					
					
					if(nx<0 || nx>=n || ny<0 || ny>=n)
						continue;
					int nw=w+arr[nx][ny];
					
					if(isvisited[nx][ny]!=-1 && isvisited[nx][ny]<nw)
						continue;
					
					pq.offer(new int[] {nx,ny,nw});									
				}				
			}
			sb.append("Problem ").append(t).append(": ").append(isvisited[n-1][n-1]).append("\n");
		}
		pw.print(sb);
		br.close();
		pw.flush();
		pw.close();
	}
}