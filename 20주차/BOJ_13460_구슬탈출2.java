import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_13460_구슬탈출2 {

	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		String[] str=br.readLine().split(" ");
		int n,m;
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		
		int[][] arr=new int[n][m];
		int red[]=new int[2];
		int blue[]=new int[2];
		//input
		for(int i=0;i<n;i++) {
			str=br.readLine().split("");
			for(int j=0;j<m;j++) {
				if(str[j].equals(".")) {//땅
					arr[i][j]=0;
				}else if(str[j].equals("#")) {//벽
					arr[i][j]=1;
				}else if(str[j].equals("O")) {//구멍
					arr[i][j]=2;
				}else if(str[j].equals("R")) {//빨간구슬
					arr[i][j]=0;
					red[0]=i;
					red[1]=j;
				}else if(str[j].equals("B")) {//파란구슬
					arr[i][j]=0;
					blue[0]=i;
					blue[1]=j;					
				}				
			}
		}
		
		Queue<Integer> q=new LinkedList<Integer>();
		q.offer(red[0]);
		q.offer(red[1]);
		q.offer(blue[0]);
		q.offer(blue[1]);
		q.offer(0);//움직인 횟수
		q.offer(-1);//이전에 움직인 방향
		int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}};//위  오른 아래 왼
		int ans=-1;
		while(!q.isEmpty()) {
			//빨강이 먼저움직임 
			//근데 이동경로에 파랑이 있으면 한칸 덜 가 (파랑 자리)
			red[0]=q.poll();
			red[1]=q.poll();
			blue[0]=q.poll();
			blue[1]=q.poll();
			int movecnt=q.poll();
			int pre=q.poll();
			
			if(movecnt==10)
				break;
					
			for(int i=0;i<4;i++) {
				//갓던곳 못가게해서 연산수 줄임
				if(pre!=-1 && (pre+2)%4==i)
					continue;
				
				//빨간구슬 먼저 움직임
				int x=red[0];
				int y=red[1];
				int isblue=0;
				int isholer=0;//isHoleRed
				while(true) {
					int nx=x+dir[i][0];
					int ny=y+dir[i][1];
					
					//인덱스 범위를 벗어난 경우
					if(nx<0 || nx>=n || ny<0 || ny>=m)
						break;					
					//벽을 만난경우
					if(arr[nx][ny]==1)
						break;					
					//경로상 파랑이 있는경우
					if(nx==blue[0] && ny==blue[1])
						isblue=1;
					
					x=nx;
					y=ny;
					//구멍에 들어가는경우
					if(arr[nx][ny]==2){
						x=-1;
						y=-1;
						isholer=1;
						break;
					}
				}
				int rx=x;
				int ry=y;
				//파랑이랑 같이 들어가면 안되는거니까 큐에 넣지말고 넘겨
				if(isholer==1 && isblue==1)
					continue;
				if(isblue==1) {//경로상에 파랑색이 있으면 한칸 비워두고 거기 파랑 둬
					rx= x- dir[i][0];
					ry= y- dir[i][1];
					q.offer(rx);//빨강
					q.offer(ry);
					q.offer(x);//파랑
					q.offer(y);
					q.offer(movecnt+1);
					q.offer(i);
					continue;
				}
				
				//파랑구슬 움직임
				x=blue[0];
				y=blue[1];
				int isholeb=0;//isHoleBlue
				while(true) {
					int nx=x+dir[i][0];
					int ny=y+dir[i][1];
					
					//인덱스 범위를 벗어난 경우
					if(nx<0 || nx>=n || ny<0 || ny>=m)
						break;
					//벽을 만난경우
					if(arr[nx][ny]==1)
						break;
					//빨강공을 만난경우
					if(nx==rx && ny==ry)
						break;
					x=nx;
					y=ny;					
					//구멍에 들어가는경우
					if(arr[nx][ny]==2){
						x=-1;
						y=-1;
						isholeb=1;
						break;
					}
				}
				if(isholeb==1)//파랑 들어가면 실패
					continue;
				else if(isholer==1) {//빨강 혼자 들어갓으니 정답임
					ans=movecnt+1;
					break;
				}
				q.offer(rx);//빨강
				q.offer(ry);
				q.offer(x);//파랑
				q.offer(y);
				q.offer(movecnt+1);
				q.offer(i);
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