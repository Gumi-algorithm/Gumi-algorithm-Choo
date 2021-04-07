import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_1806_부분합 {

	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		String[] str=br.readLine().split(" ");
		int n,s;
		n=Integer.parseInt(str[0]);
		s=Integer.parseInt(str[1]);
		
		int [] arr=new int[n];
		str=br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(str[i]);
		}
		
		int start=0;
		int end=0;
		int sum=arr[start];
		int min=100001;
		int cnt=1;
		while(true) {

			if(sum>= s) {				
				min=Math.min(min,cnt);
				
				if(start==end) {//만약 1개만 골랐을 경우
					if(end==n-1) {
						break;
					}
					end++;
					sum+=arr[end];
					cnt++;
					continue;
				}
				
				sum-=arr[start];
				start++;	
				cnt--;
			}else {
				if(end==n-1) {
					break;
				}
				end++;
				sum+=arr[end];
				cnt++;
			}			
		}
		if(min==100001)
			min=0;
		pw.print(min);
		br.close();
		pw.flush();
		pw.close();
	}
}