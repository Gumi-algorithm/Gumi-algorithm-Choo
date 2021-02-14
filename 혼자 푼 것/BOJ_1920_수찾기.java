import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_1920_수찾기 {
	static int[] arrn;

	static int binarySearch(int start, int end, int val) {
		if (start >= end)
			return 0;
		int mid = (start + end) / 2;

		if (arrn[mid] == val)
			return 1;
		if (val < arrn[mid])
			return binarySearch(start, mid, val);
		else if (val > arrn[mid])
			return binarySearch(mid + 1, end, val);
		
		return 0;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n, m;
		String[] str;

		n = Integer.parseInt(br.readLine());
		arrn = new int[n];
		str = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			arrn[i] = Integer.parseInt(str[i]);
		}
		Arrays.sort(arrn);

		m = Integer.parseInt(br.readLine());

		str = br.readLine().split(" ");
		for (int i = 0; i < m; i++) {
			int num = Integer.parseInt(str[i]);
			pw.print(binarySearch(0, n, num) + "\n");
		}
		br.close();
		pw.flush();
		pw.close();
	}
}