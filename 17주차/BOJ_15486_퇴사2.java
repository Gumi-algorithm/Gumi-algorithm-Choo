import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_15486_퇴사2 {
	static int[][] arr;
	static int[] mem;
	static int n;
	static int dp(int idx) {
		int ret=0;	
		
		if(idx>=n)
			return 0;
		
		if(mem[idx]!=0)
			return mem[idx];
		
		//선택 안하는경우
		ret=dp(idx+1);
		
		//선택하는경우
		if(idx+arr[idx][0]-1 <n)
			ret=Math.max(ret,arr[idx][1]+dp(idx+arr[idx][0]));
		
		mem[idx]=ret;
		
		return ret;
	}
	
	public static void main(String[] args)throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw= new PrintWriter(System.out);

		n=Integer.parseInt(br.readLine());
		arr=new int[n][2];
		mem=new int[n+51];
		for(int i=0;i<n;i++) {
			String[] str=br.readLine().split(" ");
			arr[i][0]=Integer.parseInt(str[0]);
			arr[i][1]=Integer.parseInt(str[1]);			
		}
		
		int ans=0;
//		ans=dp(0);//재귀쓰면 시간초과 뜸
		
		for(int i=n-1;i>=0;i--) {
			int tmp=0;
			if(i+arr[i][0]-1<n)
				tmp=mem[i+arr[i][0]]+arr[i][1];
			mem[i]=Math.max(mem[i+1],tmp);//안고른거, 고른거 중 큰 값 저장
		}
		ans=mem[0];
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();	
	}
}
