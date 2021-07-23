import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_14620_꽃길 {
	static int n;
	static int[][] arr;
	static int[][] flower;
	static int ans = 20001;

	static void dfs(int x, int y, int cnt, int cost) {

		if (cnt == 3) {
			ans = Math.min(ans, cost);
			return;
		}

		for (int i = x; i < n-1; i++) {
			for (int j = 1; j < n-1; j++) {
				if (i == x && j == 1)
					j = y;
				if (j >= n-1)
					break;

				if (flower[i][j] == 1 || flower[i + 1][j] == 1 || flower[i - 1][j] == 1 || flower[i][j + 1] == 1
						|| flower[i][j - 1] == 1) {
					continue;
				}

					flower[i][j] = 1;
				flower[i + 1][j] = 1;
				flower[i - 1][j] = 1;
				flower[i][j + 1] = 1;
				flower[i][j - 1] = 1;
				
				int sumcost=cost+arr[i][j]+arr[i+1][j]+arr[i-1][j]+arr[i][j-1]+arr[i][j+1];
				dfs(i, j + 2, cnt + 1, sumcost);

				flower[i][j] = 0;
				flower[i + 1][j] = 0;
				flower[i - 1][j] = 0;
				flower[i][j + 1] = 0;
				flower[i][j - 1] = 0;

			}

		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		n = Integer.parseInt(br.readLine());

		arr = new int[n][n];
		flower = new int[n][n];

		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}

		dfs(1,1,0,0);
		
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}
