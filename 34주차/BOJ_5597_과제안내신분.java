import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_5597_과제안내신분 {
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		
		boolean[] mem= new boolean[31];
		
		for(int i=0;i<28;i++) {
			int n=Integer.parseInt(br.readLine());
			mem[n] =true;
		}
		
		for(int i=1;i<=30;i++) {
			if(!mem[i])
				pw.println(i);
		}
		
		br.close();
		pw.close();
	}
}