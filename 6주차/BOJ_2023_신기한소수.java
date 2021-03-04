import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//1의 자리에서 소수인거 찾아
//2의 자리: 1의자리에서 찾은거에 뒤에 숫자 붙이면서 확인해

public class BOJ_2023_신기한소수 {

	// 소수인지 확인
	// ex) 16의 경우 (1,16) (2,8) (4,4)까지 만 체크하면
	// (8,2) (16,1)은 중복임 그래서 sqrt(16)까지만 확인하면됨
	static boolean isPrime(int n) {
		if (n < 2)
			return false;
		for (int i = 2; i * i <= n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb=new StringBuilder();
		
		int n = Integer.parseInt(br.readLine());

		// 한자리수부터 n자리 수까지 반복하며 BFS돌림
		ArrayList<Integer> ans = new ArrayList<>();
		Queue<int[]> q = new LinkedList<>();
		// 우선 큐에 1의 자리 소수들을 집어넣어(숫자, 몇자리인지)
		q.offer(new int[] { 2, 1 });
		q.offer(new int[] { 3, 1 });
		q.offer(new int[] { 5, 1 });
		q.offer(new int[] { 7, 1 });
		while (!q.isEmpty()) {
			int nownum = q.peek()[0];
			int numsize = q.poll()[1];

			// n자리수를 찾으면 정답에 넣고 더이상 뒤에 숫자 안붙이고 다음꺼 확인
			if (numsize == n) {
				ans.add(nownum);
				continue;
			}
			for (int i = 1; i < 10; i++) {
				int tmp = nownum * 10 + i;
				if (tmp%2!=0 && isPrime(tmp)) {//짝수가 아니면서 소수인애들 체크함(짝수인 소수는 2밖에 없으니 이거넣으면 더 빠를거같음)
					q.offer(new int[] { tmp, numsize + 1 });
				}
			}
		}
		//큐에 작은숫자부터 넣으므로 정렬 필요없음
		for(int a:ans) {
			sb.append(a).append("\n");
		}
		pw.print(sb);
		br.close();
		pw.flush();
		pw.close();
	}
}