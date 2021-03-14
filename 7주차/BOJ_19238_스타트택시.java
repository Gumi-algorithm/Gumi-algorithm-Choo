import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_19238_스타트택시 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int n,m,l;
		String[] str=br.readLine().split(" ");
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		l=Integer.parseInt(str[2]);
		int[][] arr= new int[n][n];
		
		//입력 (1은 벽, 0,은 빈칸)
		for(int i=0;i<n;i++) {
			str=br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				arr[i][j]=Integer.parseInt(str[j]);
			}			
		}
		
		//운전을 시작하는칸 행, 열
		int startx,starty;
		str=br.readLine().split(" ");
		startx=Integer.parseInt(str[0]);
		starty=Integer.parseInt(str[1]);
		
		int[][] people=new int[m][5];//출발 x,y/ 도착 x,y/ 이미 이동햇는지 
		
		for(int i=0;i<m;i++) {
			str=br.readLine().split(" ");
			people[i][0]=Integer.parseInt(str[0])-1;
			people[i][1]=Integer.parseInt(str[1])-1;
			people[i][2]=Integer.parseInt(str[2])-1;
			people[i][3]=Integer.parseInt(str[3])-1;
			people[i][4]=0;//아직 방문 안했다는뜻
			
			//지도위에 출발지 인덱스+2로 표시
			arr[people[i][0]][people[i][1]]=i+2;
		}
		
		//맵 크기 20, 고객 최대 20*20 
		//모든 손님은 이동시키는 횟수 400
		//택시가 가장거리가 짧은 고객 고르는데 필요한 연산  BFS최약 n*n=400
		//도착지 이동횟수 BFS최악 n*n=400
		//대충 연산량 400 * 400* 400 < 1억 완탐 가능
		int taxix=startx-1;
		int taxiy=starty-1;
		int taxioil=l;
		int[][] direction= {{1,0},{-1,0},{0,1},{0,-1}};
		int isend=0;
		int movecnt=0;
		int[][] isvisited=new int[n][n];
		while(movecnt<m) {
			//isvisited초기화
			for(int i=0;i<n;i++) {
				Arrays.fill(isvisited[i],0);
			}
			Queue<int[]> q=new LinkedList<>();
			q.offer(new int[] {taxix,taxiy,0});
			int isfind=-1;//최단거리 손님을 찾았는지 확인하는 변수
			
			isvisited[taxix][taxiy]=1;//방문체크
			
			//같은거리손님들 저장하는 list
			ArrayList<int[]> peoplelist=new ArrayList<>();
			while(!q.isEmpty()) {
				int x=q.peek()[0];
				int y=q.peek()[1];
				int length=q.poll()[2];
				
				if(isfind!=-1) {
					//거리 넘어가는거 체크하면 리턴
					if( isfind<length) {						
						break;
					}
					if(arr[x][y]!=0 && people[arr[x][y]-2][4]==0)
						peoplelist.add(new int[] {x,y});
					continue;
				}
				//손님중에 이미 이동시킨 손님 아닐때 저장해
				if(arr[x][y]!=0 && people[arr[x][y]-2][4]==0) {
					//연료 체크
					if(taxioil<=length) {
						isend=1;
						break;
					}
					taxioil-=length;//연료 수정
					isfind=length;//손님을 찾은 최소거리를 저장해
					peoplelist.add(new int[] {x,y});
					continue;
				}
				
				for(int i=0;i<4;i++) {
					int nx=x+direction[i][0];
					int ny=y+direction[i][1];
					
					//인덱스 범위체크
					if(nx<0 || nx>=n || ny<0 || ny>=n)
						continue;
					//벽인지 체크
					if(arr[nx][ny]==1)
						continue;
					//방문체크
					if(isvisited[nx][ny]!=0)
						continue;
					isvisited[nx][ny]=1;
					q.offer(new int[] {nx,ny,length+1});
				}
			}
			//연료 없으면 리턴
			if(isend==1)
				break;
			
			
			//isvisited초기화
			for(int i=0;i<n;i++) {
				Arrays.fill(isvisited[i],0);
			}
			
			//손님을 못찾은경우
			if(peoplelist.size()==0) {
				isend=1;
				break;
			}
				
			
			//찾은 손님리스트 정렬해서
			//행 번호가 가장 작은 승객을, 그런 승객도 여러 명이면 그중 열 번호가 가장 작은 승객을 고른다
			Collections.sort(peoplelist,(a,b)->{
				if(a[0]==b[0])
					return a[1]-b[1];
				return a[0]-b[0];					
			});
			
			taxix= peoplelist.get(0)[0];
			taxiy= peoplelist.get(0)[1];
			int idx=arr[taxix][taxiy]-2;
			//손님 도착지
			int destx=people[idx][2];
			int desty=people[idx][3];
			
			//손님 출발지에서 다시 BFS돌려서 도착지 찾아
			q.clear();
			q.offer(new int[] {taxix,taxiy,0});
			isvisited[taxix][taxiy]=1;//방문체크
			isend=1;
			while(!q.isEmpty()) {
				int x=q.peek()[0];
				int y=q.peek()[1];
				int length=q.poll()[2];
				
				//오일이 바닥날경우
				if(taxioil<length) {
					isend=1;
					break;
				}
				
				//도착할경우
				if(destx==x && desty==y) {
					//이동 시켯다고 표시해줘
					people[idx][4]=1;
					isend=0;
					taxioil+=length;
					taxix=x;
					taxiy=y;
					movecnt++;
					break;
				}
						
				for(int i=0;i<4;i++) {
					int nx=x+direction[i][0];
					int ny=y+direction[i][1];
					
					//인덱스 범위체크
					if(nx<0 || nx>=n || ny<0 || ny>=n)
						continue;
					//벽인지 체크
					if(arr[nx][ny]==1)
						continue;
					//방문체크
					if(isvisited[nx][ny]!=0)
						continue;
					isvisited[nx][ny]=1;
					q.offer(new int[] {nx,ny,length+1});
				}
			}
			//연료 없고 도착 못했으면 리턴
			if(isend==1)
				break;
		}
		
		if(isend==1)
			pw.print(-1);
		else
			pw.print(taxioil);
		
		
		
		br.close();
		pw.flush();
		pw.close();
	}
}
//손님을 못찾는경우
/*
6 3 100
0 0 1 0 0 0
0 0 1 0 0 0
0 0 0 1 0 0
0 0 0 1 0 0
0 0 0 0 1 0
0 0 0 1 0 0
6 5
2 2 5 6
5 4 1 6
4 2 3 5
*/
//손님은 찾았으나 도착을 못하는 경우
/*
6 3 100
0 0 1 0 0 0
0 0 1 0 0 0
0 0 0 1 0 0
0 0 0 1 0 0
0 0 0 0 1 0
0 0 0 1 0 0
6 5
5 6 2 2 
5 4 1 6
4 2 3 5
*/
/*
오일 바닥나는지 체크하고(167줄)
도착했는지 체크해야됨(173줄)
순서 반대로하면
6 1 2
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
3 4
3 4 2 5
이거 답4인데 제대로 안나옴
*/
/*
이미 이동시킨 손님 너머에 다른 손님이 있는걸 생각못하고 
BFS에서 4방향 체크할때 이미 이동시킨 손님 나오면 방문안하게 햇음 그게 문제엿음
6 3 10
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
2 2
1 1 2 4
1 3 1 3
1 5 1 5

답8
*/