import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_7569_토마토 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int m,n,h;
		String[] str=br.readLine().split(" ");
		m=Integer.parseInt(str[0]);
		n=Integer.parseInt(str[1]);
		h=Integer.parseInt(str[2]);
		
		int[][][] arr=new int[h][n][m];
		Queue<int[]> tomato=new LinkedList<>();//익은 토마토 좌표들
		int emptycnt=0;
		int totalcnt=0;//총 토마토 갯수
		int tomatocnt=0;//익은토마토 갯수
		for(int i=0;i<h;i++) {
			for(int j=0;j<n;j++) {
				str=br.readLine().split(" ");
				for(int k=0;k<m;k++) {
					arr[i][j][k]=Integer.parseInt(str[k]);
					if(arr[i][j][k]==1) {
						tomato.add(new int[] {i,j,k,0});
						tomatocnt++;
					}
					else if(arr[i][j][k]==-1)
						emptycnt++;
				}
			}
		}
		int ans=0;
		totalcnt=h*n*m-emptycnt;//상자안에들어있는 토마토 갯수
		//BFS
		int[][] direction= {{1,0,0},{-1,0,0},{0,1,0},{0,-1,0},{0,0,1},{0,0,-1}};
		while(!tomato.isEmpty()) {
			int nowz=tomato.peek()[0];
			int nowx=tomato.peek()[1];
			int nowy=tomato.peek()[2];
			int nowday=tomato.poll()[3];
			
			ans=Math.max(ans, nowday);
			for(int i=0;i<6;i++) {
				int nz=nowz+direction[i][0];
				int nx=nowx+direction[i][1];
				int ny=nowy+direction[i][2];
				
				//인덱스 범위체크
				if(nz>=h || nz<0 || nx>=n || nx<0 || ny>=m || ny<0)
					continue;
				
				if(arr[nz][nx][ny]!=0)
					continue;
				
				arr[nz][nx][ny]=1;
				tomatocnt++;
				tomato.add(new int[] {nz,nx,ny,nowday+1});
				
			}	
		}
		
		if(tomatocnt < totalcnt)
			pw.print("-1");
		else
			pw.print(ans);
		
		pw.flush();
		pw.close();
		br.close();
	}
}