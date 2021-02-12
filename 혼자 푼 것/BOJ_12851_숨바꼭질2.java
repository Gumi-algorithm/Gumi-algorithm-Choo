import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_12851_숨바꼭질2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n, k;// 수빈이위치, 동생위치
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		k = Integer.parseInt(str[1]);

		// 수빈이가 갈수 있는 경우는 +1, -1, *2 3가지 존재
		// 가장 빠른시간을 찾아야하기때문에 BFS가 좋음
		int[] isvisited = new int[200001];// 100000에서 *2할거 생각해서 크게잡음/ 방문했을때 time적어둿다가 같은 time에 방문한거면 그것도 큐에 넣어
		Arrays.fill(isvisited,100000);//일단 큰값으로 초기화 시켜줘
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { n, 0 });
		int time = 300000;// 일단 큰값 잡아둠
		int cnt = 0;
		while (!q.isEmpty()) {
			int now = q.peek()[0];
			int nowtime = q.poll()[1];
			if (nowtime > time)// 같은 시간꺼 끝나면 탈출
				break;
			if (now == k && time >= nowtime) {// 같은 시간중에서 동생있는곳 도착한것만 카운트해
				time = nowtime;
				cnt++;
			}

			// 더 짧은시간에 이미 방문햇으면 방문안해도 되니까 isvisited로 체크해줘 / 같은 시간꺼는 방문해도 되게 조건 걸어줘
			if (now - 1 >= 0 && nowtime + 1 <= isvisited[now - 1]) {// -1한경우
				isvisited[now - 1] = nowtime + 1;
				q.offer(new int[] { now - 1, nowtime + 1 });
			}
			if (now + 1 < 100000 && nowtime + 1 <= isvisited[now + 1]) {// +1한경우
				isvisited[now + 1] = nowtime + 1;
				q.offer(new int[] { now + 1, nowtime + 1 });
			}
			if (now * 2 < 200000 && nowtime + 1 <= isvisited[now * 2]) {// *2한경우
				isvisited[now * 2] = nowtime + 1;
				q.offer(new int[] { now * 2, nowtime + 1 });
			}
		}
		pw.print(time + "\n" + cnt);

		br.close();
		pw.flush();
		pw.close();
	}

}
