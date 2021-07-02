import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_1212_8진수2진수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String[] str = br.readLine().split("");

		StringBuilder sb = new StringBuilder();

		int isStart = 0;
		for (int i = 0; i < str.length; i++) {
			int num = Integer.parseInt(str[i]);
			
			if (num >= 4) {
				num = num - 4;
				sb.append("1");
				isStart = 1;
			}else if(isStart==1) 
				sb.append("0");

			if (num >= 2) {
				num -= 2;
				sb.append("1");
				isStart = 1;
			}else if(isStart==1) 
				sb.append("0");

			if (num == 1) {
				sb.append("1");
				isStart=1;
			}else
				sb.append("0");
		}

		pw.print(sb);
		br.close();
		pw.flush();
		pw.close();
	}
}