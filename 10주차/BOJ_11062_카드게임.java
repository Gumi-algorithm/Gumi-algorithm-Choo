import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_11062_카드게임 {
	// BOJ_1823_수확이랑 비슷한거임 [start][end] start~end까지 남아있을때 최선의 경우를 저장
	static int[][] mem;//[start][end] 근우의 점수를 나타내는 memoization

	static int[] arr;

	static int dp(int start, int end, int who) {// who 0: 명우, 1:근우

		if (start > end) {
			return 0;
		}

		if (mem[start][end] != 0)
			return mem[start][end];

		// 앞
		int a = dp(start + 1, end, 1 - who);
		// 뒤
		int b = dp(start, end - 1, 1 - who);

		int ans = 0;
		
		// 왜인지는 모르겠지만 근우 차례일때 최대값, 명우 차례일때 최솟값을 선택하면 최선인 결과가 나온대
		// mem에는 근우 점수만 저장하는거임 그러니까 근우차례엔 높은 점수를 고르고
		// 명우 차례엔 근우가 낮은점수를 골라야 하는거임
		if (who == 0)// 근우 차례 자기 차례니까 자기꺼 골라
			ans = Math.max(a + arr[start], b + arr[end]);
		else// 명우차례니까 근우가 작은경우를 생각하면 명우한테 최선인경우임(그리고 근우차례아니니까 arr안더함) 
			ans = Math.min(a, b);

		mem[start][end] = ans;

		return ans;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int t = Integer.parseInt(br.readLine());

		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());
			arr = new int[n];
			String[] str = br.readLine().split(" ");
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(str[i]);
			}
			mem = new int[n][n];
			for (int i = 0; i < n; i++) {
				Arrays.fill(mem[i], 0);
			}

			// 오른쪽, 왼쪽 두가지 고를수 있음
			// 결극 2^n승 완탐 불가능
			// 이것도 1 2 3 4 가 있을때
			// 1 4 를 선택해서 2 3 이 남는거랑
			// 4 1 을 선택해서 2 3 이 남는거랑 똑같은거임
			// 그래서 start~end까지 남아있을때의 최댓값을 저장해 두면됨
			int ans = dp(0, n - 1, 0);

			pw.print(ans + "\n");
		}

		br.close();
		pw.flush();
		pw.close();
	}
}