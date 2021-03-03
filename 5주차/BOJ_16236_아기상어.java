import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_16236_아기상어 {
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int n=Integer.parseInt(br.readLine());
		int[][] arr=new int[n][n];
		String[] str;
		
		//거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 
		//그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
		int[][] direction= {{-1,0},{0,-1},{0,1},{1,0}};
		
		int sharkSize=2;//아기상어크기
		int nowEat=0;//현재 아기상어가 먹은 물고기 수
		int nowx=0;//상어위치
		int nowy=0;
		int time=0;
		
		//입력
		for(int i=0;i<n;i++) {
			str=br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				arr[i][j]=Integer.parseInt(str[j]);
				if(arr[i][j]==9) {
					nowx=i;
					nowy=j;	
					arr[i][j]=0;
				}
			}
		}
		
		//BFS를 해서 현재 먹을수 잇는 물고기가 잇는지 체크하고 거기로 이동
		//그리고 이동한 위치에서 다시 BFS해서 확인 
		//반복
		Queue<int[]> q=new LinkedList<>();
		boolean[][] isvisited=new boolean[n][n];
		ArrayList<int[]> food=new ArrayList<>();//먹힐 예정인 물고기
		
		int end=1;
		while(true) {
			q.offer(new int[]{nowx,nowy,0});//상어위치,이동시간
			
			int maxmove=-1;
			
			//isvisited초기화
			for(int i=0;i<n;i++)
				Arrays.fill(isvisited[i], false);
			
			isvisited[nowx][nowy]=true;
			
			//아기상어가 먹을수 있는 물고기 다먹었는지 체크하는 변수
			end=1;
			
			//먹을수 있는 물고기를 BFS를 통해 찾아
			//찾은 물고기중에 같은 거리인 물고기들 일단 저장해둬
			while(!q.isEmpty()) {
				int x=q.peek()[0];
				int y=q.peek()[1];
				int move=q.poll()[2];//움직인 횟수(거리)
				
				//현재 먹을수 있는 물고기보다 거리가 먼 물고기를 탐색하면 탈출
				if(maxmove!=-1&& move>maxmove) {
					break;
				}
				
				//같은 거리의 물고기들 전부 모아둬
				if(arr[x][y]!=0 && arr[x][y]<sharkSize) {
					if(maxmove==-1)
						maxmove=move;//처음 먹을수 있는 물고기를 만나면 거리 저장해둬
					food.add(new int[] {x,y});
					end=0;//먹을수 있는 물고기가 하나라도 잇으면 end 0으로 만들어줘
				}
		
				
				//4방탐색
				//거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 
				//그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
				for(int i=0;i<4;i++) {
					int nx=x+direction[i][0];
					int ny=y+direction[i][1];
					
					if(nx<0 || nx>=n ||ny<0 ||ny>=n)//인덱스 범위체크
						continue;
					if(isvisited[nx][ny])//방문했었는지 체크
						continue;
					if(arr[nx][ny]>sharkSize)//자기보다 큰 물고기잇는곳 지날수 없음
						continue;
					
					isvisited[nx][ny]=true;
					q.offer(new int[]{nx,ny,move+1});
				}

			}
			
			//전부 탐색했는데 먹을수 있는 물고기 없으면 탈출
			if(end==1)
				break;
			
			//물고기 먹음(가장 위에있는 물고기 (여러마리면 가장 왼쪽))
			Collections.sort(food,(a,b)->{
				if(a[0]==b[0])
					return a[1]-b[1];
				return a[0]-b[0];					
			});
			nowEat++;
			nowx=food.get(0)[0];
			nowy=food.get(0)[1];
			time+=maxmove;
			arr[nowx][nowy]=0;
			//크기만큼 먹으면사이즈 증가
			if(nowEat>=sharkSize) {					
				nowEat=0;
				sharkSize++;
			}
			food.clear();
			q.clear();
			
		
		}
		
		pw.print(time);
		br.close();
		pw.flush();
		pw.close();
	}
}