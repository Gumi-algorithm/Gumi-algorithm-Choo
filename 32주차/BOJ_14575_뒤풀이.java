import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_14575_뒤풀이 {

	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(System.out);
		
		String[] str=br.readLine().split(" ");
		int n=Integer.parseInt(str[0]);
		int t=Integer.parseInt(str[1]);
		
		int[][] arr=new int[n][2];
		
	
		int min=0;
		int max=0;
		int ans=0;
		int left=100000000;
		int right=0;
		for(int i=0;i<n;i++) {
			str=br.readLine().split(" ");
			arr[i][0]=Integer.parseInt(str[0]);
			arr[i][1]=Integer.parseInt(str[1]);	
			
			left=Math.max(left,arr[i][0]);
			right=Math.max(right,arr[i][1]);
			
			min+=arr[i][0];
			max+=arr[i][1];				
		}
		
		if(t<min || t>max) {
			ans=-1;
		}else {

			int mid=0;
		
			
			while(left<=right) {
				mid=(right+left)/2;
				int summin=0;
				int summax=0;
				
				for(int i=0;i<n;i++) {
					summin+=arr[i][0];
					summax+=Math.min(arr[i][1], mid);
				}
				
				if(summin<=t&&t<=summax) {
					ans=mid;
					right=mid-1;
				}else
					left=mid+1;
				
			}
		}

		pw.print(ans);
		br.close();
		pw.close();
	}

}
