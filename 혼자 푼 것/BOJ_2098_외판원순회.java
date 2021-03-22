
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_2098_외판원순회 {

	// tsp(dp+bitmasking)
	static int n;
	static int[][] arr;
	static int[][] mem;
	static int[] isvisited;

	static int bruteForce(int city, int flag) {// city:현재 방문도시, flag: 비트마스킹
		// 다돌았으면 다시 0번으로 가는거 생각해서 리턴해줘
		if (flag == (1 << n) - 1) {
			// 만약 마지막도시에서 출발점0으로 못돌아가는 경우
			if (arr[city][0] == 0) {
				return 10000000;// 큰값을 리턴해버려
			}

			return arr[city][0];
		}
		// 이미 해당 경로로 해당도시 방문했으면 메모이제이션 한거 리턴
		int now = mem[flag][city];
		if (now != 0)
			return now;

		// DFS돌면서 완탐
		for (int i = 0; i < n; i++) {

			// 방문했던곳 다시 못감
			if (isvisited[i] == 1)
				continue;

			// 연결안된도시 못가
			if (arr[city][i] == 0)
				continue;

			// 방문 표시
			isvisited[i] = 1;

			int tmp = bruteForce(i, flag | (1 << i));
			isvisited[i] = 0;
			// 갈수있는곳이 없으면 넘겨
			if (tmp == 0)
				continue;

			if (mem[flag][city] == 0)
				mem[flag][city] = tmp + arr[city][i];
			mem[flag][city] = Math.min(mem[flag][city], tmp + arr[city][i]);
		}

		return mem[flag][city];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];

		// n=최대 16 2^(16-1)= 32768
		mem = new int[(1 << n)][n];
		isvisited = new int[n];

		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		isvisited[0] = 1;
		int ans = bruteForce(0, 1);

		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}