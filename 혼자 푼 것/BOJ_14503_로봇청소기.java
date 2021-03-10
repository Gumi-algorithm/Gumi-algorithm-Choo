import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_14503_로봇청소기 {
	//각 방향별로 왼쪽을 넣어둠 0인덱스는 북쪽이니 북쪽기준 왼쪽은 {0,-1}
	static int[][] direction= {{0,-1},{-1,0},{0,1},{1,0}};//북, 동, 서, 남
	static int n,m;
	static int[][] arr;
	static int[][] isvisited;
	//왼쪽 먼저 청소
		//왼쪽 청소안해도되면 왼쪽 회전
			//네방향 모두 청소 안해도되면 뒤로 1칸 후진
				//뒤로 후진못하면 전원 끔
		//왼쪽 청소해야되면 왼쪽으로 방향 돌리고 한칸이동	
	static int dfs(int x,int y,int d) {//x,y좌표, 바라보는 방향
		
		
		int nowd=d;
		int flag=0;//네방향 청소못했으면 후진시킬려고 변수하나 만듬
		for(int i=0;i<4;i++) {
			int nx=x+direction[nowd][0];
			int ny=y+direction[nowd][1];
			
			//왼쪽이 벽(인덱스 밖)이니까 회전만해
			if(nx>=n || nx<0 || ny>=m || ny<0) {
				//왼쪽 회전이니까 -1
				nowd=(nowd-1+4)%4;
				continue;
			}
			//왼쪽이 벽인경우 회전만해
			if( arr[nx][ny]==1) {
				nowd=(nowd-1+4)%4;
				continue;
			}
			//청소할 필요없는곳이니 회전만해
			if(isvisited[nx][ny]==1) {
				nowd=(nowd-1+4)%4;
				continue;
			}
			isvisited[nx][ny]=1;
			//청소 한번이라도 하면 flag 1집어넣어
			flag=1;
			//청소 할수 있는 공간이 있으면 이동해
			return dfs(nx,ny,(nowd-1+4)%4)+1;			
		}
		//청소할수있는곳이 없으면 뒤로 후진해
		if(flag==0) {
			int nx=x+direction[(d-1+4)%4][0];
			int ny=y+direction[(d-1+4)%4][1];
			//후진할곳이 벽이면 그냥 리턴해
			//왼쪽이 벽(인덱스 밖)이니까 회전만해
			if(nx>=n || nx<0 || ny>=m || ny<0) {
				return 0;
			}
			//왼쪽이 벽인경우 회전만해
			if( arr[nx][ny]==1) {
				return 0;
			}
			return dfs(nx,ny,d);
		}
		return 0;
	}
	
	public static void main(String[] args)throws IOException	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		
		String[] str=br.readLine().split(" ");
		
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		arr=new int[n][m];
		isvisited=new int[n][m];
		int r,c,d;
		
		str=br.readLine().split(" ");
		r=Integer.parseInt(str[0]);
		c=Integer.parseInt(str[1]);
		//북, 동, 남, 서 (0,1,2,3)
		d=Integer.parseInt(str[2]);
		
		for(int i=0;i<n;i++) {
			str=br.readLine().split(" ");
			for(int j=0;j<m;j++) {
				arr[i][j]=Integer.parseInt(str[j]);
			}
		}
		isvisited[r][c]=1;
		int ans=dfs(r,c,d);
		
		pw.print(ans+1);
		pw.flush();
		pw.close();
		br.close();
	}
}