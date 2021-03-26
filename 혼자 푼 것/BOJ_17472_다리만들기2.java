import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ_17472_다리만들기2 {

	static class Node implements Comparable<Node>{
		int num,w;

		public Node(int num, int w) {
			super();
			this.num = num;
			this.w = w;
		}

		@Override
		public String toString() {
			return "Node [num=" + num + ", w=" + w + "]";
		}

		@Override
		public int compareTo(Node o) {
			return this.w-o.w;
		}
		
	}
	
	static int[][] arr;
	static int[][] isvisited;// 여기도 arr이랑 똑같이 땅부분 1로 받음
	static ArrayList<Node> edge[];// 간선 (섬 번호, 가중치)
	static ArrayList<int[]> border[];// 가장자리(x,y, 방향)

	static int n, m;

	static int[][] direction = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	// 번호부여, 각 섬의 가장자리 찾아
	static int dfs_color(int x, int y, int num) {
		if (isvisited[x][y] != 1)// 땅 아니면 리턴
			return 0;
		arr[x][y]=num;
		isvisited[x][y] = -1;// 방문한 땅은 -1
		int ret = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + direction[i][0];
			int ny = y + direction[i][1];

			if (nx >= n || nx < 0 || ny >= m || ny < 0) {
				continue;
			}

			// 근처에 바다가 있으면(가장자리면)
			if (isvisited[nx][ny] == 0) {
				border[num].add(new int[] { x, y, i });// 좌표, 방향(0:위, 1:오른, 2:아래, 3:왼)
			}
			dfs_color(nx, ny, num);
			ret = 1;
		}
		return ret;
	}

	// 각 섬의 가장자리에서 쭉 뻗어가서 다른 섬과 이어지는지 확인(이어지면 가중치 리턴/안이어지면 -1 리턴)
	static int getedge(int x, int y, int dir,int start) {
		int nx = x;
		int ny = y;
		int w=0;
		while (true) {
			nx += direction[dir][0];
			ny += direction[dir][1];
			w++;
			// 범위 벗어나면 탈출
			if (nx >= n || nx < 0 || ny >= m || ny < 0) {
				return -1;
			}
			//섬 내부의 바다를 돌경우 그냥 리턴
			if(arr[nx][ny]==start)
				return -1;
			
			//다른섬을 만나면 해당섬 만나기까지 가중치 리턴
			if(arr[nx][ny]!=0) {
				return w;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);

		arr = new int[n][m];
		isvisited = new int[n][m];

		// input
		for (int i = 0; i < n; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
				isvisited[i][j] = arr[i][j];
			}
		}

		edge = new ArrayList[7];
		border = new ArrayList[7];
		for (int i = 0; i < 7; i++) {
			edge[i] = new ArrayList<>();
			border[i] = new ArrayList<>();
		}

		// 그래프 생성
		// arr에 각 섬들의 번호를 부여한뒤
		// 각 섬들의 가장자리들을 저장해 놓고
		// 나중에 가장자리에서 1자로 쭈욱 뻗어서 닿는 섬이 있으면 간선이 연결된 걸로해

		// arr의 가장자리 찾아
		int num = 1;//섬 갯수
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (dfs_color(i, j, num) == 1) {
					num++;
				}
			}
		}

		// 가장자리에서 방향대로 쭈욱 뻗어서 간선 생성해
		for (int i = 1; i < num; i++) {
			int size = border[i].size();
			for (int j = 0; j < size; j++) {
				int[] now = border[i].get(j);
				int ret=getedge(now[0], now[1], now[2],i);
				if(ret<0) //안이어지는경우
					continue;
				
				//연결된 섬의 좌표(가장자리)
				int nx=now[0]+direction[now[2]][0]*ret;
				int ny=now[1]+direction[now[2]][1]*ret;
				int nnum=arr[nx][ny];
				if(ret-1>=2)
					edge[i].add(new Node(nnum,ret-1));//연결된 섬 번호, 가중치				
			}
		}

		// 최소 스패닝트리알고리즘(프림)
		// 생성된 그래프를 바탕으로 mst알고리즘 돌려 
		PriorityQueue<Node> pq =new PriorityQueue<>();//섬 번호, 가중치
		int dist[]=new int[num];
		int p_isvisited[]=new int[num];
		Arrays.fill(dist, 1000000000);//일단 연결안된곳은 나올수 없는 가중치로 초기화
		Arrays.fill(p_isvisited, 0);
		pq.offer(new Node(1,0));//0부터 시작
		while(!pq.isEmpty()) {
			Node tmp=pq.poll();
			int nownum=tmp.num;//현재섬 번호
			int weight=tmp.w;
			
			//이미 방문햇던건 다시 안감
			if(p_isvisited[nownum]!=0)
				continue;
			p_isvisited[nownum]=1;
			dist[nownum]=weight;
			
			//현재섬에 연결된 간선정보를 이용해 다른섬들과 연결정보 업데이트
			int size=edge[nownum].size();
			for(int i=0;i<size;i++) {
				Node next=edge[nownum].get(i);//섬번호, 가중치
				if(p_isvisited[next.num]==1)
					continue;
				
				if(dist[next.num]>next.w) {	
					dist[next.num]=next.w;
					pq.offer(new Node(next.num,dist[next.num]));
				}
			}
		}
		//스패닝 트리만들면 연결된 간선의 합 구해서 출력해
		int ans=0;
		for(int i=1;i<num;i++) {
			if(dist[i]>=1000000000) {
				ans=-1;
				break;					
			}
			ans+=dist[i];
		}		
		
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}
