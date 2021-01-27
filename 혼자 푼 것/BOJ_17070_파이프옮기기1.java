import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/*
 * n * n으로 주어지면 가장 왼쪽 세로줄 없다 생각해
 * n * n-1로 생각하고 풀자
 * 그대신 status변수에 현재 어떤상태인지 저장해서 움직일수 있는 방향을 판단하자
 * // 가로=1, 세로=2, 대각선=3
 */
public class BOJ_17070_파이프옮기기1 {

	static ArrayList<Integer[]> arr = new ArrayList<>();
	static int n;

	static int pipe(int x, int y, int status) {
		int result = 0;

		if (x == (n - 1) && y == (n - 1))
			return 1;

		// 대각선 우선(대각선은 가로 세로 대각선 상태 모두일때 갈수 있음)
		if (x + 1 < n && y + 1 < n && arr.get(x + 1)[y] == 0 && arr.get(x + 1)[y + 1] == 0 && arr.get(x)[y + 1] == 0) {
			result += pipe(x + 1, y + 1, 3);
		} 
		if (status == 1) {// 가로일경우
			if(y + 1 < n && arr.get(x)[y + 1] == 0)
				result += pipe(x, y + 1, 1);
		} else if (status == 2) {// 세로인경우
			if(x + 1 < n && arr.get(x + 1)[y] == 0)
				result += pipe(x + 1, y, 2);
		} else if (status == 3) {// 대각선인 경우
			if (y + 1 < n && arr.get(x)[y + 1] == 0)//가로로
				result += pipe(x, y + 1, 1);
			if (x + 1 < n && arr.get(x + 1)[y] == 0)//세로로
				result += pipe(x + 1, y, 2);
		}

		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			String[] str = br.readLine().split(" ");
			arr.add(new Integer[n]);
			for (int j = 0; j < n; j++) {
				arr.get(i)[j] = Integer.parseInt(str[j]);
			}
		}
		int result=pipe(0, 1, 1);
		bw.write(Integer.toString(result));

		bw.close();
		br.close();
	}
}