import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class BOJ_14501_퇴사_DP {

	static int[][] arr;//[날짜][0=t, 1=p]
	static int[] day=new int[21];//n최대 15, t최대 5
	static int n;

	static int dp(int idx) {
		//값 넘어가면 리턴
		if(idx>n)
			return 0;
		//이미 값이 있으면 리턴
		if(day[idx]!=0 )
			return day[idx];
		int ret;
		//선택안하는경우
		ret=dp(idx+1);
		
		//선택하는 경우(-1하는거 빼먹어서 실수함 )
		if(idx+arr[idx][0]-1<=n)
			ret=Math.max(dp(idx+arr[idx][0])+arr[idx][1],ret);
		day[idx]=ret;
		return ret;
	}
	
	public static void main(String[] args)throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw= new PrintWriter(System.out);
		
		n=Integer.parseInt(br.readLine());
		arr=new int[n+1][2];
		String[] str;
		for(int i=1;i<=n;i++) {
			str=br.readLine().split(" ");
			arr[i][0]=Integer.parseInt(str[0]);
			arr[i][1]=Integer.parseInt(str[1]);			
		}
		int ans=0;
		

//		ans=dp(1);//재귀를 이용한 DP
	
		
		//반복문을 이용한 DP
		for(int i=n;i>0;i--) {
			int tmp=0;
			if(i+arr[i][0]-1<=n)
				tmp=day[i+arr[i][0]]+arr[i][1];
			day[i]=Math.max(day[i+1],tmp);//안고른거, 고른거 중 큰 값 저장
		}
		ans=day[1];
		pw.print(ans);
		br.close();
		pw.flush();
		pw.close();
	}
}