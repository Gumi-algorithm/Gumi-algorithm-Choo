import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_2744_대소문자바꾸기 {
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw =new PrintWriter(System.out);
		
		char[] charArr=br.readLine().toCharArray();
		
		StringBuilder sb=new StringBuilder();
		
		for(char ch: charArr) {
			if(ch>90) {
				sb.append((char)(ch-32));
			}else
				sb.append((char)(ch+32));
		}
		
		pw.print(sb);
		pw.close();
		br.close();
	}
}
