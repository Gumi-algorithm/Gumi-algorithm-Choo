import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class BOJ_2470_두용액 {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);

		int n=Integer.parseInt(br.readLine());
		String[] str=br.readLine().split(" ");
		int[] arr=new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(str[i]);
		}
		Arrays.sort(arr);//오름차순 정렬
		
		//처음엔 양 끝을 고르며 시작
		int s=0;//시작
		int e=n-1;//끝
		int sum=arr[s]+arr[e];
		int ans=sum;
		int ans1=arr[s];
		int ans2=arr[e];		
		while(s<e) {
			if(ans==0)
				break;
			if(sum>0) {//sum이 0보다 크면 양수쪽(오른쪽)에서부터 더 작은 용액 골라
				sum=sum-arr[e--]+arr[e];
				
				if(s==e)//한가지용액을 두번 못고르니까 탈출
					break;
				
				if(Math.abs(ans)>Math.abs(sum)) {//합이 더 작아지면 정답 갱신
					ans=sum;
					ans1=arr[s];
					ans2=arr[e];
				}
			}else if(sum<0) {//sum이 0보다 작으면 음수쪽(왼쪽)에서부터 더 큰 용액 골라
				sum=sum-arr[s++]+arr[s];
				
				if(s==e)//한가지용액을 두번 못고르니까 탈출
					break;
				
				if(Math.abs(ans)>Math.abs(sum)) {//합이 더 작아지면 정답 갱신
					ans=sum;
					ans1=arr[s];
					ans2=arr[e];
				}
			}			
		}
		
		pw.print(ans1+" "+ans2);
		br.close();
		pw.flush();
		pw.close();
	}
}