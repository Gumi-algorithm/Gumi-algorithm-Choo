import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_2812_크게만들기 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		String[] strarr = br.readLine().split(" ");

		int n = Integer.parseInt(strarr[0]);
		int k = Integer.parseInt(strarr[1]);

		int[] stk = new int[n];// 스택
		int stkidx = 0;

		String str = br.readLine();
		int[] arr = new int[n];// 인풋 받는곳

		for (int i = 0; i < n; i++) {
			arr[i] = str.charAt(i) - '0';// 값
		}
		int idx = 0;
		int delcnt = 0;
		while (delcnt < k) {// 스택이랑 i인덱스비교
			//배열꺼 다썻으면 스택에서 비교해야됨
			if(idx>=n) {
				stkidx--;
				delcnt++;
				continue;
			}
			// (스택이 빌경우)
			if (stkidx == 0) {
				// 스택에 i삽입
				stk[stkidx++] = arr[idx++];
				continue;
			}
			// (스택의 값이 i보다 큰경우)
			if (stk[stkidx - 1] >= arr[idx]) {
				// 일단 삽입
				stk[stkidx++] = arr[idx++];
				continue;
			}
			// (스택의 값이 i보다 작은경우)
			while (stk[stkidx - 1] < arr[idx]) {
				// 스택에서 값 빼서 버려
				stkidx--;
				delcnt++;
				if (stkidx == 0)
					break;
				if (delcnt >= k)
					break;
			}
		}
		// 스택에 있던값 먼저 출력
		for (int i = 0; i < stkidx; i++)
			sb.append(stk[i]);

		// 배열에 남아잇던거 출력
		for (; idx < n; idx++) {
			sb.append(arr[idx]);
		}
		if(sb.length()==0)//아무것도 없을경우
			sb.append(0);
		pw.print(sb);
		pw.flush();
		br.close();
		pw.close();
	}
}
/*
처음방법: 작은 숫자들을 모두 빼버리자
반례 
7 1
1216021
답:216021
작은숫자부터 없애면 0없앰 답 x


1. 값이 같은경우 고려안함
4 2
1111
무한반복

2. 다 지워지는경우
4 4
1234
0나오게 고침

3. 
7 3
7654321
답 7654인데
7123나옴
(스택의 값이 i보다 큰경우)
스택에 넣엇어야됫는데
i를 없애버림
 */
