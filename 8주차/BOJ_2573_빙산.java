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
	
	//4방탐색하며 찾은 빙산의 갯수를 출력해주는 함수
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
		isvisited=new boolean[n][m];//DFS할때 방문체크를 위한 배열
		ArrayList<int[]> melt=new ArrayList<>();//녹은 빙하정보(x좌표, y좌표, 녹은 높이)
		
		//빙하 입력 받아옴
		for(int i=0;i<n;i++) {
			str=br.readLine().split(" ");
			for(int j=0;j<m;j++) {
				arr[i][j]=Integer.parseInt(str[j]);
			}
		}
		
		//while(true)를 끝내기위한 변수
		int isend=0;//1이면 2덩어리이상 불리된것 / 2면 분리없이 전부다 녹은것
		
		//배열상의 빙하 갯수 저장하는 변수
		int glaciercnt=0;
		
		//while(true)문 몇번 도는지 체크하는 변수(시간체크 1번돌때 1년)
		int time=0;
		
		
		while(true) {
			glaciercnt=0;
			int dfsx=-1,dfsy=-1;//dfs시작할 좌표(빙하가 반드시 존재하는곳)
			
//1. 전체를 돌면서 빙하가 녹는 높이를 melt배열에 저장함
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					//방문체크 초기화(시간 줄일려고 반복문 따로안만들고 같이 돌림)
					isvisited[i][j]=false;
					
					//빙하 없으면 넘어가
					if(arr[i][j]==0)
						continue;
					//현 좌표가 빙하인 경우
					//빙하갯수 카운트
					glaciercnt++;
					
					//4방위 체크
					int zeronum=0;//사방위에 있는 0의 갯수 카운트하는 변수
					for(int k=0;k<4;k++) {
						int dx=i+direction[k][0];
						int dy=j+direction[k][1];
						
						if(dx<0 || dx>=n || dy<0 || dy>=m)
							continue;
						if(arr[dx][dy]==0)
							zeronum++;//0이 존재하면 ++해줘
					}
					if(zeronum!=0)//1이라도 높이가 녹으면 melt배열에 저장
						melt.add(new int[] {i,j,zeronum});
					
					//나중에 DFS를 돌기위한 시작좌표를 저장
					//녹은후에도 높이가 0보다 큰 좌표를 저장
					if(dfsx==-1 && arr[i][j]-zeronum>0) {
						dfsx=i;
						dfsy=j;
					}
				}
			}
			
			int iszero=0;//녹은 빙하가 잇는지 체크하는 변수
			
//2. 모든 빙하가 다 녹고나면 녹은걸 적용해줘
			//(만약 이걸 따로 안나누고 한번에 그때그때 빙하의 녹은값을 적용시키면 문제가 원하는바와 달라짐)
			int size=melt.size();
			for(int i=0;i<size;i++) {
				int x=melt.get(i)[0];
				int y=melt.get(i)[1];
				int val=melt.get(i)[2];
				arr[x][y]-=val;
				if(arr[x][y]<=0) {
					iszero=1;
					arr[x][y]=0;
					//녹으면 빙하갯수 줄어들어
					glaciercnt--;
				}
			}
			melt.clear();
			time++;

//3. 한 빙하를 시작점으로 DFS돌면서 끊기지않고 한번에 갈수있는 빙하의 갯수를 받아와 
			//현재 빙하 갯수와 비교해 끊긴곳이 있는지 체크함 
			
			//dfs돌면서 연결 끊긴곳 잇는지 확인해
			if(iszero==1) {//녹은빙하 있는경우에만 dfs체크해
				//빙산이 다녹았으면 탈출
				if(dfsx==-1) {//모든 빙하가 녹았기때문에 dfsx에 저장된 값이 없을경우
					isend=2;
					break;
				}
				//ret변수에 시작 빙산에서 갈수잇는 모든 빙산의 갯수를 받아와
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