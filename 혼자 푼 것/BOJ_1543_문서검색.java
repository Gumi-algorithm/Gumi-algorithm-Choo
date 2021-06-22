import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_1543_문서검색 {

	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String a=br.readLine();
		String b=br.readLine();
		
		char[] str=a.toCharArray();
		char[] find=b.toCharArray();
		
		int ans=0;
		
		for(int i=0;i<str.length;i++) {
			if(i+find.length-1 >=str.length)
				break;
			for(int j=0;j<find.length;j++) {
				if(str[i+j]!=find[j]) {
					break;
				}
				if(j==find.length-1) {
					i+=j;
					ans++;
				}
			}
		}
		
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}

}
