import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_1699_제곱수의합 {
	static int mem[] = new int[200000];

	// 만약 43이 주어지면 43에 루트 씌우면 6.ㅁㅁㅁㅁ 나오는데 거기서
	// dp(43-2^2)+1부터 dp(43-6^2)+1까지 다 해봐서 제일 작은거 집어넣으면됨
	static int dp(int num) {
		if (num == 0)
			return 0;
		if (mem[num] != 0)
			return mem[num];
		int tmp1 = (int) Math.sqrt(num);

		int min = -1;
		for (int i = 2; i <= tmp1; i++) {
			if (min == -1) {
				min = dp(num - i * i) + 1;
				continue;
			}
			int tmp3 = dp(num - i * i) + 1;
			min = min > tmp3 ? tmp3 : min;
		}
		mem[num] = min;
		return mem[num];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n = Integer.parseInt(br.readLine());
		mem[0] = 0;
		mem[1] = 1;
		mem[2] = 2;
		mem[3] = 3;

		int ans = dp(n);

		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}