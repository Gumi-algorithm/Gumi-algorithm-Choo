package N과M;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15650_N과M2 {
	static int n,m;
	static int[] arr;
	static void combination(int idx,int pre) {
		if(idx==m) {
			for(int a:arr)
				System.out.print(a+" ");
			System.out.println();
			return;
		}
		
		for(int i=pre;i<=n;i++) {
			arr[idx]=i;
			combination(idx+1,i+1);		
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
	}
}