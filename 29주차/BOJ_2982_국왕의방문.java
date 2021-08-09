import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class BOJ_2982_국왕의방문 {

	private static class Node {
		int department;
		int weight;
		int nowGodulaIdx;// 고둘라 위치한 교차로
		int restTime;// 위치한 교차로 끝날때까지 남은시간

		public Node(int department, int weight) {
			super();
			this.department = department;
			this.weight = weight;
		}

		public Node(int department, int weight, int nowGodulaIdx, int restTiem) {
			super();
			this.department = department;
			this.weight = weight;
			this.nowGodulaIdx = nowGodulaIdx;
			this.restTime = restTiem;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n, m;
		String[] str = br.readLine().split(" ");

		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);

		int a, b, k, g;
		str = br.readLine().split(" ");

		a = Integer.parseInt(str[0]);
		b = Integer.parseInt(str[1]);
		k = Integer.parseInt(str[2]);
		g = Integer.parseInt(str[3]);

		int cross[] = new int[g];// 고둘라가 지나가는 교차로
		int gtime[] = new int[g - 1];
		str = br.readLine().split(" ");
		for (int i = 0; i < g; i++) {
			cross[i] = Integer.parseInt(str[i]);
		}

		List<Node>[] road = new ArrayList[n + 1];

		for (int i = 0; i < n + 1; i++) {
			road[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			str = br.readLine().split(" ");
			int start = Integer.parseInt(str[0]);
			int end = Integer.parseInt(str[1]);
			int weight = Integer.parseInt(str[2]);

			// 양방향
			road[start].add(new Node(end, weight));
			road[end].add(new Node(start, weight));
		}
		// 고돌라 지나가는 시간
		for (int i = 0; i < g - 1; i++) {
			for (Node node : road[cross[i]]) {
				if (node.department == cross[i + 1]) {
					gtime[i] = node.weight;
				}
			}
		}

		int[] dist = new int[n + 1];
		boolean[] isvisited = new boolean[n + 1];
		Arrays.fill(dist, 10000 * 1000);// M*L 최댓값
		dist[a] = 0;

		// k시간만큼 기다린다
		int gidx = 0;
		while (true) {
			k = k - gtime[gidx];
			if (k < 0) {
				k *=-1;
				break;
			}
			gidx++;
		}

		PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
		q.add(new Node(a, 0, gidx, k));

		while (!q.isEmpty()) {
			Node cur = q.poll();
			if (isvisited[cur.department])
				continue;
			isvisited[cur.department] = true;
			dist[cur.department] = cur.weight;

			for (Node node : road[cur.department]) {
				int next = node.department;
				// 이미 최소시간 정해지면 넘어감
				if (isvisited[next])
					continue;

				int restTime = cur.restTime;
				int godulaIdx = cur.nowGodulaIdx;
				int nextWeight = cur.weight;
				// 고둘라가 다 지나가면 조건들 생각하지마
				if (godulaIdx < g-1) {
					// 진행방향에 고둘라가 지나는 중인 경우 기다린 시간만큼 더해야함
					if ((cur.department == cross[godulaIdx] && node.department == cross[godulaIdx + 1])
							|| (cur.department == cross[godulaIdx + 1] && node.department == cross[godulaIdx])) {
						nextWeight += cur.restTime + node.weight;
						godulaIdx++;
						if (godulaIdx < g-1) {
							restTime = gtime[godulaIdx]-node.weight;
							while (restTime <= 0) {//상근이가 길 1개지나는동안 왕이 길 여러개 지날 수도 있음
								godulaIdx += 1;
								if (godulaIdx < g-1)
									restTime = gtime[godulaIdx] + restTime;
								else {
									restTime = 0;
									break;
								}
							}
						}
						else
							restTime = 0;
					} else {// 진행방향에 고돌라가 없는경우
						restTime -= node.weight;
						while (restTime <= 0) {//상근이가 길 1개지나는동안 왕이 길 여러개 지날 수도 있음
							godulaIdx += 1;
							if (godulaIdx < g-1)
								restTime = gtime[godulaIdx] + restTime;
							else {
								restTime = 0;
								break;
							}
						}
						nextWeight += node.weight;
					}
				}else
					nextWeight += node.weight;
				q.add(new Node(node.department, nextWeight, godulaIdx, restTime));
			}

		}

		pw.print(dist[b]);
		pw.close();
		br.close();
	}

}
