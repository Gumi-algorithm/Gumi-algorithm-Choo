import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_6987_월드컵 {

	static int[] w = new int[6];
	static int[] d = new int[6];
	static int[] l = new int[6];
	static int ans = 0;

	static void dfs(int a, int b) {

		if (a == 5) {
			for (int i = 0; i < 6; i++) {
				if (w[i] != 0 && d[i] != 0 && l[i] != 0)
					return;
			}
			ans = 1;
			return;
		}

		// a가 이기는 경우
		if (w[a] > 0 && l[b] > 0) {
			w[a]--;
			l[b]--;
			if (b + 1 >= 6)
				dfs(a + 1, a + 2);
			else
				dfs(a, b + 1);
			w[a]++;
			l[b]++;
		}
		if (ans == 1)
			return;

		// 무승부
		if (d[a] > 0 && d[b] > 0) {
			d[a]--;
			d[b]--;
			if (b + 1 >= 6)
				dfs(a + 1, a + 2);
			else
				dfs(a, b + 1);
			d[a]++;
			d[b]++;
		}
		if (ans == 1)
			return;

		// a가 지는 경우
		if (l[a] > 0 && w[b] > 0) {
			l[a]--;
			w[b]--;
			if (b + 1 >= 6)
				dfs(a + 1, a + 2);
			else
				dfs(a, b + 1);
			l[a]++;
			w[b]++;
		}
		return;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int t = 4;
		while (t-- > 0) {
			ans = 0;
			String[] str = br.readLine().split(" ");
			int total = 0;
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 3; j++) {
					int now = Integer.parseInt(str[i * 3 + j]);
					if (j == 0)
						w[i] = now;
					else if (j == 1)
						d[i] = now;
					else
						l[i] = now;
					total += now;
				}
			}

			if (total != 30)
				pw.print("0 ");
			else {
				dfs(0, 1);
				pw.print(ans + " ");
			}
		}

		br.close();
		pw.flush();
		pw.close();
	}
}