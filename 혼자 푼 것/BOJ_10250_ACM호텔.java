import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_10250_ACM호텔 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int t=Integer.parseInt(br.readLine());
		String[] str;
		for(int i=0;i<t;i++) {
			str=br.readLine().split(" ");
			int h=Integer.parseInt(str[0]);
			int w=Integer.parseInt(str[1]);
			int n=Integer.parseInt(str[2]);
			
			int a=n/h;//몇호실인지
			int b=n%h;//몇층인지
			if(b==0) {
				b=h;
				a--;
			}
			int ret=b*100+a+1;
			pw.print(ret+"\n");
		}
		pw.flush();
		pw.close();
		br.close();
	}
}
