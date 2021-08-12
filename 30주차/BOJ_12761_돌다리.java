import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_12761_돌다리 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int a, b, n, m;
		boolean[] isvisited = new boolean[100001];

		String[] str = br.readLine().split(" ");

		a = Integer.parseInt(str[0]);
		b = Integer.parseInt(str[1]);
		n = Integer.parseInt(str[2]);
		m = Integer.parseInt(str[3]);

		Queue<Integer> q = new LinkedList<Integer>();

		q.add(n);
		q.add(0);
		isvisited[n] = true;
		int[] arr = { -1, 1, a, -1 * a, b, -1 * b, a, b };
		//뒤로한칸, 앞으로한칸, 앞으로 a칸, 뒤로 a칸, 앞으로 b칸, 뒤로 b칸, 힘모아서 a배, 힘모아서 b배
		int ans = 0;
		while (!q.isEmpty()) {
			int cur = q.poll();
			int cnt = q.poll();

			if (cur == m) {
				ans = cnt;
				break;
			}

			for (int i = 0; i < 8; i++) {
				int next = cur;
				if (i < 6)
					next += arr[i];
				else
					next *= arr[i];
				if (next < 0 || next > 100000)
					continue;
				if (isvisited[next])
					continue;

				isvisited[next] = true;
				q.add(next);
				q.add(cnt + 1);
			}
		}

		pw.print(ans);
		pw.close();
		br.close();
	}

}
