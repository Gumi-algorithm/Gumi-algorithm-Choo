
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class BOJ_19942_다이어트 {

	static int n;
	static int[][] arr;
	static int[] minval;

	static int[] stk;
	static int stkidx = 0;
	static int ansWeight = 10000;
	static List<Integer> ansList = new ArrayList<>();

	static void dfs(int idx, int[] sum, int cnt, int weight) {

		if (weight >= ansWeight)
			return;

		if (sum[0] >= minval[0] && sum[1] >= minval[1] && sum[2] >= minval[2] && sum[3] >= minval[3]) {
			if (ansWeight > weight) {
				ansWeight = weight;
				ansList.clear();
				for (int i = 0; i < stkidx; i++)
					ansList.add(stk[i]);
			}
			return;
		}
		if (idx == n)
			return;
		// 고르는경우
		sum[0] += arr[idx][0];
		sum[1] += arr[idx][1];
		sum[2] += arr[idx][2];
		sum[3] += arr[idx][3];
		stk[stkidx++] = idx;
		dfs(idx + 1, sum, cnt + 1, weight + arr[idx][4]);

		// 안고르는경우
		stkidx--;
		sum[0] -= arr[idx][0];
		sum[1] -= arr[idx][1];
		sum[2] -= arr[idx][2];
		sum[3] -= arr[idx][3];
		dfs(idx + 1, sum, cnt, weight);

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		n = Integer.parseInt(br.readLine());
		arr = new int[n][5];
		minval = new int[4];
		stk = new int[n];
		for (int i = 0; i < n + 1; i++) {
			String[] str = br.readLine().split(" ");

			if (i == 0) {
				minval[0] = Integer.parseInt(str[0]);
				minval[1] = Integer.parseInt(str[1]);
				minval[2] = Integer.parseInt(str[2]);
				minval[3] = Integer.parseInt(str[3]);
				continue;
			}
			arr[i - 1][0] = Integer.parseInt(str[0]);
			arr[i - 1][1] = Integer.parseInt(str[1]);
			arr[i - 1][2] = Integer.parseInt(str[2]);
			arr[i - 1][3] = Integer.parseInt(str[3]);
			arr[i - 1][4] = Integer.parseInt(str[4]);
		}
		int[] sum = new int[4];
		dfs(0, sum, 0, 0);

		if (ansWeight == 10000) {
			pw.print(-1);
		} else {
			pw.println(ansWeight);
			for (int ans : ansList)
				pw.print(ans + 1 + " ");
		}
		pw.close();
		br.close();
	}
}