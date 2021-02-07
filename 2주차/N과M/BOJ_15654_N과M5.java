package N과M;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_15654_N과M5 {
	static int n,m;
	static int[] arr;
	static int[] arr2;
	static boolean[] isSelect;
	static PrintWriter pw=new PrintWriter(System.out);
	static void permutation(int idx) {
		if(idx==m) {
			for(int a:arr2)
				pw.print(a+" ");
			pw.println();
			return;
		}
		
		for(int i=0;i<n;i++) {
			if(isSelect[i])
				continue;
			isSelect[i]=true;
			arr2[idx]=arr[i];
			permutation(idx+1);
			isSelect[i]=false;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str= br.readLine().split(" ");
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		arr=new int[n];
		arr2=new int[m];
		isSelect=new boolean[n];
		str= br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(str[i]);
		}
		Arrays.sort(arr);
		permutation(0);
		
		br.close();
		pw.close();
	}
}