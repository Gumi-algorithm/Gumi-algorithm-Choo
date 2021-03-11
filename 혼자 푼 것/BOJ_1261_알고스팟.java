import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;

//0-1 BFS
public class BOJ_1261_알고스팟 {
	static int[][] arr;
	static int[][] direction = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
	static int n, m;



	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String[] strarr = br.readLine().split(" ");
		m = Integer.parseInt(strarr[0]);
		n = Integer.parseInt(strarr[1]);
		arr = new int[n][m];

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		int ans=0;
		Deque< int[] > dq = new ArrayDeque<>();
		dq.offerFirst(new int[] {0,0,0});
		arr[0][0]=-1;
		//가중치가 1이기때문에 BFS를 돌리면 데크에 벽갯수가 오름차순으로 정렬됨
		//그래서 처음 도착지에 도착하는애를 찾으면 최소값임
		while(!dq.isEmpty()) {
			//데크에서 앞에서 부터 뽑아와
			int x=dq.peekFirst()[0];
			int y=dq.peekFirst()[1];
			int cnt=dq.pollFirst()[2];
			
			if(x==n-1 && y==m-1) {
				ans=cnt;
				break;
			}
			for (int i = 0; i < 4; i++) {
				int nx = x + direction[i][0];
				int ny = y + direction[i][1];

				if (nx < 0 || nx >= n || ny < 0 || ny >= m)
					continue;
				if(arr[nx][ny]==-1)
					continue;
				
				//벽이 있으면 뒤에 넣고
				if (arr[nx][ny] == 1) {
					dq.offerLast(new int[] {nx,ny,cnt+1});
				}else {//벽이없으면 가중치 없으니 앞에 넣어
					dq.offerFirst(new int[] {nx,ny,cnt});
				}	
				arr[nx][ny]=-1;
			}			
		}

		pw.print(ans);

		br.close();
		pw.flush();
		pw.close();
	}
}