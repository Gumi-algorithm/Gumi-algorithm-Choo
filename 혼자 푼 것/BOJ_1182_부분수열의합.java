import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_1182_부분수열의합 {

	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		String[] str=br.readLine().split(" ");
		int n=Integer.parseInt(str[0]);
		int s=Integer.parseInt(str[1]);
		int[] arr=new int[n];
		str=br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(str[i]);
		}
		
		
		//binaryCounting
		int ans=0;
		int length=1<<n;
		for(int i=1;i<length;i++) {
			
			int sum=0;
			for(int j=0;j<n;j++) {				
				if((i&(1<<j))!=0) {//j번째가 선택된경우
					sum+=arr[j];
				}				
			}
			if(sum==s)
				ans++;
		}
		
		
		
		pw.print(ans);
		pw.flush();
		pw.close();
		br.close();
	}

}
