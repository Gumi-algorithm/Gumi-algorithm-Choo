package N과M;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15649_N과M1 {
	static int n,m;
	static boolean[] isSelect;
	static int[] arr;
	static void permutation(int idx) {
		if(idx==m) {
			for(int a:arr)
				System.out.print(a+" ");
			System.out.println();
			return;
		}
		
		for(int i=1;i<=n;i++) {
			if(isSelect[i])
				continue;

			isSelect[i]=true;
			arr[idx]=i;
			permutation(idx+1);
			isSelect[i]=false;			
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] str= br.readLine().split(" ");
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		isSelect=new boolean[n+1];
		arr=new int[m];
		permutation(0);
		
		br.close();
	}
}