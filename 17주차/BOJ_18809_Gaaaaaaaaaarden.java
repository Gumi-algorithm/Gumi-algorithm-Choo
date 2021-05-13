import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_18809_Gaaaaaaaaaarden {

	static class Pos{
		int x,y;
		int t;//시간
		int c;//색상 (1:빨강, 2:초록)
		public Pos(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		public Pos(int x, int y, int t, int c) {
			super();
			this.x = x;
			this.y = y;
			this.t = t;
			this.c = c;
		}
		@Override
		public String toString() {
			return "Pos [x=" + x + ", y=" + y + ", t=" + t + ", c=" + c + "]";
		}	
		
	}
	
	static int[] rpos;//배양액이 뿌릴수 있는곳중 빨간색을 뿌릴곳
	static int[] gpos;//배양액이 뿌릴수 있는곳중 빨간색을 뿌릴곳
	static int[] isvisited;//배양액이 뿌릴수 있는곳중 중복체크
	static ArrayList<Pos> landpos=new ArrayList<>();////배양액이 뿌릴수 있는곳 좌표
	static int n,m;
	static int g,r;
	static int ans=0;//최대꽃갯수	
	static int[][] arr;
	
	static void bfs() {
		int[][] cparr=new int[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				cparr[i][j]=arr[i][j];
			}
		}
		
		Queue<Pos> q=new LinkedList<>();
		
		//정원에 배양액 위치 뿌릴곳 정해진곳에 각 색의 배양액을 뿌림, 그리고 큐에 집어넣음
		for(int i:rpos) {//빨강:1
			cparr[landpos.get(i).x][landpos.get(i).y]=3;
			q.offer(new Pos(landpos.get(i).x,landpos.get(i).y,1,1));
		}
		for(int i:gpos) {//초록:2
			cparr[landpos.get(i).x][landpos.get(i).y]=4;
			q.offer(new Pos(landpos.get(i).x,landpos.get(i).y,1,2));
		}
		
		
		int flower=0;//꽃갯수
		int[][] dir= {{1,0},{-1,0},{0,1},{0,-1}};
		while(!q.isEmpty()) {
			Pos cur=q.poll();
			
			//이미 다른 배양액과 만나 꽃이 폇으면 넘겨
			if(cparr[cur.x][cur.y]==5)
				continue;
			
			
			for(int i=0;i<4;i++) {
				int nx=cur.x+dir[i][0];
				int ny=cur.y+dir[i][1];
				
				if(nx>=n || nx<0 || ny>=m || ny<0)
					continue;
				
				//꽃이 핀곳이거나 호수인경우
				if(cparr[nx][ny]==0 || cparr[nx][ny]==5)
					continue;
				
				//3은 빨강 배양액이 뿌려진땅, 4는 초록배양액이 뿌려진땅, 5는 꽃이핀 땅
				//현재 배양액이 빨강일경우
				if(cur.c==1) {
					int t=cparr[nx][ny]/10;
					int c=cparr[nx][ny]%10;
					if(c==3)//빨강이 뿌려져있으면 넘겨
						continue;
					else if(c==4) {//초록이랑 만나면 꽃이핌
						if(t==cur.t) {//시간 같으면 꽃핌
							cparr[nx][ny]=5;
							flower++;
							continue;
						}else
							continue;
					}
				}else if(cur.c==2) {//현재 배양액이 초록일경우
					int t=cparr[nx][ny]/10;
					int c=cparr[nx][ny]%10;
					if(c==4)//초록이 뿌려져있으면 넘겨
						continue;
					else if(c==3) {//빨강이랑 만나면 꽃이핌
						if(t==cur.t) {
							cparr[nx][ny]=5;
							flower++;
							continue;
						}else
							continue;
					}
				}				
				//그냥 땅이면 배양액이 퍼짐
				//퍼진 시간이 동일해야 꽃이 피기때문에 1의자리는 배양액 색 10의 자리는 초를 나타냄
				cparr[nx][ny]=cur.t*10+(cur.c+2);
				q.offer(new Pos(nx,ny,cur.t+1,cur.c));
			}			
		}
		ans=Math.max(ans, flower);
		
		
	}
	
	static void gPosition(int now,int idx) {
		if(idx==g) {
			bfs();
			return;
		}
		int size=landpos.size();
		for(int i=now;i<size;i++) {
			if(isvisited[i]!=0)
				continue;
			gpos[idx]=i;
			isvisited[i]=1;
			gPosition(i+1, idx+1);
			isvisited[i]=0;
		}
		
	}
	
	static void rPosition(int now,int idx) {
		if(idx==r) {
			gPosition(0, 0);
			return;
		}
		int size=landpos.size();
		for(int i=now;i<size;i++) {
			if(isvisited[i]!=0)
				continue;
			rpos[idx]=i;
			isvisited[i]=1;
			rPosition(i+1, idx+1);
			isvisited[i]=0;
		}
		
	}
	
	public static void main(String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw= new PrintWriter(System.out);

		
		String[] str=br.readLine().split(" ");
		
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		g=Integer.parseInt(str[2]);
		r=Integer.parseInt(str[3]);
		
		gpos=new int[g];
		rpos=new int[r];
		
		//정원 정보 input (0:호수, 1:배양액 못 뿌리는 곳, 2:배양액 뿌릴 수 있는 곳)
		//r+g<=10
		arr=new int[n][m];
		for(int i=0;i<n;i++) {
			str=br.readLine().split(" ");
			for(int j=0;j<m;j++) {
				arr[i][j]=Integer.parseInt(str[j]);
				if(arr[i][j]==2) {
					landpos.add(new Pos(i,j));
				}
			}
		}		
		isvisited=new int[landpos.size()];
		
		//배양액을 뿌릴수 있는 땅의 최대는 10임
		//연산량: 10Cr * (10-r)Cg * n*m
		//최대값 이정도? 5!5!*50*50 =36,000,000
		//r이  5일때 10C5 * 5C5= 252
		//완탐 가능할듯
		
		//일단 R,L의 배양액 위치 선정
		//그리고 BFS돌림
		rPosition(0,0);

		
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();		
	}

}
