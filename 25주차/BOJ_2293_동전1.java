import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_2293_동전1 {

	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int n,k;
		String[] str=br.readLine().split(" ");
		
		n=Integer.parseInt(str[0]);
		k=Integer.parseInt(str[1]);

		int[] w = new int[n];
		int[] arr=new int[k+1];
		
		for(int i=0;i<n;i++) {
			w[i]=Integer.parseInt(br.readLine());
		}
		Arrays.sort(w);
		
		//arr[i][j]= arr[i-1][j]+arr[i][j-w[i]];
		for(int i=0;i<n;i++) {
			for(int j=0;j<=k;j++) {
				int tmp=0;
				if(j-w[i]>0)
					tmp=arr[j-w[i]];
				else if(j-w[i]==0)
					tmp=1;
				arr[j]=arr[j]+tmp;
			}
		}
		pw.print(arr[k]);
		pw.flush();
		pw.close();
		br.close();
	}

}
