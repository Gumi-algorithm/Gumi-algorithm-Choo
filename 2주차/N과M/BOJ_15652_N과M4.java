package N과M;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_15652_N과M4 {
	static int n,m;
	static int[] arr;
	static PrintWriter pw=new PrintWriter(System.out);
	static void combination(int idx,int pre) {
		if(idx==m) {
			for(int a:arr)
				pw.print(a+" ");
			pw.println();
			return;
		}
		
		for(int i=pre;i<=n;i++) {
			arr[idx]=i;
			combination(idx+1,i);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str= br.readLine().split(" ");
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		arr=new int[m];
		combination(0,1);
		
		br.close();
		pw.close();
	}
}