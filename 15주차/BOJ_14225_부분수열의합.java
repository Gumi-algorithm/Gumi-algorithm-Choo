import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_14225_부분수열의합 {
	
	static int n;
	static int[] arr;
	static boolean[] isselected=new boolean[2100000];
	
	static void dfs(int num,int sum) {
		if(num==n)
			return;
		
		isselected[sum]=true;
		dfs(num+1,sum);
		isselected[sum+arr[num]]=true;
		dfs(num+1,sum+arr[num]);
		
		
		return;	
	}
	
	public static void main(String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		n=Integer.parseInt(br.readLine());
		
		arr=new int[n];
		String[] str=br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(str[i]);
		}
		
		Arrays.sort(arr);
		
		dfs(0,0);
		
		for(int i=1;;i++) {
			if(!isselected[i]) {
				pw.print(i);
				break;
			}				
		}		
		
		pw.flush();
		pw.close();
		br.close();
	}
}
