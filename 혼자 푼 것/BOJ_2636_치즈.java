import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2636_치즈 {

	static int arr[][];
	static int n, m;

	// 0,0부터 BFS탐색하며 공기부분을 전부 -1로 바꿔 그리고 공기부분기준 상하좌우에 치즈가 있으면 -1로 바꿔 대신 큐에는 넣지마
	static void bfs0() {
		int x = 0, y = 0;
		Queue<int[]> q = new LinkedList<>();

		q.offer(new int[] { x, y });
		arr[x][y] = 2;
		while (!q.isEmpty()) {
			x = q.peek()[0];
			y = q.poll()[1];

			if (x + 1 < n && arr[x + 1][y] != 2) {
				if (arr[x + 1][y] == 0)
					q.offer(new int[] { x + 1, y });
				arr[x + 1][y] = 2;
			}
			if (x - 1 >= 0 && arr[x - 1][y] != 2) {
				if (arr[x - 1][y] == 0)
					q.offer(new int[] { x - 1, y });
				arr[x - 1][y] = 2;
			}
			if (y + 1 < m && arr[x][y + 1] != 2) {
				if (arr[x][y + 1] == 0)
					q.offer(new int[] { x, y + 1 });
				arr[x][y + 1] = 2;
			}
			if (y - 1 >= 0 && arr[x][y - 1] != 2) {
				if (arr[x][y - 1] == 0)
					q.offer(new int[] { x, y - 1 });
				arr[x][y - 1] = 2;
			}
		}
	}

	static int melt() {

		int num = 0;//1의 갯수 리턴
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 2) {
					arr[i][j] = 0;
				}
				if(arr[i][j]==1)
					num++;
			}
		}
		

		return num;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		m = Integer.parseInt(str[1]);

		arr = new int[n][m];

		int ans = 0;
		int num = 0;//BFS돌고난후 1의 갯수를 저장해
		int pre = 0;//BFS돌기전 1의 갯수를 저장해
		//num이 0이면 치즈가 다녹았으니까 BFS돌기전인 pre에 저장된 1의갯수를 출력하면됨
		
		for (int i = 0; i < n; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
				if(arr[i][j]==1)
					pre++;
			}
		}
		
		while (true) {
			// 0,0인 지점에서 탐색을 통해 연결되어있는부분을 전부 -1로 바꾸고(공기가 있는부분인거임) 그부분기준 상하좌우에 1인곳(치즈)이 있으면
			// 거기도 -1로 바꿔버려(-1은 이미 방문햇다는거임)
			bfs0();
			// 그리고 -1해놧던곳 전부 0으로 바꾸고 다시 반복
			num = melt();
			if (num == 0)
				break;
			pre = num;
			ans++;
		}
		pw.print((ans+1) + "\n" + pre);
		pw.flush();
		pw.close();
		br.close();
	}
}

/*
5 5
0 0 0 0 0
0 1 1 1 0
0 1 0 1 0
0 1 1 1 0
0 0 0 0 0
답 1 8

5 6 
0 0 0 0 0 0 
0 1 1 1 1 0
0 1 0 1 1 0
0 1 1 1 1 0
0 0 0 0 0 0
답 2 1

*/