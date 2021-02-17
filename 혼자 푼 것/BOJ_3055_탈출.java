import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_3055_탈출 {
	// 가장 빠른시간이니까 BFS로 해야됨
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String[] strarr = br.readLine().split(" ");
		int r = Integer.parseInt(strarr[0]);
		int c = Integer.parseInt(strarr[1]);

		char[][] arr = new char[r][c];
		Queue<int[]> water = new LinkedList<>();
		Queue<int[]> hedgehog = new LinkedList<>();
		int[][] isvisited = new int[r][c];// 고슴도치 이동할수잇는 좌표들(물이 잇으면 고슴도치 못지나가니 물도 이 배열에 영향을줌)
		int[][] isvisited2 = new int[r][c];// 물이 이동할수 있는 좌표들

		String str;
		// 입력
		for (int i = 0; i < r; i++) {
			str = br.readLine();
			for (int j = 0; j < c; j++) {
				arr[i][j] = str.charAt(j);
				if (arr[i][j] == '*') {
					water.offer(new int[] { i, j, 0 });// 좌표, 깊이
					isvisited[i][j] = 1;
					isvisited2[i][j] = 1;					
				} else if (arr[i][j] == 'S') {
					arr[i][j] = '.';
					hedgehog.offer(new int[] { i, j, 0 });
					isvisited[i][j] = 1;
				} else if (arr[i][j] == 'X') {
					isvisited[i][j] = 1;
					isvisited2[i][j] = 1;					
				}
			}
		}

		// 물이 먼저 흐른뒤 고슴도치가 움직여야함
		int ans = -1;
		while (!hedgehog.isEmpty()) {

			// 우선 물부터 흘러
			int nowwatersize = water.size();
			for (int i = 0; i < nowwatersize; i++) {
				int x = water.peek()[0];
				int y = water.peek()[1];
				int d = water.poll()[2];

				if (x + 1 < r && isvisited2[x + 1][y] == 0 && arr[x + 1][y] != 'D') {
					isvisited[x + 1][y] = 1;// 고슴도치는 물잇는곳 못가니까 이것도 해줘야됨
					isvisited2[x + 1][y] = 1;
					arr[x + 1][y] = '*';
					water.offer(new int[] { x + 1, y, d + 1 });
				}
				if (x - 1 >= 0 && isvisited2[x - 1][y] == 0 && arr[x - 1][y] != 'D') {
					isvisited[x - 1][y] = 1;
					isvisited2[x - 1][y] = 1;
					arr[x - 1][y] = '*';
					water.offer(new int[] { x - 1, y, d + 1 });
				}
				if (y + 1 < c && isvisited2[x][y + 1] == 0 && arr[x][y + 1] != 'D') {
					isvisited[x][y + 1] = 1;
					isvisited2[x][y + 1] = 1;
					arr[x][y + 1] = '*';
					water.offer(new int[] { x, y + 1, d + 1 });
				}
				if (y - 1 >= 0 && isvisited2[x][y - 1] == 0 && arr[x][y - 1] != 'D') {
					isvisited[x][y - 1] = 1;
					isvisited2[x][y - 1] = 1;
					arr[x][y - 1] = '*';
					water.offer(new int[] { x, y - 1, d + 1 });
				}

			}

			// 이제 고슴도치 이동
			int nowhhsize = hedgehog.size();
			for (int i = 0; i < nowhhsize; i++) {
				int x = hedgehog.peek()[0];
				int y = hedgehog.peek()[1];
				int d = hedgehog.poll()[2];

				if (x + 1 < r && isvisited[x + 1][y] == 0) {
					if (arr[x + 1][y] == 'D') {
						ans = d + 1;
						break;
					}
					isvisited[x + 1][y] = 1;
					hedgehog.offer(new int[] { x + 1, y, d + 1 });
				}
				if (x - 1 >= 0 && isvisited[x - 1][y] == 0) {
					if (arr[x - 1][y] == 'D') {
						ans = d + 1;
						break;
					}
					isvisited[x - 1][y] = 1;
					hedgehog.offer(new int[] { x - 1, y, d + 1 });
				}
				if (y + 1 < c && isvisited[x][y + 1] == 0) {
					if (arr[x][y + 1] == 'D') {
						ans = d + 1;
						break;
					}
					isvisited[x][y + 1] = 1;
					hedgehog.offer(new int[] { x, y + 1, d + 1 });
				}
				if (y - 1 >= 0 && isvisited[x][y - 1] == 0) {
					if (arr[x][y - 1] == 'D') {
						ans = d + 1;
						break;
					}
					isvisited[x][y - 1] = 1;
					hedgehog.offer(new int[] { x, y - 1, d + 1 });
				}
			}
			if (ans != -1) {
				break;
			}
		}
		if(ans==-1)
			pw.print("KAKTUS");
		else
			pw.print(ans);
		pw.flush();
		br.close();
		pw.close();
	}

}
/*
 * '.' 비어잇는곳 
 * '*' 물이 차 있는곳 
 * 'X' 돌이 있는곳 
 * 'D' 비버의 굴 
 * 'S' 고슴도치의 위치
 */