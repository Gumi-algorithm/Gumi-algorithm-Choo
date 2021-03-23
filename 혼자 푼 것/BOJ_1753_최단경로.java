import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;


public class BOJ_1753_최단경로 {

	static class Node implements Comparable<Node> {
		int idx, w;

		public Node(int idx, int w) {
			this.idx = idx;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb=new StringBuilder();		
		
		int v, e;
		String[] str = br.readLine().split(" ");
		v = Integer.parseInt(str[0]);
		e = Integer.parseInt(str[1]);

		int dist[] = new int[v + 1];// 다익스트라 거래 갱신하는 배열
		int isvisited[] = new int[v + 1];// 방문여부 체크

		ArrayList<Node> edge[] = new ArrayList[v + 1];// 간선정보 저장
		for (int i = 0; i <= v; i++) {
			edge[i] = new ArrayList<>();
		}

		int start = Integer.parseInt(br.readLine());

		for (int i = 0; i < e; i++) {
			str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			int w = Integer.parseInt(str[2]);
			edge[a].add(new Node(b, w));
		}

		// 일단 dist초기화해
		for (int i = 0; i <= v; i++) {
			dist[i] = -1;
		}
		dist[start] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0));// 출발 인덱스, 가중치
		while (!pq.isEmpty()) {
			// 현재 연결된 도시중 가중치가 가장 작은 도시를 골라 다음 도시로 선정해
			start = pq.peek().idx;
			int noww = pq.poll().w;

			if (isvisited[start] == 1)
				continue;

			isvisited[start] = 1;

			// 추가된 간선정보를 기존 간선정보와 비교하여 최소값 갱신해
			int size = edge[start].size();
			for (int i = 0; i < size; i++) {
				int now = edge[start].get(i).idx;
				int w = edge[start].get(i).w;

				if (dist[now]==-1 || dist[now] > dist[start] + w) {
					dist[now] = dist[start] + w;
					pq.offer(new Node(now, dist[start] + w));
				}
			}
		}
		for(int i=1;i<=v;i++) {
			if(dist[i]==-1)
				sb.append("INF\n");
			else
				sb.append(dist[i]).append("\n");
			
		}
		pw.print(sb);
		br.close();
		pw.flush();
		pw.close();
	}
}