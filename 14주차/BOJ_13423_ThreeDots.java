import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_13423_ThreeDots {
	static int[] arr;
	static boolean binarysearch(int a,int b,int val) {
		int mid=0;
		
		while(a<=b) {
			mid=(a+b)/2;
			int now=arr[mid];
			if(now==val) {
				return true;
			}else if(now<val) {
				a=mid+1;
			}else
				b=mid-1;			
		}
		
		return false;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		StringBuilder sb=new StringBuilder();
		
		int t=Integer.parseInt(br.readLine());
		
		while(t-->0) {
			int n=Integer.parseInt(br.readLine());
			arr=new int[n];
			String[] str=br.readLine().split(" ");
			for(int i=0;i<n;i++) {
				arr[i]=Integer.parseInt(str[i]);
			}
			
			Arrays.sort(arr);
			int ans=0;
			for(int i=0;i<n;i++) {
				for(int j=i+1;j<n;j++) {
					int a=arr[i];
					int b=arr[j];
					
					//a,b의 합이 홀수이면 2로 나눈값이 실수가 되니까 넘겨
					if(Math.abs(a+b)%2==1)
						continue;
					
					if(binarysearch(i, j, (a+b)/2)) {
						ans++;
					}
				}
			}
			sb.append(ans).append("\n");
		}
		
		pw.print(sb);
		pw.flush();
		pw.close();
		br.close();
	}

}
