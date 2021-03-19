import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BOJ_2573_빙산 {
	static int n,m;
	static int[][] arr;
	static boolean[][] isvisited;
	static int[][] direction= {{1,0},{-1,0},{0,-1},{0,1}};
	
	static int search(int x,int y) {
		
		if(isvisited[x][y])
			return 0;
		
		isvisited[x][y]=true;
		
		int cnt=1;
		for(int i=0;i<4;i++) {
			int dx=x+direction[i][0];
			int dy=y+direction[i][1];
			
			if(dx<0 || dx>=n || dy<0 || dy>=m)
				continue;

			//빙하 아니면 넘어가
			if(arr[dx][dy]==0)
				continue;
			
			cnt+=search(dx,dy);
		}
		
		return cnt;
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		
	
		String[] str=br.readLine().split(" ");
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		
		arr=new int[n][m];//전체 맵 정보
		isvisited=new boolean[n][m];
		ArrayList<int[]> melt=new ArrayList<>();//녹은 빙하정보
		
		
		for(int i=0;i<n;i++) {
			str=br.readLine().split(" ");
			for(int j=0;j<m;j++) {
				arr[i][j]=Integer.parseInt(str[j]);
			}
		}
		
		int isend=0;//1이면 2덩어리이상 불리된것 2면 분리없이 전부다 녹은것
		int glaciercnt=0;
		int time=0;
		while(true) {
			glaciercnt=0;
			int dfsx=-1,dfsy=-1;//dfs시작할 좌표(빙하가 반드시 존재하는곳)
			//빙하가 녹아
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					//방문체크 초기화
					isvisited[i][j]=false;
					
					//빙하 없으면 넘어가
					if(arr[i][j]==0)
						continue;
					
					//빙하갯수 카운트
					glaciercnt++;
					
					//4방위 체크
					int zeronum=0;
					for(int k=0;k<4;k++) {
						int dx=i+direction[k][0];
						int dy=j+direction[k][1];
						
						if(dx<0 || dx>=n || dy<0 || dy>=m)
							continue;
						if(arr[dx][dy]==0)
							zeronum++;
					}
					if(zeronum!=0)
						melt.add(new int[] {i,j,zeronum});
					if(dfsx==-1 && arr[i][j]-zeronum>0) {
						dfsx=i;
						dfsy=j;
					}
				}
			}
			//녹은 빙하가 잇는지 체크하는 변수
			int iszero=0;
			//빙하 녹고나면 녹은애들 적용
			int size=melt.size();
			for(int i=0;i<size;i++) {
				int x=melt.get(i)[0];
				int y=melt.get(i)[1];
				int val=melt.get(i)[2];
				arr[x][y]-=val;
				if(arr[x][y]<=0) {
					iszero=1;
					arr[x][y]=0;
					//빙하갯수 줄어들어
					glaciercnt--;
				}
			}
			melt.clear();
			time++;
			//dfs돌면서 연결 끊긴곳 잇는지 확인해
			if(iszero==1) {//녹은빙하 있는경우에만 dfs체크해
				//빙산이 다녹았으면 탈출
				if(dfsx==-1) {
					isend=2;
					break;
				}
				int ret=search(dfsx,dfsy);
				//dfs돈결과 전체 빙하갯수랑 다르면 빙하가 나눠진거니까 탈출해
				if(ret!=glaciercnt) {
					isend=1;
					break;
				}
			}
			
		}
		if(isend==2)
			pw.print(0);
		else if(isend==1)
			pw.print(time);
		br.close();
		pw.flush();
		pw.close();
	}

}

