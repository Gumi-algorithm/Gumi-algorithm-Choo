import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_3980_선발명단 {
	static int arr[][];
	static int[] isSelected;
	static int ans;
	static void dfs(int n,int sum) {
		
		if(n==11) {
			for(int i=0;i<11;i++) {
				if(isSelected[i]==0)
					return;
			}
			ans=Math.max(ans, sum);
			return;
		}
		for(int i=0;i<11;i++) {
			if(arr[n][i]==0)
				continue;
			if(isSelected[i]==1)
				continue;
			
			isSelected[i]=1;
			dfs(n+1,sum+arr[n][i]);
			isSelected[i]=0;			
		}	
		return;
	}

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int t=Integer.parseInt(br.readLine());
		
		while(t-->0) {
			ans=0;
			arr=new int[11][11];
			isSelected=new int[11];
			
			for(int i=0;i<11;i++) {
				String[] str=br.readLine().split(" ");
				for(int j=0;j<11;j++) {
					arr[i][j]=Integer.parseInt(str[j]);
				}
			}
			
			
			dfs(0,0);
			
			pw.println(ans);
		}
		
		br.close();
		pw.flush();
		pw.close();
	}
}