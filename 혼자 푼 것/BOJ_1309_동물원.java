import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_1309_동물원 {

	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		
		int n=Integer.parseInt(br.readLine());
		
		int[][] arr=new int[n][3];// 0: 사자 없는경우, 1: 왼쪽에 사자, 2: 오른쪽에 사자
		
		//초깃값 초기화(우리가 한줄일 경우)
		arr[0][0]=1;
		arr[0][1]=1;
		arr[0][2]=1;
		
		for(int i=1;i<n;i++) {
			//i번째 우리에 사자를 놓지않으면 이전 우리에서 왼쪽, 오른쪽, 업음 모두 더해야함
			arr[i][0]=(arr[i-1][0]+arr[i-1][1]+arr[i-1][2])%9901;
			
			//i번째 우리에서 왼쪽에 사자를 놓으면 이전우리에서 왼쪽에 사자를 못놓음
			arr[i][1]=(arr[i-1][0]+arr[i-1][2])%9901;
			
			//i번째 우리에서 오른쪽에 사자를 놓으면 이전우리에서 오른쪽에 사자를 못놓음
			arr[i][2]=(arr[i-1][0]+arr[i-1][1])%9901;
		}
		int ans=arr[n-1][0]+arr[n-1][1]+arr[n-1][2];
		ans=ans%9901;
		
		pw.print(ans);
		br.close();
		pw.close();
	}
}