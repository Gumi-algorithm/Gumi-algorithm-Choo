import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_2118_두개의탑 {
	static int[] arr;
	static int sum=0;
	static int binarysearch(int a,int b) {
		int mid=0;
		int start=a;
		int ret=0;
		while(a<=b) {
			mid=(a+b)/2;

			int tmp1=arr[mid]-arr[start];
			int tmp2=arr[start]-arr[mid]+sum;
			
			if(tmp1<tmp2) {
				a=mid+1;				
			}else {
				b= mid-1;
			}	
			ret=Math.max(ret, Math.min(tmp1, tmp2));
		}
		return ret;
	}
	public static void main(String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n=Integer.parseInt(br.readLine());
		arr=new int[n+1];
		arr[0]=0;
		for(int i=1;i<=n;i++) {
			int tmp=Integer.parseInt(br.readLine());
			arr[i]=arr[i-1]+tmp;
		}
		sum=arr[n];//전체합
		int ans=0;
		for(int i=1;i<=n;i++) {
			int ret=binarysearch(i, n);		
			ans=Math.max(ans, ret);
		}
				
		pw.print(ans);
		pw.flush();
		pw.close();
		br.close();
	}
}
