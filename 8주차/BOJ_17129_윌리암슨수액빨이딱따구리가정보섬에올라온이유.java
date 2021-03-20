import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_17129_윌리암슨수액빨이딱따구리가정보섬에올라온이유 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int n,m;
		String[] str=br.readLine().split(" ");
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		int[][] arr=new int[n][m];
		
		Queue<int[]> q=new LinkedList<>();
		
		for(int i=0;i<n;i++) {
			str=br.readLine().split("");
			for(int j=0;j<m;j++) {
				arr[i][j]=Integer.parseInt(str[j]);
				if(arr[i][j]==2) {
					q.offer(new int[]{i,j,0});
					arr[i][j]=0;
				}
			}
		}
		
		int[][] dir= {{1,0},{-1,0},{0,1},{0,-1}};
		int ans=-1;
		
		//최단거리니까 BFS
		while(!q.isEmpty()) {
			int x=q.peek()[0];
			int y=q.peek()[1];
			int len=q.poll()[2];
			
			
			
			for(int i=0;i<4;i++) {
				int nx=x+dir[i][0];
				int ny=y+dir[i][1];
				//범위체크
				if(nx<0 || nx>=n || ny<0 || ny>=m)
					continue;
				//벽이거나 이미 방문했을경우 지나감
				if(arr[nx][ny]==1)
					continue;
				//음식 만나면 정답 저장하고 탈출
				if(arr[nx][ny]>2) {
					ans=len+1;
					break;
				}
				
				arr[nx][ny]=1;
				q.offer(new int[] {nx,ny,len+1});				
			}
			//정답이 있으면 탈출
			if(ans!=-1)
				break;			
		}
		
		if(ans==-1)
			pw.print("NIE");
		else
			pw.print("TAK\n"+ans);
			
		
		
		br.close();
		pw.flush();
		pw.close();
	}
}