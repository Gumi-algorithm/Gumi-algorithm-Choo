import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_20944_팰린드롬척화비 {

	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		
		int n=Integer.parseInt(br.readLine());
		
		StringBuilder sb=new StringBuilder();
		
		for(int i=0;i<n;i++) {
			sb.append("a");
		}
		
		pw.print(sb);
		br.close();
		pw.close();
	}
}