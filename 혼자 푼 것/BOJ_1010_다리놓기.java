import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_1010_다리놓기 {

	//조합이랑 똑같음
	static int mem[][]=new int[31][31];
	static int combi(int n,int r) {
		if(r==1 || n-r==1) {			
			return n;
		}
		if(n==r) {			
			return 1;
		}
		if(mem[n][r]!=0)
			return mem[n][r];
		
		return mem[n][r]=combi(n-1,r)+combi(n-1,r-1);		
	}
	
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int t=Integer.parseInt(br.readLine());
		
		while(t-- >0) {
			String[] str=br.readLine().split(" ");
			int n,m;
			n=Integer.parseInt(str[0]);
			m=Integer.parseInt(str[1]);
			
			int a=Math.max(n, m);
			int b=Math.min(n, m);
			pw.print(combi(a,b)+"\n");
		}
		
		
		br.close();
		pw.flush();
		pw.close();
	}

}
