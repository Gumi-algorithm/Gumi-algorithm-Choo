import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1219_오민식의고민 {

	static class Edge {
		int city;
		int weight;

		public Edge(int city, int weight) {
			super();
			this.city = city;
			this.weight = weight;
		}
	}

	static class Vertex {
		ArrayList<Edge> edge = new ArrayList<>();
		int city;
		int sellmoney;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n, s, e, m;
		String[] str = br.readLine().split(" ");

		n = Integer.parseInt(str[0]);
		s = Integer.parseInt(str[1]);
		e = Integer.parseInt(str[2]);
		m = Integer.parseInt(str[3]);

		Vertex[] v = new Vertex[n];

		for (int i = 0; i < m; i++) {
			str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			int w = Integer.parseInt(str[2]);

			if (v[a] == null)
				v[a] = new Vertex();
			v[a].edge.add(new Edge(b, w));
		}
		str = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			if (v[i] == null)
				v[i] = new Vertex();
			v[i].sellmoney = Integer.parseInt(str[i]);
		}

		// 벨만포드
		long[] weight = new long[n];// 가중치
		Arrays.fill(weight, -210000000);// inf값 할당

		weight[s] = v[s].sellmoney;
		int ischange = 0;
		int isendchange = 0;
		int idx = 0;
		Queue<Integer> cycle = new LinkedList<Integer>();
		while (true) {
			ischange = 0;
			for (int i = 0; i < n; i++) {
				if (weight[i] == -210000000)
					continue;
				for (int j = 0; j < v[i].edge.size(); j++) {
					Edge cur = v[i].edge.get(j);
					long tmp = weight[i] - cur.weight + v[cur.city].sellmoney;// 이전 가중치-에지의 가중치 + 도시에서 판매이익;
					if (weight[cur.city] < tmp) {
						weight[cur.city] = tmp;
						ischange = 1;
						if (idx == n)
							cycle.add(cur.city);
					}
				}
			}

			// 사이클 n번 돌때까지 계속 갱신되면 돈 무한정 벌 수 있는거임
			if (idx == n)
				break;
			if (ischange == 0)
				break;

			idx++;
		}

		// 사이클이 생겼던 정점에서 e까지 도달할수 있는지 체크
		// 도달할수 있다면 Gee
		// 도달할수 없다면 weight[e]
		int[] isvisited = new int[n];
		Queue<Integer> q = new LinkedList<>();
		int isarrived = 0;// e에 도달할수 있는지
		while (!cycle.isEmpty()) {
			Arrays.fill(isvisited, 0);

			int num = cycle.poll();
			q.add(num);
			isvisited[num] = 1;
			while (!q.isEmpty()) {
				int cur = q.poll();

				if (cur == e) {
					isarrived = 1;
					break;
				}

				for (int i = 0; i < v[cur].edge.size(); i++) {
					int next = v[cur].edge.get(i).city;
					if (isvisited[next] != 0)
						continue;
					isvisited[next] = 1;
					q.add(next);
				}
			}
			if (isarrived == 1)
				break;
		}

		// 시작점에서 끝에 도달할 수 없는경우
		if (weight[e] == -210000000)
			pw.print("gg");
		else if (ischange == 1 && isarrived == 1) {// 무한히 돈을 벌 수 있고 e에 도달할 수 있는 경우
			pw.print("Gee");
		} else
			pw.print(weight[e]);
		br.close();
		pw.flush();
		pw.close();
	}
}