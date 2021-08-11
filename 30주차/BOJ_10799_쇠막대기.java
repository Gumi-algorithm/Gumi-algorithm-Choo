import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_10799_쇠막대기 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		int ans=0;
		int cnt=0;
		char pre=')';
		char[] arr=br.readLine().toCharArray();
		
		for(char tmp: arr) {
			if(tmp=='(') {
				pre='(';
				cnt++;
			}else if(pre=='('){
				cnt--;
				pre=')';
				ans+=cnt;
			}else {
				cnt--;
				ans++;
			}
		}
		pw.print(ans);
		pw.close();
		br.close();
	}
}