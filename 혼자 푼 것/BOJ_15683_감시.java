import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

//cctv는 개별적으로 회전이 가능하다
//1번 cctv가 2개 있을때 한대는 위를, 한대는 아래를 볼수 있음
//cctv는 최대 8개를 넘지않는다
public class BOJ_15683_감시 {
	
	static class Node{
		int cctvno;
		int x,y;
		public Node(int cctvno, int x, int y) {
			super();
			this.cctvno = cctvno;
			this.x = x;
			this.y = y;
		}
		
	}
	
	//cctv [회전][cctv번호][방향][x,y좌표]
	static int[][][][] cctv= {
			{	{},//0번은 없음 
				{{0,1}},//1번cctv(회전은 시계방향)
				{{0,1},{0,-1}},//2번cctv
				{{-1,0},{0,1}},//3번cctv
				{{0,1},{-1,0},{0,-1}},//4번cctv
				{{0,1},{0,-1},{1,0},{-1,0}},//5번cctv			
			},
			{	{},//0번은 없음 
				{{1,0}},//1번cctv(회전은 시계방향)
				{{1,0},{-1,0}},//2번cctv
				{{1,0},{0,1}},//3번cctv
				{{0,1},{-1,0},{1,0}},//4번cctv
				{{0,1},{0,-1},{1,0},{-1,0}},//5번cctv			
			},
			{	{},//0번은 없음 
				{{0,-1}},//1번cctv(회전은 시계방향)
				{{0,1},{0,-1}},//2번cctv
				{{1,0},{0,-1}},//3번cctv
				{{0,1},{1,0},{0,-1}},//4번cctv
				{{0,1},{0,-1},{1,0},{-1,0}},//5번cctv			
			},
			{	{},//0번은 없음 
				{{-1,0}},//1번cctv(회전은 시계방향)
				{{1,0},{-1,0}},//2번cctv
				{{-1,0},{0,-1}},//3번cctv
				{{1,0},{-1,0},{0,-1}},//4번cctv
				{{0,1},{0,-1},{1,0},{-1,0}},//5번cctv			
			}			
	};
	static int[][] arr;
	static int[][] arrcopy;
	static int n,m;
	static ArrayList<Node> cctvpos;
	static int[] selectedRotate;
	static int ans=0;
	
	static void dfs(int x,int y,int c,int d,int r) {//시작좌표, cctv번호, 방향번호, 회전
		if(x>=n || x<0 || y>=m || y<0)
			return;
		
		if(arrcopy[x][y]==6) {//벽을 만나면 리턴
			return;
		}
		
		//cctv들은 바꾸면 안됨
		if(arrcopy[x][y]==0)
			arrcopy[x][y]=-1;//감시지점은 -1로 표시
		int dx,dy;
		dx=x+cctv[r][c][d][0];
		dy=y+cctv[r][c][d][1];
		dfs(dx,dy,c,d,r);
		
	}
	
	static void selectrotatefunc(int idx) {
		
		if(idx==selectedRotate.length) {
			//회전방향 다 골랐으면 이제 사각지대 체크해
			
			//arrcopy초기화
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					arrcopy[i][j]=arr[i][j];
				}
			}
			
			//dfs
			for(int i=0;i<selectedRotate.length;i++) {
				Node nowcctv=cctvpos.get(i);//현재 cctv정보
				int nowrot=selectedRotate[i];//선택된 회전방향
				//현재 cctv의 감시 방향 갯수
				int size=cctv[nowrot][nowcctv.cctvno].length;
				for(int j=0;j<size;j++) {
					dfs(nowcctv.x,nowcctv.y,nowcctv.cctvno,j,nowrot);
				}
			}
			
			
			//감시지점 다 표시하면 사각지대 갯수 카운트해
			int cnt=0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(arrcopy[i][j]==0)
						cnt++;
				}
			}
			ans=Math.min(ans, cnt);
			return;
		}
		
		for(int i=0;i<4;i++) {//회전 90도씩 4가지
			selectedRotate[idx]=i;
			selectrotatefunc(idx+1);
		}
		
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		String [] str= br.readLine().split(" ");
		
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		arr=new int[n][m];
		arrcopy=new int[n][m];
		cctvpos=new ArrayList<>();
		
		
		for(int i=0;i<n;i++) {
			str=br.readLine().split(" ");
			for(int j=0;j<m;j++) {
				arr[i][j]=Integer.parseInt(str[j]);
				if(arr[i][j]>0 && arr[i][j]<6)
					cctvpos.add(new Node(arr[i][j],i,j));
			}
		}
		selectedRotate=new int[cctvpos.size()];
		ans=n*m;
		
		selectrotatefunc(0);
	
		
		

		
		pw.print(ans);
		pw.flush();
		br.close();
		pw.close();
	}

}
