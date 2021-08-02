import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_4179_불 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int r, c;
		String[] str = br.readLine().split(" ");

		r = Integer.parseInt(str[0]);
		c = Integer.parseInt(str[1]);

		int[][] arr = new int[r][c];

		int jihoonX = 0;
		int jihoonY = 0;
		boolean isvisited[][] = new boolean[r][c];
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < r; i++) {
			str = br.readLine().split("");
			for (int j = 0; j < c; j++) {
				if (str[j].equals("#"))
					arr[i][j] = 1;
				if (str[j].equals("J")) {
					isvisited[i][j] = true;
					q.add(0);
					q.add(i);
					q.add(j);
					q.add(0);
				}
				if (str[j].equals("F")) {
					arr[i][j] = 1;
					q.add(1);
					q.add(i);
					q.add(j);
					q.add(0);
				}

			}
		}

		int ans = -1;
		int pretime = -1;

		int[][] direction = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
		while (!q.isEmpty()) {
			int type = q.poll();// 불인지 지훈이인지 구분
			int curx = q.poll();
			int cury = q.poll();
			int time = q.poll();

			if (type == 0) {
				if (arr[curx][cury] == 1)
					continue;

				if (curx == 0 || cury == 0 || curx == r - 1 || cury == c - 1) {
					ans = time;
					break;
				}

				// 사람 움직임
				for (int i = 0; i < 4; i++) {
					int nx = curx + direction[i][0];
					int ny = cury + direction[i][1];

					if (nx < 0 || ny < 0 || nx >= r || ny >= c)
						continue;
					if (arr[nx][ny] == 1)
						continue;
					if (isvisited[nx][ny])
						continue;
					isvisited[nx][ny] = true;
					q.add(0);
					q.add(nx);
					q.add(ny);
					q.add(time + 1);
				}
			} else {
				// 불 움직임
				for (int i = 0; i < 4; i++) {
					int nx = curx + direction[i][0];
					int ny = cury + direction[i][1];

					if (nx < 0 || ny < 0 || nx >= r || ny >= c)
						continue;
					if (arr[nx][ny] == 1)
						continue;
					q.add(1);
					q.add(nx);
					q.add(ny);
					q.add(time + 1);
					arr[nx][ny] = 1;
				}
			}
		}
		if(ans==-1)
			pw.print("IMPOSSIBLE");
		else
			pw.print(ans+1);
		pw.close();
		br.close();
	}
}
