import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1600_말이되고픈원숭이 {

	static int w, h;
	static int[][] arr;
	static int[][] horse = { { -1, -2 }, { -2, -1 }, { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 } };
	static int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static boolean[][][] isvisited;

	static class Node {
		int x, y;
		int k;
		int len;

		public Node(int x, int y, int k, int len) {
			this.x = x;
			this.y = y;
			this.k = k;
			this.len = len;
		}
//		@Override
//		public String toString() {
//			return "Node [x=" + x + ", y=" + y + ", k=" + k + ", len=" + len + "]";
//		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int k = Integer.parseInt(br.readLine());
		String[] str = br.readLine().split(" ");

		w = Integer.parseInt(str[0]);
		h = Integer.parseInt(str[1]);
		arr = new int[h][w];

		for (int i = 0; i < h; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < w; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(0, 0, k, 0));
		isvisited = new boolean[k+1][h][w];//k는 현재 k를 쓴횟수의 방문체크 한번도 안썻으면 [0][h][w]
		int isend=-1;
		while (!q.isEmpty()) {
			Node tmp=q.poll();
			int x = tmp.x;
			int y = tmp.y;
			int nk = tmp.k;//현재 남은 말 점프 수
			int len = tmp.len;

			if(x==h-1 && y==w-1) {
				isend=len;
				break;
			}
				
			
			//원숭이 움직임
			for (int i = 0; i < 4; i++) {
				int nx = x + dir[i][0];
				int ny = y + dir[i][1];

				//인덱스 범위체크
				if (nx < 0 || nx >= h || ny < 0 || ny >= w)
					continue;
				//방문여부 체크
				if (isvisited[k-nk][nx][ny])
					continue;
				//장애물 체크
				if(arr[nx][ny]==1)
					continue;
				
				if(nx==h-1 && ny==w-1) {
					isend=len+1;
					break;
				}
				
//				for(int j=k-nk;j<=k;j++)
//					isvisited[j][nx][ny] = true;
				isvisited[k-nk][nx][ny] = true;
				
				q.offer(new Node(nx, ny, nk, len + 1));
			}
			if(isend!=-1)
				break;
			//말점프
			if(nk<=0) 
				continue;
			nk--;
			for(int i=0;i<8;i++) {
				int nx = x + horse[i][0];
				int ny = y + horse[i][1];

				//인덱스 범위체크
				if (nx < 0 || nx >= h || ny < 0 || ny >= w)
					continue;
				//방문여부 체크
				if (isvisited[k-nk][nx][ny])
					continue;
				//장애물 체크
				if(arr[nx][ny]==1)
					continue;
				
				if(nx==h-1 && ny==w-1) {
					isend=len+1;
					break;
				}
				
//				for(int j=k-nk;j<=k;j++)
//					isvisited[j][nx][ny] = true;
				isvisited[k-nk][nx][ny] = true;
				q.offer(new Node(nx, ny, nk, len + 1));
			}	
			if(isend!=-1)
				break;
		}
		pw.print(isend);
		br.close();
		pw.flush();
		pw.close();
	}
}