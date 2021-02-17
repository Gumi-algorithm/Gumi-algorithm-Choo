import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2206_벽부수고이동하기 {

	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String[] strarr=br.readLine().split(" ");
		int n=Integer.parseInt(strarr[0]);
		int m=Integer.parseInt(strarr[1]);
		int [][] arr=new int[n][m];
		String str;
		
		//벽을 뚫을 기회가 남아있는지 없는지 2가지에 대해 isvisited를 체크해야됨
		//근데 벽 뚫을 기회 있었는데  지낫던 곳에 가능성이 없으면 벽 뚫을 기회 없을때 지낫어도 해결될 가능성이 없음
		//그래서 벽 뚫을 기회 있을때 지나면 isvisited1,2 둘다 1로 바꿔줘야됨
		int[][] isvisited1=new int[n][m];//벽뚫을 기회 남았을때
		int[][] isvisited2=new int[n][m];//벽뚫을 기회 없을때
		
		Queue<int[]> q=new LinkedList<>();
		int[] dx= {1,-1,0,0};
		int[] dy= {0,0,1,-1};
		
		for(int i=0;i<n;i++) {
			str=br.readLine();
			for(int j=0;j<m;j++) {
				arr[i][j]=str.charAt(j)-'0';
				if(arr[i][j]=='1')
					isvisited2[i][j]=1;
			}
		}
	
		q.offer(new int[] {0,0,1,1});//x,y좌표,이동횟수, 벽뚫을수있는지 여부(1번 벽 뚫으면 0으로 바꿔)
		isvisited1[0][0]=1;
		isvisited2[0][0]=1;
		
		int ans=-1;
		
		while(!q.isEmpty()) {
			int x=q.peek()[0];
			int y=q.peek()[1];
			int move=q.peek()[2];
			int isBreak=q.poll()[3];
			if(x==n-1 && y==m-1) {//도착햇을때
				ans=move;
				break;
			}
			if(isBreak==1) {//벽 부술 기회가 있을때
				for(int i=0;i<4;i++) {
					int nx=x+dx[i];
					int ny=y+dy[i];
					if(nx==n-1 && ny==m-1) {//도착햇을때
						ans=move+1;
						break;
					}
					//벽 부술 기회있으니까 isvisited1로 방문체크해야됨
					if(nx<n&&nx>=0&&ny<m&&ny>=0) {//인덱스 범위체크
						if(isvisited1[nx][ny]==0) {//방문했는지 체크
							isvisited1[nx][ny]=1;
							isvisited2[nx][ny]=1;
							if(arr[nx][ny]==1)
								q.offer(new int[] {nx,ny,move+1,0});
							else
								q.offer(new int[] {nx,ny,move+1,1});
						}						
					}
				}	
			}else {//벽부술 기회 없을때
				for(int i=0;i<4;i++) {
					int nx=x+dx[i];
					int ny=y+dy[i];
					if(nx==n-1 && ny==m-1) {//도착햇을때
						ans=move+1;
						break;
					}
					//기회 없으니까 isvisited2로 방문여부 체크해야됨
					if(nx<n&&nx>=0&&ny<m&&ny>=0) {//인덱스 범위체크
						if(isvisited2[nx][ny]==0 && arr[nx][ny]==0) {//방문했는지 체크, 벽이 없어야함
							isvisited2[nx][ny]=1;
							q.offer(new int[] {nx,ny,move+1,0});
						}						
					}
				}
			}
			if(ans!=-1)
				break;
		}
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}