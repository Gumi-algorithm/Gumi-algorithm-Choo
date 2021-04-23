package 풀다만거;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_3197_백조의호수 {

	static class Node{
		int x,y;
		int time;
		public Node(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", time=" + time + "]";
		}
		
	}
	
	static int[] parent;
	
	static int find(int n) {
		if(parent[n]==n)
			return n;
		return parent[n]=find(parent[n]);
	}
	static void union_init() {
		for(int i=0;i<parent.length;i++) {
			parent[i]=i;
		}
	}
	
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int r,c;
		int ans=0;
		int isans=0;//정답 찾았는지여부
		String[] str=br.readLine().split(" ");
		
		r=Integer.parseInt(str[0]);
		c=Integer.parseInt(str[1]);
		char[][] arr=new char[r][c];
	
		int swan[][]=new int[2][2];
		int swanidx=0;
		
		//유니온파인드에서 사용될 백조들의 인덱스
		int union_swan[]=new int[2];
		
		parent=new int[r*c];
		union_init();
		
		
		//bfs로 매번 얼음 녹는거 검사하면 1500*1500*1500이라서 시간초과남
		//그래서 큐 사용
		//처음에 bfs한번 돌면서 물과 닿아있는 얼음을 큐에 넣음
		//하루가 지나면 큐에있는 얼음과 닿아있는 얼음들이 다음날에 녹을 차례니까 큐에 집어넣음
		
		//백조의 영역은 유니온 파인드를 이용해 연결되어있는지 확인한다
		
		//유니온 파인드를 쓸때 값들을 i*c+j로 인덱스를 관리한다
		
		
		for(int i=0;i<r;i++) {
			arr[i]=br.readLine().toCharArray();
			for(int j=0;j<c;j++) {
				if(arr[i][j]=='L') {
					swan[swanidx][0]=i;
					swan[swanidx][1]=j;
					union_swan[swanidx++]=i*c+j;
					arr[i][j]='.';//백조가 있는곳도 물가임
				}
					
			}
		}
		

		
		//1. bfs로 물과 닿아있는얼음 큐에 넣어, 그리고 물끼리 합쳐서 같은유니온에 있는걸 표시해줌
		Queue<Node> melt=new LinkedList<>();
		boolean[][] isvisited=new boolean[r][c];
		int[][] dir= {{1,0},{-1,0},{0,1},{0,-1}};
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				char now=arr[i][j];
				if(now=='X') {//얼음근처에 물있는경우
					for(int k=0;k<4;k++) {
						int nx=i+dir[k][0];
						int ny=j+dir[k][1];
						if(nx<0 || nx>=r || ny<0 || ny>=c)
							continue;
						if(arr[nx][ny]=='.') {
							melt.offer(new Node(i,j,0));//날짜
							isvisited[i][j]=true;
							break;
						}
					}
				}				
				//물이 있는곳은 합쳐주어 같은 유니온임을 표시한다
				if(now=='.') {
					for(int k=0;k<4;k++) {
						int nx=i+dir[k][0];
						int ny=j+dir[k][1];
						if(nx<0 || nx>=r || ny<0 || ny>=c)
							continue;
						if(arr[nx][ny]=='.') {
							int a=i*c+j;
							int b=nx*c+ny;
							int tmpa=find(a);
							int tmpb=find(b);
							if(tmpa!=tmpb) {
								//만약 얼음이 하나도 안녹은 처음부터 백조가 만날수 있는경우
								if(tmpa==union_swan[0] && tmpb ==union_swan[1] 
										|| tmpb==union_swan[0] && tmpa ==union_swan[1]) {
									ans=0;
									isans=1;
									break;
								}
								
								//백조가 존재하면 루트가 백조가 되게함
								if(tmpa==union_swan[0] || tmpa==union_swan[1]) {
									parent[tmpb]=tmpa;
								}else{
									parent[tmpa]=tmpb;
								}
							}
						}
					}
				}
				if(isans!=0)
					break;
			}
			if(isans!=0)
				break;
		}
		
		//2. 하루가 지날때마가 얼음이 녹게되고, 얼음이 녹으면서 물구역이 합쳐지게됨 (합쳐질때 다른 두 영역에 백조가 만나면 끝)
		while(!melt.isEmpty() && isans==0) {
			Node cur=melt.poll();
			int x=cur.x;
			int y=cur.y;
			int time=cur.time;
			
			arr[x][y]='.';
			
			//얼음이 녹고 주변을 확인해서 
			//얼음이 있으면 다음 녹을 차례니까 큐에 집어넣고
			//물이 있으면 같은 유니온이 되어야하니까 합쳐줘
			for(int i=0;i<4;i++) {
				int nx=x+dir[i][0];
				int ny=y+dir[i][1];
				
				if(nx<0 || nx>=r || ny<0 || ny>=c)
					continue;
				
				if(arr[nx][ny]=='X') {//얼음을 만나면 다음으로 녹은 차례니까 큐에 넣음
					if(isvisited[nx][ny])
						continue;
					melt.offer(new Node(nx,ny,time+1));
					isvisited[nx][ny]=true;
				}else {//물을 만나면 같은 유니온으로 합쳐
					int a=x*c+y;
					int b=nx*c+ny;
					int tmpa=find(a);//이미 넣었던거 또 넣는경우 생김 isvisited처리해줘야함
					int tmpb=find(b);
					if(tmpa!=tmpb) {
						//백조가 만난경우
						if((tmpa==union_swan[0] && tmpb ==union_swan[1]) 
								|| (tmpb==union_swan[0] && tmpa ==union_swan[1])) {
							ans=time+1;
							isans=1;							
							break;
						}
						
						//백조가 존재하면 루트가 백조가 되게함
						if(tmpa==union_swan[0] || tmpa==union_swan[1]) {
							parent[tmpb]=tmpa;
						}else{
							parent[tmpa]=tmpb;
						}
					}
				}
			}			
		}
		
		
		pw.print(ans);
		pw.flush();
		br.close();
		pw.close();
	}
}
