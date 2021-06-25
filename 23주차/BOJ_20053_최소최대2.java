import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_20053_최소최대2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());

		while (t-- > 0) {
			int n = Integer.parseInt(br.readLine());

			int min = 1000000;
			int max = -1000000;
			String[] str = br.readLine().split(" ");
			for (int i = 0; i < n; i++) {
				int tmp = Integer.parseInt(str[i]);
				max = Math.max(max, tmp);
				min = Math.min(min, tmp);
			}
			sb.append(min).append(" ").append(max).append("\n");
		}
		pw.print(sb);
		br.close();
		pw.flush();
		pw.close();
	}
}
