import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_2110_공유기설치 {
	static int arr[];
	static int n, c;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String[] str = br.readLine().split(" ");
		n = Integer.parseInt(str[0]);
		c = Integer.parseInt(str[1]);

		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);

//		가장 작은 인풋 , 가장큰 인풋의 중간값을 최대 거리로, 0을 최소 거리로 잡고 이분탐색 진행 
		int ans = 1;

		int s = 1;// 최소거리
		int e = arr[n - 1] - arr[0]+1;// 최대거리
		while (s < e) {
			int mid = (s + e) / 2;
			int cnt = 0;
			int pre = arr[0];
			cnt++;

			//mid간격으로 몇개의 공유기를 설치할수 있는지 확인
			for (int i = 1; i < n; i++) {
				if (arr[i] - pre >= mid) {
					pre = arr[i];
					cnt++;
				}
			}

			if (cnt < c)//c개보다 적으면 간격을 줄여
				e = mid;
			else {//c개와 같거나 많이 설치할수 있으면 일단 정답 넣고 더 큰 간격으로 확인
				ans = Math.max(ans, mid);
				s = mid + 1;
			}
		}
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}
