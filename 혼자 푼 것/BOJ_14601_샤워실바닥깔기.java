import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_14600_샤워실바닥깔기 {
	static int arr[][];

	static int giyeok(int n, int x1, int x2, int y1, int y2, int type, int num) {

		if (n == 2) {
			// 좌측 상단이 빈 ㄱ모양
			if (type == 0) {
				arr[x1 + 1][y1] = num;
				arr[x1][y1 + 1] = num;
				arr[x1 + 1][y1 + 1] = num;
			} else if (type == 1) {// 좌측 하단
				arr[x1][y1] = num;
				arr[x1][y1 + 1] = num;
				arr[x1 + 1][y1 + 1] = num;
			} else if (type == 2) {// 우측 상단
				arr[x1][y1] = num;
				arr[x1 + 1][y1] = num;
				arr[x1 + 1][y1 + 1] = num;
			} else if (type == 3) {// 우측 하단
				arr[x1][y1] = num;
				arr[x1 + 1][y1] = num;
				arr[x1][y1 + 1] = num;
			}
			return num + 1;
		}
		int ret = num;
		// 좌측 상단이 빈 ㄱ모양
		if (type == 0) {
			ret = giyeok(n / 2, x1 + n / 2, x2, y1, y1 + n / 2, 2, ret);
			ret = giyeok(n / 2, x1 + n / 2, x2, y1 + n / 2, y2, 0, ret);
			ret = giyeok(n / 2, x1, x1 + n / 2, y1 + n / 2, y2, 1, ret);
			ret = giyeok(n / 2, x1 + n / 4, x1 + 3 * n / 4, y1 + n / 4, y1 + 3 * n / 4, 0, ret);
		} else if (type == 1) {// 좌측 하단
			ret = giyeok(n / 2, x1, x1 + n / 2, y1, y1 + n / 2, 3, ret);
			ret = giyeok(n / 2, x1, x1 + n / 2, y1 + n / 2, y2, 1, ret);
			ret = giyeok(n / 2, x1 + n / 2, x2, y1 + n / 2, y2, 0, ret);
			ret = giyeok(n / 2, x1 + n / 4, x1 + 3 * n / 4, y1 + n / 4, y1 + 3 * n / 4, 1, ret);
		} else if (type == 2) {// 우측 상단
			ret = giyeok(n / 2, x1, x1 + n / 2, y1, y1 + n / 2, 3, ret);
			ret = giyeok(n / 2, x1 + n / 2, x2, y1, y1 + n / 2, 2, ret);
			ret = giyeok(n / 2, x1 + n / 2, x2, y1 + n / 2, y2, 0, ret);
			ret = giyeok(n / 2, x1 + n / 4, x1 + 3 * n / 4, y1 + n / 4, y1 + 3 * n / 4, 2, ret);
		} else if (type == 3) {// 우측 하단
			ret = giyeok(n / 2, x1, x1 + n / 2, y1 + n / 2, y2, 1, ret);// 오른쪽 위
			ret = giyeok(n / 2, x1, x1 + n / 2, y1, y1 + n / 2, 3, ret);// 왼쪽 위
			ret = giyeok(n / 2, x1 + n / 2, x2, y1, y1 + n / 2, 2, ret);// 왼쪽 아래
			ret = giyeok(n / 2, x1 + n / 4, x1 + 3 * n / 4, y1 + n / 4, y1 + 3 * n / 4, 3, ret);// 중앙
		}
		return ret + 1;
	}

	static int div(int n, int sx, int sy, int x, int y) {
		// sx,sy 시작지점

		if (n == 1) {
			arr[sx + x][sy + y] = -1;
			return 1;
		}

		int tx = 0, ty = 0;
		int nx = x, ny = y;

		if (x >= n / 2) {
			tx = 1;
			nx = nx - n / 2;
		}
		if (y >= n / 2) {
			ty = 1;
			ny = ny - n / 2;
		}

		int ret = 0;

		// 좌측 상단에 배수구가 있는경우
		if (tx == 0 && ty == 0) {
			ret = div(n / 2, sx, sy, nx, ny);// 배수구쪽
			ret = giyeok(n, sx, sx + n, sy, sy + n, 0, ret);// 배수구 제외한 나머지 기역모양
		} else if (tx == 1 && ty == 0) {// 좌측 하단
			ret = div(n / 2, sx + n / 2, sy, nx, ny);
			ret = giyeok(n, sx, sx + n, sy, sy + n, 1, ret);// 배수구 제외한 나머지 기역모양
		} else if (tx == 0 && ty == 1) {// 우측 상단
			ret = div(n / 2, sx, sy + n / 2, nx, ny);
			ret = giyeok(n, sx, sx + n, sy, sy + n, 2, ret);// 배수구 제외한 나머지 기역모양
		} else if (tx == 1 && ty == 1) {// 우측 하단
			ret = div(n / 2, sx + n / 2, sy + n / 2, nx, ny);
			ret = giyeok(n, sx, sx + n, sy, sy + n, 3, ret);// 배수구 제외한 나머지 기역모양
		}

		return ret;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int k = Integer.parseInt(br.readLine());
		int x, y;
		String[] str = br.readLine().split(" ");

		x = Integer.parseInt(str[0]);
		y = Integer.parseInt(str[1]);

		int n = 1 << k;// 2^k를 구함

		// 좌측 상단이 0,0이고 좌표평면식 xy에서 배열식 xy로 바꿈
		int tmp=x;
		x = n - y;
		y = tmp - 1;

		arr = new int[n][n];

		div(n, 0, 0, x, y);

		
		StringBuilder sb=new StringBuilder();
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		pw.print(sb);
		br.close();
		pw.flush();
		pw.close();
	}
}