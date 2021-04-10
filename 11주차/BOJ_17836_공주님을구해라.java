import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_17836_공주님을구해라 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n, m, t;
		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);
		t = Integer.parseInt(str[2]);

		int[][] arr = new int[n][m];
		int[][][] isvisited = new int[2][n][m];// 0: 검을 가지고있지 않을때, 1:검을 가지고있을때

		// 입력
		for (int i = 0; i < n; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(0);// x
		q.offer(0);// y
		q.offer(0);// 검 을 가졋는지 여부
		q.offer(0);// 시간
		isvisited[0][0][0] = 1;
		int ans=-1;
		while (!q.isEmpty()) {
			int x = q.poll();
			int y = q.poll();
			int s = q.poll();
			int time=q.poll();

			if(time>t)
				continue;
			
			if(x==n-1 && y==m-1) {
				ans=time;
				break;
			}
				
			
			for (int i = 0; i < 4; i++) {
				int nx=x+dir[i][0];
				int ny=y+dir[i][1];
				int ns=s;
				if(nx>=n || nx<0 || ny>=m || ny<0) {
					continue;
				}
				//검 없을때 벽을 만나면 패스
				if(ns==0 && arr[nx][ny]==1)
					continue;
				//검을 주음
				if(arr[nx][ny]==2) {
					ns=1;
				}
				//이미 방문한 곳이면 패스
				if(isvisited[ns][nx][ny]==1)
					continue;
				
				isvisited[ns][nx][ny]=1;
				
				q.offer(nx);
				q.offer(ny);
				q.offer(ns);
				q.offer(time+1);
			}
		}
		if(ans==-1)
			pw.print("Fail");
		else
			pw.print(ans);
		
		br.close();
		pw.flush();
		pw.close();
	}
}