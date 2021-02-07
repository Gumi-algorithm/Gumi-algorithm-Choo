import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_2798_블랙잭 {
	static int n,m;
	static int[] arr1;
	static int[] arr2;	
	static int combination(int idx, int pre) {
		if(idx==3) {
			int val=arr2[0]+arr2[1]+arr2[2];
			if(val<=m)
				return val;
			return 0;
		}
		int ret=0;
		for(int i=pre;i<n;i++) {
			arr2[idx]=arr1[i];
			int tmp=combination(idx+1, i+1);
			ret=ret<tmp?tmp:ret;
		}
		return ret;
	}

	public static void main(String[] args)throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);

		String[] str=br.readLine().split(" ");
		n=Integer.parseInt(str[0]);
		m=Integer.parseInt(str[1]);
		arr1=new int[n];
		arr2=new int[3];
		str=br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			arr1[i]=Integer.parseInt(str[i]);
		}
		int result=combination(0,0);
		pw.print(result);
		
		br.close();
		pw.close();
	}
}