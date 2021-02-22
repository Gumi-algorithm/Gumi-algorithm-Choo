import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_2448_별찍기11 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pw = new PrintWriter(System.out);
	static StringBuilder sb = new StringBuilder();
	static boolean[][] arr;// false는 공백 , true는 *

	static void bigTree(int x1, int x2, int y1, int y2) {

		if (x2 - x1 == 3) {
			int mid = (y1 + y2) / 2;
			arr[x1][mid] = true;

			arr[x1 + 1][mid - 1] = true;
			arr[x1 + 1][mid + 1] = true;

			arr[x1 + 2][mid - 2] = true;
			arr[x1 + 2][mid - 1] = true;
			arr[x1 + 2][mid] = true;
			arr[x1 + 2][mid + 1] = true;
			arr[x1 + 2][mid + 2] = true;
			return;
		}

		int midx = (x1 + x2) / 2;

		bigTree(x1, midx, (y1 + y2) / 2 -(y2-y1)/4 , (y1 + y2) / 2 + (y2 - y1) / 4 + 1);
		bigTree(midx, x2, y1, (y1 + y2) / 2);
		bigTree(midx, x2, (y1 + y2) / 2 + 1, y2);
	}

	public static void main(String[] args) throws IOException {

		int n = Integer.parseInt(br.readLine());

		arr = new boolean[n][n * 2 - 1];

		bigTree(0, n, 0, n * 2 - 1);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n * 2 - 1; j++) {
				if (arr[i][j])
					sb.append("*");
				else
					sb.append(" ");
			}
			sb.append("\n");
		}
		pw.print(sb);
		pw.flush();
		br.close();
		pw.close();
	}

}
