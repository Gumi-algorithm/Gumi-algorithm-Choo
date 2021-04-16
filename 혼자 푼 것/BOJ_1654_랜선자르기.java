import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_1654_랜선자르기 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		int k,n;
		String[] str=br.readLine().split(" ");
		
		k=Integer.parseInt(str[0]);
		n=Integer.parseInt(str[1]);
		int[] arr=new int[n];
		long sum=0;
		for(int i=0;i<k;i++) {
			int tmp=Integer.parseInt(br.readLine());
			arr[i]=tmp;
			sum+=tmp/n+1;//실수때문에 오차생길까바 +1해버림
		}
		
		long s=0;
		long e=sum;
		long ans=0;
		while(s<e) {
			long mid=(s+e)/2;
			int cnt=0;
			for(int i=0;i<k;i++) {
				cnt+=arr[i]/mid;
			}
			if(cnt>=n) {
				ans=mid;
				s=mid+1;
			}else {
				e=mid;
			}
		}
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}