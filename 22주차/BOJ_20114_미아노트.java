import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_20114_미아노트 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n,h,w;
		String[] str=br.readLine().split(" ");
		
		n=Integer.parseInt(str[0]);
		h=Integer.parseInt(str[1]);
		w=Integer.parseInt(str[2]);
		
		String[] ans=new String[n];
		
		for(int i=0;i<h;i++) {
			str=br.readLine().split("");
			for(int j=0;j<n*w;j++) {
				if(!str[j].equals("?")) {
					ans[j/w]=str[j];
				}
			}
		}
		
		StringBuilder sb=new StringBuilder();
		
		for(int i=0;i<n;i++) {
			if(ans[i]==null)
				sb.append("?");
			else
				sb.append(ans[i]);
		}
		pw.print(sb.toString());
		br.close();
		pw.flush();
		pw.close();
	}
}