import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_9252_LCS2 {
	static char[] s1;
	static char[] s2;
	static int mem[][];
	static int len;// lcs길이
	static StringBuilder ans = new StringBuilder();

	static void dp() {
		for (int i = 1; i <= s1.length; i++) {
			for (int j = 1; j <= s2.length; j++) {
				if (s1[i - 1] == s2[j - 1]) {
					mem[i][j] = mem[i - 1][j - 1] + 1;
				} else {
					mem[i][j] = Math.max(mem[i - 1][j], mem[i][j - 1]);
				}
			}
		}

		len = mem[s1.length][s2.length];

		char stk[]=new char[len];
		int idx=0;
		int i = s1.length;
		int j = s2.length;
		while (i != 0 && j != 0) {
			if (mem[i][j] == mem[i - 1][j]) {
				i--;
			} else if (mem[i][j] == mem[i][j - 1]) {
				j--;
			} else {
				stk[idx++] += s1[i - 1];
				i--;
				j--;
			}
		}
		for(i=len-1;i>=0;i--) {
			ans.append(stk[i]);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String str1 = br.readLine();
		String str2 = br.readLine();
		mem = new int[str1.length()+1][str2.length()+1];
		s1 = str1.toCharArray();
		s2 = str2.toCharArray();

		dp();
		
		pw.println(len);
		pw.print(ans.toString());
		br.close();
		pw.flush();
		pw.close();
	}
}