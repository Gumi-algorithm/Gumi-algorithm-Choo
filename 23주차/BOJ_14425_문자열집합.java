import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

public class BOJ_14425_문자열집합 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n,m;
		String[] str=br.readLine().split(" ");
		
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		
		Set<String> S=new HashSet<>();
		for(int i=0;i<n;i++) {
			S.add(br.readLine());
		}
		
		String tmp;
		int ans=0;
		for(int i=0;i<m;i++) {
			tmp=br.readLine();
			if(S.contains(tmp)) {
				ans++;
			}
		}

		
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}