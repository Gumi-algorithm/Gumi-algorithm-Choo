import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_2193_이친수 {

	static long[][] mem;
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
	
		int n=Integer.parseInt(br.readLine());
		
		mem=new long[n+3][2];//1의자리가 0인경우, 1인경우
		
		mem[1][1]=1;
		mem[2][0]=1;
		mem[3][0]=1;
		mem[3][1]=1;
		for(int i=4;i<n+1;i++) {
			mem[i][0]=mem[i-1][0]+mem[i-1][1];
			mem[i][1]=mem[i-1][0];
		}
		
		
		pw.print(mem[n][0]+mem[n][1]);
		br.close();
		pw.flush();
		pw.close();
	}

}
