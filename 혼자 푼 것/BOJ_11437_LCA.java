import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//설명: https://blog.naver.com/ndb796/221282478466
public class BOJ_11437_LCA {

	static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
	static int[][] parent;
	static int[] depth;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n = Integer.parseInt(br.readLine());
		String[] str;
		// tree사이즈 초기화
		for (int i = 0; i <= n; i++) {
			tree.add(new ArrayList<>());
		}
		parent = new int[n + 1][17];// 2^16>50000;
		depth = new int[n + 1];

		// 트리 생성
		for (int i = 0; i < n - 1; i++) {
			str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			tree.get(a).add(b);
			tree.get(b).add(a);
		}

		// 깊이 지정(루트깊이 1)
		int[] isvisited = new int[n + 1];
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { 1, 1 });
		isvisited[1] = 1;
		isvisited[0] = 1;
		depth[1] = 1;
		while (!q.isEmpty()) {
			int nowval = q.peek()[0];
			int nowdep = q.poll()[1];
			ArrayList<Integer> now = tree.get(nowval);
			int size = now.size();
			for (int i = 0; i < size; i++) {
				int nowchild = now.get(i);
				if (isvisited[nowchild] == 1)
					continue;
				isvisited[nowchild] = 1;
				depth[nowchild] = nowdep + 1;
				parent[nowchild][0] = nowval;
				q.offer(new int[] { nowchild, nowdep + 1 });
			}
		}

		// 부모관계 설정
		for (int j = 1; j <= 16; j++) {// 2^16>50000
			for (int i = 0; i <= n; i++) {
				parent[i][j] = parent[parent[i][j - 1]][j - 1];
			}
		}

		int ret = 0;
		// 이제 공통부모 찾아
		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			// a에 깊이 깊은애(큰 애)가 들어가게
			if (depth[a] < depth[b]) {
				int tmp = a;
				a = b;
				b = tmp;
			}

			// a의 깊이를 b의 깊이와 같게 만들어
			for (int j = 16 - 1; j >= 0; j--) {
				if (depth[a] - depth[b] >= (1 << j)) {
					a = parent[a][j];
				}
			}

			// 깊이가 같아졋으면 부모가 같아지면 멈춰
			if (a == b)
				ret = a;
			else {
				for (int j = 16 - 1; j >= 0; j--) {
					// 조상을 향해 거슬러 올라감
					if (parent[a][j] != parent[b][j]) {
						a = parent[a][j];
						b = parent[b][j];
					}
				}
				ret = parent[a][0];
			}
			pw.print(ret + "\n");
		}
		pw.flush();
		pw.close();
		br.close();
	}
}